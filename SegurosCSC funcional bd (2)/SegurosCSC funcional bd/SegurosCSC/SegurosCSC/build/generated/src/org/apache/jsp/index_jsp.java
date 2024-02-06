package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>Seguros</title>\n");
      out.write("    <style>\n");
      out.write("        body {\n");
      out.write("            font-family: 'Arial', sans-serif;\n");
      out.write("            margin: 0;\n");
      out.write("            padding: 0;\n");
      out.write("            background-color: #e0f2f7; /* Azul claro */\n");
      out.write("            color: #333;\n");
      out.write("            position: relative;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        header {\n");
      out.write("            background-color: #004080; /* Azul oscuro */\n");
      out.write("            color: #fff;\n");
      out.write("            padding: 1em;\n");
      out.write("            text-align: center;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        main {\n");
      out.write("            max-width: 800px;\n");
      out.write("            margin: 20px auto;\n");
      out.write("            padding: 20px;\n");
      out.write("            background-color: #f4f9fb; /* Azul claro */\n");
      out.write("            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n");
      out.write("            position: relative; \n");
      out.write("        }\n");
      out.write("\n");
      out.write("        form {\n");
      out.write("            margin-top: 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        label {\n");
      out.write("            display: block;\n");
      out.write("            margin-bottom: 10px;\n");
      out.write("            color: #004080; /* Azul oscuro */\n");
      out.write("            font-weight: bold;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input[type=\"radio\"] {\n");
      out.write("            margin-right: 5px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input[type=\"submit\"] {\n");
      out.write("            background-color: #004080; /* Azul oscuro */\n");
      out.write("            color: #fff;\n");
      out.write("            padding: 10px 20px;\n");
      out.write("            border: none;\n");
      out.write("            cursor: pointer;\n");
      out.write("            font-size: 16px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        footer {\n");
      out.write("            background-color: #004080; /* Azul oscuro */\n");
      out.write("            color: #fff;\n");
      out.write("            padding: 1em;\n");
      out.write("            text-align: center;\n");
      out.write("            position: fixed;\n");
      out.write("            bottom: 0;\n");
      out.write("            width: 100%;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        footer a {\n");
      out.write("            color: #fff;\n");
      out.write("            text-decoration: none;\n");
      out.write("            margin: 0 10px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        /* Estilos para el botón de configuraciones */\n");
      out.write("        .config-button {\n");
      out.write("            position: absolute;\n");
      out.write("            top: 10px;\n");
      out.write("            right: 10px;\n");
      out.write("            background-color: #004080; /* Azul oscuro */\n");
      out.write("            color: #fff;\n");
      out.write("            padding: 10px;\n");
      out.write("            border: none;\n");
      out.write("            cursor: pointer;\n");
      out.write("            font-size: 16px;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <header>\n");
      out.write("        <h1>Bienvenido a nuestro servicio de seguros</h1>\n");
      out.write("    </header>\n");
      out.write("\n");
      out.write("    <main>\n");
      out.write("        <p>Por favor, elige el tipo de seguro:</p>\n");
      out.write("        \n");
      out.write("        <form action=\"SeleccionarSeguro\" method=\"post\">\n");
      out.write("            <label>\n");
      out.write("                <input type=\"radio\" name=\"tipoSeguro\" value=\"viaje\" id=\"viaje\" required>\n");
      out.write("                Seguro de Viaje\n");
      out.write("            </label>\n");
      out.write("\n");
      out.write("            <label>\n");
      out.write("                <input type=\"radio\" name=\"tipoSeguro\" value=\"estudiante\" id=\"estudiante\" required>\n");
      out.write("                Seguro Estudiantil\n");
      out.write("            </label>\n");
      out.write("\n");
      out.write("            <!-- Botón de configuraciones -->\n");
      out.write("            <a href=\"modificarPrecio.jsp\" class=\"config-button\">\n");
      out.write("                Configuraciones\n");
      out.write("            </a>\n");
      out.write("\n");
      out.write("            <input type=\"submit\" value=\"Seleccionar\">\n");
      out.write("        </form>\n");
      out.write("    </main>\n");
      out.write("\n");
      out.write("    <footer>\n");
      out.write("        © 2023 Seguros Company. Todos los derechos reservados. <br>\n");
      out.write("        Síguenos en: <a href=\"#\">Facebook</a> | <a href=\"#\">Twitter</a> | <a href=\"#\">Instagram</a>\n");
      out.write("    </footer>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
