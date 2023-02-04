package br.curso.loja.dto;

import br.curso.loja.model.Endereco;
import br.curso.loja.model.Produto;
import lombok.Builder;

import java.util.List;

@Builder
public class PedidoDTO {

    public Long id;
    public double valorTotal;
    public List<Produto> produtos;
    public String nomeCliente;
    public String cpfCliente;
    public Endereco entrega;

}
