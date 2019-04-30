<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="messages"></div>
<script type="text/javascript">
	var timer;

	function success(data) {
		$("#form" + data.target).toggle();
		$("#alertspan" + data.target).text("message sent");
		startTimer();
	}

	function error(data) {
		alert("error sending message");
	}

	function showReply(i) {
		stopTimer();
		$("#form" + i).toggle();
	}

	function sendMessage(i, name, email) {
		var text = $("#textbox" + i).val();

		$.ajax({
			"type" : 'POST',
			"url" : '<c:url value="/sendmessage"></c:url>',
			"data" : JSON.stringify({
				"text" : text,
				"name" : name,
				"target" : i
			}),
			"success" : success,
			"error" : error,
			"contentType" : "application/json",
			"dataType" : "json"
		});
	}

	function updateMessages(data) {

		$("div#messages").html("");

		for (var i = 0; i < data.messages.length; i++) {
			var message = data.messages[i];

			var messageDiv = document.createElement("div");
			messageDiv.setAttribute("class", "message");

			var subjectSpan = document.createElement("span");
			subjectSpan.setAttribute("class", "subjects");
			subjectSpan.appendChild(document.createTextNode(message.subject));

			var contentSpan = document.createElement("span");
			contentSpan.setAttribute("class", "messageBody");
			contentSpan.appendChild(document.createTextNode(message.content));

			var nameSpan = document.createElement("span");
			nameSpan.setAttribute("class", "name");
			nameSpan.appendChild(document.createTextNode(message.name + " ("));

			var link = document.createElement("a");
			link.setAttribute("class", "replylink");
			link.setAttribute("href", "#");
			link.setAttribute("onClick", "showReply(" + i + ")");
			link.appendChild(document.createTextNode(message.email));
			nameSpan.appendChild(link);
			nameSpan.appendChild(document.createTextNode(")"));

			var alertSpan = document.createElement("span");
			alertSpan.setAttribute("class", "alertspan");
			alertSpan.setAttribute("id", "alertspan" + i);
			//alertSpan.appendChild(document.createTextNode("message Sent"));

			var replyForm = document.createElement("form");
			replyForm.setAttribute("class", "replyform");
			replyForm.setAttribute("id", "form" + i);

			var textarea = document.createElement("textarea");
			textarea.setAttribute("class", "replyarea");
			textarea.setAttribute("id", "textbox" + i);
			replyForm.appendChild(textarea);

			var replyButton = document.createElement("input");
			replyButton.setAttribute("class", "replybutton");
			replyButton.setAttribute("type", "button");
			replyButton.setAttribute("value", "Reply");
			replyButton.onclick = function(j, name, email) {
				return function() {
					sendMessage(j, name, email);
				}
			}(i, message.name, message.email);

			replyForm.appendChild(replyButton);

			messageDiv.appendChild(subjectSpan)
			messageDiv.appendChild(contentSpan)
			messageDiv.appendChild(nameSpan)
			messageDiv.appendChild(alertSpan)
			messageDiv.appendChild(replyForm)

			$("div#messages").append(messageDiv);
		}

	}

	function onLoad() {
		updatePage();
		startTimer();

	}

	function stopTimer() {
		window.clearInterval(timer);

	}

	function startTimer() {
		timer = window.setInterval(updatePage, 10000);

	}

	function updatePage() {

		$.getJSON("<c:url value="/getmessages"></c:url>", updateMessages)
	}

	$(document).ready(onLoad);
</script>