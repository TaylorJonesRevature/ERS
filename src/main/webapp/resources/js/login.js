let form = document.getElementById('login').addEventListener('submit', login);

async function login(e) {

	e.preventDefault();

	let username = document.getElementById("username").value;
	let password = document.getElementById("password").value;


	let user = {
		username,
		password
	}

	console.log(username);
	console.log(password);

	try {
		let req = await fetch('http://localhost:8080/ERS/api/login', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(user)
		});
		let res = await req.json();
		if (res.roleID == 1) {
			location.href = '../html/home.html';
		} else {
			location.href = '../html/managerhome.html';
		}
	} catch (e) {
		alert('Username or password was incorrect!');
		return;
	}

}