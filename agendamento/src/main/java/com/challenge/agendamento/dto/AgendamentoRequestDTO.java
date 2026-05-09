package com.challenge.agendamento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AgendamentoRequestDTO(

    @NotBlank @Size(max = 120) String procedimento,
    @Size(max = 4000) String descricao,
    @NotNull LocalDateTime dataInicio,
    @NotNull LocalDateTime dataFim,
    @NotNull Long pacienteId,
    @NotNull Long medicoId,
    @NotNull Long colaboradorId
){
}