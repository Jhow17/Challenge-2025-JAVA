package com.challenge.agendamento.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

public record AgendamentoDTO() {
    @NotBlank @Size(max = 128) String procedimento,
    @Size(max = 4000) String descricao,
    @NotNull LocalDateTime dataInicio,
    @NotNull LocalDateTime dataFim,
    @NotBlank  Paciente paciente

}
