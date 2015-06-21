<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Comentar </title>
</head>
<body>
   
     <h3> Insira o seu comentário para a Noticia!</h3>
     
     <form action="adicionarComentario" method="POST">
         <input type="hidden" name="id_noticia" value="${noticia.noticiaId}" >
         Texto Comentário: <input type="text" name="texto">
         <input type="submit" value="Inserir">
     </form>
     
</body>
</html>