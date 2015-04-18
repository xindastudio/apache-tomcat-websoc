onmessage = function(event) {
	if ("start" == event.data) {
		start();
	}
};
/*
function out(msg) {
	postMessage(msg);
}*/

function start() {
	var url = "ws://localhost/websoc/WebSocReq";
	var soc = new WebSocket(url);
	var size = 0;
	soc.onopen = function(event) {
		out("open");
	};
	soc.onmessage = function(event) {
		try {
			size += event.data.length;
		} catch (e) {
		}
		out(size);
		soc.close();
	};
	soc.onclose = function(event) {
		out("close");
	};
	soc.onerror = function(event) {
		out("error");
	};
}