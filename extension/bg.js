SSEConnect();

function SSEConnect(){
	const sse = new EventSource("http://localhost:9998/sse");
	sse.addEventListener("play", async (e) => {		
		let url = "http://localhost:9998/sound/"+e.data;
		readsoundOnMessage(url);
	});
}


function readsoundOnMessage(url) {
	var audioElement = new Audio();
	audioElement.src = url;
	audioElement.load();
	audioElement.play();
}