package br.curso.loja.service;

import br.curso.loja.dto.PedidoDTO;
import br.curso.loja.dto.PedidoProdutoDTO;
import br.curso.loja.exceptions.ClienteNaoEncontradoException;
import br.curso.loja.exceptions.PedidoNaoEncontradoException;
import br.curso.loja.exceptions.ProdutoNaoEncontradoException;
import br.curso.loja.mapper.PedidoMapper;
import br.curso.loja.model.Pedido;
import br.curso.loja.model.Produto;
import br.curso.loja.repository.ClienteRepository;
import br.curso.loja.repository.PedidoRepository;
import br.curso.loja.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoMapper pedidoMapper;

    public PedidoService(PedidoRepository pedidoRepository,
                         ProdutoRepository produtoRepository,
                         ClienteRepository clienteRepository,
                         PedidoMapper pedidoMapper){
        this.pedidoMapper = pedidoMapper;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    public void criarPedido(Long idCliente, PedidoProdutoDTO pedidoProdutoDTO){

        log.info("Efetuando o pedido");

        var cliente = clienteRepository.findById(idCliente)
                                               .orElseThrow(() -> new ClienteNaoEncontradoException("O cliente com o ID" + idCliente + "não existe ou não foi encontrado!"));

        var produtos = Optional.of(produtoRepository.findAllById(pedidoProdutoDTO.idProduto))
                                            .orElseThrow(() -> new ProdutoNaoEncontradoException("O produto não pode ser encontrado ou não existe!"));

        var valorTotal = produtos.stream()
                                         .mapToDouble(Produto::getPreco)
                                         .sum();

        var pedido = Pedido.builder()
                                   .endereco(cliente.getEndereco())
                                   .produtos(produtos)
                                   .valorTotal(valorTotal)
                                   .build();


                cliente.getPedidos().add(pedido);
                pedido.setCliente(cliente);

                pedidoRepository.save(pedido);
    }

    public List<PedidoDTO> acharOsPedidosDeUmCliente(Long id){
        log.info("Encontrando os pedidos do cliente");
        return Optional.of(pedidoRepository.findAllByClienteId(id)
                                            .stream()
                                            .map(pedidoMapper::toDTO)
                                            .collect(Collectors.toList()))
                                            .orElseThrow(() -> new ClienteNaoEncontradoException("O cliente com o ID" + id + "não foi encontrado ou não existe!"));
    }

    public List<PedidoDTO> acharTodosOsPedidos(){
        log.info("Encontrando todos os pedidos");
        return pedidoRepository.findAll().stream().map(pedidoMapper::toDTO).collect(Collectors.toList());
    }

    public PedidoDTO acharPedidoPorId(Long id){
        log.info("Encontrando o pedido com o ID" + id);
        return pedidoRepository.findById(id).map(pedidoMapper::toDTO)
                .orElseThrow(() -> new PedidoNaoEncontradoException("O pedido de ID" + id + "não foi encontrado ou não existe!"));
    }


}
