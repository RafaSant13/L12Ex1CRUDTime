<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">
<title>Jogador</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" />
	</div>
	<br>
	<div align="center" class="container">
		<form action="jogador" method="post">
			<p class="title">
				<b>Jogador</b>
			</p>
			<table>
				<tr>
					<td colspan="3">
						<input class="id_input_data" type="number" min="0" step="1" id="codigo" name="codigo" placeholder="Codigo"
						value='<c:out value="${jogador.id }"></c:out>'>
					</td>
					<td> 
						<input type="submit" id="botao" name="botao" value="Buscar">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="text" id="nome" name="nome" placeholder="Nome"
						value='<c:out value="${jogador.nome }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td colspan ="1"><b>Data de Nascimento:</b></td>
					<td colspan="3">
						<input class="id_input_data" type="date" id="dataNasc" name="dataNasc" placeholder="Data de Nascimento" style="width: 150px"
						value='<c:out value="${jogador.dataNasc }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="number" min="0" step="0.1" id="altura" name="altura" placeholder="Altura do jogador (m)"
						value='<c:out value="${jogador.altura }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="number" min="0" step="0.1" id="peso" name="peso" placeholder="Peso do jogador (kg)"
						value='<c:out value="${jogador.peso }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<select class="input_data" id="time" name="time" style="width: 350px">
							<option value="0">Código do Time</option>
							<c:forEach var="t" items="${times }">
								<c:if test="${(empty jogador) || (t.codigo ne jogador.time.codigo) }">
									<option value="${t.codigo }">
										<c:out value="${t }"/>
									</option>
								</c:if>
								<c:if test="${t.codigo eq jogador.time.codigo }">
									<option value="${t.codigo }" selected="selected">
										<c:out value="${t }"/>
									</option>
								</c:if>
							</c:forEach>
						</select>	
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
	<c:if test="${not empty jogadores }">
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Data de Nascimento</th>
					<th>Altura</th>
					<th>Peso</th>
					<th>Time</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="j" items="${jogadores }">
				<tr>
					<td><c:out value="${j.id }"></c:out></td>
					<td><c:out value="${j.nome }"></c:out></td>
					<td><c:out value="${j.dataNasc }"></c:out></td>
					<td><c:out value="${j.altura }"></c:out></td>
					<td><c:out value="${j.peso }"></c:out></td>
					<td><c:out value="${j.time }"></c:out></td>
				</tr>
			</c:forEach>	
			</tbody>
		</table>
		
	</c:if>
</body>
</html>