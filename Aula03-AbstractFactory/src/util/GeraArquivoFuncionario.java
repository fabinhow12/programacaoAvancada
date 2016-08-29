/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
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
public class GeraArquivoFuncionario extends GerenciadorArquivo {

    public static void salvarFuncionarios(final ArrayList<Funcionario> funcionarios) {

        FileOutputStream fileOut = null;
        XStream xstream = new XStream();
        try {

            File file = new File(ARQUIVO_BANCO_DADOS_XML);
            if (file.isFile()) {
                System.out.println("lendo arquivo: " + file.getAbsolutePath());
                xstream.alias("funcionarios", List.class);
                xstream.alias("funcionario", Funcionario.class);
                fileOut = new FileOutputStream(file);
                fileOut.write(xstream.toXML(funcionarios).getBytes());

                atualizaContadorCodigos(funcionarios);

            } else {
                System.out.println("cria arquivo: " + file.getAbsolutePath());
                xstream.alias("funcionarios", List.class);
                xstream.alias("funcionario", Funcionario.class);
                fileOut = new FileOutputStream(file);
                fileOut.write(xstream.toXML(funcionarios).getBytes());

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

    public static ArrayList<Funcionario> recuperarFuncionarios() {

        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        try {
            XStream xStream = new XStream(new Dom4JDriver());
            xStream.alias("funcionarios", ArrayList.class);
            xStream.processAnnotations(Funcionario.class);

            BufferedReader input = new BufferedReader(new FileReader("funcionarios.xml"));
            funcionarios = (ArrayList) xStream.fromXML(input);
            input.close();

        } catch (FileNotFoundException e) {
            System.err.println("ARQUIVO NAO EXISTE AINDA: " + e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        atualizaContadorCodigos(funcionarios);

        return funcionarios;
    }

    private static void atualizaContadorCodigos(ArrayList<Funcionario> funcionarios) {
        // atualizar contador de codigo de funcionario
        int contadorAtual = funcionarios.size() + 1;
        Funcionario.setContador(contadorAtual);
    }

}
