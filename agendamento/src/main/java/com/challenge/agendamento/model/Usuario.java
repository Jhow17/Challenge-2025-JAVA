package com.challenge.agendamento.model;



import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha; // Usar o depois (BCrypt)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleUsuario role;






}
