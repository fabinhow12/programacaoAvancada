/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dados.Funcionario;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author douglasfrari
 */
public abstract class GerenciadorArquivo implements IGerenciadorArquivo{
    
    protected static final String FOLDER_NAME = "arquivos";
    protected static final String NOME_ARQUIVO_SERIALIZADO = FOLDER_NAME+File.separator+"funcionario_";
    protected static final String ARQUIVO_BANCO_DADOS_XML = "funcionarios.xml";

    protected static void checkFolder() {
        File theDir = new File(FOLDER_NAME);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + FOLDER_NAME);
            boolean result = false;

            try{
                theDir.mkdir();
                result = true;
            } 
            catch(SecurityException se){
                //handle it
                System.out.println(se.getMessage());  
            }        
            if(result) {    
                System.out.println("DIR created");  
            }
        }
    }
    
    //Métodos GerenciadoArquivoUnicoFuncionario
    @Override
    public void salvarFuncionarios(final ArrayList<Funcionario> funcionarios,
            final ArrayList<Funcionario> listaFuncionariosArquivo){}
    
    @Override
    public ArrayList<Funcionario> recuperarFuncionarios(){return null;}
    
    
    //Metodos GerenciadorArquivoSeparado
    @Override
    public void salvarFuncionarioEmArquivo(final Funcionario funcionario){}
    
    @Override
    public Funcionario recuperarFuncionarioDeArquivo(final int codigo){return null;}
}
