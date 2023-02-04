package br.curso.loja.controller;

import br.curso.loja.dto.PedidoDTO;
import br.curso.loja.dto.PedidoProdutoDTO;
import br.curso.loja.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @PostMapping("/{id}")
    public ResponseEntity criarPedido(@RequestBody PedidoProdutoDTO pedidoProdutoDTO, @PathVariable("id") Long idCliente){
        pedidoService.criarPedido(idCliente, pedidoProdutoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pedido efetuado com sucesso!");
    }

    @GetMapping("cliente/{id}")
    public ResponseEntity<List<PedidoDTO>> encontrarPedidosDeUmCliente(@PathVariable("id") Long id){
        var resultado = pedidoService.acharOsPedidosDeUmCliente(id);
            return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> encontrarTodosOsPedidos(){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.acharTodosOsPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> acharPedidoPorId(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.acharPedidoPorId(id));
    }

}
