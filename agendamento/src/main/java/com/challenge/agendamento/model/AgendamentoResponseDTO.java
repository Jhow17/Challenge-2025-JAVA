package com.challenge.agendamento.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

public record AgendamentoResponseDTO(
        Long id,
        String procedimento,
        String descricao,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        StatusAgendamento statusAgendamento,
        Long pacienteId,
        String pacienteNome,
        Long medicoId,
        String medicoNome
) {


}
