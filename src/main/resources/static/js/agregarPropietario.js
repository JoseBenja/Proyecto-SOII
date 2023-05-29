// Call the dataTables jQuery plugin
$(document).ready(function() {
});

async function agregarPropietario() {

    let datos = {};
    let estado = true;

    datos.nomPropietario = document.getElementById('txtNomProp').value;
    datos.fechaNac = document.getElementById('dateFechaNac').value;
    datos.estado = estado;

    await fetch('api/agregarPropietario', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
}