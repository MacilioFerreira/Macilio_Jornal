<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Classificados </title>
</head>
<body>
   
    <h2 align="center"> Classificados  </h2> 
      
    <ul type="circle" >
      <c:forEach var="cass" items="${classificados }">
        <li> 
       <table >
         <tr> 
           <td> Titulo: </td>
           <td><h3>${cass.titulo }</h3></td>
         </tr>
       
         <tr> 
           <td> Texto: </td>
           <td><h4>${cass.texto }</h4></td>
         </tr>
       
         <tr> 
           <td> Preço Inicial: </td>
           <td> ${cass.preco } </td>
         </tr>

         <tr> 
           <td> Telefone: </td>
           <td> ${cass.telefone }</td>
         </tr>

         <tr> 
           <td> Melhor Oferta: </td>
           <td> ${cass.melhor_oferta } </td>
         </tr>

         <tr> 
           <td> Data da Oferta: </td>
           <td> ${cass.data_oferta } </td>
         </tr>

         <tr>
            <c:if test="${not empty cass.autorOferta }">
            <td> Autor da Oferta: </td>
            <td> ${cass.autorOferta.nome }</td>     
            </c:if>
         </tr>

         <tr>
            <c:forEach var="role" items="${usuario.roles}">
                <c:if test="${role.role == 'Leitor' }">
                	<c:if test="${usuario.getRoleId() == 1 }">
                   		<td><a href="inserirOferta?classificadoId=${cass.classificadoId}"> Inserir Oferta </a><br> </td> 
                	</c:if>
                </c:if>
            </c:forEach> 
         </tr>
         
       
    </table>
       </li>
     </c:forEach>
     </ul>
   
   <center> <a href="index.jsp"> Voltar para á página Incial</a> </center>    
</body>
</html>

        <!-- 
          Forma utilizando div, design não muito legal
          <div align="left" id="titulo" title="Classificados" >
             <h2>Titulo:         ${cass.titulo }</h2>
             <h3>Texto:          ${cass.texto }</h3>
             <h4>Preço Inicial:  ${cass.preco } </h4>
             <h4>Telefone:       ${cass.telefone } </h4>
             <h4>Melhor Oferta:  ${cass.melhor_oferta } </h4>
             <h4>Data da Oferta: ${cass.data_oferta } </h4>
             <h4><a href="inserirOferta"> Inserir Oferta </a></h4>     
            </div>  -->