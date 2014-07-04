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

        <title>Bienvenido <s:text name="%{#session.correo}"></s:text></title>

            <!-- Bootstrap core CSS -->
            <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

            <!-- Custom styles for this template -->
            <link href="bootstrap/css/cover.css" rel="stylesheet">

            <!-- Just for debugging purposes. Don't actually copy this line! -->
            <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

            <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
            <!--[if lt IE 9]>
              <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
              <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
            <![endif]-->
        </head>
        <body>
        <jsp:include page="include/menu.jsp" />
        <div class="site-wrapper">  
            <div class="site-wrapper-inner">
                <div class="cover-container">
                    <div class="inner cover">
                        <div class="panel panel-default">
                            <div class="panel-heading">Nueva Comunicación</div>
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
                                            <s:textfield type="text" cssClass="form-control" id="inputAsunto"  placeholder="Asunto" name="asunto"></s:textfield>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputBody" class="control-label col-xs-2">Mensaje</label>
                                            <div class="col-xs-10">
                                            <s:textarea cssClass="form-control" rows="3" name="mensaje"></s:textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-offset-2 col-xs-10">
                                            <s:hidden id="alumno" value=""></s:hidden>
                                            <s:hidden id="option" value="%{option}" name="option"></s:hidden>
                                                <button type="button" class="btn btn-primary">Cancelar</button>
                                                <button type="submit" class="btn btn-primary">Enviar</button>
                                            </div>
                                        </div>
                                </s:form>
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
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
