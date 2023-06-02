
async function agregarPropietario() {
    let datos = {};
    let estado = true;

    const nomPropietario = document.getElementById('txtNomProp').value;
    const fechaNac = document.getElementById('dateFechaNac').value;

    if (!nomPropietario || !fechaNac) {
        alert('Informacion incompleta');
        return;
    }

    datos.nomPropietario = nomPropietario;
    datos.fechaNac = fechaNac;
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
