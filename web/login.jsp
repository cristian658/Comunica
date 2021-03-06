<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<s:set var="type" value="typeUser" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Sistema de comunicaciones">
        <meta name="author" content="Cristian-Esteban-Carlos-Eduardo">


        <title>Comunica - Sistema de Comunicaciones</title>

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
                <div class="container cover-container">
                    <div class="masthead clearfix">
                       <div class="inner">
                            <h3 class="masthead-brand">Comunica</h3>
                            <ul class="nav masthead-nav">
                                <li class="active"><a href="LoginAction.action?typeUser=Apoderado">Apoderado</a></li>
                                <li><a href="LoginAction.action?typeUser=Profesor">Profesor</a></li>
                                <li><a href="LoginAction.action?typeUser=Administrador">Administrador</a></li>
                            </ul>
                        </div>
                    </div>
                    <h2 class="form-signin-heading">Bienvenido <s:text name="type"></s:text></h2>
                    <s:form cssClass="form-signin" role="form"  action="LoginAction">  
                        <s:textfield type="email" required = "" cssClass="form-control" placeholder="Email" name="user.email"></s:textfield>  
                        <s:password cssClass="form-control" required = "" placeholder="Contresaña" name="user.clave"></s:password>  
                        <s:submit cssClass="btn btn-lg btn-primary btn-block" value="Ingresar"></s:submit>  
                    </s:form> 
                    <div class="mastfoot">
                        <div class="inner">
                            <p>Grupo 1</p>
                        </div>
                    </div>
                </div> <!-- /container -->
            </div>
        </div>
    </body>
</html>
