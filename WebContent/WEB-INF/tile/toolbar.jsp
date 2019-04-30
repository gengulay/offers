<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:choose>
	<c:when test="${hasOffer}">

		<a href="${pageContext.request.contextPath}/postoffer">Edit or
			delete your Offer</a>
	</c:when>
	<c:otherwise>

		<a href="${pageContext.request.contextPath}/postoffer">Post a new
			Offer</a>

	</c:otherwise>
</c:choose>

&nbsp;

<sec:authorize access="hasRole('ROLE_ADMIN')">

	<a href="<c:url value="/admin" />">ADMIN</a>

</sec:authorize>
&nbsp;

<sec:authorize access="isAuthenticated()">
	<a href="<c:url value="/messages" />">Messages(<span
		id="numberMessages">0</span>)
	</a>
</sec:authorize>