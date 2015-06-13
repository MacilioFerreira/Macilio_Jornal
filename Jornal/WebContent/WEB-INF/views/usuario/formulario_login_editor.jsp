<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Login </title>
</head>
<body>

         <h2>Login: </h2> <br />
            
      <form action="efetuarLoginEditor" method="POST">
       <table>
                <tr>
                 <td> Login: </td>
                 <td><input type="text" name="login" /></td>
                </tr>
             
                <tr>
                 <td> Senha: </td>
                 <td><input type="password" name="senha" /> <br/> </td>
                </tr>      
                
                <tr>
                 <td>
                  <select name="roleId">  
                     <c:forEach var="role" items="${roles}">
                       <option value= "${role.roleId}"> ${role.role} </option> 
                     </c:forEach>
                  </select> Função:  </td>
                </tr>
              
                <tr> 
                 <td><br/> <center> <input type="reset" value="Limpar"> </center> </td>
                 <td><br/> <center> <input type="submit" value="Entrar" /> </center> </td>
                </tr>       
             
         </table> 
      </form>
</body>
</html>