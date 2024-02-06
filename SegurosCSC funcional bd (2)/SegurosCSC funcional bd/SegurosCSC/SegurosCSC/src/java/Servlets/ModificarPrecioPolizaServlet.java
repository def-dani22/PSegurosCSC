package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ModificarPrecioPoliza")
public class ModificarPrecioPolizaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String codigoAcceso = request.getParameter("codigoAcceso");
        if ("1234".equals(codigoAcceso)) {
            // Obtener los parámetros de la solicitud
            int idPoliza = Integer.parseInt(request.getParameter("idPoliza"));
            double nuevoPrecio = Double.parseDouble(request.getParameter("nuevoPrecio"));

            
            modificarPrecioPoliza(idPoliza, nuevoPrecio);

            
            List<TipoPolizaViajes> todasLasPolizas = obtenerListaSeguros();
            request.setAttribute("polizas", todasLasPolizas);

            
            RequestDispatcher dispatcher = request.getRequestDispatcher("exito.jsp");
            dispatcher.forward(request, response);
        } else {
           
            response.sendRedirect("error.jsp");
        }
    }

    private void modificarPrecioPoliza(int idPoliza, double nuevoPrecio) {
        try {
           
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-KNSONQV:1433;databaseName=SegurosCSC;user=sa;password=1234;loginTimeout=30;";

            try (Connection connection = DriverManager.getConnection(url)) {
                
                String sql = "UPDATE tipoPolizaViajes SET precio = ? WHERE idPoliza = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setDouble(1, nuevoPrecio);
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
}
