package br.curso.loja.service;

import br.curso.loja.dto.ClienteDTO;
import br.curso.loja.exceptions.ClienteNaoEncontradoException;
import br.curso.loja.mapper.ClienteMapper;
import br.curso.loja.model.Cliente;
import br.curso.loja.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper){
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public void criarCliente(Cliente cliente){
        log.info("Criando cliente");
            clienteRepository.save(cliente);
    }

    public void atualizarCliente(Cliente cliente, Long id){
        log.info("Atualizando cliente");
       var resultado = clienteRepository.findById(id)
                         .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente com o ID " + id + " não encontrado ou não existe!"));

        cliente.setId(resultado.getId());
        cliente.setEndereco(resultado.getEndereco());
            clienteRepository.save(cliente);
    }

    public List<ClienteDTO> buscarTodosClientes(){
        log.info("Retornando lista de todos os clientes");
            return clienteRepository.findAll()
                                    .stream()
                                    .map(clienteMapper::toDTO)
                                    .collect(Collectors.toList());
    }

    public ClienteDTO acharClientePorId(Long id){
        log.info("Encontrando cliente com o ID:" + id);
            return clienteRepository.findById(id)
                                    .map(clienteMapper::toDTO)
                                    .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado ou não existe!"));
    }

    public void deleteCliente(Long id){
        log.info("Deletando cliente com o ID:" + id);
        clienteRepository.findById(id)
                         .orElseThrow(() -> new ClienteNaoEncontradoException("O cliente de ID " + id + " não pode ser deletado pois não foi encontrado ou não existe!"));

        clienteRepository.deleteById(id);
    }




}
