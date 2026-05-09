package com.challenge.agendamento.mapper;

import com.challenge.agendamento.dto.AgendamentoRequestDTO;
import com.challenge.agendamento.dto.AgendamentoResponseDTO;
import com.challenge.agendamento.dto.AgendamentoUpdateRequestDTO;
import com.challenge.agendamento.model.*;

import java.time.LocalDateTime;

public class AgendamentoMapper {
    public static Agendamento toEntity(AgendamentoRequestDTO req){
        return Agendamento.builder()
                .procedimento(req.procedimento())
                .descricao(req.descricao())
                .dataInicio(req.dataInicio())
                .dataFim(req.dataFim())
                .statusAgendamento(StatusAgendamento.AGENDADO)
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDateTime.now())
                .build();

    }

    public static void merge(Agendamento entity, AgendamentoUpdateRequestDTO req){
        if (req.procedimento() != null) {
            entity.setProcedimento(req.procedimento());
        }

        if (req.descricao() != null) {
            entity.setDescricao(req.descricao());
        }

        if (req.dataInicio() != null) {
            // Converte o LocalDateTime do DTO para a String da Entidade
            entity.setDataInicio(req.dataInicio());
        }

        if (req.dataFim() != null) {
            // Converte o LocalDateTime do DTO para a String da Entidade
            entity.setDataFim(req.dataFim());
        }
    }

    public static AgendamentoResponseDTO toResponse(Agendamento a) {
        return new AgendamentoResponseDTO(
                a.getId(),
                a.getProcedimento(),
                a.getDescricao(),
                a.getDataInicio(),
                a.getDataFim(),
                a.getStatusAgendamento(),
                a.getPaciente() != null ? a.getPaciente().getId() : null,
                a.getPaciente() != null ? a.getPaciente().getNome() : null,
                a.getMedico() != null ? a.getMedico().getId() : null,
                a.getMedico() != null ? a.getMedico().getNome() : null,
                a.getColaborador() != null ? a.getColaborador().getIdColaborador() : null,
                a.getColaborador() != null ? a.getColaborador().getNome() : null
        );
    }

}


