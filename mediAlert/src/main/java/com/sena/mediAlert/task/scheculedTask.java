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
    private emailService email;
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
            System.out.println("El paciente " + pm.getPacienteid().getNombre() +
                " debe tomar " + pm.getMedicamentoid().getNombre() +
                " (" + pm.getDosis() + ") ahora.");
        }
    }
}
