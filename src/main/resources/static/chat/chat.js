var ws;
function connect() {
	ws = new WebSocket('ws://186.189.134.19:8080/chat');
	if (ws != null) {
		console.log("WebSocket connection success!");
	}
	
	ws.onmessage = function(data) {
		addMessage(data);
	}
	
}

function addMessage(message){
	let mesNode = `<div class="message">
          				<p class="message-text">${message.data}</p>
				    </div>`;
	
	$(".chat-messages").append(mesNode);
	$(".chat-messages").scrollTop($(".chat-messages")[0].scrollHeight);
}

function disconnect() {
	if (ws != null) {
		ws.close();
	}
}

function sendData() {
	let message = $("#message").val();
	
	if(message !== ''){
		var data = JSON.stringify({
			'message' : $("#message").val()
		})
		ws.send(data);
		$("#message").val("");	
	}
}

$(function() {
	connect();
	$("#sendBtn").on("click", function(e){
		e.preventDefault();
		sendData();
	});
});