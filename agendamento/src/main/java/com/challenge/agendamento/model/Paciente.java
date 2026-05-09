package com.challenge.agendamento.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    private String email;

    private LocalDate dataNascimento;

    private String sexo;

    private Double peso;
    private Double altura;

    private String telefone;
    private String porOndeNosConheceu;

    // Relacionamento com a tabela de login
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;


    //para o banco ignorar nao vai virar coluna
    @Transient
    public Double getImc() {
        return this.peso / (this.altura * this.altura);
     //   if (this.peso != null && this.altura != null && this.altura > 0) {
      //      return this.peso / (this.altura * this.altura);
    //    }
    //    return null;
}


}