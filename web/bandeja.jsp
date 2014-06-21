<%-- 
    Document   : bandeja
    Created on : Jun 16, 2014, 8:40:41 PM
    Author     : cristian
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Bienvenido <s:text name="%{#session.correo}"></s:text></title>

            <!-- Bootstrap core CSS -->
            <link href="css/bootstrap.min.css" rel="stylesheet">

            <!-- Custom styles for this template -->
            <link href="css/cover.css" rel="stylesheet">

            <!-- Just for debugging purposes. Don't actually copy this line! -->
            <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

            <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
            <!--[if lt IE 9]>
              <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
              <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
            <![endif]-->
        </head>
        <body>

            <div class="site-wrapper">
                <div class="site-wrapper-inner">
                    <div class="cover-container">
                        <div class="masthead clearfix">
                            <div class="inner">
                                <h3 class="masthead-brand">Comunica</h3>
                                <ul class="nav masthead-nav">
                                    <li>Nueva Comunicación</li>
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Seleccione <b class="caret"></b></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">Todo el Curso</a></li>
                                            <li><a href="#">Individual</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="<%= request.getContextPath() %>/logoutAction.action">Salir</a></li>
                                </ul>
                            </div>
                        </div>

                        <div class="inner cover">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <td>Remitente</td>
                                        <td>Asunto</td>
                                        <td>Fecha</td>
                                        <td>Estado</td>
                                    </tr>
                                </thead>
                                <tbody>
                                <s:iterator value="comunicaciones" > 
                                    <tr> 
                                        <td><s:property value="profesor.nombreProfesor"/></td>
                                        <s:iterator value="detallecomunicacions" begin="0" end="0" > 
                                            <td><s:property value="asunto"/></td> 
                                            <td><s:property value="fechaRegistroComunicacion"/></td>
                                            <td><s:property value="estado"/></td>
                                        </s:iterator> 
                                        
                                    </tr>  
                                </s:iterator>  
                            </tbody>

                        </table>

                    </div>

                    <div class="mastfoot">
                        <div class="inner">
                            <p>Grupo EECC</p>
                        </div>
                    </div>

                </div>

            </div>

        </div>

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/docs.min.js"></script>
    </body>
</html>
