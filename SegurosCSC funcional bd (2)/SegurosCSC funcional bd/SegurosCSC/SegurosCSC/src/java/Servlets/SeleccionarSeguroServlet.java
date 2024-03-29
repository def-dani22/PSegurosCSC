package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SeleccionarSeguro")
public class SeleccionarSeguroServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String tipoSeguro = request.getParameter("tipoSeguro");

            if ("viaje".equals(tipoSeguro)) {
                response.sendRedirect("formularioViaje.jsp");
            } else if ("estudiante".equals(tipoSeguro)) {
                response.sendRedirect("formularioEstudiantes.jsp");
            } else {
                
                response.sendRedirect("index.jsp");
            }
        } catch (IOException e) {
           
            e.printStackTrace();
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
