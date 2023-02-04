package br.curso.loja.mapper;

import br.curso.loja.dto.ClienteDTO;
import br.curso.loja.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {


    public Cliente toObject(ClienteDTO clienteDTO){

        var cliente = Cliente.builder()
                            .id(clienteDTO.id)
                            .nome(clienteDTO.nome)
                            .cpf(clienteDTO.cpf)
                            .idade(clienteDTO.idade)
                            .endereco(clienteDTO.endereco)
                            .build();

            return cliente;
    }

    public ClienteDTO toDTO(Cliente cliente){

        var dto = ClienteDTO.builder()
                            .id(cliente.getId())
                            .nome(cliente.getNome())
                            .cpf(cliente.getCpf())
                            .idade(cliente.getIdade())
                            .endereco(cliente.getEndereco())
                            .build();

        return dto;
    }



}
