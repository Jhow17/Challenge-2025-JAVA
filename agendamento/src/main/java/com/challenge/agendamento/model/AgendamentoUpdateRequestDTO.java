package com.challenge.agendamento.model;

import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AgendamentoUpdateRequestDTO(
        @Size(max = 120)String procedimento,
        @Size(max = 4000) String descricao,
        LocalDateTime dataInicio,
        LocalDateTime dataFim
) {


}
