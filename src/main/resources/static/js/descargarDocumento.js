$(document).ready(function() {
    listaPropietario();
});

async function descargarDocumento() {
    let datosDescarga = {};

    datosDescarga.idDoc = document.getElementById("txtIdDocDesc").value;
    datosDescarga.propietario = document.getElementById("SelectTableDescarga").value;
    datosDescarga.fechaDoc = document.getElementById("txtFechaDesde").value;

    const request = await fetch('api/descargarDocumento', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datosDescarga)
    });
    const resultado = await request.json()

    alert(resultado.docGuardado);

    buscarDoc(resultado.docGuardado);
}

function buscarDoc (path){
    fetch(path)
        .then(response => {
            if (response.ok) {
                // El archivo se encontró correctamente
                return response.blob();
            } else {
                throw new Error('Error al buscar el archivo');
            }
        })
        .then(blob => {
            // Crear un enlace para descargar el archivo
            const downloadLink = document.createElement('a');
            downloadLink.href = URL.createObjectURL(blob);
            downloadLink.download = 'file.pdf'; // Nombre de archivo para la descarga
            downloadLink.click();
        })
        .catch(error => {
            console.error(error);
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

