package br.curso.loja.dto;

import br.curso.loja.model.Endereco;
import lombok.Builder;

@Builder
public class ClienteDTO {

    public Long id;
    public String nome;
    public String cpf;
    public int idade;
    public Endereco endereco;

}
