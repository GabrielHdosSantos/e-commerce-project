package br.curso.loja.controller;

import br.curso.loja.dto.ClienteDTO;
import br.curso.loja.model.Cliente;
import br.curso.loja.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity criarCliente(@Valid @RequestBody Cliente cliente){

        clienteService.criarCliente(cliente);
                return ResponseEntity.status(HttpStatus.CREATED).body("Cliente criado com sucesso!");

    }

    @PatchMapping("/{id}")
    public ResponseEntity atualizarCliente(@Valid @RequestBody Cliente cliente, @PathVariable("id") Long id){

        clienteService.atualizarCliente(cliente, id);
                return ResponseEntity.status(HttpStatus.OK).body("Cliente atualizado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> acharTodosClientes(){
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarTodosClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> acharClientePorId(@PathVariable("id") Long id){
                return ResponseEntity.status(HttpStatus.OK).body(clienteService.acharClientePorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarCliente(@PathVariable("id") Long id){
            clienteService.deleteCliente(id);
                return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");
    }

}
