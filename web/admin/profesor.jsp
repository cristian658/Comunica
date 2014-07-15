<%-- 
    Document   : profesor
    Created on : 21-06-2014, 12:24:08 PM
    Author     : eduardo
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
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">

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
              <li><a href="CursosAction.action">Curso</a></li>
              <li class="active"><a href="ProfesorAction.action">Profesor</a></li>
              <li><a href="MatriculaAction.action">Matricula</a></li>
              
              
            </ul>
            <ul class="nav navbar-nav navbar-right">
              <li><a href="../logoutAction.action">Salir</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </div>
      <!-- Main component for a primary marketing message or call to action -->
      <div class="well">
        <h2>Profesor</h2>
        <p>
            <s:form  cssClass="form-inline" role="form" action="ProfesorAction">
                    <s:select label="Curso" 
                              headerKey="" headerValue="Seleccione Curso"
                              list="cursosList"
                              listKey = "idCurso"  
                              listValue = "nombreCurso"  
                              name="id_Curso"                                          
                              emptyOption = "false"                                    
                              cssClass="btn btn-default dropdown-toggle"
                              required="true"
                              />
                    <s:textfield placeholder="Nombre" name="nombre_profesor" type="text" id="TextNameProfesor" cssClass="form-control" required="" />
                    <s:textfield placeholder="Ap. Paterno" name="apellido_pat_profesor" type="text" id="TextNameProfesorPat" cssClass="form-control" required="" />
                    <s:textfield placeholder="Ap. Materno" name="apellido_mat_profesor" type="text" id="TextNameProfesorMat" cssClass="form-control" required="" />
                    <s:textfield placeholder="Email" name="email_profesor" type="email" id="TextNameProfresorEmail" cssClass="form-control" required="" />
                    <br><br>
                    <s:submit cssClass="btn btn-primary" value="Ingresar"></s:submit>     
            </s:form>
      </p>
      
      </div>
                 <s:if test="hasActionMessages()">
                    <div class="alert alert-success alert-dismissable" role="alert" style="width:30%">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <s:actionmessage/>
                    </div>
                </s:if> 
                <s:if test="hasActionErrors()">
                    <div class="alert alert-danger alert-dismissable" role="alert" style="width:30%">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <s:actionerror/>
                    </div>
                </s:if>      
      <p>
           <div class="row">
                    <div class="col-xs-7">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title"><strong>Profesores</strong></h3>
                            </div>
                        <table class="table table-hover table-striped">                             
                            <thead>                                
                                <tr>
                                    <td><strong>#</strong></td>
                                    <td><strong>Nombre</strong></td>
                                    <td><strong>Apellido Paterno</strong></td>
                                    <td><strong>Apellido Materno</strong></td>
                                    <td><strong>Email</strong></td>
                                    <td><strong>Fecha Registro</strong></td>
                                    <td><strong>curso</strong></td>
                                </tr>                                
                            </thead>                          
                            <tbody>
                                <s:iterator value="profesoresList" > 
                                    <tr> 
                                        <td><s:property value="IdProfesor"/></td>
                                        <td><s:property value="NombreProfesor"/></td>
                                        <td><s:property value="ApellidoPatProfesor"/></td>
                                        <td><s:property value="ApellidoMatProfesor"/></td>
                                        <td><s:property value="emailProfesor"/></td>
                                        <td><s:property value="FechaRegistroProfesor"/></td>
                                        <td><s:property value="curso.nombreCurso"/></td>
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
    <script src="../bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>

