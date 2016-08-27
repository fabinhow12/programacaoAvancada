package repositorios;


import dados.Funcionario;
import dados.Cliente;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import util.GeraCpfAleatorio;
import util.GeraSalarioAleatorio;
import util.GerenciadorArquivoUnicoCliente;
import util.GerenciadorArquivoUnicoFuncionario;

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
    
    
    private static RepositorioPessoa instancia;
    
    private ArrayList<Cliente>clientes;
    private ArrayList<Cliente> clientesEmArquivo;
    
    private ArrayList<Funcionario>funcionarios;
    private static ArrayList<Funcionario> funcionariosEmArquivo;
    
    
    // SINGLETON com construtor private
    private RepositorioPessoa() throws ParseException {
                
        // verificar se existe arquivos
        
        recuperarDadosEmArquivo();
        
    }

    public static RepositorioPessoa getInstancia() throws ParseException {
        
        if (instancia == null){
            synchronized (RepositorioPessoa.class){
                if (instancia == null){
                    instancia = new RepositorioPessoa();
                }
            }
        }
        
        return instancia;
    }
    
    //Metodo para cadastrar funcionario e cliente aleatorios em arquivo
    public void cadastraEmArquivo(){
        
        if (this.clientes.isEmpty() && this.funcionarios.isEmpty()){
        
        for (int i = 0; i < 10; i++) {
            Funcionario f = new Funcionario("funcionario" + (i+1),GeraSalarioAleatorio.aleatorio(),"funcionario"+ (i+1), "funcionario"+(i+1));
            this.adicionarFuncionario(f);
        }
            for (int i = 0; i < 10; i++) {
            GeraCpfAleatorio gera = new GeraCpfAleatorio();
            
            Cliente c = new Cliente("Cliente" + (i+1),new Date(), gera.geraCPFFinal());
            this.adicionarCliente(c);
       
            }
        }else {
            System.out.println("Já cadastrou os clientes e funcionários");
        }
        
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
        salvarClientes();
    }
    
    
            
    private boolean salvarFuncionarios() {
        boolean resultado = false;
        
        
        GerenciadorArquivoUnicoFuncionario.salvarFuncionarios(funcionarios, funcionariosEmArquivo);
        
        return resultado;
                
    }
    
    
    
    
    private boolean salvarClientes(){
        boolean resultado = false;
        
        GerenciadorArquivoUnicoCliente.salvarClientes(clientes, clientesEmArquivo);
        
        return resultado;
    }
    
    /**
     * Recupera os dados que estao salvos em arquivo local e atualiza
     * listas de dados em memória.
     */
    private void recuperarDadosEmArquivo() throws ParseException {
        funcionariosEmArquivo = GerenciadorArquivoUnicoFuncionario.recuperarFuncionarios();
        funcionarios = new ArrayList<Funcionario>(funcionariosEmArquivo);
        clientesEmArquivo = GerenciadorArquivoUnicoCliente.recuperarClientes();
        clientes = new ArrayList<Cliente>(clientesEmArquivo);
        
    }

    
}
