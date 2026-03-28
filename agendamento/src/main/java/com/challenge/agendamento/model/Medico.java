package com.challenge.agendamento.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String crm;

    private String telefone;

    private String email;



    private LocalDate dataNascimento;

    @Column(unique = true)
    private String cpf;


    public Medico() {
    }



}
