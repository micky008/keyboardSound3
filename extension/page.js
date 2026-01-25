connect();

function connect(){
	remplirMaps().then (()=>{
		clearUl('myselect');
		putChannelInSelect();
		getChanInStorage().then(chanId => {
			remplirSound(chanId);
			document.getElementById("myselect").addEventListener("change", setStorage);
	});
});
}


document.addEventListener("DOMContentLoaded", function (event) {
	document.getElementById("reco").addEventListener("click", connect);	
});

function remplirSound(chanId, forceSelect = true) {
	let select = document.getElementById("myselect");
	let pos = selectGoodOption(chanId);
	let opt = select.item(pos);
	if (forceSelect) {
		opt.selected = true;
	}
	clearUl('myTable');
	let sounds = mapSound.get(chanId);
	for (let sound of sounds){
		buildLine(sound);
	}
}

function selectGoodOption(chanId) {
	let i = 0;
	for (let k of mapChannel.keys()){
		if (k==chanId) {
			return i;
		}
		i++;
	}
	return -1;	
}

var mapSound = undefined;
var mapChannel= undefined;


async function remplirMaps(){
	let response = await fetch("http://localhost:9998/channel/");
	let objChannels = await response.json();
	mapChannel = new Map(Object.entries(objChannels));
	response = await fetch("http://localhost:9998/sound/");
	let objSounds = await response.json();
	mapSound = new Map(Object.entries(objSounds));	
	return Promise.resolve(undefined);
}

async function getChanInStorage() { //Promise<string> => c1
	let chanRaw = await chrome.storage.local.get(['chan']); 
	let chanId = 'c'+1;
	if (chanRaw['chan']){
		chanId = chanRaw['chan'];
	}
	return Promise.resolve(chanId);
}


function putChannelInSelect() {		
		clearUl('myselect');
		let select = document.getElementById("myselect");
		
		mapChannel.forEach( (v,k) => {
			let opt = document.createElement("option");
			opt.text = v.name;
			opt.id = k;
			opt.selected = false;
			//opt.selected = v.id == chanId;
			select.appendChild(opt);
		});
}


function setStorage(event) {
	let chan = 'c'+(event.target.selectedIndex+1);
	chrome.storage.local.set({ 'chan': chan });
	remplirSound(chan,false);
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

function buildLine(sound) { //sound = {id: int, name:string} id=s1
	let url = `http://localhost:9998/sse/${sound['id']}`;
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