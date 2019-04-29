<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function onDeleteClick(event) {

		var doDelete = confirm("Are you sure you want to delete this offer?");

		if (doDelete == false) {
			event.preventDefault();
		}
	}

	function onReady() {
		$('#delete').click(onDeleteClick);
	}

	$(document).ready(onReady);
</script>

<sf:form method="post"
	action="${pageContext.request.contextPath}/postoffer"
	modelAttribute="offer">
	<sf:input path="id" name="id" type="hidden" />
	<table class="formtable">
		<tr>
			<td class="label">Your Offer:</td>
			<td><sf:textarea class="control" name="text" path="text"
					rows="10" cols="10" /><br /> <sf:errors path="text"
					cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" name="Save Offer" type="submit"
				value="Save Offer"></td>
		</tr>

		<c:if test="${offer.id != 0 }">
			<tr>
				<td class="label"></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="label"></td>
				<td><input id="delete" class="delete control" name="delete"
					type="submit" value="Delete this Offer"></td>
			</tr>
		</c:if>

	</table>
</sf:form>
