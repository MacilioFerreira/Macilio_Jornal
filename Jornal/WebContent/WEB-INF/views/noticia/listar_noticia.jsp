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

     <%-- <c:if test="${noticia.caminho_imagem != null }">          
           <img alt="Imagem da Noticia" src="${noticia.nomeImagem}" title="Foto" width="100" height="50" >
     </c:if>   --%>  

     <h4 align="left">  ${noticia.secao.titulo } <br/> ${noticia.dataNoticia } </h4>

       <div id="titulo" title="titulo" align="left">
       <h3> ${noticia.titulo } </h3>
       <h4> ${noticia.subtitulo } </h4>
       <b>Autor da Noticia:</b> ${noticia.autorNoticia.nome } <br>
       
       <p >
          ${noticia.texto}  
       </p> 	
       
       <div id="imagem" title="imagem">
            <c:if test="${noticia.caminho_imagem != null }">
            <h4> Imagens </h4>          
              <img alt="Imagem da Noticia" src="${noticia.nomeImagem}" title="Foto" width="450" height="250" >
            </c:if> 
       </div>
       
       </div>
       
       <c:if test="${usuario != null}">		
       <h4 align="left" > 
       <c:forEach var="role" items="${usuario.roles}">
           
           <c:if test="${role.role=='Leitor'}">
               <!--  Fazer com que mostre o Comentário!!! -->
              <c:if test="${usuario.getRoleId() == 1 }">
              <a href="formularioComentario?noticiaId=${noticia.noticiaId}"> Inseir Comentário </a> <br>
              </c:if>
           </c:if>
           
           <c:if test="${role.role=='Editor'}"> 
           <!-- Como ele pode ter mais de um role, testo se o papel atual no qual ele está logado é Editor -->
              <c:if test="${usuario.getRoleId() == 2 }"> 
              <a href="excluirNoticia?noticiaId=${noticia.noticiaId }"> Apagar </a> <br> <!-- Teste para o caso de ter mais de um papel -->
              </c:if>
           </c:if>
       
           <c:if test="${role.role=='Jornalista'}"> 
           
           <!-- Como ele pode ter mais de um role, testo se o papel atual no qual ele está logado é Jornalista -->
              <c:if test="${usuario.getRoleId() == 3 }"> 
              <a href="excluirNoticia?noticiaId=${noticia.noticiaId }"> Apagar </a> <br>
              </c:if>
           </c:if>
       
       </c:forEach>
       </h4>
       </c:if>

       <center> <a href="index.jsp"> Voltar </a> </center>

</body>
</html>