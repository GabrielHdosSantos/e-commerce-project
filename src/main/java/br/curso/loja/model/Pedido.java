package br.curso.loja.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valorTotal;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Produto> produtos;

    @OneToOne
    private Endereco endereco;

    @ManyToOne
    private Cliente cliente;

}
