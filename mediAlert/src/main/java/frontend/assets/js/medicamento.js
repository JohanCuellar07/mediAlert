function registrarMedicamento() {
    return new Promise (async (resolve) => {
        const nombre = document.getElementById("nombre").value;

        // Validaciones
        if (nombre === "") {
            alert("El nombre del medicamento no puede estar vacío");
            return;
        }

        if (nombre.length > 200) {
            alert("El nombre del medicamento no puede tener más de 200 caracteres");
            return;
        }

        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let bodyContent = JSON.stringify({
            "id": 0,
            "nombre": nombre
        });

        let response = await fetch("http://127.0.0.1:8080/medicamento/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });

        let data = await response.json();
        console.log(data);
        // Limpiar campos de formulario
        document.getElementById("nombre").value = "";
        cargarMedicamentos();
        alert("El medicamento se ha registrado correctamente");
    });
}

function cargarMedicamentos() {
    return new Promise (async (resolve) => {
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let response = await fetch("http://127.0.0.1:8080/medicamento/", {
            method: "GET",
            headers: headersList
        });

        let data = await response.json();
        var tabla = document.querySelector("#medicamentos-lista tbody");
        tabla.innerHTML = "";
        data.forEach(medicamento => {
            const fila = document.createElement("tr");
            fila.innerHTML += `
                <tr>
                    <td>${medicamento.nombre}</td>
                </tr>
            `;
            tabla.appendChild(fila);
        });
    });
}