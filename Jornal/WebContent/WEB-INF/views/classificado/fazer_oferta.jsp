<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Realizar Oferta </title>
</head>
<body>

      <h3 align="left"> Faça sua Oferta de Compra! </h3> <br/>
      <form action="realizarCompra" method="POST">
         <input type="hidden" name="id" value="${classificado.classificadoId}">
         Valor da Oferta: <input type="text" name="oferta">
         <input type="submit" value="Inserir">
      </form>
</body>
</html>