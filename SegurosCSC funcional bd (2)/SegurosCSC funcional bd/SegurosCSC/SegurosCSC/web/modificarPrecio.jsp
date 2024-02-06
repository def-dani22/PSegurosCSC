<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modificar Precio de Póliza</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #e0f2f7; /* Azul claro */
            color: #333;
        }

        header {
            background-color: #004080; /* Azul oscuro */
            color: #fff;
            padding: 1em;
            text-align: center;
        }

        main {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #f4f9fb; /* Azul claro */
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

        input[type="radio"] {
            margin-right: 5px;
        }

        input[type="submit"],
        button {
            background-color: #004080; /* Azul oscuro */
            color: #fff;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }

        a button {
            
            text-decoration: none;
            display: inline-block;
            margin-top: 10px;
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
        <h1>Bienvenido a nuestro servicio de seguros</h1>
    </header>

    <main>
        <h2>Modificar Precio de Póliza</h2>
        
        <form action="ModificarPrecioPoliza" method="post">
            Código de Acceso: <input type="password" name="codigoAcceso" required><br>
            ID de Póliza:
            <select name="idPoliza" required>
                <c:forEach var="poliza" items="${polizas}">
                    <option value="${poliza.idPoliza}">${poliza.tipoPoliza}</option>
                </c:forEach>
            </select><br>
            Nuevo Precio: <input type="text" name="nuevoPrecio" required><br>
            <input type="submit" value="Modificar Precio">
        </form>

        <a href="index.jsp">
            <button>volver al inicio</button>
        </a>
    </main>

    <footer>
        © 2023 Seguros Company. Todos los derechos reservados. <br>
        Síguenos en: <a href="#">Facebook</a> | <a href="#">Twitter</a> | <a href="#">Instagram</a>
    </footer>
</body>
</html>
