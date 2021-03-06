<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Cadastrar Noticia </title>
</head>
<body>

      <form action="cadastrarNoticia" enctype="multipart/form-data" method="POST">
      <table>
             <thead>
                 
                 <tr>
                 <td> Titulo: </td>   
                 <td> <input type="text" name="titulo" /> </td>
                 </tr>
                                  
                 <tr>              
                 <td> Subtitulo: </td>                  
                 <td>  <input type="text" name="subtitulo" /> </td>
                 </tr>
                 
                 <tr>
                 <td> Texto: </td>
                 <td> <input type="text" name="texto" /> </td>
                 </tr>
                 
                 <tr> 
                   <td>
                     <select name="secaoId">  
                        <c:forEach var="secao" items="${secoes}">
                           <option value= "${secao.secaoId}"> ${secao.titulo} </option> 
                        </c:forEach>
                     </select> 
                      Seção: </td>                     
                 </tr>
                                  
                 <tr>
                 <td> Data da Noticia: </td>
                 <td>
                     <input type="date" name="data" />
                 </td>
                 </tr>
                 
                 <tr>
                 <td> Imagem para a Noticia: </td>
                 <td>
                     <input type="file" name="imagem" />
                 </td>
                 </tr>
                 
                 
                 
                 <tr> 
                 <td><input type="submit" value="Cadastrar" /> </td>
                 </tr>
                 
             </thead>
             
      </table>      
      </form>
      
</body>
</html>