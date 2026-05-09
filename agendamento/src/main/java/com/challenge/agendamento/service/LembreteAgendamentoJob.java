package com.challenge.agendamento.service;

import com.challenge.agendamento.model.Agendamento;
import com.challenge.agendamento.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LembreteAgendamentoJob {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 8 * * *")
    public void enviarLembretesDeAmanha() {
        System.out.println("Iniciando rotina de envio de lembretes...");

        LocalDate amanha = LocalDate.now().plusDays(1);
        LocalDateTime inicioDoDia = amanha.atStartOfDay();
        LocalDateTime fimDoDia = amanha.atTime(LocalTime.MAX);

        List<Agendamento> agendamentosDeAmanha = agendamentoRepository
                .findByDataInicioBetween(inicioDoDia, fimDoDia);

        DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("HH:mm");

        for (Agendamento agendamento : agendamentosDeAmanha) {
            String emailPaciente = agendamento.getPaciente().getEmail();

            if (emailPaciente != null && !emailPaciente.isEmpty()) {
                String nomePaciente = agendamento.getPaciente().getNome();
                String horaConsulta = agendamento.getDataInicio().format(formatadorHora);
                String medico = agendamento.getMedico().getNome();

                String assunto = "Lembrete: Sua consulta é amanhã!";
                String corpoEmail = String.format(
                        "Olá %s,\n\nEste é um lembrete automático de que você tem uma consulta marcada conosco amanhã às %s, com o(a) %s.\n\nPor favor, chegue com 15 minutos de antecedência.\n\nAtenciosamente,\nEquipe da Clínica",
                        nomePaciente, horaConsulta, medico
                );

                emailService.enviarEmailLembrete(emailPaciente, assunto, corpoEmail);
            }
        }
    }
}
