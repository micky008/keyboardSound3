SSEConnect();

function SSEConnect(){
	const sse = new EventSource("http://localhost:9998/sse");
	sse.addEventListener("play", async (e) => {		
		let chanRaw = await chrome.storage.local.get(['chan']); 
		let {key} = chanRaw;
		if (!key){
			key = 1;
		}
		let split = e.data.split(":");
		if (split[0] != key) {
			return;
		}
		let url = "http://localhost:9998/sound/"+key+"/"+split[1];
		readsoundOnMessage(url);
	});
}


function readsoundOnMessage(url) {
	var audioElement = new Audio();
	audioElement.src = url;
	audioElement.load();
	audioElement.play();
}