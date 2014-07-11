<%-- 
    Document   : detalleComunicacion
    Created on : 10-jul-2014, 16:13:21
    Author     : Esteban
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
                                        <li><s:a href="NuevaComunicacionAction.action?option=curso">Curso</s:a></li>
                                        <li><s:a href="NuevaComunicacionAction.action?option=Apoderado">Apoderado</s:a></li>
                                        </ul> 
                                    </li>
                            </s:if>
                            <s:if test="%{#session.typeUser == 'Apoderado'}">
                                <li><a href="NuevaComunicacionAction.action">Nueva Comunicaci&oacute;n</a></li>
                                </s:if>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="<%= request.getContextPath()%>/logoutAction.action">Salir</a></li>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div><!--/.container-fluid -->
            </div>

            <!-- Main component for a primary marketing message or call to action -->
            <s:iterator value="dcPrimerResgistro"> 
                <div class="well">
                    <h2>Detalle '<s:property value="asunto"/>'</h2>      
                </div>
                <div class="row">                                  
                    <div class="col-sm-7">
                        <div class="list-group">
                            <s:iterator value="detalleComunicacionList"> 
                                <a class="list-group-item">
                                    <h4 class="list-group-item-heading">
                                        <s:property value="emisor"/>
                                    </h4>
                                    <p class="list-group-item-text">
                                        <i><s:property value="mensaje"/></i>
                                        <br><br>
                                        <s:date name="fechaRegistroComunicacion" format="dd/MM/yyyy HH:mm:ss"/>               
                                    </p>
                                </a>
                                <br> 
                            </s:iterator> <!--iterator 1--> 
                            <s:if test="%{dcPrimerResgistro.comunicacion.apoderado != null}"> 
                                <s:form cssClass="form-horizontal" action="DetalleComunicacionAction.action"> 
                                    <s:textarea cssClass="form-control" rows="3" required="" placeholder="Escriba aqui su respuesta..." name="mensajeForm"/>
                                    <s:hidden id="idComunicacion" value="%{idComunicacion}" name="idComunicacion"></s:hidden>                     
                                        <br>
                                    <s:submit type="submit" cssClass="btn btn-primary" value="Responder"></s:submit>
                                    <s:reset type="reset" cssClass="btn btn-primary" value="Limpiar"></s:reset>
                                </s:form>
                                <br>
                            </s:if>
                            <a href="BandejaAction.action"><button type="button" class="btn btn-primary">Volver a bandeja</button></a>
                        </div><!--list-->
                    </div><!--col-sm-7-->
                </div><!--row-->
            </s:iterator>  <!--iterator 2--> 
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

