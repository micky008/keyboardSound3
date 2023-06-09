connect();

document.addEventListener("DOMContentLoaded", function (event) {
	document.getElementById("reco").addEventListener("click", connect);
	document.getElementById("myselect").addEventListener("change", setStorage);
});

async function connect() {
	
		let chanRaw = await chrome.storage.local.get(['chan']); 
		let chan = 1;	
		if (chanRaw['chan']){
			chan = chanRaw['chan'];
		}
		clearUl('myselect');
		let response = await fetch("http://localhost:9998/channel/");
		let channels = await response.json();
		let select = document.getElementById("myselect");
		for (let chanObj of channels) { //chan = {id:int, channel:string}
			let opt = document.createElement("option");
			opt.text = chanObj.channel;
			opt.selected = chanObj.id == chan;
			if (opt.selected) {
				readSongList(chanObj.id);
			}
			select.appendChild(opt);
		}
	
}

function setStorage(event) {
	let chan = event.target.selectedIndex+1; //OPS ou DEV		
	chrome.storage.local.set({ 'chan': chan });
	readSongList(chan);	
}



async function readSongList(channelId) {
	let url = "http://localhost:9998/sound/"+channelId;	
	let resp = await fetch(url);
	let sounds = await resp.json();
	clearUl('myTable');
	for (let sound of sounds) {
		buildLine(channelId, sound);
	}
}



function clearUl(what) {
	let ul = document.getElementById(what);
	while (ul.firstChild) {
		ul.removeChild(ul.firstChild);
	}
}

function mysend(url) {
		fetch(url, {method:"POST"}).then(()=>{});
}

function buildLine(idChannel, sound) { //sound = {id: int, name:string}
	let url = `http://localhost:9998/sse/${idChannel}/${sound['id']}`
	console.log(url)
	let table = document.getElementById('myTable');
	let tr = document.createElement("tr");
	let td1 = document.createElement("td");
	let td2 = document.createElement("td");
	let mybutton = document.createElement("button");	
	mybutton.type = 'button';
	mybutton.innerHTML = 'GO';
	mybutton.addEventListener('click', mysend.bind(null, url)); //trick to make a call
	td1.innerHTML = `${sound['name']}`;
	td2.appendChild(mybutton);
	tr.appendChild(td1);
	tr.appendChild(td2);
	table.appendChild(tr);
}