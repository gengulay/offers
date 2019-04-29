<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2>Send Message</h2>
<sf:form method="post" modelAttribute="message">

	<input name="_flowExecutionKey" value="${flowExecutionKey}"
		type="hidden" />
	<input name="_eventId" value="send" type="hidden" />
	<table class="formtable">
		<tr>
			<td class="label">Your Name:</td>
			<td><sf:input class="control" name="name" path="name"
					type="text" value="${fromName}" /><br />
				<div class="error">
					<sf:errors path="name">
					</sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Your Email:</td>
			<td><sf:input class="control" name="email" path="email"
					type="text" value="${fromEmail}" /><br />
				<div class="error">
					<sf:errors path="email">
					</sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Subject:</td>
			<td><sf:input class="control" name="subject" path="subject"
					type="text" /><br />
				<div class="error">
					<sf:errors path="subject">
					</sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Your Message:</td>
			<td><sf:textarea class="control" name="content" path="content"
					type="text" /><br />
				<div class="error">
					<sf:errors path="content">
					</sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" value="Send Message" type="submit"></td>
		</tr>
	</table>
</sf:form>
