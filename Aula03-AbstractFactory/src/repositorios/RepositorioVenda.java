/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import dados.Venda;
import java.util.ArrayList;
import util.GeraArquivoVenda;

/**
 *
 * @author fabio
 */
public class RepositorioVenda {
    private static RepositorioVenda instancia;
    
    private ArrayList<Venda> vendas;
    
    private RepositorioVenda(){
        recuperaArquivos();
    }
    
    public static RepositorioVenda getInstancia(){
           
        if (instancia == null){
            synchronized (RepositorioVenda.class){
                if (instancia == null){
                instancia = new RepositorioVenda();
                }
            }
        }
        
        return instancia;
    }
    
    
    public void adicionarVenda(Venda v){
        this.vendas.add(v);
        
        salvarVenda();
    }
    
    public ArrayList<Venda> getVendas(){
        return this.vendas;
    }
    
    //Métodos Privados
    
    private boolean salvarVenda(){
        boolean resultado = false;
        
        GeraArquivoVenda.salvarVendas(vendas);
        
        
        return resultado;
    }
    
    
    private void recuperaArquivos(){
        this.vendas = GeraArquivoVenda.recuperarVendas();
    }
    
}
