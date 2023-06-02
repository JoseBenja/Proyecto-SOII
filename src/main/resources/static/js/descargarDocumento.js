/*function descargarDocumento() {
    let datosDescarga = {};

    datosDescarga.idDoc = document.getElementById("txtIdDocDesc").value;
    datosDescarga.fechaDoc = document.getElementById("txtFechaDesde").value;
    datosDescarga.docGuardado = "";

    const request = fetch('api/descargarDocumento', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datosDescarga),
    });
    const resultado = request.json();
    alert(resultado.idDoc);
}*/

function descargarDocumento() {
    let datosDescarga = {};

    datosDescarga.idDoc = $("#txtIdDocDesc").val();
    datosDescarga.fechaDoc = $("#txtFechaDesde").val();
    datosDescarga.docGuardado = "";

    fetch('api/descargarDocumento', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datosDescarga)
    })
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error('Error en la solicitud: ' + response.status);
            }
        })
        .then(resultado => {
            const regex = /"docGuardado":"(.*?)"/;
            const match = regex.exec(resultado);
            const docGuardado = match && match[1] ? match[1] : null;

            if (docGuardado) {
                const fileUrl = docGuardado.replace('/home/app-benja/file-system-soii', 'http://192.168.0.16');
                alert(fileUrl);
                window.open(fileUrl, '_blank');
            } else {
                alert('No se encontró el archivo para descargar.');
            }
        })
        .catch(error => {
            console.error('Error en la solicitud:', error);
        });
}


/*function descargarDocumento() {
    let datosDescarga = {};

    datosDescarga.idDoc = $("#txtIdDocDesc").val();
    datosDescarga.fechaDoc = $("#txtFechaDesde").val();
    datosDescarga.docGuardado = "";

    $.ajax({
        url: 'api/descargarDocumento',
        type: 'POST',
        dataType: 'text', // Cambio de 'json' a 'text' para recibir una respuesta de texto
        contentType: 'application/json',
        data: JSON.stringify(datosDescarga),
        success: function(resultado) {
        alert(resultado)
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });


}*/

function downloadFile(fileUrl) {
    alert(fileUrl);
    // Crea un elemento <a> temporal
    var link = document.createElement('a');
    link.href = fileUrl;
    link.download = fileUrl.split('/').pop(); // Obtiene el nombre del archivo de la URL

    // Simula el clic en el enlace para iniciar la descarga
    link.dispatchEvent(new MouseEvent('click'));
}
