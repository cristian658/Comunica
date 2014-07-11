<%-- 
    Document   : cursos
    Created on : Jun 21, 2014, 10:37:25 AM
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
              <li class="active"><a href="CursosAction.action">Curso</a></li>
              <li><a href="ProfesorAction.action">Profesor</a></li>              
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
        <h2>Curso</h2>
        <p>
             <s:form cssClass="form-inline" role="form" action="CursosAction"> 
                <div class="form-group">
                    <label class="sr-only" for="inputCurso">Nombre Curso</label>
                    <input type="text" class="form-control" id="inputCurso" placeholder="Nombre Curso" name="nombre"> 
                </div>
                <button type="submit" class="btn btn-primary">Ingresar</button>
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
                   <div class="col-xs-4">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                         <h3 class="panel-title"><strong>Cursos</strong></h3>
                                    </div>
                                    <div class="panel-body">
                                        <table class="table table-striped table-hover">
                                            <thead>
                                                <tr>
                                                    <td><strong>Id</strong></td>
                                                    <td><strong>Curso</strong></td>
                                                    <td><strong>Fecha</strong></td>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <s:iterator value="cursosList" > 
                                                    <tr> 
                                                        <td><s:property value="idCurso"/></td>
                                                        <td><s:property value="nombreCurso"/></td>
                                                        <td><s:date name="fechaRegistroCurso" format="dd/MM/yyyy" /></td>
                                                    </tr>  
                                                </s:iterator>  
                                            </tbody>
                                        </table>
                                    </div>
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

