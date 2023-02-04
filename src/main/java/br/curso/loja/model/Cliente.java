package br.curso.loja.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome não pode ser nulo!")
    @NotBlank(message = "O nome não pode estar em branco!")
    @Column(length = 50)
    private String nome;

    @NotNull(message = "O cpf não pode ser nulo!")
    @NotBlank(message = "O cpf não pode ser vazio!")
    @Column(length = 11, unique = true)
    private String cpf;

    @NotNull(message = "A idade não pode ser nula!")
    @Min(18)
    private int idade;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

}
