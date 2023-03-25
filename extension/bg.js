SSEConnect();

function SSEConnect(){
	const sse = new EventSource("http://localhost:9998/sse");
	sse.addEventListener("play", async (e) => {		
		let chanRaw = await chrome.storage.local.get(['chan']); 
		let chan = 1;	
		if (chanRaw['chan']){
			chan = chanRaw['chan'];
		}
		let split = e.data.split(":");
		if (split[0] != chan) {
			return;
		}
		let url = "http://localhost:9998/sound/"+chan+"/"+split[1];
		readsoundOnMessage(url);
	});
}


function readsoundOnMessage(url) {
	var audioElement = new Audio();
	audioElement.src = url;
	audioElement.load();
	audioElement.play();
}