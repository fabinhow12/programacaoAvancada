/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import dados.Cliente;
import dados.Funcionario;
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
import static util.GerenciadorArquivo.ARQUIVO_BANCO_DADOS_XML;

/**
 *
 * @author fabio
 */
public class GeraArquivoCliente extends GerenciadorArquivo {

    public static void salvarClientes(final ArrayList<Cliente> clientes) {

        FileOutputStream fileOut = null;
        XStream xstream = new XStream();
        try {

            File file = new File(ARQUIVO_BANCO_DADOS_CLIENTE);
            if (file.isFile()) {
                System.out.println("lendo arquivo: " + file.getAbsolutePath());
                xstream.alias("clientes", List.class);
                xstream.alias("cliente", Cliente.class);
                fileOut = new FileOutputStream(file);
                fileOut.write(xstream.toXML(clientes).getBytes());

                atualizaContadorCodigos(clientes);

            } else {
                System.out.println("cria arquivo: " + file.getAbsolutePath());
                xstream.alias("clientes", List.class);
                xstream.alias("cliente", Cliente.class);
                fileOut = new FileOutputStream(file);
                fileOut.write(xstream.toXML(clientes).getBytes());

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

    public static ArrayList<Cliente> recuperarClientes() {

        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            XStream xStream = new XStream(new Dom4JDriver());
            xStream.alias("clientes", ArrayList.class);
            xStream.processAnnotations(Cliente.class);

            BufferedReader input = new BufferedReader(new FileReader(ARQUIVO_BANCO_DADOS_CLIENTE));
            clientes = (ArrayList) xStream.fromXML(input);
            input.close();

        } catch (FileNotFoundException e) {
            System.err.println("ARQUIVO NAO EXISTE AINDA: " + e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        atualizaContadorCodigos(clientes);

        return clientes;
    }

    private static void atualizaContadorCodigos(ArrayList<Cliente> clientes) {
        // atualizar contador de codigo de funcionario
        int contadorAtual = clientes.size() + 1;
        Cliente.setContador(contadorAtual);
    }

}
