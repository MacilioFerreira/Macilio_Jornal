<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Comentário</title>
</head>
<body>
     
     
     <h4 align="left">  ${noticia.secao.titulo } <br/> ${noticia.dataNoticia } </h4>

       <div id="titulo" title="titulo" align="left">
       <h3> ${noticia.titulo } </h3>
       <h4> ${noticia.subtitulo } </h4>
       <b>Autor da Noticia:</b> ${noticia.autorNoticia.nome } <br>
       
       <p >${noticia.texto} </p> 	
       
       </div>
               <!--  Fazer com que mostre o Comentário!!! -->
       
       <div id="id_comentario" title="id_comentario" align="left">
         <c:forEach var="comentario" items="${noticia.comentarios }" >
            <table>
                <tr>
                   <td> Autor Comentário: </td>
                   <td> ${comentario.autorComentario.nome }</td>
                </tr>
                
                <tr>
                   <td> Comentário: </td>
                   <td> ${comentario.texto }</td>
                </tr>
                
            </table>
         </c:forEach>
       </div>
       
       		
       <h4 align="left" > 
       <c:forEach var="role" items="${roles}">
           
           <c:if test="${role.role=='Leitor'}">
              <a href="formularioComentario?noticiaId=${noticia.noticiaId}"> Inseir Comentário </a>
           </c:if>
                  
       </c:forEach>
       </h4>
       <center> <a href="index.jsp" > Voltar </a> </center>
</body>
</html>