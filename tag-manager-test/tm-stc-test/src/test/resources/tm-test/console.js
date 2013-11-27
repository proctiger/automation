console = {};

console.waitLogs = [];

console.log = function(msg) {
	var list = document.getElementById('logs');

	if (list) {
		var litem = document.createElement('li');
		litem.appendChild(document.createTextNode(msg));
		list.appendChild(litem);
	} else {
		console.waitLogs.push(msg);
	}
};

console.writeWaitLogs = function() {
	for ( var i = 0; i < console.waitLogs.length; i++) {
		console.log(console.waitLogs[i]);
	}
};

console.printLpConfig = function() {
	try {
		if (TM.SIGA.pages[0].config) {
			var conf = TM.SIGA.pages[0].config;
			var arr = [];

			for ( var i in conf) {
				if (typeof conf[i] != 'undefined') {
					arr.push(i + '=' + conf[i]);
				}
			}

			if (arr.length > 0) {
				var ul = document.createElement('ul');
				for ( var i = 0; i < arr.length; i++) {
					var li = document.createElement('li');
					li.appendChild(document.createTextNode(arr[i]));
					ul.appendChild(li);
				}

				var h2 = document.createElement('h2');
				h2.appendChild(document.createTextNode('Uol Cliques Config'));

				var divLp = document.createElement('div');
				divLp.setAttribute('id', 'uolCliquesConfig');
				divLp.appendChild(h2);
				divLp.appendChild(ul);

				document.body.appendChild(divLp);
			}
		}
	} catch (e) {
		return;
	}
}
