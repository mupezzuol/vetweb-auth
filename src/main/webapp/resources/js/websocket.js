var endpoint = new WebSocket("ws://localhost:8080/vetweb/endpoint/auth");

endpoint.onopen = function () {
	console.log('CONEXÃO ABERTA JUNTO AO SERVIDOR P/ COMUNICAÇÃO');
}

endpoint.onmessage = function (message) {
	var mappings = JSON.parse(message.data);
}

endpoint.onerror = function (error) {
	console.log('ERRO NA COMUNICAÇÃO COM O SERVIDOR: ' + error);
}

endpoint.onclose = function (close) {
	console.log('CONEXÃO COM O SERVIDOR FINALIZADA: ' + close.code);
}