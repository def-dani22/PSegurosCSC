<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contrato de Seguro de Estudiante</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f9fb; /* Azul claro */
            color: #333;
            min-height: 100vh; /* Asegura que el cuerpo ocupa al menos toda la altura de la ventana */
            position: relative;
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

        h1, h2 {
            color: #004080; /* Azul oscuro */
        }

        ul {
            list-style: none;
            padding: 0;
        }

        ul li {
            margin-bottom: 10px;
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
        <h1>Contrato de Seguro de Estudiante</h1>
    </header>

    <main>
        <ul>
            <li><strong>COD Poliza:</strong> ${idPxE.idPxE}</li>
        </ul>
        
        <p><strong>${estudiante.nombre} ${estudiante.apellido}</strong>, ha adquirido nuestros servicios de seguro, a continuación, una breve información de su contrato:</p>

        <h2>Detalles del Asegurado:</h2>
        <ul>
            <li><strong>Cedula:</strong> ${estudiante.cedula}</li>
            <li><strong>Nombre Centro Educativo:</strong> ${estudiante.nomCentroE}</li>
            <li><strong>Nombre:</strong> ${estudiante.nombre}</li>
            <li><strong>Apellido:</strong> ${estudiante.apellido}</li>
            <li><strong>Email:</strong> ${estudiante.email}</li>
            <li><strong>Teléfono:</strong> ${estudiante.telefono}</li>
        </ul>

        <h2>Detalles del Seguro de Estudiante:</h2>
        <ul>
            <li><strong>Tipo de Seguro:</strong> ${seguroEstudiante.tipoPoliza}</li>
            <li><strong>Descripción:</strong> ${seguroEstudiante.descripcion}</li>
            <li><strong>Cobertura:</strong> ${seguroEstudiante.cobertura}</li>
            <li><strong>Precio:</strong> ${seguroEstudiante.precio}</li>
            <li><strong>Identidad del beneficiario:</strong> ${seguroEstudiante.beneficiario}</li>
        </ul>

        <p>El Asegurado reconoce haber revisado y aceptado los términos y condiciones establecidos en este contrato de seguro de viaje.</p>

        <p>
            <a href="index.jsp" class="button">Volver al inicio</a>
        </p>
    </main>
        
    <footer>
        © 2023 Seguros CSC. Todos los derechos reservados. <br>
        Síguenos en: <a href="#">Facebook</a> | <a href="#">Twitter</a> | <a href="#">Instagram</a>
    </footer>
</body>
</html>