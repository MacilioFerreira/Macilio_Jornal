<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Noticia </title>
</head>
<body>

     <h4 align="left">  ${noticia.secao.titulo } <br/> ${noticia.dataNoticia } </h4>

       <div id="titulo" title="titulo" align="left">
       <h3> ${noticia.titulo } </h3>
       <h4> ${noticia.subtitulo } </h4>
       <b>Autor da Noticia:</b> ${noticia.autorNoticia.nome } <br>
       
       <p >${noticia.texto} </p> 	
       
       </div>
       
       <c:if test="${usuario != null}">		
       <h4 align="left" > 
       <c:forEach var="role" items="${usuario.roles}">
           
           <c:if test="${role.role=='Leitor'}">
               <!--  Fazer com que mostre o Comentário!!! -->
              <a href="formularioComentario?noticiaId=${noticia.noticiaId}"> Inseir Comentário </a>
           </c:if>
           
           <c:if test="${role.role=='Editor'}"> 
              <a href="excluirNoticia?noticiaId=${noticia.noticiaId }"> Apagar </a>
           </c:if>
       
           <c:if test="${role.role=='Jornalista'}"> 
              <a href="excluirNoticia?noticiaId=${noticia.noticiaId }"> Apagar </a>
           </c:if>
       
       </c:forEach>
       </h4>
       </c:if>

       <center> <a href="index.jsp"> Voltar </a> </center>

</body>
</html>