// Call the dataTables jQuery plugin
$(document).ready(function() {
    verPropietario();
});

async function verPropietario() {
    let datos = {};

    datos.estado = true;

    const request = await fetch('api/verPropietario', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    const resultadoBusqueda = await request.json()

    let resultadoBusquedaHtml = '';

    for (let resultado of resultadoBusqueda) {
        let resultadoHtml = `<option value="` + resultado.idPropietario + `">` + resultado.nomPropietario + `</option>`;

        resultadoBusquedaHtml += resultadoHtml;
    }
        resultadoBusquedaHtml = `<option selected>Seleccionar...</option>` + resultadoBusquedaHtml;
        document.querySelector("#SelectTable").innerHTML = resultadoBusquedaHtml;
}
