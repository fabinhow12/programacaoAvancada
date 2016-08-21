package repositorios;


import dados.Funcionario;
import dados.Cliente;
import java.util.ArrayList;
import util.GerenciadorArquivo;
import util.GerenciadorArquivoFactory;
import util.IGerenciadorArquivo;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author douglasfrari
 */
public class RepositorioPessoa {
    
    //Instancia da fabrica de objetos
    GerenciadorArquivoFactory fabrica = GerenciadorArquivoFactory.getInstance();
    IGerenciadorArquivo gerenciador= fabrica.create(GerenciadorArquivoFactory.ARQUIVOUNICO);
    
    //Instancia
    private static RepositorioPessoa instancia;
    
    private ArrayList<Cliente>clientes;
    
    private ArrayList<Funcionario>funcionarios;
    private static ArrayList<Funcionario> funcionariosEmArquivo;
    
    
    // SINGLETON com construtor private
    private RepositorioPessoa() {
        
        clientes = new ArrayList<>();
//        funcionarios = new ArrayList<>();
        
        // verificar se existe arquivos
        
        recuperarDadosEmArquivo();
        
    }

    public static RepositorioPessoa getInstancia() {
        
        if (instancia == null) {
            
            synchronized (RepositorioPessoa.class){
            
                instancia = new RepositorioPessoa();
            
            }
        }
        
        return instancia;
    }
    
    
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }
    
    public void adicionarFuncionario(Funcionario funcionario) {
        
        this.funcionarios.add(funcionario);

        salvarFuncionarios();        
    }
    
    public void adicionarCliente(Cliente cliente) {
        
        this.clientes.add(cliente);
    }
    
    
            
    private boolean salvarFuncionarios() {
        boolean resultado = false;
        
        
        gerenciador.salvarFuncionarios(funcionarios, funcionariosEmArquivo);
        
        return resultado;
                
    }
    
    /**
     * Recupera os dados que estao salvos em arquivo local e atualiza
     * listas de dados em memória.
     */
    private void recuperarDadosEmArquivo() {
        
        funcionariosEmArquivo = gerenciador.recuperarFuncionarios();
        funcionarios = new ArrayList<Funcionario>(funcionariosEmArquivo);
        
    }

    
}
