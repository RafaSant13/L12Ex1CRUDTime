<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Time</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" />
	</div>
	<br>
	<div align="center" class="container">
		<form action="time" method="post">
			<p class="title">
				<b>Time</b>
			</p>
			<table>
				<tr>
					<td colspan="3">
						<input class="id_input_data" type="number" min="0" step="1" id="codigo" 
						name="codigo" placeholder="Codigo"
						value='<c:out value="${time.codigo}"></c:out>'>
					</td>
					<td> 
						<input type="submit" id="botao" name="botao" value="Buscar">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="text" id="nome" name="nome" placeholder="Nome"
						value='<c:out value="${time.nome}"></c:out>'>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="text" id="cidade" name="cidade" placeholder="Cidade"
						value='<c:out value="${time.cidade}"></c:out>'>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="submit" id="botao" name="botao" value="Cadastrar"> 
						<input type="submit" id="botao" name="botao" value="Alterar"> 
						<input type="submit" id="botao" name="botao" value="Excluir"> 
						<input type="submit" id="botao" name="botao" value="Listar">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<br>
	<div align="center">
		<c:if test="${not empty erro}">
			<H2><b><c:out value="${erro }"/></b></H2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty saida}">
			<H3><b><c:out value="${saida }"/></b></H3>
		</c:if>
	</div>
	<br>
	<div align="center">
		<c:if test="${not empty times }">
			<table class="table_round">
				<thead>
					<tr>
						<th>Código</th>
						<th>Nome</th>
						<th>Cidade</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="t" items="${times }">
					<tr>
						<td><c:out value="${t.codigo }"></c:out></td>
						<td><c:out value="${t.nome }"></c:out></td>
						<td><c:out value="${t.cidade }"></c:out></td>
					</tr>
				</c:forEach>	
				</tbody>
			</table>	
		</c:if>
	</div>
</body>
</html>