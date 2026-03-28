package com.challenge.agendamento.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agendamento")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String procedimento;

    @Column(nullable = false, length = 400)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime dataInicio ;

    @Column(name = "data_fim", nullable = false)
    private LocalDateTime dataFim ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 28)
    private StatusAgendamento statusAgendamento;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;







}
