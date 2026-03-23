package com.challenge.agendamento.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    private LocalDate dataNascimento;

    private String sexo;

    private Double peso; // em kg
    private Double altura; // em metros

    private String email;
    private String telefone;
    private String porOndeNosConheceu;


    public Paciente() {
    }


    @Transient
    public Double getImc() {
        if (this.peso != null && this.altura != null && this.altura > 0) {
            return this.peso / (this.altura * this.altura);
        }
        return null;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public Double getAltura() { return altura; }
    public void setAltura(Double altura) { this.altura = altura; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getPorOndeNosConheceu() { return porOndeNosConheceu; }
    public void setPorOndeNosConheceu(String porOndeNosConheceu) { this.porOndeNosConheceu = porOndeNosConheceu; }
}