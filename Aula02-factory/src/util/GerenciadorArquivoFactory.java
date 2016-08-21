/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author fabio
 */
public class GerenciadorArquivoFactory {

    private static GerenciadorArquivoFactory instancia;
    
    public static final int ARQUIVOSEPARADO = 0;
    public static final int ARQUIVOUNICO = 1;

    
    //Singleton
    
    private GerenciadorArquivoFactory(){
        
    }
    
    public static GerenciadorArquivoFactory getInstance(){
        
        if (instancia == null){
            
            synchronized (GerenciadorArquivoFactory.class){
                 if (instancia == null){
                     instancia = new GerenciadorArquivoFactory();
                 }
            }
        }
        
        return instancia;
    }
    
    
    public IGerenciadorArquivo create(int opcao) {
        IGerenciadorArquivo result = null;
        
        if (opcao == ARQUIVOSEPARADO) {
            result = new GerenciadorArquivoSeparadoFuncionario();
        } else if (opcao == ARQUIVOUNICO){
            result = new GerenciadorArquivoUnicoFuncionario();
        } else {
            throw new IllegalArgumentException("Erro");
        }
        
        return result;
    }
    
    

}
