package com.challenge.agendamento.service;


import com.challenge.agendamento.model.Medico;
import com.challenge.agendamento.repository.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico salvar(Medico medico) {
        try {
            return medicoRepository.save(medico);
        } catch (Exception exception) {
            System.out.println("Erro ao salvar o médico no banco de dados.");
            System.out.println(exception.getMessage());
            throw new RuntimeException("Falha ao salvar o médico", exception);
        }
    }

    public Medico buscarPorId(Long id) {
        try {
            return medicoRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado com o ID: " + id));
        } catch (Exception exception) {
            System.out.println("Erro ao buscar o médico pelo ID: " + id);
            System.out.println(exception.getMessage());
            throw exception;
        }
    }
}
