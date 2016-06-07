<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Noticia </title>
</head>
<body>
   
      <ul type="circle">
		<c:forEach var="noticia" items="${noticias}">
			<c:if test="${noticia.visivel==true}">
			 <li>
			  <div id="titulo_secao" title="titulo_secao">
			     <h1> ${noticia.titulo }  </h1>  
                 <h3> ${noticia.subtitulo }</h3> <br>
                 <a href="lerNoticia?noticiaId=${noticia.noticiaId}"> Ler Noticia Completa  </a> <br>
              </div>
			 </li>
            </c:if>
		</c:forEach>
	</ul>
	
          <center> <a href="index.jsp"> Voltar </a><br> </center>

</body>
</html>