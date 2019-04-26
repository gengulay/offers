<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<sf:form method="post"
	action="${pageContext.request.contextPath}/postoffer"
	modelAttribute="offer">
	<table class="formtable">
		<tr>
			<td class="label">Name:</td>
			<td><sf:input class="control" name="name" path="name"
					type="text" /><br /> <sf:errors path="name" cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<td class="label">Email:</td>
			<td><sf:input class="control" name="email" path="email"
					type="text" /><br /> <sf:errors path="email" cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<td class="label">Your Offer:</td>
			<td><sf:textarea class="control" name="text" path="text"
					rows="10" cols="10" /><br /> <sf:errors path="text"
					cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" name="Post Offer" type="submit"></td>
		</tr>

	</table>
</sf:form>
