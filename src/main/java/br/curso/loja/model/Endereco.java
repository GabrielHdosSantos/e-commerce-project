package br.curso.loja.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A rua não pode ser nula!")
    @NotBlank(message = "A rua não pode ser vazia!")
    private String rua;

    @NotNull(message = "A rua não pode ser nula!")
    @NotBlank(message = "A rua não pode ser vazia!")
    @Column(length = 9)
    private String cep;

    @NotNull(message = "O número não pode ser nulo!")
    private int numero;

    private String referencia;


}
