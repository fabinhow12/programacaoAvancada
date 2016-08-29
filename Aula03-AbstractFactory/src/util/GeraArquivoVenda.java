/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fabio
 */
public class GeraArquivoVenda extends GerenciadorArquivo {

    public static void salvarVendas(final ArrayList<Venda> vendas) {

        FileOutputStream fileOut = null;
        XStream xstream = new XStream();
        try {

            File file = new File(ARQUIVO_BANCO_DADOS_VENDA);
            if (file.isFile()) {
                System.out.println("lendo arquivo: " + file.getAbsolutePath());
                xstream.alias("vendas", List.class);
                xstream.alias("venda", Venda.class);
                xstream.alias("funcionario", Funcionario.class);
                xstream.alias("produto", Produto.class);
                xstream.alias("itens", ItensVenda.class);
                
                fileOut = new FileOutputStream(file);
                fileOut.write(xstream.toXML(vendas).getBytes());

                atualizaContadorCodigos(vendas);

            } else {
                System.out.println("cria arquivo: " + file.getAbsolutePath());
                xstream.alias("vendas", List.class);
                xstream.alias("venda", Venda.class);
                xstream.alias("funcionario", Funcionario.class);
                xstream.alias("produto", Produto.class);
                xstream.alias("itens", ItensVenda.class);
                fileOut = new FileOutputStream(file);
                fileOut.write(xstream.toXML(vendas).getBytes());

            }

            // salva no XML
            Date myDate = new Date();
            String simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(myDate);
            System.out.println("Dados Salvos: " + simpleDateFormat);

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo ainda nao existe: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {

                if (fileOut != null) {
                    fileOut.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static ArrayList<Venda> recuperarVendas() {

        ArrayList<Venda> vendas = new ArrayList<>();

        try {
            XStream xStream = new XStream(new Dom4JDriver());
            xStream.alias("vendas", ArrayList.class);
            xStream.processAnnotations(Venda.class);

            BufferedReader input = new BufferedReader(new FileReader(ARQUIVO_BANCO_DADOS_VENDA));
            vendas = (ArrayList) xStream.fromXML(input);
            input.close();

        } catch (FileNotFoundException e) {
            System.err.println("ARQUIVO NAO EXISTE AINDA: " + e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        atualizaContadorCodigos(vendas);

        return vendas;
    }

    private static void atualizaContadorCodigos(ArrayList<Venda> vendas) {
        // atualizar contador de codigo de funcionario
        int contadorAtual = vendas.size() + 1;
        Venda.setContador(contadorAtual);
    }

}
