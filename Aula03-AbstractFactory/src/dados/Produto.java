/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.io.Serializable;

/**
 *
 * @author Douglas
 */
@XStreamAlias("produto")
public class Produto implements Serializable {
    
    public static final String NOME = "nome_";
    public static final String CODIGO = "codigo_";  
    public static final String DESCRICAO = "descricao_";
    public static final String PRECOCUSTO = "precocusto_";
    public static final String PRECOVENDA = "precovenda_";
    
    // ATRIBUTOS
    private int codigo;
    private String nome;
    private String desc;
    private double precoVenda;
    private double precoCusto;
    
    // variavel auxiliar
    private static int contadorCodigo = 1;
    
    // Construtor default
    public Produto() {
        
        // criar um código autoincremental
        codigo = contadorCodigo;
        
        // atualizar o contador de codigo
        contadorCodigo++;
    }
    
    public Produto(String nome,
                   String desc,
                   double precoVenda, 
                   double precoCusto) {
        
                // criar um código autoincremental
        codigo = contadorCodigo;
        
        // atualizar o contador de codigo
        contadorCodigo++;
        
        System.out.println("Construtor com argumentos");
        
        
        this.nome = nome;
        this.desc = desc;
        this.precoVenda = precoVenda;
        this.precoCusto = precoCusto;
    }
    
    
    
    
    // METODOS

    public void imprimirDados() {
        
        System.out.println( toString() );
    }
    
    public static void setContador(int contadorAtual){
        Produto.contadorCodigo = contadorAtual;
    }
    
    
    // sobrecarga do método toString da classe Object

    @Override
    public String toString() {
        return "Produto{" + "codigo=" + codigo + ", nome=" + nome + ", desc=" + desc + ", precoVenda=" + precoVenda + ", precoCusto=" + precoCusto + '}';
    }
   


    public int getCodigo() {
        return codigo;
    }
    
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }
    
}
