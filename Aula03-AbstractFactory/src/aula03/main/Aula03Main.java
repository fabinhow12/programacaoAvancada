/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula03.main;


import modulos.emissor.comunicador.abstractfactory_factorymethod.IComunicadorFactory;
import modulos.emissor.comunicador.abstractfactory_factorymethod.IReceptor;
import modulos.emissor.comunicador.abstractfactory_factorymethod.IEmissor;
import modulos.emissor.comunicador.abstractfactory_factorymethod.VisaComunicadorFactory;
import dados.Funcionario;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import repositorios.RepositorioPessoa;
import repositorios.RepositorioProduto;
import telas.TelaCadastrarFuncionario;
import telas.TelaLogin;
import util.GeraArquivoFuncionario;

/**
 *
 * @author dddf
 */
public class Aula03Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        RepositorioPessoa repositorioPessoa = RepositorioPessoa.getInstancia();
        RepositorioProduto repositorioProduto = RepositorioProduto.getInstancia();
        
        repositorioPessoa.cadastraEmArquivo();
        repositorioProduto.cadastraEmArquivo();
        
        
        
        // VERIFICAR SE TEM USUARIO
        ArrayList<Funcionario> funcionarios = GeraArquivoFuncionario.recuperarFuncionarios();
        System.out.println(funcionarios.size());
        if (funcionarios.isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "Nenhum usuário encontrado!\n\n"
                    + "-faça um cadastro do usuário; \n-efetue o login no sistema");
            
            new TelaCadastrarFuncionario(true).setVisible(true);
            
        } else {
            
            new TelaLogin().setVisible(true);
        }
        
       
    } // fim main

} // fim da classe
