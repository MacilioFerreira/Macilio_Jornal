<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Cadrastar Jornalista </title>
</head>
<body>
  
     <h2> Formulário Jornalista: </h2>
     <form action="adicionarJornalista" method="POST">
     <table>
         <tr> 
            <td> Nome do Jornalista: </td>
            <td><input type="text" name="nome" ></td>
         </tr>
         
         <tr> 
            <td> Login: </td>
            <td><input type="text" name="login" ></td>
         </tr>
         
         <tr> 
            <td> Senha: </td>
            <td><input type="password" name="senha" ></td>
         </tr>
         
         
         <tr> 
            <td> E-mail: </td>
            <td><input type="text" name="email" ></td>
         </tr>
         
         <tr> 
            <td><input type="reset" value="Limpar" ></td>
            <td><input type="submit" value="Enviar" ></td>
         </tr>
         
         
         
     </table>
     </form>

</body>
</html>