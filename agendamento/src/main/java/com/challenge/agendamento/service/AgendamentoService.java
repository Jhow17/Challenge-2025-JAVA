package com.challenge.agendamento.service;

import com.challenge.agendamento.dto.AgendamentoRequestDTO;
import com.challenge.agendamento.dto.AgendamentoResponseDTO;
import com.challenge.agendamento.dto.AgendamentoUpdateRequestDTO;
import com.challenge.agendamento.mapper.AgendamentoMapper;
import com.challenge.agendamento.model.*;
import com.challenge.agendamento.repository.AgendamentoRepository;
import com.challenge.agendamento.repository.ColaboradorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private ColaboradorRepository colaboradorRepository;


    //DONE : metodo criar novo agendamento
    //O Valid serve para para verificar se as regras de validacao criadas estao sendo obdecidas pelo
    public AgendamentoResponseDTO criaAgendamento(@Valid AgendamentoRequestDTO request){
        try {
            Paciente paciente = pacienteService.buscarPorId(request.pacienteId());
            Medico medico = medicoService.buscarPorId(request.medicoId());

            Colaborador colaborador = colaboradorRepository.findById(request.colaboradorId())
                    .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado"));

            Agendamento novoAgendamento = AgendamentoMapper.toEntity(request);

            novoAgendamento.setColaborador(colaborador);

            novoAgendamento.setPaciente(paciente);
            novoAgendamento.setMedico(medico);

            validaIIntervalo(novoAgendamento.getDataInicio(), novoAgendamento.getDataFim());
            verificaConflito(medico.getId(), novoAgendamento.getDataInicio(), novoAgendamento.getDataFim());

            Agendamento agendamentoSalvo = agendamentoRepository.save(novoAgendamento);
            System.out.println("Agendamento criado com sucesso!");

            return AgendamentoMapper.toResponse(agendamentoSalvo);

        } catch (Exception exception) {
            System.out.println("Erro ao tentar criar o agendamento.");
            System.out.println(exception.getMessage());
            throw exception;
        }
    }

    //DONE : metodo ATUALIZAR um agendamento

    public AgendamentoResponseDTO atualizar(Long id, @Valid AgendamentoUpdateRequestDTO req){
        Agendamento entity = agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento nao encontrado"));

        AgendamentoMapper.merge(entity, req);

        verificaConflito(
                entity.getMedico().getId(),
                entity.getDataInicio(),
                entity.getDataFim()
        );

        entity = agendamentoRepository.save(entity);
        return AgendamentoMapper.toResponse(entity);

    }

    // DONE : metodo auxiliar para verificar Intervalo
    public void validaIIntervalo(LocalDateTime ini, LocalDateTime fim){
        if (ini.isAfter(fim)){
            throw new IllegalArgumentException("A data de início não pode ser depois da data de fim.");
        }

    }
    // DONE : metodo auxiliar para verificar se o novo Agendamento Conflita
    public void verificaConflito(Long medicoId, LocalDateTime inicio, LocalDateTime fim) {
        boolean temConflito = agendamentoRepository.existSobreposicao(medicoId, inicio, fim);
        if (temConflito){
            throw new RuntimeException("O medico ja tem um compromisso neste horário");
        }
    }

    //DONE metodo para cancelar

    public AgendamentoResponseDTO cancelar(Long id){
        Agendamento entity = agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento nao encontrado"));
        entity.setStatusAgendamento(StatusAgendamento.CANCELADO);
        entity = agendamentoRepository.save(entity);
        return AgendamentoMapper.toResponse(entity);
    }

    //DONE metodo para concluir
    public AgendamentoResponseDTO concluir(Long id){
        Agendamento entity =  agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento nao encontrado"));
                entity.setStatusAgendamento(StatusAgendamento.CONCLUIDO);
                entity = agendamentoRepository.save(entity);
                return AgendamentoMapper.toResponse(entity);
    }

    //DONE metodo para buscar por Id
    public AgendamentoResponseDTO buscarPorId(Long id){
        Agendamento agendamento = agendamentoRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Agendamento nao encontrado"));
        return AgendamentoMapper.toResponse(agendamento);

    }

    public List<AgendamentoResponseDTO> listarTodos() {
        return agendamentoRepository.findAll()
                .stream()
                .map(AgendamentoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void deletar(Long id) {
        if (!agendamentoRepository.existsById(id)) {
            throw new EntityNotFoundException("Agendamento não encontrado com o ID: " + id);
        }
        agendamentoRepository.deleteById(id);
    }

}