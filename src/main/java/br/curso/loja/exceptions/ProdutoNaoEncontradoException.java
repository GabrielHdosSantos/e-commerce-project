package br.curso.loja.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProdutoNaoEncontradoException extends RuntimeException{

    public ProdutoNaoEncontradoException(String mensagem){
        super(mensagem);
    }


}
