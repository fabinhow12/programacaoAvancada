/*
 * Factory Method: 
 *
 * Objetivo: Encapsular a escolha da classe concreta a ser utilizada na criação 
 * de objetos de um determinado tipo.
 */
package aula02.main;

import dados.Funcionario;
import java.util.logging.Level;
import java.util.logging.Logger;
//import modulos.emissor.EmissorFactory;
//import modulos.emissor.EmissorFactoryAssincrono;
//import modulos.emissor.IEmissor;
import telas.TelaInicial;
import telas.TelaLogin;
import util.GerenciadorArquivoFactory;
import util.IGerenciadorArquivo;

/**
 *
 * @author dddf
 */
public class Aula02Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new TelaLogin().setVisible(true);

        /*
        GerenciadorArquivoFactory fabrica = GerenciadorArquivoFactory.getInstance();
        
        IGerenciadorArquivo gerenciador = fabrica.create(GerenciadorArquivoFactory.ARQUIVOSEPARADO);
        Funcionario f = new Funcionario("Fábio", 20, "ads", "ads");
        gerenciador.salvarFuncionarioEmArquivo(f);
        System.out.println(gerenciador.recuperarFuncionarioDeArquivo(1));
        */
 /*        
        EmissorFactoryAssincrono factory = new EmissorFactoryAssincrono();
        
        IEmissor emissor1;
        try {
            emissor1 = factory.create(EmissorFactory.EMAIL);
            emissor1.envia("Teste de mensagem EMAIL");
            
        } catch (Exception ex) {
            Logger.getLogger(Aula02Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        IEmissor emissor2;
        try {
            emissor2 = factory.create(EmissorFactory.SMS);
            emissor2.envia("Teste de mensagem SMS");
        } catch (Exception ex) {
            Logger.getLogger(Aula02Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
        IEmissor emissor3;
        try {
            emissor3 = factory.create(EmissorFactory.JMS);
            emissor3.envia("Teste de mensagem JMS");
        } catch (Exception ex) {
            Logger.getLogger(Aula02Main.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
    }

}
