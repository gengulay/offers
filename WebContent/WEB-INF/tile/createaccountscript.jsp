<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function onLoad() {
		$("#password").keyup(checkPassword);
		$("#confirmPassword").keyup(checkPassword);

		$("#userdetails").submit(canSubmit);
	}

	function canSubmit() {

		var password = $("#password").val();
		var confirmPassword = $("#confirmPassword").val();

		if (password != confirmPassword) {
			alert("<fmt:message key='UnmatchedPasswords.user.password' />");
			return false;
		} else {
			return true;
		}
	}

	function checkPassword() {

		var password = $("#password").val();
		var confirmPassword = $("#confirmPassword").val();

		if (password.length > 3 || confirmPassword.length > 3) {
			if (password == confirmPassword) {
				$("#matchedpass").text(
						"<fmt:message key='MatchedPasswords.user.password' />");
				$("#matchedpass").addClass("valid")
				$("#matchedpass").removeClass("error")
			} else {
				$("#matchedpass")
						.text(
								"<fmt:message key='UnmatchedPasswords.user.password' />");
				$("#matchedpass").addClass("error")
				$("#matchedpass").removeClass("valid")
			}

		}

	}

	$(document).ready(onLoad);
</script>