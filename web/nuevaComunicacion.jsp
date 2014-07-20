<%-- 
    Document   : nuevaComunicacion
    Created on : Jun 22, 2014, 7:37:10 PM
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

    <title>Sistema - Comunica</title>

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
                  <li class="active"><a href="NuevaComunicacionAction.action">Nueva Comunicaci&oacute;n</a></li>
              </s:if>
              
              
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a>Bienvenido : <s:text name="%{#session.correo}"></s:text></a></li>
              <li><a href="<%= request.getContextPath() %>/logoutAction.action">Salir</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </div>
          
      <!-- Main component for a primary marketing message or call to action -->
      <div class="well">
          <h2>Nueva Comunicaci&oacute;n</h2>      
      </div>
        <s:if test="hasActionMessages()">
            <div class="alert alert-success alert-dismissable" role="alert" style="width:30%">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <s:actionmessage/>
            </div>
        </s:if> 
      <p>
           <div class="row">
               
                    <div class="col-xs-7">
                        
                            <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title"><strong>Redactar</strong></h3>
                            </div>
                            <div class="panel-body">
                                <s:if test="hasActionErrors()">
                                    <div class="alert alert-danger alert-dismissable">
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        <s:actionerror/>
                                    </div>
                                </s:if>
                                <s:form cssClass="form-horizontal" action="NuevaComunicacionAction"> 
                                    <s:if test="%{option == 'Apoderado'}">
                                        <div class="form-group" id="alumGroup">
                                            <label for="inputApoderado" class="control-label col-xs-2">Apoderado de</label>
                                            <div class="col-xs-10">
                                                <div class="btn-group">
                                                        <s:select label="Alumnos"
                                                                  headerKey=""
                                                                  headerValue="Seleccione Alumno"
                                                                  list="alumnos"
                                                                  listKey = "idAlumno"  
                                                                  listValue = "nombreAlumno"  
                                                                  name="idAlumno"                                          
                                                                  emptyOption = "false"                                    
                                                                  cssClass="btn btn-default dropdown-toggle"
                                                                  required="true"
                                                                  />
                                                </div>
                                            </div>
                                        </div>
                                    </s:if>
                                    <div class="form-group">
                                        <label for="inputAsunto" class="control-label col-xs-2">Asunto</label>
                                        <div class="col-xs-10">
                                            <s:textfield type="text" cssClass="form-control" id="inputAsunto" name="asunto" required=""></s:textfield>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputBody" class="control-label col-xs-2">Mensaje</label>
                                            <div class="col-xs-10">
                                            <s:textarea cssClass="form-control" rows="3" name="mensaje" required=""></s:textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-offset-2 col-xs-10">
                                            <s:hidden id="alumno" value=""></s:hidden>
                                            <s:hidden id="option" value="%{option}" name="option"></s:hidden>                                                
                                                <button type="submit" class="btn btn-primary">Enviar</button>
                                                <button type="reset" class="btn btn-primary">Limpiar</button>
                                            </div>
                                        </div>
                                </s:form>
                            </div>                          
                        </div>   
                        <a href="BandejaAction.action"><button type="button" class="btn btn-primary">Volver a la bandeja</button></a>
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

