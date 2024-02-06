<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Seguros</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #e0f2f7; /* Azul claro */
            color: #333;
            position: relative;
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
            position: relative; 
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

        /* Estilos para el botón de configuraciones */
        .config-button {
            position: absolute;
            top: 10px;
            right: 10px;
            background-color: #004080; /* Azul oscuro */
            color: #fff;
            padding: 10px;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
    </style>
</head>
<body>
    <header>
        <h1>Bienvenido a nuestro servicio de seguros</h1>
    </header>

    <main>
        <p>Por favor, elige el tipo de seguro:</p>
        
        <form action="SeleccionarSeguro" method="post">
            <label>
                <input type="radio" name="tipoSeguro" value="viaje" id="viaje" required>
                Seguro de Viaje
            </label>

            <label>
                <input type="radio" name="tipoSeguro" value="estudiante" id="estudiante" required>
                Seguro Estudiantil
            </label>

            <!-- Botón de configuraciones -->
            <a href="modificarPrecio.jsp" class="config-button">
                Configuraciones
            </a>

            <input type="submit" value="Seleccionar">
        </form>
    </main>

    <footer>
        © 2023 Seguros Company. Todos los derechos reservados. <br>
        Síguenos en: <a href="#">Facebook</a> | <a href="#">Twitter</a> | <a href="#">Instagram</a>
    </footer>
</body>
</html>
