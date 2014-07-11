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
    <link rel="icon" href="../../favicon.ico">

    <title>Bienvenido <s:text name="%{#session.correo}"></s:text></title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="navbar.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <s:url action="NuevaComunicacionAction.action" var="urlTag" ></s:url>
  </head>

  <body style="background-color: #FAFAFA">
      
      <div class="container" style="background-color: #FFFFFF">

      <!-- Static navbar -->
      <div class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Comunica</a>
          </div>
          <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <s:if test="%{#session.typeUser == 'Profesor'}">
              <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Enviar Comunicación <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><s:a href="%{urlTag}?option=curso">Curso</s:a></li>
                        <li><s:a href="%{urlTag}?option=Apoderado">Apoderado</s:a></li>
                    </ul>
              </li>
              </s:if>
              <s:if test="%{#session.typeUser == 'Apoderado'}">
                  <li ><s:a href="%{urlTag}">Nueva Comunicaci&oacute;n</s:a></li>
              </s:if>
              
              
            </ul>
            <ul class="nav navbar-nav navbar-right">
              <li><a href="<%= request.getContextPath() %>/logoutAction.action">Salir</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </div>
          
      <!-- Main component for a primary marketing message or call to action -->
      <div class="well">
          <h2>Bandeja de Comunicaciones</h2>      
      </div>
      <p>
           <div class="row">
               
                    <div class="col-xs-7">
                        
                            <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title"><strong>Comunicaciones</strong></h3>
                            </div>
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <td><strong>Remitente</strong></td>
                                    <td><strong>Asunto</strong></td>
                                    <td><strong>Fecha</strong></td>
                                    <td><strong>Estado</strong></td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="comunicaciones" > 
                                    <tr>

                                            <td><s:property value="emisor"/>(n)</td>
                                            <td><s:property value="asunto"/></td> 
                                            <td><s:date name="fechaRegistroComunicacion" format="dd/MM/yyyy HH:mm:ss"/></td>
                                            <td><s:property value="estado"/></td>               
                                            <td><s:a href="DetalleComunicacionAction.action?idComunicacion=%{comunicacion.idComunicacion}"><img src="imagenes/lupa.png" width="25" height="25" title="Ver"/></s:a></td>

                                    </tr>  
                                </s:iterator>                       
                            </tbody>
                        </table>                            
                        </div>    
                    </div>
                </div>
      </p>

      <!--hr>

      <footer>
        <p>&copy; EECC 2014</p>
      </footer-->
      
      <hr>
        <footer>
        <p>&copy; EECC 2014</p>
      </footer>
      

    </div> <!-- /container -->
    

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>

