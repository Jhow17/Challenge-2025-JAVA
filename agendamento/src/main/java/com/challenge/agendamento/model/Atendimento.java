package com.challenge.agendamento.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "atendimentos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_interacao")
    private LocalDateTime dataInteracao;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;

    @Column(name = "tempo_gasto_minutos")
    private Integer tempoGastoMinutos;

    // Mapeamento de CLOB do Oracle para String no Java
    @Lob
    @Column(name = "resumo_atendimento")
    private String resumoAtendimento;

    @Column(name = "proximo_passo", length = 255)
    private String proximoPasso;

    @Enumerated(EnumType.STRING)
    @Column(name = "novo_status_lead", length = 50)
    private StatusLead novoStatusLead;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id", nullable = false)
    private Lead lead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colaborador_id", nullable = false)
    private Colaborador colaborador;
}