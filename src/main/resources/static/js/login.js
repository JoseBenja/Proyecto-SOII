$(document).ready(function() {
});

async function iniciarSesion() {
    let datos = {};
    datos.nomUser = document.getElementById('txtUser').value;
    datos.pass = document.getElementById('txtPassword').value;

    const request = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    const respuesta = await request.text()

    if(respuesta !== 'Fail') {
        localStorage.token = respuesta;
        localStorage.user = datos.nomUser;
        window.location.href = '../menuPrincipal.html';
    } else {
        alert("El correo o contraseña es incorrecto")
    }
}