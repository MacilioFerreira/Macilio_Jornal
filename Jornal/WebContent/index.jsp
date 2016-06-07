<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Jornal El Mundo </title>
</head>
<body>

           <!-- Mostrando uma imagem do logo da UFC -->
         <center><img align="top" alt="" src="/jornalImagens/UFC.gif" title="Foto" width="300" height="150" ></center>  
            
           <!-- Mostrando a foto Jornalista -->
           <c:forEach var="role" items="${usuario.roles }" >
               <c:if test="${role.role == 'Jornalista'}">
                  <c:if test="${usuario.getRoleId() == 3 }">
                       <c:if test="${usuario.caminho_imagem != null}">
                            <h4 align="left"> Jornalista:</h4>
                            <img align="top" alt="" src="${usuario.nomeImagem}" title="Jornalista" width="100" height="150" > <br>
                       </c:if>
                  </c:if>
               </c:if> 
          </c:forEach>
          
            <!-- Caso em que pode-se fazer o login, porém, não necessita-se fazer o login em uma mesma seção mais de uma vez.
            Assim como pode se cadastrar sem está logado. -->
            <c:if test="${usuario == null }">
                 <h4 align="right"> <a href="formularioLogin"> Login </a> <br> </h4>
                 <h4 align="right"> <a href="formularioUsuario"> Cadastrar-se </a> <br></h4>
            </c:if>


            <!-- Caso para ter o controle de quem está na seção, afinal não pode-se se deslogar o usuário que não está logado. -->
      		<c:if test="${usuario != null }">
         		<br>
         		<h4 align="right"> <a href="sair" > Sair </a> </h4> 
      		</c:if>

   
                  <!-- Titulo do Jornal -->
     		<h1 align="center"> El Mundo </h1> <br />  
     		      <!-- Mensagem de Apresentação -->
     		<h2> Bem-Vindo ao Jornal El Mundo, o lugar onde lhe manter informado é prioridade.</h2> <br>
   
   
            <h3 align="left"> Manchetes: <br> </h3>
             
   			<!-- Listar Seções -->
            <!-- setDataSource é uma tag para configuração de acesso ao servidor de banco de dados, ela
            cria um objeto dataSource que será utilizado pelas outras tags sql.-->
 
             <sql:setDataSource var="secao" driver="com.mysql.jdbc.Driver" 
   	         url="jdbc:mysql://localhost/jornal"
    	     user="root" password="" />
            
            <!-- Realiza uma consulta no banco de dados gerando um ResultSet, utilizando-se da conexão aberta com o dataSource. -->
            
             <sql:query dataSource="${secao}"  var="secoes">
             SELECT s.secao_id, s.descricao, s.titulo from SECAO as s join NOTICIA as n on (s.secao_id = n.noticia_id)            
             </sql:query>
            
            <!-- Pecorrendo o resultado do ResultSet, ou seja, da consulta. --> 
             <ul>
                <c:forEach var="sc" items="${secoes.rows}">
			   	 	<li>
                    	<a href="lerManchetes?secaoId=${sc.secao_id}"> ${sc.titulo}  <br> </a>  
                    </li>
		     	</c:forEach>
             </ul>

               <!-- Todos os usuários podem visualizar os Classificados. --> 
               <h3 align="left"> Classificados: <br> </h3>
               <a href="listarClassificado"> Visualizar Classificados </a> <br>
    

               <!-- Controle de Acesso as Páginas -->
               
		       <c:if test="${usuario != null }">
        		   <c:forEach var="role" items="${usuario.roles }" >

                   <c:if test="${role.role == 'Editor'}">
                      <c:if test="${usuario.getRoleId() == 2 }">
                        <h3 align="left"> Demais Opções: <br> </h3>                      
            		    <a href="formularioJornalista"> Cadastrar Jornalista </a> <br>
                		<a href="formularioClassificado"> Cadastrar Classificado </a> <br/>
						<a href="formularioSecao"> Cadastrar Seção </a> <br/>
					  </c:if>                                             
              	   </c:if>
             

             	   <!-- Funcionalidades que o Jornalista pode realizar. -->             
                   <c:if test="${role.role == 'Jornalista'}">
                      <c:if test="${usuario.getRoleId() == 3 }">
                        <h3 align="left"> Demais Opções: <br> </h3>                      
                        <a href="noticiaForm"> Cadastrar Noticia </a> <br/>                
                      </c:if>
                   </c:if>
          		   </c:forEach>
       		   </c:if>   
              

</body>
</html>
