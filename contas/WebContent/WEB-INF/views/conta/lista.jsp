<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>.: Contas Cadastradas :.</title>
	<script src="resources/js/jquery.js"></script>
	<script type="text/javascript">

		function pagarConta(id) {
			$.post("pagarConta?id=" + id, pagarContaSucesso);
			
		}
		function removerConta(id) {
			$.post("removerConta?id=" + id, removerContaSucesso);
		}
		function pagarContaSucesso(id) {
			alert("Conta paga com sucesso!");
		}
		function removerContaSucesso() {
			alert("Conta removida!");
		}
	</script>
</head>
<body>

	<table>
	
		<tr>
			<th>Código</th>
			<th>Descrição</th>
			<th>Valor</th>
			<th>Tipo</th>
			<th>Paga?</th>
			<th>Data de Pagamento</th>
			<th>Ações</th>
		</tr>
		<c:forEach items="${contas}" var="conta">
			<tr>
				<td>${conta.id}</td>
				<td>${conta.descricao}</td>
				<td>${conta.valor}</td>
				<td>${conta.tipo}</td>
				<td>
					<c:if test="${conta.paga}">
					Sim
					</c:if>
					<c:if test="${!conta.paga}">
					Não
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${conta.dataPagamento.time}" pattern="dd/MM/yyyy" />
				</td>
				<td id="">
					<a href="#" onclick="removerConta(${conta.id})">Deletar</a>
					<c:if test="${conta.paga eq false}">
						 | 
						<a href="#" onclick="pagarConta(${conta.id})">Pagar</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		
	</table>

</body>
</html>