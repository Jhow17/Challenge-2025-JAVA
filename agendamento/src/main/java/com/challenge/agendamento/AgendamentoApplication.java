package com.challenge.agendamento;

import com.challenge.agendamento.model.*;
import com.challenge.agendamento.service.AgendamentoService;
import com.challenge.agendamento.service.MedicoService;
import com.challenge.agendamento.service.PacienteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class AgendamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendamentoApplication.class, args);
		teste();
	}

	//========== ZONA DE TESTE ==========

	private static AgendamentoService agendamentoService;
	private static PacienteService pacienteService;
	private static MedicoService medicoService;


	public AgendamentoApplication(AgendamentoService agendamentoService, MedicoService medicoService, PacienteService pacienteService) {
		this.agendamentoService = agendamentoService;
		this.medicoService = medicoService;
		this.pacienteService = pacienteService;
	}

	public static void teste(){

		Paciente pacienteJoao = Paciente.builder()
				.nome("João Carlos da Silva")
				.cpf("123.456.789-00")
				.dataNascimento(LocalDate.of(1990, 5, 15)) // 15 de Maio de 1990
				.sexo("M")
				.peso(82.5)
				.altura(1.80)
				.telefone("(11) 98765-4321")
				.porOndeNosConheceu("Pesquisa no Google")
				.build();
		pacienteJoao = pacienteService.salvar(pacienteJoao);
		Medico medicaAna = Medico.builder()
				.nome("Dra. Ana Beatriz Souza")
				.crm("123456-SP")
				.telefone("(11) 91111-2222")
				.email("dra.ana.beatriz@clinica.com.br")
				.dataNascimento(LocalDate.of(1985, 10, 20))
				.cpf("987.654.321-11")
				.build();
		medicaAna = medicoService.salvar(medicaAna);

		AgendamentoRequestDTO consultaA = new AgendamentoRequestDTO(
				"Avaliação Pré-Operatória - Rinoplastia",
				"Aplicação no terço superior da face",
				LocalDateTime.of(2026, 4, 2, 14, 0),
				LocalDateTime.of(2026, 4, 2, 15, 0),
				pacienteJoao.getId(),
				medicaAna.getId()
		);


		agendamentoService.criaAgendamento(consultaA);



	}

	//========== ZONA DE TESTE ==========



}
