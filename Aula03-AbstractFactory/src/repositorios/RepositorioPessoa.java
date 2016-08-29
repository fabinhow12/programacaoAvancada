package repositorios;

import dados.Funcionario;
import dados.Cliente;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import util.GeraArquivoCliente;
import util.GeraCpfAleatorio;
import util.GeraSalarioAleatorio;
import util.GeraArquivoFuncionario;

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

    private ArrayList<Cliente> clientes;
   

    private ArrayList<Funcionario> funcionarios;

    // SINGLETON com construtor private
    private RepositorioPessoa() throws ParseException {

        // verificar se existe arquivos
        recuperarDadosEmArquivo();

    }

    public static RepositorioPessoa getInstancia() throws ParseException {

        if (instancia == null) {
            synchronized (RepositorioPessoa.class) {
                if (instancia == null) {
                    instancia = new RepositorioPessoa();
                }
            }
        }

        return instancia;
    }

    //Metodo para cadastrar funcionario e cliente aleatorios em arquivo
    public void cadastraEmArquivo() {

        if (this.funcionarios.isEmpty()) {

            for (int i = 0; i < 10; i++) {
                Funcionario f = new Funcionario("funcionario" + (i + 1), GeraSalarioAleatorio.aleatorio(), "funcionario" + (i + 1), "funcionario" + (i + 1));
                this.adicionarFuncionario(f);
            }
            
        } else {
            System.out.println("Já cadastrou os funcionários");
        }
        
        if (this.clientes.isEmpty()){
            for (int i = 0; i < 10; i++) {
                GeraCpfAleatorio gera = new GeraCpfAleatorio();

                Cliente c = new Cliente("Cliente" + (i + 1), new Date(), gera.geraCPFFinal());
                this.adicionarCliente(c);

            }
        }else {
            System.out.println("Já Cadastrou os clientes");
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

        GeraArquivoFuncionario.salvarFuncionarios(funcionarios);

        return resultado;

    }

    private boolean salvarClientes() {
        boolean resultado = false;

        GeraArquivoCliente.salvarClientes(clientes);

        return resultado;
    }

    /**
     * Recupera os dados que estao salvos em arquivo local e atualiza listas de
     * dados em memória.
     */
    private void recuperarDadosEmArquivo() throws ParseException {
        funcionarios = GeraArquivoFuncionario.recuperarFuncionarios();

        clientes = GeraArquivoCliente.recuperarClientes();

    }

}
