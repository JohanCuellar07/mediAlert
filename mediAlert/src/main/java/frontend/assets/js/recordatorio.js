function cargarRecordatorios() {
    return new Promise (async (resolve) => {
        const pacienteId = document.getElementById("paciente").value;
        console.log(pacienteId);

        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let url = "http://127.0.0.1:8080/paciente_medicamento/paciente/";

         if (pacienteId) {
            url += `${pacienteId}`;
        }

        let response = await fetch(url, {
            method: "GET",
            headers: headersList
        });

        let data = await response.json();
        var tabla = document.querySelector("#recordatorios-lista tbody");
        tabla.innerHTML = "";

        // Si hay paciente seleccionado, filtrar
        let recordatoriosFiltrados = [];

        if (pacienteId) {
            recordatoriosFiltrados = data.filter(paciente_medicamento => paciente_medicamento.pacienteid.id == pacienteId);
        } else {
            recordatoriosFiltrados = data;
        }

        recordatoriosFiltrados.forEach(paciente_medicamento => {
            const fila = document.createElement("tr");
            fila.innerHTML += `
                <tr>
                    <td>${paciente_medicamento.pacienteid.nombre}</td>
                    <td>${paciente_medicamento.medicamentoid.nombre}</td>
                    <td>${paciente_medicamento.dosis}</td>
                    <td>${paciente_medicamento.hora}</td>
                    <td>${paciente_medicamento.estado}</td>
                    <td><button onclick="tomar(${paciente_medicamento.id})">Tomar</button>
                    <button onclick='suspender(${JSON.stringify(paciente_medicamento)})'>Suspender</button></td>
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
        });
    });
}

function inicializarPagina() {
    cargarPacientes().then(() => {
        cargarRecordatorios();
    });
}

function tomar(paciente_medicamento) {
    return new Promise (async (resolve) => {
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let bodyContent = JSON.stringify({
            "estado": "RECIBIDO",
        });

        let response = await fetch(`http://127.0.0.1:8080/paciente_medicamento/${id}`, {
            method: "PUT",
            body: bodyContent,
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
        cargarRecordatorios();
        alert("Medicamento tomado correctamente");
    });
}

function suspender(paciente_medicamento) {
    return new Promise (async (resolve) => {
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let bodyContent = JSON.stringify({
            "estado": "SUSPENDIDO",
            "pacienteid": {
                "id": paciente_medicamento.pacienteid.id
            },
            "medicamentoid": {
                "id": paciente_medicamento.medicamentoid.id
            },
            "dosis": paciente_medicamento.dosis,
            "hora": paciente_medicamento.hora
        });

        let response = await fetch(`http://127.0.0.1:8080/paciente_medicamento/${id}`, {
            method: "PUT",
            body: bodyContent,
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
        cargarRecordatorios();
        alert("Recordatorio suspendido correctamente");
    });
}