/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dados.Cliente;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;


/**
 *
 * @author fabio
 */
public class GerenciadorArquivoUnicoCliente extends GerenciadorArquivo{
    
    private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    public static void salvarClientes(final ArrayList<Cliente> clientes,
            final ArrayList<Cliente> listaClientes) {

        if (clientes.size() == 0) {
            System.err.println("NAO TEM DADOS PARA SALVAR");
            return;
        }

        FileInputStream fileInput = null;
        FileOutputStream fileOut = null;

        try {

            Properties properties = new Properties();

            File file = new File(ARQUIVO_BANCO_DADOS_CLIENTE);
            if (file.isFile()) {
                System.out.println("lendo arquivo: " + file.getAbsolutePath());

                fileInput = new FileInputStream(file);
                properties.loadFromXML(fileInput);

                // somente adicionar os novos
                for (Cliente cliente : clientes) {
                    if (!listaClientes.contains(cliente)) {

                        properties.setProperty(Cliente.CODIGO + cliente.getCodigo(), "" + cliente.getCodigo());
                        properties.setProperty(Cliente.NOME + cliente.getCodigo(), cliente.getNome());
                        properties.setProperty(Cliente.CPF + cliente.getCodigo(), cliente.getCpf());
                        
                        properties.setProperty(Cliente.DATANASC + cliente.getCodigo(), String.valueOf(formato.format(cliente.getDataNascimento())));

                    }
                }

                atualizaContadorCodigos(clientes);

            } else {
                System.out.println("cria arquivo: " + file.getAbsolutePath());
                for (Cliente cliente : clientes) {
                    properties.setProperty(Cliente.CODIGO + cliente.getCodigo(), "" + cliente.getCodigo());
                    properties.setProperty(Cliente.NOME + cliente.getCodigo(), cliente.getNome());
                    properties.setProperty(Cliente.CPF + cliente.getCodigo(), cliente.getCpf());
                    properties.setProperty(Cliente.DATANASC + cliente.getCodigo(), String.valueOf(formato.format(cliente.getDataNascimento())));

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
    
    
    public static ArrayList<Cliente> recuperarClientes() throws ParseException {

        ArrayList<Cliente> clientes = new ArrayList<>();
        FileInputStream fileInput = null;

        try {
            File file = new File(ARQUIVO_BANCO_DADOS_CLIENTE);
            fileInput = new FileInputStream(file);

            System.out.println("lendo arquivo existente: " + file.getAbsolutePath());

            Properties properties = new Properties();
            properties.loadFromXML(fileInput);

            int size = properties.size();

            // percorre a lista 
            for (int i = 1; i <= size; i++) {

                // se contem uma chave inicial, obtem os dados restantes desta mesma chave 
                if (properties.containsKey(Cliente.CODIGO + i)) {
                    // cria objeto
                    Cliente cliente = new Cliente();
                    // seta objeto com os dados do arquivo
                    cliente.setCodigo(Integer.parseInt(properties.getProperty(Cliente.CODIGO + i)));
                    cliente.setNome(properties.getProperty(Cliente.NOME + i));
                    cliente.setCpf(properties.getProperty(Cliente.NOME + i));
                    cliente.setDataNascimento(formato.parse(properties.getProperty(Cliente.DATANASC + i)));
                    
                    
                     
                    // seta o ArrayList com objeto novo
                    clientes.add(cliente);
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

        atualizaContadorCodigos(clientes);

        return clientes;
    }
    

    private static void atualizaContadorCodigos(ArrayList<Cliente> clientes) {
        int contadorAtual = clientes.size() + 1;
        Cliente.setContador(contadorAtual);
    }



}
