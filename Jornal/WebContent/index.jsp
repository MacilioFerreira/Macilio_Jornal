<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Jornal El Mundo </title>
</head>
<body>

            <!-- Caso em que pode-se fazer o login, porém, não necessita-se fazer o login em uma mesma seção mais de uma vez -->
            <c:if test="${usuario == null }">
                 <h4 align="right"> <a href="formularioLogin"> Login </a> </h4>
            </c:if>

            <!-- Caso para ter o controle de quem está na seção, afinal não pode-se se deslogar o usuário que não está logado. -->
      		<c:if test="${usuario != null }">
         		<h4 align="right"> <a href="sair" > Sair </a> </h4> 
      		</c:if>
   
                  <!-- Titulo do Jornal -->
     		<h1 align="center"> El Mundo </h1> <br />  
     		      <!-- Mensagem de Apresentação -->
     		<h2> Seja muito Bem Vindo ao nosso Jornal!</h2> <br>
   
         		<a href="verCategorias"> Visualizar Noticias por Seção </a>  <br>
                
                <!-- Controlando o caso de o usuário se cadastrar  depois de logado -->
                <c:if test="${usuario == null }">
                     <a href="formularioUsuario"> Cadastrar-se no Sistema </a>   <br>
                </c:if>
                
                <a href="listarClassificado"> Visualizar Classificados </a> <br>
    

		       <c:if test="${usuario != null }">
        		   <c:forEach var="role" items="${usuario.roles }" >

                   <c:if test="${role.role == 'Editor'}">
            		    <a href="formularioJornalista"> Cadastrar Jornalista </a> <br>
                		<a href="formularioClassificado"> Cadastrar Classificado </a> <br/>
						<a href="formularioSecao"> Cadastrar Seção </a> <br/>                                             
              	   </c:if>
             
             			<!-- Funcionalidades que o Jornalista pode realizar. -->
             
                   <c:if test="${role.role == 'Jornalista'}">
            		    <a href="noticiaForm"> Cadastrar Noticia </a> <br/>                
                   </c:if>
          		   </c:forEach>
       		   </c:if>


</body>
</html>