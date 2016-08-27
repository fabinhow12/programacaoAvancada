/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import dados.Produto;
import java.util.ArrayList;
import util.GerenciadorArquivoUnicoProduto;

/**
 *
 * @author fabio
 */
public class RepositorioProduto {
    private static RepositorioProduto instancia;
    
    private ArrayList<Produto> produtos;
    private ArrayList<Produto> produtosEmArquivo;
    
    private RepositorioProduto(){
        recuperaArquivos();
    }
    
    public static RepositorioProduto getInstancia(){ 
        if (instancia == null){
            synchronized (RepositorioProduto.class){
                if (instancia == null){
                    instancia = new RepositorioProduto();
                }
            }
        }
        
        return instancia;
    }
    
    public void cadastraEmArquivo(){
        if (this.produtos.isEmpty()){
        
        for (int i = 0; i < 10; i++) {
            Produto p = new Produto("produto" + (i+1),"ProdutoDesc"+(i+1),(i+1) * 2, (i+1));
            this.adicionaProduto(p);
        }
        
        }else {
            System.out.println("Já Cadastrou os produtos");
        }
    }
    
    public void adicionaProduto(Produto p){
        this.produtos.add(p);
        
        salvarProduto();
    }
    
    public ArrayList<Produto> getProdutos(){
        return this.produtos;
    }
    
    //Métodos Privados
    
    private boolean salvarProduto(){
        boolean resultado = false;
        
        GerenciadorArquivoUnicoProduto.salvarProdutos(produtos, produtosEmArquivo);
        
        return resultado;
    }
    
    private void recuperaArquivos(){
        produtosEmArquivo = GerenciadorArquivoUnicoProduto.recuperarProdutos();
        produtos = new ArrayList<>(produtosEmArquivo);
    }
    
}
