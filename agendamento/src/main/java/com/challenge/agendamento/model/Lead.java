package com.challenge.agendamento.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "leads")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String nome;

    @Column(length = 20)
    private String telefone;

    @Column(length = 255)
    private String email;

    @Column(name = "canal_entrada", length = 100)
    private String canalEntrada;

    @Column(name = "procedimento_interesse", length = 120)
    private String procedimentoInteresse;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_lead", length = 50)
    private StatusLead statusLead; // Crie este Enum (ex: NAO_ATENDIDO, EM_ANDAMENTO, CONVERTIDO)

    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;

    // Relacionamento Opcional: O lead pode ter preferência por um médico
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_preferencia_id")
    private Medico medicoPreferencia;

    @PrePersist
    public void prePersist() {
        // Se ninguém mandou uma data, coloca a data e hora de agora
        if (this.dataRegistro == null) {
            this.dataRegistro = LocalDateTime.now();
        }

        // Todo lead novo que entra (da planilha ou site) nasce como NÃO ATENDIDO
        if (this.statusLead == null) {
            this.statusLead = StatusLead.NAO_ATENDIDO;
        }
    }
}