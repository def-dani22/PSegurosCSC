/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MostrarSeguros extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Obtener la cédula del formulario
            String cedula = request.getParameter("cedula");

            // Realizar la conexión a la base de datos
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://DESKTOP-KNSONQV:1433;databaseName=SegurosCSC;user=sa;password=1234;loginTimeout=30;";
                Connection connection = DriverManager.getConnection(url);

                // Consulta SQL para obtener los seguros asociados a un cliente por cédula
                String sql = "SELECT p.tipoPoliza, p.descripcion, p.cobertura, p.precio, p.beneficiario " +
                             "FROM polizaXclientes pc " +
                             "JOIN tipoPolizaViajes p ON pc.idPoliza = p.idPoliza " +
                             "WHERE pc.cedula = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, cedula);
                ResultSet resultSet = preparedStatement.executeQuery();

                // Mostrar los resultados en HTML
                out.println("<html><head><title>Resultados de Seguros</title></head><body>");
                out.println("<h2>Seguros asociados al cliente con cédula " + cedula + ":</h2>");
                out.println("<table border='1'><tr><th>Tipo de Póliza</th><th>Descripción</th><th>Cobertura</th><th>Precio</th><th>Beneficiario</th></tr>");

                while (resultSet.next()) {
                    out.println("<tr>");
                    out.println("<td>" + resultSet.getString("tipoPoliza") + "</td>");
                    out.println("<td>" + resultSet.getString("descripcion") + "</td>");
                    out.println("<td>" + resultSet.getDouble("cobertura") + "</td>");
                    out.println("<td>" + resultSet.getDouble("precio") + "</td>");
                    out.println("<td>" + resultSet.getString("beneficiario") + "</td>");
                    out.println("</tr>");
                }

                out.println("</table></body></html>");

                // Cerrar recursos
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
