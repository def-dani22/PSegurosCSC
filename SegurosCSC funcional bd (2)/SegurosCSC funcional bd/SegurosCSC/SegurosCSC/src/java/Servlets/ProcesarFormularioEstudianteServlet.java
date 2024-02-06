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

@WebServlet("/ProcesarFormularioEstudiante")
public class ProcesarFormularioEstudianteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ProcesarFormularioEstudianteServlet() {
        super();
    }

    // Manejar tanto las solicitudes GET como las POST
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ProcesarFormularioEstudianteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ProcesarFormularioEstudianteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método principal de procesamiento
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        
        int cedula = Integer.parseInt(request.getParameter("cedula"));
        String nomCentroE = request.getParameter("nomCentroE");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        int idPoliza = Integer.parseInt(request.getParameter("idPoliza"));

        
        // Obtener información del seguro de viaje desde la base de datos
        TipoPolizaEstudiante seguroEstudiante = obtenerInformacionseguroEstudiante(idPoliza);

        // Insertar el cliente en la base de datos
        int dato = insertarEstudiante(cedula,nomCentroE, nombre, apellido, email, telefono);

        // Asociar el seguro de viaje al cliente en la base de datos
        asociarseguroEstudianteAEstudiante(cedula, idPoliza);

        // Obtener una lista de todas las pólizas de seguro de viaje
        List<TipoPolizaEstudiante> todosLosSeguros = obtenerListaSeguros();
        request.setAttribute("seguros", todosLosSeguros);

        // Mostrar la página de confirmación con la información del cliente y del seguro
        request.setAttribute("estudiante", new Estudiante(cedula, nomCentroE, nombre, apellido, email, telefono));
        request.setAttribute("seguroEstudiante", seguroEstudiante);

        // Mostrar información detallada sobre los seguros para permitir la elección
        List<TipoPolizaEstudiante> seguros = obtenerListaSeguros();
        request.setAttribute("seguros", seguros);

        // Verificar si el seguro seleccionado es "Plus" para mostrar el campo de beneficiario
        boolean mostrarBeneficiario = seguroEstudiante != null && "Plus".equalsIgnoreCase(seguroEstudiante.getTipoPoliza());
        request.setAttribute("mostrarBeneficiario", mostrarBeneficiario);

        // Mostrar la página de confirmación
        RequestDispatcher dispatcher = request.getRequestDispatcher("confirmacionEstudiante.jsp");
        dispatcher.forward(request, response);
    }

    // Obtener información de un seguro de viaje desde la base de datos
    private TipoPolizaEstudiante obtenerInformacionseguroEstudiante(int idPoliza) {
        TipoPolizaEstudiante seguroEstudiante = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-KNSONQV:1433;databaseName=SegurosCSC;user=sa;password=1234;loginTimeout=30;";
            try (Connection connection = DriverManager.getConnection(url)) {
                String sql = "SELECT * FROM tipoPolizaEstudiante WHERE idPoliza = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, idPoliza);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            seguroEstudiante = new TipoPolizaEstudiante(
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
        return seguroEstudiante;
    }

    // Insertar un cliente en la base de datos
    private int insertarEstudiante(int cedula, String nomCentroE, String nombre, String apellido, String email, String telefono) {
        int dato = -1;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-KNSONQV:1433;databaseName=SegurosCSC;user=sa;password=1234;loginTimeout=30;";
            try (Connection connection = DriverManager.getConnection(url)) {
                String sql = "INSERT INTO Estudiantes (cedula,nomCentroE,  nombre, apellido, email, telefono) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    statement.setInt(1, cedula);
                    statement.setString(2, nomCentroE);
                    statement.setString(3, nombre);
                    statement.setString(4, apellido);
                    statement.setString(5, email);
                    statement.setString(6, telefono);


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
    private void asociarseguroEstudianteAEstudiante(int cedula, int idPoliza) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-KNSONQV:1433;databaseName=SegurosCSC;user=sa;password=1234;loginTimeout=30;";
            try (Connection connection = DriverManager.getConnection(url)) {
                String sql = "INSERT INTO polizaXestudiantes (cedula, idPoliza) VALUES (?, ?)";
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
    private List<TipoPolizaEstudiante> obtenerListaSeguros() {
        List<TipoPolizaEstudiante> seguros = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-KNSONQV:1433;databaseName=SegurosCSC;user=sa;password=1234;loginTimeout=30;";
            try (Connection connection = DriverManager.getConnection(url)) {
                String sql = "SELECT * FROM TipoPolizaEstudiantes";
                try (PreparedStatement statement = connection.prepareStatement(sql);
                     ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        TipoPolizaEstudiante seguro = new TipoPolizaEstudiante(
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
    public class TipoPolizaEstudiante {
        private int idPoliza;
        private String tipoPoliza;
        private String descripcion;
        private double cobertura;
        private double precio;
        private String beneficiario;

        public TipoPolizaEstudiante(int idPoliza, String tipoPoliza, String descripcion, double cobertura, double precio, String beneficiario) {
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

    
    public class Estudiante {
        private int cedula;
        private String nomCentroE;
        private String nombre;
        private String apellido;
        private String email;
        private String telefono;

        public Estudiante(int cedula, String nomCentroE, String nombre, String apellido, String email, String telefono) {
            this.cedula = cedula;
            this.nomCentroE = nomCentroE;
            this.nombre = nombre;
            this.apellido = apellido;
            this.email = email;
            this.telefono = telefono;
        }

        
        public int getcedula() {
            return cedula;
        }

        public void setcedula(int cedula) {
            this.cedula = cedula;
        }

        
        public String getnomCentroE() {
            return nomCentroE;
        }

        public void setnomCentroE(String nomCentroE) {
            this.nomCentroE = nomCentroE;
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
    }
}
