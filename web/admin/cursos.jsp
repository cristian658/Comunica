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

        <title>Mantenedor - Cursos</title>

        <!-- Bootstrap core CSS -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="../css/cover.css" rel="stylesheet">

        <!-- Just for debugging purposes. Don't actually copy this line! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>
        <jsp:include page="menu.jsp" />
        <div class="site-wrapper">
            <div class="site-wrapper-inner">
                <div class="cover-container">

                    <div class="inner cover">
                        <div class="row">
                            <div class="col-md-10">
                                <div class="panel panel-default">
                                    <div class="panel-heading">Agregar Curso</div>
                                    <div class="panel-body">
                                        <s:if test="hasActionErrors()">
                                            <div class="alert alert-danger alert-dismissable">
                                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                                <s:actionerror/>
                                            </div>
                                        </s:if>
                                        <s:form cssClass="form-inline" role="form" action="CursosAction"> 
                                            <div class="form-group">
                                                <label class="sr-only" for="inputCurso">Nombre Curso</label>
                                                <input type="text" class="form-control" id="inputCurso" placeholder="Nombre Curso" name="nombre"> 
                                            </div>
                                            <button type="submit" class="btn btn-primary">Ingresar</button>
                                        </s:form> 
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-10">
                                <div class="panel panel-default">
                                    <div class="panel-heading">Cursos</div>
                                    <div class="panel-body">
                                        <table class="table table-striped table-hover">
                                            <thead>
                                                <tr>
                                                    <td>#</td>
                                                    <td>Curso</td>
                                                    <td>Fecha</td>
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
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/docs.min.js"></script>
    </body>
</html>

