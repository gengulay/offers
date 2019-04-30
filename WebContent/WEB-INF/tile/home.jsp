<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<table class="offers">

	<c:forEach var="offer" items="${offers}">
		<tr class="offerrow">
			<td class="name"><c:out value="${offer.user.name}"></c:out></td>
			<td class="contact"><a
				href="<c:url value='/message?uid=${offer.user.username}'/>">
					Contact</a></td>
			<td class="text"><c:out value="${offer.text}"></c:out></td>
		</tr>
	</c:forEach>
</table>

<br />

<script type="text/javascript">
	function updateMessageLink(data) {
		$("#numberMessages").text(data.number);

	}

	function onLoad() {
		updatePage();
		window.setInterval(updatePage, 5000);
	}

	function updatePage() {

		$.getJSON("<c:url value="/getmessages"></c:url>", updateMessageLink)
	}

	$(document).ready(onLoad);
</script>