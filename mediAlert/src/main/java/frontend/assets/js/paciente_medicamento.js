function registrarPacienteMedicamento() {
    return new Promise (async (resolve) => {
        const paciente = document.getElementById("paciente").value;
        const medicamento = document.getElementById("medicamento").value;
        const dosis = document.getElementById("dosis").value;
        const hora = document.getElementById("hora").value;

        // Validaciones
        if (paciente === "" || medicamento === "" || dosis === "" || hora === "") {
            alert("Todos los campos son obligatorios");
            return;
        }

        if (dosis.length > 100) {
            alert("La dosis no puede tener más de 100 caracteres");
            return;
        }

        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let bodyContent = JSON.stringify({
            "id": 0,
            "paciente": { "id": parseInt(paciente) },
            "medicamento": { "id": parseInt(medicamento) },
            "dosis": dosis,
            "hora": hora
        });

        let response = await fetch("http://127.0.0.1:8080/paciente_medicamento/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
        // Limpiar campos de formulario
        document.getElementById("paciente").value = "";
        document.getElementById("medicamento").value = "";
        document.getElementById("dosis").value = "";
        document.getElementById("hora").value = "";
        cargarPacientesMedicamentos();
        alert("El registro se ha realizado correctamente");
    });
}

function cargarPacientesMedicamentos() {
    return new Promise (async (resolve) => {
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let response = await fetch("http://127.0.0.1:8080/paciente_medicamento/", {
            method: "GET",
            headers: headersList
        });

        let data = await response.json();
        var tabla = document.querySelector("#pacientes_medicamentos-lista tbody");
        tabla.innerHTML = "";
        console.log("Respuesta del servidor:", data);
        data.forEach(paciente_medicamento => {
            const fila = document.createElement("tr");
            fila.innerHTML += `
                <tr>
                    <td>${paciente_medicamento.pacienteid.nombre}</td>
                    <td>${paciente_medicamento.medicamentoid.nombre}</td>
                    <td>${paciente_medicamento.dosis}</td>
                    <td>${paciente_medicamento.hora}</td>
                    <td>${paciente_medicamento.estado}</td>
                </tr>
            `;
            tabla.appendChild(fila);
        });
    });
}

function cargarPacientes() {
    return new Promise (async (resolve) => {
        const selectPaciente  = document.getElementById("paciente")
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let response = await fetch("http://127.0.0.1:8080/paciente/", {
            method: "GET",
            headers: headersList
        });

        let data = await response.json();
        selectPaciente .innerHTML = "<option value=''>Selecciona un paciente</option>";
        data.forEach(paciente => {
            const option = document.createElement("option");
            option.value = paciente.id;
            option.innerHTML = paciente.nombre;
            selectPaciente.appendChild(option);
            console.log(paciente);
        });
    });
}

function cargarMedicamentos() {
    return new Promise (async (resolve) => {
        const selectMedicamento  = document.getElementById("medicamento")
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
        selectMedicamento .innerHTML = "<option value=''>Selecciona un medicamento</option>";
        data.forEach(medicamento => {
            const option = document.createElement("option");
            option.value = medicamento.id;
            option.innerHTML = medicamento.nombre;
            selectMedicamento.appendChild(option);
        });
    });
}

function inicializarPagina() {
    cargarPacientes();
    cargarMedicamentos();
    cargarPacientesMedicamentos();
}