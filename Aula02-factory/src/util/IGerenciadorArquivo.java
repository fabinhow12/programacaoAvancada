/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dados.Funcionario;
import java.util.ArrayList;

/**
 *
 * @author fabio
 */
public interface IGerenciadorArquivo {

    //Métodos GerenciadoArquivoUnicoFuncionario
    public void salvarFuncionarios(final ArrayList<Funcionario> funcionarios,
            final ArrayList<Funcionario> listaFuncionariosArquivo);

    public ArrayList<Funcionario> recuperarFuncionarios();

    //Metodos GerenciadorArquivoSeparado
    public void salvarFuncionarioEmArquivo(final Funcionario funcionario);

    public Funcionario recuperarFuncionarioDeArquivo(final int codigo);

}
