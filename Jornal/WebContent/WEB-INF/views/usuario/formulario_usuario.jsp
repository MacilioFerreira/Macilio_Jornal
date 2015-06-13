<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Formulário Usuário </title>
</head>
<body>

    <h2> Formulário Usário: </h2>
    
    <form action="inserirUsuario" method="POST"> 
    <table>
    
       <tr>
           <td> Nome Completo: </td>
           <td>
           <input type="text" name="nome" />   
           </td>
       </tr>
    
       <tr>
           <td> Login: </td>
           <td>
           <input type="text" name="login" />   
           </td>
       </tr>
       
       <tr>
           <td> Senha: </td>
           <td>
           <input type="password" name="senha" />   
           </td>
       </tr>
          
       <tr>
           <td> E-mail: </td>
           <td>
           <input type="text" name="email" />   
           </td>
       </tr>
  
       <tr>
           <td><input type="reset" value="Limpar"> </td>
		   <td><input type="submit" value="Inserir" /></td>
		</tr>
       
    </table>
    </form>

</body>
</html>