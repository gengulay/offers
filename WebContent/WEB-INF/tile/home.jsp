<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<p>
	<a href="${pageContext.request.contextPath}/offers">Show current
		Offers.</a>
</p>
<p>
	<a href="${pageContext.request.contextPath}/postoffer">Post an
		Offer</a>
</p>


<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p>
		<a href="<c:url value="/admin" />">ADMIN</a>
	</p>
</sec:authorize>

