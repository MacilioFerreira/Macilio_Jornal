<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Cadastrar Classificado </title>
</head>
<body>
   
 <center>

    <h2> Inserir Classificado: </h2> <br />
    
    <form action="adicionarClassificado">
      <table>
         <tr> 
            <td> Titulo: </td>
            <td><input type="text" name="titulo"></td>
         </tr>
         
         <tr> 
            <td> Texto: </td>
            <td><input type="text" name="texto"></td>
         </tr>
         
         <tr> 
            <td> Preço Inicial: </td>
            <td><input type="text" name="preco"></td>
         </tr>
         
         <tr> 
            <td> Telefone: </td>
            <td><input type="text" name="telefone"></td>
         </tr>
         
         <tr> 
            <td><input type="reset" value="Limpar"></td>
            <td><input type="submit" value="Cadastrar"></td>
         </tr>
      </table>
    </form>

 </center>

</body>
</html>