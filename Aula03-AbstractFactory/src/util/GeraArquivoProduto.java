/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import dados.Funcionario;
import dados.Produto;
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
public class GeraArquivoProduto extends GerenciadorArquivo {

    public static void salvarProdutos(final ArrayList<Produto> produtos) {

        FileOutputStream fileOut = null;
        XStream xstream = new XStream();
        try {

            File file = new File(ARQUIVO_BANCO_DADOS_PRODUTO);
            if (file.isFile()) {
                System.out.println("lendo arquivo: " + file.getAbsolutePath());
                xstream.alias("produtos", List.class);
                xstream.alias("produto", Produto.class);
                fileOut = new FileOutputStream(file);
                fileOut.write(xstream.toXML(produtos).getBytes());

                atualizaContadorCodigos(produtos);

            } else {
                System.out.println("cria arquivo: " + file.getAbsolutePath());
                xstream.alias("produtos", List.class);
                xstream.alias("produto", Funcionario.class);
                fileOut = new FileOutputStream(file);
                fileOut.write(xstream.toXML(produtos).getBytes());

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

    public static ArrayList<Produto> recuperarProdutos() {

        ArrayList<Produto> produtos = new ArrayList<>();

        try {
            XStream xStream = new XStream(new Dom4JDriver());
            xStream.alias("produtos", ArrayList.class);
            xStream.processAnnotations(Produto.class);

            BufferedReader input = new BufferedReader(new FileReader(ARQUIVO_BANCO_DADOS_PRODUTO));
            produtos = (ArrayList) xStream.fromXML(input);
            input.close();

        } catch (FileNotFoundException e) {
            System.err.println("ARQUIVO NAO EXISTE AINDA: " + e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        atualizaContadorCodigos(produtos);

        return produtos;
    }

    private static void atualizaContadorCodigos(ArrayList<Produto> produto) {
        // atualizar contador de codigo de funcionario
        int contadorAtual = produto.size() + 1;
        Produto.setContador(contadorAtual);
    }

}
