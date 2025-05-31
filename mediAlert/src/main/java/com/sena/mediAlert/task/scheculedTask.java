package com.sena.mediAlert.task;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sena.mediAlert.model.Paciente_Medicamento;
import com.sena.mediAlert.repository.IPaciente_Medicamento;
import com.sena.mediAlert.service.emailService;

@Component
public class scheculedTask {
    @Autowired
    private emailService emailService;
    @Autowired
    private  IPaciente_Medicamento paciente_Medicamento;

    // tiempo en milisegundo
    // 1 segundo = 1000 milisegundos

    // cron = "* * * * * *"
    // "Second Minute Hour DayOfMonth Month DayOfWeek"

    @Scheduled(fixedRate = 300000)
    public void verificarMedicamentosPendientes() {
        LocalTime ahora = LocalTime.now().withSecond(0).withNano(0);
        List<Paciente_Medicamento> lista = paciente_Medicamento.findByHorario(ahora);

        for (Paciente_Medicamento pm : lista) {
            String nombre = pm.getPacienteid().getNombre();
            String correo = pm.getPacienteid().getCorreo();
            String nombreMedicamento = pm.getMedicamentoid().getNombre();

            String asunto = "Recordatorio de Medicamento";
            String cuerpo = "Hola " + nombre + ", recuerda tomar tu medicamento: "
                          + nombreMedicamento + " (" + pm.getDosis() + ") ahora.";

            try {
                emailService.enviarRecordatorio(correo, asunto, cuerpo);
                System.out.println("Recordatorio de Medicamento" +
                "Hola " + nombre + ", recuerda tomar tu medicamento: " +
                nombreMedicamento + " (" + pm.getDosis() + ") ahora.");
            } catch (Exception e) {
                System.out.println("No se pudo enviar correo a " + correo + ": " + e.getMessage());
            }
        }
    }
}
