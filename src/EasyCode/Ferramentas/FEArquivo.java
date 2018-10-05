/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EasyCode.Ferramentas;

import EasyCode.BlocosEstruturais.*;
import EasyCode.BlocosFuncionais.*;
import EasyCode.BlocosOperadores.*;
import EasyCode.Pais.Bloco;
import EasyCode.Pais.Variavel;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;

/**
 *
 * @author Endy Takano
 */
public class FEArquivo {

    static String nomeDoArquivo = "";

    public FEArquivo() {
    }

    public void salvar(ArrayList<Bloco> lista) throws IOException {

        JButton open = new JButton();
        JButton cancel = new JButton();
        JFileChooser fc = new JFileChooser();
        fc.setBackground(new Color(255, 255, 255));
        fc.setDialogTitle("Escolha o local");
        fc.setCurrentDirectory(new java.io.File("C:"));
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
            FileWriter arquivo;
            arquivo = new FileWriter(new File(fc.getSelectedFile().getPath() + "\\Arquivo.txt"));
            converterBlocoArquivo(arquivo, lista);
            arquivo.close();
        } else {
            return;
        }

    }

    public void converterBlocoArquivo(FileWriter arquivo, ArrayList<Bloco> lista) throws IOException {
        for (Bloco bloco : lista) {
            if (bloco.getFuncao().contains("Estrutura")) {
                if (bloco.getFuncao().contains("Estrutura Fim") || bloco.getFuncao().equals("Estrutura Inicio")
                        || bloco.getFuncao().equals("Estrutura Faça-Enquanto")) {
                    arquivo.write(bloco.getFuncao() + "\r\n");
                } else if (bloco.getFuncao().equals("Estrutura Para")) {
                    arquivo.write(bloco.getFuncao() + "\r\n");
                    arquivo.write(bloco.getCondicao() + "\r\n");
                    arquivo.write(bloco.getRecebe() + "\r\n");
                    arquivo.write(bloco.getAte() + "\r\n");
                } else {
                    arquivo.write(bloco.getFuncao() + "\r\n");
                    arquivo.write(bloco.getCondicao() + "\r\n");
                }
            } //////////////////////////////////////////////////////////////////
            else if (bloco.getFuncao().contains("Funcao")) {
                if (bloco.getFuncao().contains("Funcao Variavel")) {
                    arquivo.write(bloco.getFuncao() + "\r\n");
                    arquivo.write(bloco.getCondicao() + "\r\n");
                    arquivo.write(bloco.getTipo() + "\r\n");
                } else {
                    arquivo.write(bloco.getFuncao() + "\r\n");
                    arquivo.write(bloco.getCondicao() + "\r\n");
                }
            } //////////////////////////////////////////////////////////////////
            else if (bloco.getFuncao().contains("Operador")) {
                if (bloco.getN2() == null) {
                    arquivo.write(bloco.getFuncao() + "\r\n");
                    arquivo.write(bloco.getN1() + "\r\n");
                } else {
                    arquivo.write(bloco.getFuncao() + "\r\n");
                    arquivo.write(bloco.getN1() + "\r\n");
                    arquivo.write(bloco.getN2() + "\r\n");
                }
            } //////////////////////////////////////////////////////////////////
            else if (bloco.getFuncao().contains("variavel")) {
                arquivo.write(bloco.getFuncao() + "\r\n");
                arquivo.write(bloco.getTipo() + "\r\n");
                arquivo.write(bloco.getVar() + "\r\n");

            }
        }
    }

    public void carregar(ArrayList<Bloco> lista, ArrayList<Variavel> listaVar) throws FileNotFoundException {
        Scanner arq = null;
        JButton open = new JButton();
        JButton cancel = new JButton();
        JFileChooser fc = new JFileChooser();
        fc.setBackground(new Color(255, 255, 255));
        fc.setCurrentDirectory(new java.io.File("C:"));
        fc.setDialogTitle("Escolha o arquivo");

        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
            arq = new Scanner(new FileReader(fc.getSelectedFile()));
            converterArquivoBloco(arq, lista, listaVar);
        } else {
            return;
        }

    }

    public void converterArquivoBloco(Scanner in, ArrayList<Bloco> lista, ArrayList<Variavel> listaVar) {
        lista.clear();
        while (in.hasNextLine()) {
            String nome = in.nextLine();
            System.out.println("nome:" + nome);
            switch (nome) {
                case "Estrutura Enquanto":
                    lista.add(new BEnquanto(in.nextLine()));
                    break;
                case "Estrutura Fim Enquanto":
                    lista.add(new BEnquantoFim());
                    break;
                case "Estrutura Escolha":
                    lista.add(new BEscolha(in.nextLine()));
                    break;
                case "Estrutura Caso":
                    lista.add(new BEscolhaCaso(in.nextLine()));
                    break;
                case "Estrutura Fim Caso":
                    lista.add(new BEscolhaCasoFim());
                    break;
                case "Estrutura Fim Escolha":
                    lista.add(new BEscolhaFim());
                    break;
                case "Estrutura Faça-Enquanto":
                    lista.add(new BFaca());
                    break;
                case "Estrutura fim Faça-Enquanto":
                    lista.add(new BFacaEnquanto(in.nextLine()));
                    break;
                case "Estrutura Fim":
                    lista.add(new BFim());
                    break;
                case "Estrutura Inicio":
                    lista.add(new BInicio());
                    break;
                case "Estrutura Para":
                    lista.add(new BPara(in.nextLine(), Integer.parseInt(in.nextLine()), in.nextLine()));
                    break;
                case "Estrutura Fim Para":
                    lista.add(new BParaFim());
                case "Estrutura Se":
                    lista.add(new BSe(in.nextLine()));
                    break;
                case "Estrutura Fim Se":
                    lista.add(new BSeFim());
                    break;
                case "Estrutura Senao":
                    lista.add(new BSenao());
                    break;
                case "Estrutura FimSenao":
                    lista.add(new BSenaoFim());
                    break;
                case "Funcao Escreva":
                    lista.add(new BFEscrevaTXT(in.nextLine()));
                    break;
                case "Funcao Variavel Escreva":
                    lista.add(new BFEscrevaVar(in.nextLine(), in.nextLine(), listaVar));
                    break;
                case "Funcao Variavel Leia":
                    lista.add(new BFLeia(in.nextLine(), in.nextLine()));
                    break;
                case "Operador Decrementa":
                    lista.add(new BOpDecrementa(in.nextLine()));
                    break;
                case "Operador Igual":
                    lista.add(new BOpIgual(in.nextLine(), in.nextLine()));
                    break;
                case "Operador Igual Divisao":
                    lista.add(new BOpIgualDivisao(in.nextLine(), in.nextLine()));
                    break;
                case "Operador Igual Multiplicacao":
                    lista.add(new BOpIgualMultiplicacao(in.nextLine(), in.nextLine()));
                    break;
                case "Operador Igual Soma":
                    lista.add(new BOpIgualSoma(in.nextLine(), in.nextLine()));
                    break;
                case "Operador Igual Subtracao":
                    lista.add(new BOpIgualSubtracao(in.nextLine(), in.nextLine()));
                    break;
                case "Operador Igual Incrementa":
                    lista.add(new BOpIncrementa(in.nextLine()));
                    break;
                case "variavel":
                    String aux1 = in.nextLine();
                    String aux2 = in.nextLine();
                    listaVar.add(new Variavel(aux1, aux2));
                    lista.add(new Variavel(aux1, aux2));
                    break;
            }

        }
    }
}
