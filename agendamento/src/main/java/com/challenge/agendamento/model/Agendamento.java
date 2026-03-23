package com.challenge.agendamento.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "agendamento")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String procedimento;

    @Column(nullable = false, length = 400)
    private String descricao;

    @Column(name = "paciente", nullable = false)
    private Paciente paciente;



    @Column(name = "medico", nullable = false)
    private Medico medico;

    @Column(name = "data_inicio", nullable = false)
    private String dataInicio ;

    @Column(name = "data_fim", nullable = false)
    private String dataFim ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 28)
    private StatusAgendamento statusAgendamento;

    @Column(name = "criado_em", nullable = false)
    private String criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private String atualizadoEm;







}
