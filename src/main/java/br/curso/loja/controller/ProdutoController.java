package br.curso.loja.controller;

import br.curso.loja.model.Produto;
import br.curso.loja.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity criarProduto(@RequestBody Produto produto) {
        produtoService.criarProduto(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Produto criado com sucesso!");
    }

    @PatchMapping("/{id}")
    public ResponseEntity atualizandoProduto(@RequestBody Produto produto, @PathVariable("id") Long id) {
        produtoService.atualizarProduto(produto, id);
            return ResponseEntity.status(HttpStatus.OK).body("Produto atualizado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<Produto>> acharTodosOsProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.acharTodosOsProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> acharProdutoPorId(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.acharProdutoPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarProduto(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
    }



}
