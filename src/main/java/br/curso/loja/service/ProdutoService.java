package br.curso.loja.service;

import br.curso.loja.exceptions.ProdutoNaoEncontradoException;
import br.curso.loja.model.Produto;
import br.curso.loja.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public void criarProduto(Produto produto){
        log.info("Criando produto");
            produtoRepository.save(produto);
    }

    public void atualizarProduto(Produto produto, Long id){
        var resultado = produtoRepository.findById(id)
                                                    .orElseThrow(() -> new ProdutoNaoEncontradoException("O produto com o ID " + id + " não existe ou não foi cadastrado!"));
        produto.setId(resultado.getId());
        log.info("atualizando produto");
            produtoRepository.save(produto);
    }

    public List<Produto> acharTodosOsProdutos(){
        log.info("Achando todos os produtos");
            return produtoRepository.findAll();
    }

    public Produto acharProdutoPorId(Long id){
        log.info("Encontrando produto com o ID:" + id);
            return produtoRepository.findById(id)
                                    .orElseThrow(() -> new ProdutoNaoEncontradoException("O produto com o ID " + id + " não existe ou não foi cadastrado!"));
    }

    public void deletarProduto(Long id){
        log.info("Deletando o produto com ID " + id);
        var produto = produtoRepository.findById(id)
                                                .orElseThrow(() -> new ProdutoNaoEncontradoException("O produto com o ID " + id + " não existe ou não foi cadastrado!"));

            produtoRepository.deleteById(id);
    }


}
