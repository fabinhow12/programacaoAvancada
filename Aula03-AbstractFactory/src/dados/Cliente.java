package dados;


/**
 * Escreva a descrição da classe Cliente aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente extends Pessoa implements Serializable
{
    
    public static final String CODIGO = "codigo_";
    public static final String NOME = "nome_";   
    public static final String DATANASC = "dataNascimento_";
    public static final String CPF = "cpf_";
    
    
    private int codigo;
    private Date dataNascimento;
    private static int contador = 1;
    private String cpf;

    public Cliente(String nome,Date dataNascimento,String cpf) {
        this.codigo = contador++;
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.cpf = cpf;
    }
    
    public void ImprimiCliente(){
        System.out.println(toString());
    }

    @Override
    public String toString() {
        String simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(this.dataNascimento);
        return "Cliente{" + "codigo=" + codigo +"Nome: "+this.nome+ ", dataNascimento=" + simpleDateFormat + ", cpf=" + cpf + '}';
    }
    
    
    
    public Cliente(){
        this.codigo = contador ++;
    }
    
    public static void setContador(int contadorAtual){
        Cliente.contador = contadorAtual;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
    
    
    
}
