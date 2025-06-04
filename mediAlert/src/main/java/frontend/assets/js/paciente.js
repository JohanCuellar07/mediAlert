function registrarPaciente() {
    return new Promise (async (resolve) => {
        const nombre = document.getElementById("nombre").value;
        const fechaNacimiento = document.getElementById("fecha_nacimiento").value;
        const correo = document.getElementById("correo").value;

        // Validaciones
        if (nombre === "" || fechaNacimiento === "" || correo === "") {
            alert("Todos los campos son obligatorios");
            return;
        }

        if (nombre.length > 200) {
            alert("El nombre del paciente no puede tener más de 200 caracteres");
            return;
        }

        const today = new Date().toISOString().split('T')[0]; // Formato yyyy-mm-dd

        if (fechaNacimiento > today) {
            alert("La fecha de nacimiento no puede ser posterior a la fecha actual");
            return;
        }

        if (!correo.includes("@")) {
            alert("El correo no es válido");
            return;
        }

        if (correo.length > 255) {
            alert("El correo no puede tener más de 255 caracteres");
            return;
        }

        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let bodyContent = JSON.stringify({
            "id": 0,
            "nombre": nombre,
            "fecha_nacimiento": fechaNacimiento,
            "correo": correo
        });

        let response = await fetch("http://127.0.0.1:8080/paciente/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
        // Limpiar campos de formulario
        document.getElementById("nombre").value = "";
        document.getElementById("fecha_nacimiento").value = "";
        document.getElementById("correo").value = "";
        cargarPacientes();
        alert("El paciente se ha registrado correctamente");
    });
}

function cargarPacientes() {
    return new Promise (async (resolve) => {
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
        var tabla = document.querySelector("#pacientes-lista tbody");
        tabla.innerHTML = "";
        data.forEach(paciente => {
            const fila = document.createElement("tr");
            fila.innerHTML += `
                <tr>
                    <td>${paciente.nombre}</td>
                    <td>${paciente.fecha_nacimiento}</td>
                    <td>${paciente.correo}</td>
                </tr>
            `;
            tabla.appendChild(fila);
        });
    });
}