/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dados.Produto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author fabio
 */
public class GerenciadorArquivoUnicoProduto extends GerenciadorArquivo {

    public static void salvarProdutos(final ArrayList<Produto> produtos,
            final ArrayList<Produto> listaProdutos) {

        if (produtos.size() == 0) {
            System.err.println("NAO TEM DADOS PARA SALVAR");
            return;
        }

        FileInputStream fileInput = null;
        FileOutputStream fileOut = null;

        try {

            Properties properties = new Properties();

            File file = new File(ARQUIVO_BANCO_DADOS_PRODUTO);
            if (file.isFile()) {
                System.out.println("lendo arquivo: " + file.getAbsolutePath());

                fileInput = new FileInputStream(file);
                properties.loadFromXML(fileInput);

                // somente adicionar os novos
                for (Produto produto : produtos) {
                    if (!listaProdutos.contains(produto)) {

                        properties.setProperty(Produto.CODIGO + produto.getCodigo(), "" + produto.getCodigo());
                        properties.setProperty(Produto.NOME + produto.getCodigo(), produto.getNome());
                        properties.setProperty(Produto.PRECOVENDA + produto.getCodigo(), String.valueOf(produto.getPrecoVenda()));
                        properties.setProperty(Produto.PRECOCUSTO + produto.getCodigo(), String.valueOf(produto.getPrecoCusto()));

                    }
                }

                atualizaContadorCodigos(produtos);

            } else {
                System.out.println("cria arquivo: " + file.getAbsolutePath());
                for (Produto produto : produtos) {
                    properties.setProperty(Produto.CODIGO + produto.getCodigo(), "" + produto.getCodigo());
                    properties.setProperty(Produto.NOME + produto.getCodigo(), produto.getNome());
                    properties.setProperty(Produto.PRECOVENDA + produto.getCodigo(), String.valueOf(produto.getPrecoVenda()));
                    properties.setProperty(Produto.PRECOCUSTO + produto.getCodigo(), String.valueOf(produto.getPrecoCusto()));

                }
            }

            // salva no XML
            fileOut = new FileOutputStream(file);

            Date myDate = new Date();
            String simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(myDate);
            System.out.println("Dados Salvos: " + simpleDateFormat);

            properties.storeToXML(fileOut, "ultima atualizacao: " + simpleDateFormat);

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo ainda nao existe: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (fileInput != null) {
                    fileInput.close();
                }

                if (fileInput != null) {
                    fileOut.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static ArrayList<Produto> recuperarProdutos() {

        ArrayList<Produto> produtos = new ArrayList<>();
        FileInputStream fileInput = null;

        try {
            File file = new File(ARQUIVO_BANCO_DADOS_PRODUTO);
            fileInput = new FileInputStream(file);

            System.out.println("lendo arquivo existente: " + file.getAbsolutePath());

            Properties properties = new Properties();
            properties.loadFromXML(fileInput);

            int size = properties.size();

            // percorre a lista 
            for (int i = 1; i <= size; i++) {

                // se contem uma chave inicial, obtem os dados restantes desta mesma chave 
                if (properties.containsKey(Produto.CODIGO + i)) {
                    // cria objeto
                    Produto produto = new Produto();
                    // seta objeto com os dados do arquivo
                    produto.setCodigo(Integer.parseInt(properties.getProperty(Produto.CODIGO + i)));
                    produto.setNome(properties.getProperty(Produto.NOME + i));
                    produto.setPrecoVenda(Double.parseDouble(properties.getProperty(Produto.PRECOVENDA + i)));
                    produto.setPrecoCusto(Double.parseDouble(properties.getProperty(Produto.PRECOCUSTO + i)));
                    // seta o ArrayList com objeto novo
                    produtos.add(produto);
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("ARQUIVO NAO EXISTE AINDA: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (fileInput != null) {
                    fileInput.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        atualizaContadorCodigos(produtos);

        return produtos;
    }

    private static void atualizaContadorCodigos(ArrayList<Produto> produtos) {
        int contadorAtual = produtos.size() + 1;
        Produto.setContador(contadorAtual);
    }
}
