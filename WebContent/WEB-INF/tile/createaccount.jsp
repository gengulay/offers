<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2>Create Account</h2>
<sf:form method="post" id="userdetails"
	action="${pageContext.request.contextPath}/createaccount"
	modelAttribute="user">
	<table class="formtable">
		<tr>
			<td class="label">Username:</td>
			<td><sf:input class="control" name="username" path="username"
					type="text" /><br />
				<div class="error">
					<sf:errors path="username">
					</sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Email:</td>
			<td><sf:input class="control" name="email" path="email"
					type="text" /><br />
				<div class="error">
					<sf:errors path="email"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Password:</td>
			<td><sf:input id="password" class="control" name="password"
					path="password" type="password" /><br />
				<div class="error">
					<sf:errors path="password"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Confirm Password:</td>
			<td><input id="confirmPassword" class="control"
				name="confirmpassword" type="password" />
				<div id="matchedpass"></div></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" value="Create Account" type="submit"></td>
		</tr>

	</table>
</sf:form>
