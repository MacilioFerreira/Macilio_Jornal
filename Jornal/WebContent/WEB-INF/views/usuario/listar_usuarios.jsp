<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Usuários </title>
</head>
<body>

      <h3> Usuários: <br /> Quantidade de Usuários: ${quantidade}</h3>
      
      <table border="1"> 
      <thead>      
               <tr>
               <th><b> Login: </b></th> 
               <th><b> Senha:  </b></th>
               <th><b> Nome:   </b></th>
               <th><b> E-mail:    </b></th>               
               </tr>
      </thead>
      <tbody>
      <c:forEach var="user" items="${usuarios}">
	     <tr>
	     	<td>${user.login}</td>
	     	<td>${user.senha}</td>
	     	<td>${user.nome}</td>	     
	    	<td>${user.email}</td>
	        <td><a href="excluirUsuario?login=${user.login}">Excluir</a></td>
	  </tr>
	  </c:forEach>      
      <tbody>
      
      </table>     

</body>
</html>