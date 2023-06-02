
function descargarDocumento() {
    let idDoc = $("#txtIdDocDesc").val();
    let fechaDoc = $("#txtFechaDesde").val();
    let fechaDocHasta = $("#txtFechaHasta").val();
    let propietario = $("#txtPropietario").val();

    if (idDoc && fechaDoc && fechaDocHasta && propietario) {
        let datosDescarga = {
            idDoc: idDoc,
            fechaDoc: fechaDoc,
            docGuardado: ""
        };

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
                    const fileUrl = docGuardado.replace('http://localhost:8080/root/file-system-soii/', 'http://192.168.0.16');
                    window.open(fileUrl, '_blank');
                } else {
                    alert('No se encontró el archivo para descargar.');
                }
            })
            .catch(error => {
                console.error('Error en la solicitud:', error);
            });
    } else {
        alert('Informacion incompleta');
    }
}
