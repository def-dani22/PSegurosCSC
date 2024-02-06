package Servlets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProcesarFormularioViaje")
public class ProcesarFormularioViajeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ProcesarFormularioViajeServlet() {
        super();
    }

    // Manejar tanto las solicitudes GET como las POST
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ProcesarFormularioViajeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ProcesarFormularioViajeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método principal de procesamiento
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        
        int cedula = Integer.parseInt(request.getParameter("cedula"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFinal = request.getParameter("fechaFinal");
        int idPoliza = Integer.parseInt(request.getParameter("idPoliza"));

        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateInicio = sdf.parse(fechaInicio);
        Date dateFinal = sdf.parse(fechaFinal);

       
        long diffInMillies = Math.abs(dateFinal.getTime() - dateInicio.getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        request.setAttribute("diasDeViaje", diffInDays);

        // Obtener información del seguro de viaje desde la base de datos
        TipoPolizaViajes seguroViaje = obtenerInformacionSeguroViaje(idPoliza);

        // Insertar el cliente en la base de datos
        int dato = insertarCliente(cedula, nombre, apellido, email, telefono, fechaInicio, fechaFinal);

        // Asociar el seguro de viaje al cliente en la base de datos
        asociarSeguroViajeACliente(cedula, idPoliza);

        // Obtener una lista de todas las pólizas de seguro de viaje
        List<TipoPolizaViajes> todosLosSeguros = obtenerListaSeguros();
        request.setAttribute("seguros", todosLosSeguros);

        // Mostrar la página de confirmación con la información del cliente y del seguro
        request.setAttribute("cliente", new Cliente(cedula, nombre, apellido, email, telefono, fechaInicio, fechaFinal));
        request.setAttribute("seguroViaje", seguroViaje);

        // Mostrar información detallada sobre los seguros para permitir la elección
        List<TipoPolizaViajes> seguros = obtenerListaSeguros();
        request.setAttribute("seguros", seguros);

        // Verificar si el seguro seleccionado es "Plus" para mostrar el campo de beneficiario
        boolean mostrarBeneficiario = seguroViaje != null && "Plus".equalsIgnoreCase(seguroViaje.getTipoPoliza());
        request.setAttribute("mostrarBeneficiario", mostrarBeneficiario);

        // Mostrar la página de confirmación
        RequestDispatcher dispatcher = request.getRequestDispatcher("confirmacionViaje.jsp");
        dispatcher.forward(request, response);
    }

    // Obtener información de un seguro de viaje desde la base de datos
    private TipoPolizaViajes obtenerInformacionSeguroViaje(int idPoliza) {
        TipoPolizaViajes seguroViaje = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-KNSONQV:1433;databaseName=SegurosCSC;user=sa;password=1234;loginTimeout=30;";
            try (Connection connection = DriverManager.getConnection(url)) {
                String sql = "SELECT * FROM tipoPolizaViajes WHERE idPoliza = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, idPoliza);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            seguroViaje = new TipoPolizaViajes(
                                    resultSet.getInt("idPoliza"),
                                    resultSet.getString("tipoPoliza"),
                                    resultSet.getString("descripcion"),
                                    resultSet.getDouble("cobertura"),
                                    resultSet.getDouble("precio"),
                                    resultSet.getString("beneficiario")
                            );
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return seguroViaje;
    }

    // Insertar un cliente en la base de datos
    private int insertarCliente(int cedula, String nombre, String apellido, String email, String telefono, String fechaInicio, String fechaFinal) {
        int dato = -1;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-KNSONQV:1433;databaseName=SegurosCSC;user=sa;password=1234;loginTimeout=30;";
            try (Connection connection = DriverManager.getConnection(url)) {
                String sql = "INSERT INTO Clientes (cedula, nombre, apellido, email, telefono, fechaInicio, fechaFinal) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    statement.setInt(1, cedula);
                    statement.setString(2, nombre);
                    statement.setString(3, apellido);
                    statement.setString(4, email);
                    statement.setString(5, telefono);
                    statement.setString(6, fechaInicio);
                    statement.setString(7, fechaFinal);

                    int affectedRows = statement.executeUpdate();
                    if (affectedRows > 0) {
                        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                cedula = generatedKeys.getInt(1);
                            }
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return cedula;
    }

    // Asociar un seguro de viaje a un cliente en la base de datos
    private void asociarSeguroViajeACliente(int cedula, int idPoliza) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-KNSONQV:1433;databaseName=SegurosCSC;user=sa;password=1234;loginTimeout=30;";
            try (Connection connection = DriverManager.getConnection(url)) {
                String sql = "INSERT INTO polizaXclientes (cedula, idPoliza) VALUES (?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, cedula);
                    statement.setInt(2, idPoliza);
                    statement.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtener una lista de todos los seguros de viaje
    private List<TipoPolizaViajes> obtenerListaSeguros() {
        List<TipoPolizaViajes> seguros = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-KNSONQV:1433;databaseName=SegurosCSC;user=sa;password=1234;loginTimeout=30;";
            try (Connection connection = DriverManager.getConnection(url)) {
                String sql = "SELECT * FROM tipoPolizaViajes";
                try (PreparedStatement statement = connection.prepareStatement(sql);
                     ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        TipoPolizaViajes seguro = new TipoPolizaViajes(
                                resultSet.getInt("idPoliza"),
                                resultSet.getString("tipoPoliza"),
                                resultSet.getString("descripcion"),
                                resultSet.getDouble("cobertura"),
                                resultSet.getDouble("precio"),
                                resultSet.getString("beneficiario")
                        );
                        seguros.add(seguro);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return seguros;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    // Clase interna que representa un tipo de póliza de viaje
    public class TipoPolizaViajes {
        private int idPoliza;
        private String tipoPoliza;
        private String descripcion;
        private double cobertura;
        private double precio;
        private String beneficiario;

        public TipoPolizaViajes(int idPoliza, String tipoPoliza, String descripcion, double cobertura, double precio, String beneficiario) {
            this.idPoliza = idPoliza;
            this.tipoPoliza = tipoPoliza;
            this.descripcion = descripcion;
            this.cobertura = cobertura;
            this.precio = precio;
            this.beneficiario = beneficiario;
        }

        // Getters y setters
        public int getIdPoliza() {
            return idPoliza;
        }

        public void setIdPoliza(int idPoliza) {
            this.idPoliza = idPoliza;
        }

        public String getTipoPoliza() {
            return tipoPoliza;
        }

        public void setTipoPoliza(String tipoPoliza) {
            this.tipoPoliza = tipoPoliza;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public double getCobertura() {
            return cobertura;
        }

        public void setCobertura(double cobertura) {
            this.cobertura = cobertura;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        public String getBeneficiario() {
            return beneficiario;
        }

        public void setBeneficiario(String beneficiario) {
            this.beneficiario = beneficiario;
        }
    }

    
    public class Cliente {
        private int cedula;
        private String nombre;
        private String apellido;
        private String email;
        private String telefono;
        private String fechaInicio;
        private String fechaFinal;

        public Cliente(int cedula, String nombre, String apellido, String email, String telefono, String fechaInicio, String fechaFinal) {
            this.cedula = cedula;
            this.nombre = nombre;
            this.apellido = apellido;
            this.email = email;
            this.telefono = telefono;
            this.fechaInicio = fechaInicio;
            this.fechaFinal = fechaFinal;
        }

        
        public int getcedula() {
            return cedula;
        }

        public void setcedula(int cedula) {
            this.cedula = cedula;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getFechaInicio() {
            return fechaInicio;
        }

        public void setFechaInicio(String fechaInicio) {
            this.fechaInicio = fechaInicio;
        }

        public String getFechaFinal() {
            return fechaFinal;
        }

        public void setFechaFinal(String fechaFinal) {
            this.fechaFinal = fechaFinal;
        }
    }
    
    
    
    
    
    
    
    
    
    
}
