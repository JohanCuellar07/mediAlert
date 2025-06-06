package com.sena.mediAlert.task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sena.mediAlert.DTO.BitacoraDTO;
import com.sena.mediAlert.model.EstadoRecordatorio;
import com.sena.mediAlert.model.Paciente_Medicamento;
import com.sena.mediAlert.repository.IPaciente_Medicamento;
import com.sena.mediAlert.service.BitacoraService;
import com.sena.mediAlert.service.emailService;

@Component
public class ScheduledTask {
    @Autowired
    private emailService emailService;
    @Autowired
    private IPaciente_Medicamento paciente_Medicamento;
    @Autowired
    private BitacoraService bitacoraService;

    // tiempo en milisegundo
    // 1 segundo = 1000 milisegundos

    // cron = "* * * * * *"
    // "Second Minute Hour DayOfMonth Month DayOfWeek"

    @Scheduled(fixedRate = 300000)
    public void verificarMedicamentosPendientes() {
        LocalTime ahora = LocalTime.now().withSecond(0).withNano(0);

        // Buscar todos los que están SINENVIAR y su hora ya pasó o es ahora
        List<Paciente_Medicamento> lista = paciente_Medicamento
        .findByHoraSinEnviar(ahora);

        for (Paciente_Medicamento pm : lista) {
            // Cambiar estado a PENDIENTE
            pm.setEstado(EstadoRecordatorio.PENDIENTE);
            paciente_Medicamento.save(pm);
        }

        // Buscar todos los que están en estado PENDIENTE para enviar el correo
        List<Paciente_Medicamento> pendientes = paciente_Medicamento
            .findByEstado(EstadoRecordatorio.PENDIENTE);

        for (Paciente_Medicamento pm : pendientes) {
            String nombre = pm.getPacienteid().getNombre();
            String correo = pm.getPacienteid().getCorreo();
            String nombreMedicamento = pm.getMedicamentoid().getNombre();

            String asunto = "Recordatorio de Medicamento";
            String cuerpo = "Hola " + nombre + ", recuerda tomar tu medicamento: "
                        + nombreMedicamento + " (" + pm.getDosis() + ") ahora.";

            try {
                emailService.enviarRecordatorio(correo, asunto, cuerpo);
                System.out.println("Correo enviado a " + correo);

                pm.setEstado(EstadoRecordatorio.ENVIADO);
                BitacoraDTO registro = new BitacoraDTO(
                    pm.getPacienteid(),
                    pm.getMedicamentoid(),
                    LocalDateTime.now()
                );
                bitacoraService.save(registro);
                paciente_Medicamento.save(pm);
            } catch (Exception e) {
                System.out.println("No se pudo enviar correo a " + correo + ": " + e.getMessage());
            }
        }
    }

    @Scheduled(fixedRate = 300000)
    public void verificarMedicamentosAtrasados() {
        LocalTime ahora = LocalTime.now().withSecond(0).withNano(0);

        // Buscar todos los que están en estado ENVIADO y su hora pasó o es ahora
        List<Paciente_Medicamento> enviados = paciente_Medicamento
        .findByEstado(EstadoRecordatorio.ENVIADO);

        for (Paciente_Medicamento pm : enviados) {
            LocalTime horaMedicamento = pm.getHora();
            if (horaMedicamento != null && ahora.isAfter(horaMedicamento.plusHours(1))){
                pm.setEstado(EstadoRecordatorio.ATRASADO);
                paciente_Medicamento.save(pm);
                System.out.println("El medicamento de " + pm.getPacienteid().getNombre() + " ha sido atrasado");
            }
        }

        // Buscar todos los que están en estado RECIBIDO para enviar el correo
        List<Paciente_Medicamento> atrasados = paciente_Medicamento
            .findByEstado(EstadoRecordatorio.ATRASADO);

        for (Paciente_Medicamento pm : atrasados) {
            String nombre = pm.getPacienteid().getNombre();
            String correo = pm.getPacienteid().getCorreo();
            String nombreMedicamento = pm.getMedicamentoid().getNombre();

            String asunto = "Recordatorio de Medicamento";
            String cuerpo = "Hola " + nombre + ", parece que no has confirmado la toma del medicamento: "
                + nombreMedicamento + ". Han pasado más de 60 minutos desde el recordatorio.";

            try {
                emailService.enviarRecordatorio(correo, asunto, cuerpo);
                System.out.println("Correo enviado a " + correo);
                pm.setEstado(EstadoRecordatorio.ENVIADO);
                BitacoraDTO registro = new BitacoraDTO(
                    pm.getPacienteid(),
                    pm.getMedicamentoid(),
                    LocalDateTime.now()
                );
                bitacoraService.save(registro);
                paciente_Medicamento.save(pm);
            } catch (Exception e) {
                System.out.println("No se pudo enviar correo a " + correo + ": " + e.getMessage());
            }
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void reiniciarEstado() {
        List<Paciente_Medicamento> lista = paciente_Medicamento.findAll();

        for (Paciente_Medicamento pm : lista) {
            if (pm.getEstado() != EstadoRecordatorio.SUSPENDIDO) {
                pm.setEstado(EstadoRecordatorio.SINENVIAR);
                paciente_Medicamento.save(pm);
            }
        }
        System.out.println("Todos los registros de medicamentos enviados han sido reiniciados");
    }
}
