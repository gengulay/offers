<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<table class="offers">
	<tr>
		<td>Name</td>
		<td>Email</td>
		<td>Offer</td>
	</tr>
	<c:forEach var="offer" items="${offers}">
		<tr>
			<td><c:out value="${offer.user.name}"></c:out></td>
			<td><a
				href="<c:url value='/message?uid=${offer.user.username}'/>"> Contact</a></td>
			<td><c:out value="${offer.text}"></c:out></td>
		</tr>
	</c:forEach>
</table>

<br />

<c:choose>
	<c:when test="${hasOffer}">

		<a href="${pageContext.request.contextPath}/postoffer">Edit or
			delete your Offer</a>
	</c:when>
	<c:otherwise>
		<p>
			<a href="${pageContext.request.contextPath}/postoffer">Post a new
				Offer</a>
		</p>
	</c:otherwise>
</c:choose>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p>
		<a href="<c:url value="/admin" />">ADMIN</a>
	</p>
</sec:authorize>