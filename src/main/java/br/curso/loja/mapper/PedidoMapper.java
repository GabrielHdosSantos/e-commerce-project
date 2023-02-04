package br.curso.loja.mapper;

import br.curso.loja.dto.PedidoDTO;
import br.curso.loja.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {


    public PedidoDTO toDTO(Pedido pedido){

       return PedidoDTO.builder()
                .id(pedido.getId())
                .valorTotal(pedido.getValorTotal())
                .cpfCliente(pedido.getCliente().getCpf())
                .entrega(pedido.getEndereco())
                .produtos(pedido.getProdutos())
                .nomeCliente(pedido.getCliente().getNome())
                .build();
    }

}
