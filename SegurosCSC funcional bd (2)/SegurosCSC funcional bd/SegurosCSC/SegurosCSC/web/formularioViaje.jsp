<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Formulario Seguro de Viaje</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f9fb; /* Azul claro */
            color: #333;
        }

        header {
            background-color: #004080; /* Azul oscuro */
            color: #fff;
            padding: 1em;
            text-align: center;
        }

        main {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        form {
            margin-top: 20px;
        }

        label {
            display: block;
            margin-bottom: 10px;
            color: #004080; /* Azul oscuro */
            font-weight: bold;
        }

        input[type="text"],
        input[type="email"],
        input[type="date"],
        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #004080; /* Azul oscuro */
            color: #fff;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        
        footer {
            background-color: #004080; /* Azul oscuro */
            color: #fff;
            padding: 1em;
            text-align: center;
            position: fixed;
            bottom: 0;
            width: 100%;
        }

        footer a {
            color: #fff;
            text-decoration: none;
            margin: 0 10px;
        }
    </style>
</head>
<body>
    <header>
        <h1>Formulario Seguro de Viaje</h1>
    </header>

    <main>
        <form action="ProcesarFormularioViaje" method="post">
            <!-- Campos del cliente -->
            <label for="cedula">NCedula:</label>
            <input type="text" name="cedula" required>
            
            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" required>

            <label for="apellido">Apellido:</label>
            <input type="text" name="apellido" required>

            <label for="email">Email:</label>
            <input type="email" name="email" required>

            <label for="telefono">Teléfono:</label>
            <input type="text" name="telefono">

            <label for="fechaInicio">Fecha de Inicio:</label>
            <input type="date" name="fechaInicio" required>

            <label for="fechaFinal">Fecha Final:</label>
            <input type="date" name="fechaFinal" required>
            
            
            <h2>Seguros de Viaje disponibles</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tipo de Póliza</th>
                    <th>Descripción</th>
                    <th>Cobertura</th>
                    <th>Precio</th>
                    
                </tr>
            </thead>
            <tbody>
                <c:forEach var="seguro" items="${seguros}">
                    <tr>
                        <td>${seguro.idPoliza}</td>
                        <td>${seguro.tipoPoliza}</td>
                        <td>${seguro.descripcion}</td>
                        <td>${seguro.cobertura}</td>
                        <td>${seguro.precio}</td>
                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>

            
            

           
            <label for="idPoliza">Seleccione un Seguro de Viaje:</label>
            <select name="idPoliza">
                <option value="1">Seguro de plus</option>
                <option value="2">Seguro standar</option>
                <option value="3">Seguro básico</option>
            </select>

            <input type="submit" value="Enviar">
        </form>
    </main>
    
        <footer>
        © 2023 Seguros Company. Todos los derechos reservados. <br>
        Síguenos en: <a href="#">Facebook</a> | <a href="#">Twitter</a> | <a href="#">Instagram</a>
    </footer>
</body>
</html>
