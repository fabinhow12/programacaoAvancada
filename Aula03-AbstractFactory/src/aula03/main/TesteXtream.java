/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula03.main;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import dados.Cliente;
import dados.Funcionario;
import dados.ItensVenda;
import dados.Produto;
import dados.Venda;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import repositorios.RepositorioPessoa;
import repositorios.RepositorioProduto;
import util.GeraArquivoVenda;

/**
 *
 * @author fabio
 */
public class TesteXtream {

    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {

        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        XStream xstream = new XStream();
        xstream.alias("venda", Venda.class);
        xstream.alias("funcionario", Funcionario.class);
        xstream.alias("produto", Produto.class);
        xstream.alias("itens", ItensVenda.class);
        xstream.alias("arrayItens", ArrayList.class);

        Funcionario funcionario = new Funcionario("TESTE", 10, "123", "123");
        Cliente cliente1 = new Cliente("Xuxa", new Date(), "123");

        Venda venda1 = new Venda(funcionario, cliente1);

        Produto produto1 = new Produto("PC", "descricao...", 1000, 550);
        Produto produto2 = new Produto("Notebook", "descricao...", 2000, 1250);
        Produto produto3 = new Produto("Capa iPhone 6s", "capa ...", 300, 50);

        venda1.adicionarItemVenda(produto1, 1);
        venda1.adicionarItemVenda(produto2, 1);
        venda1.adicionarItemVenda(produto3, 3);

        Venda venda2 = new Venda(funcionario, cliente1);
        venda2.adicionarItemVenda(produto1, 1);
        venda2.adicionarItemVenda(produto2, 1);
        venda2.adicionarItemVenda(produto3, 3);

        xstream.alias("vendas", List.class);
        ArrayList<Venda> lista = new ArrayList<>();
        lista.add(venda1);
        lista.add(venda2);

        String xml = xstream.toXML(venda1);
        System.out.println("XML = ");
        System.out.println(xml);

        GeraArquivoVenda.salvarVendas(lista);
        ArrayList<Venda> mostra = GeraArquivoVenda.recuperarVendas();
        
        for (Venda venda : mostra) {
            System.out.println(venda);
        }
    }

}
