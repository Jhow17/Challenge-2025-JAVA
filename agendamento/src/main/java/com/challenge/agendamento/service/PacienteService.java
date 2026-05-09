package com.challenge.agendamento.service;


import com.challenge.agendamento.model.Paciente;
import com.challenge.agendamento.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente salvar(Paciente paciente) {
        try {
            return pacienteRepository.save(paciente);
        } catch (Exception exception) {
            System.out.println("Erro ao salvar o paciente no banco de dados.");
            System.out.println(exception.getMessage());
            throw new RuntimeException("Falha ao salvar o paciente", exception);
        }
    }

    public Paciente buscarPorId(Long id) {
        try {
            return pacienteRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com o ID: " + id));
        } catch (Exception exception) {
            System.out.println("Erro ao buscar o paciente pelo ID: " + id);
            System.out.println(exception.getMessage());
            throw exception;
        }
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public void deletar(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Paciente não encontrado com o ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }
}
