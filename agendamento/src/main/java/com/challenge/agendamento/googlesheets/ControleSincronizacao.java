package com.challenge.agendamento.googlesheets;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ControleSincronizacao {

    @Id
    private String nomePlanilha;

    private int ultimaLinhaProcessada;
}