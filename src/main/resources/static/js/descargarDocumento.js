$(document).ready(function() {
    listaPropietario();
});

async function descargarDocumento() {
    let datosDescarga = {};

    datosDescarga.idDoc = document.getElementById("txtIdDocDesc").value;
    datosDescarga.propietario = document.getElementById("SelectTableDescarga").value;
    datosDescarga.fechaDoc = document.getElementById("txtFechaDesde").value;

    try {
        const request3 = await fetch('api/descargarDocumento', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datosDescarga)
    });
        const resultado2 = await request3.json();
        downloadFile(resultado2.docGuardado,resultado2.idDoc);
    } catch (error) {
        //Capturar y manejar cualquier error ocurrido durante la solicitud o el procesamiento
        console.error('Error es:', error);
    }
}

function downloadFile(fileUrl,fileName) {

    fetch(fileUrl)
        .then(response => response.blob())
        .then(blob => {
            // Crea un enlace temporal
            const url = window.URL.createObjectURL(blob);

            // Crea un elemento <a> para descargar el archivo
            const link = document.createElement('a');
            link.href = url;
            link.download = fileName + ".pdf";

            // Simula el clic en el enlace para iniciar la descarga
            link.click();

            // Libera el objeto URL
            window.URL.revokeObjectURL(url);
        })
        .catch(error => {
            console.error('Error al descargar el archivo:', error);
        });
}

async function listaPropietario() {
    let datosPro = {};

    datosPro.estado = true;

    const request2 = await fetch('api/verPropietario', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datosPro)
    });
    const resultadoBusqueda = await request2.json()

    let resultadoBusquedaHtml = '';

    for (let resultado of resultadoBusqueda) {
        let resultadoHtml = `<option value="` + resultado.idPropietario + `">` + resultado.nomPropietario + `</option>`;

        resultadoBusquedaHtml += resultadoHtml;
    }
    resultadoBusquedaHtml = `<option selected>Seleccionar...</option>` + resultadoBusquedaHtml;
    document.querySelector("#SelectTableDescarga").innerHTML = resultadoBusquedaHtml;
}

