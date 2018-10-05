/*
Essa classe tem a função de converter todos os blocos para C usando uma mega maldito switch

 */
package EasyCode.Ferramentas;

import EasyCode.Pais.Variavel;
import EasyCode.Pais.Bloco;
import java.util.ArrayList;

/**
 *
 * @author CCE
 */
public class FEConversor {

    public static String imprime(ArrayList<Bloco> lista, ArrayList<Variavel> listaVar,boolean arduino) {
        int ident = 0;
        boolean estrutura = false;
        String txt = "";
        
        if(arduino==false) txt+= "#include<stdio.h>\n#include<stdlib.h>\n\n";

        for (int i = 0; i < lista.size(); i++) {
            String nome = lista.get(i).getNome();
            switch (nome) {
                case "Inicio":
                    txt += "int main() {\n";
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    ident++;
                    estrutura = true;
                    break;
                case "Se":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "if (" + lista.get(i).getCondicao() + ") {\n";
                    ident++;
                    estrutura = true;
                    break;
                case "Para":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "for (" + lista.get(i).getCondicao() + "=" + lista.get(i).getRecebe() + " ; " + lista.get(i).getCondicao()
                            + "" + lista.get(i).getAte() + " ; " + lista.get(i).getCondicao().trim() + "++ ) {\n";
                    ident++;
                    estrutura = true;
                    break;
                case "Enquanto":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "while (" + lista.get(i).getCondicao() + ") {\n";
                    ident++;
                    estrutura = true;
                    break;
                case "Escolha":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "switch (" + lista.get(i).getCondicao() + ") {\n";
                    ident++;
                    estrutura = true;
                    break;
                case "Caso":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "case " + lista.get(i).getCondicao() + ":\n";
                    estrutura = true;
                    break;
                case "Fim":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "system(\"pause\");\n";
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "return 0;\n}";
                    estrutura = false;
                    break;
                case "Variavel":
                    String tipo = "";
                    switch (lista.get(i).getTipo()) {
                        case "Inteiro":
                            tipo = "int";
                            break;
                        case "Real":
                            tipo = "float";
                            break;
                        case "Caracter":
                            tipo = "char";
                            break;
                    }
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += tipo + " " + lista.get(i).getVar() + ";\n";
                    if (ident > 1) {
                        ident--;
                    }
                    estrutura = false;
                    break;

                case "FimCaso":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "break;\n";
                    if (ident > 1) {
                        ident--;
                    }
                    estrutura = false;
                    break;
                case "Igual":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += lista.get(i).getN1() + "=" + lista.get(i).getN2() + ";\n";
                    break;
                case "IgualSoma":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += lista.get(i).getN1() + "+=" + lista.get(i).getN2() + ";\n";

                    break;
                case "IgualSubtracao":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += lista.get(i).getN1() + "-=" + lista.get(i).getN2() + ";\n";

                    break;
                case "IgualDivisao":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += lista.get(i).getN1() + "/=" + lista.get(i).getN2() + ";\n";

                    break;
                case "IgualMultiplicacao":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += lista.get(i).getN1() + "*=" + lista.get(i).getN2() + ";\n";

                    break;
                case "Incrementa":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += lista.get(i).getN1() + "++;\n";

                    break;
                case "Decrementa":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += lista.get(i).getN1() + "--;\n";

                    break;
                case "EscrevaTxt":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "printf(\"" + lista.get(i).getCondicao() + "\");\n";
                    break;
                case "EscrevaVar":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    switch (lista.get(i).getTipo()) {
                        case "Inteiro":
                            txt += "printf(\"%d\\n\"," + lista.get(i).getCondicao() + ");\n";
                            break;
                        case "Real":
                            txt += "printf(\"%f\\n\"," + lista.get(i).getCondicao() + ");\n";
                            break;
                        case "Caracter":
                            txt += "printf(\"%c\\n\"," + lista.get(i).getCondicao() + ");\n";
                            break;
                    }
                    break;
                case "Faca-Enquanto":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "do{\n";
                    ident++;
                    estrutura = true;
                    break;
                case "fimFaca-Enquanto":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "}";
                    estrutura = false;
                    if (ident > 1) {
                        ident--;
                    }
                    break;
                case "Leia":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "scanf(\"";
                    switch (lista.get(i).getTipo()) {
                        case "Inteiro":
                            txt += "%d\",";
                            break;
                        case "Real":
                            txt += "%f\",";
                            break;
                        case "Caracter":
                            txt += "%c \",";
                            break;
                    }
                    txt += "&" + lista.get(i).getCondicao() + ");\n";
                    break;
                case "Senao":
                    if (ident > 1) {
                        ident--;
                    }
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "} else {\n";
                    ident++;
                    estrutura = true;
                    break;
                case "FimSenao":
                    if (ident > 1) {
                        ident--;
                    }
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "}\n";
                    estrutura = false;
                    break;
                    
                case "Faca":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "do {\n";
                    ident++;
                    estrutura = true;
                    break;
                case "FacaEnquanto":
                    if (ident > 1) {
                        ident--;
                    }
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "} while (" + lista.get(i).getCondicao() + "); \n";
                    estrutura = false;
                    break;
                case "Configurar":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "\nvoid setup () {\n";
                    ident++;
                    estrutura = true;
                    break;
                case "PinMode":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "pinMode (" + lista.get(i).getPino() + "," + (lista.get(i).getModo().equals("ENTRADA") ? "INPUT" : "OUTPUT") + ");\n";
                    break;
                case "Repetir":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "void loop () {\n";
                    ident++;
                    estrutura = true;
                    break;
                case "DigitalWrite":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "digitalWrite (" + lista.get(i).getPino() + "," + (lista.get(i).getModo().equals("ALTO") ? "HIGH" : "LOW") + ");\n";
                    break;
                case "DigitalRead":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += lista.get(i).getVar() + " = digitalRead (" + lista.get(i).getPino() + ");\n";
                    break;
                case "AnalogWrite":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "analogWrite (" + lista.get(i).getPino() + "," + lista.get(i).getModo() + ");\n";
                    break;
                case "AnalogRead":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += lista.get(i).getVar() + " = analogRead (" + lista.get(i).getPino() + ");\n";
                    break;
                case "Esperar":
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "delay (" + lista.get(i).getVal() + ");\n";
                    break;
                 case "FimArduino":
                    ident--;
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "}\n\n";
                    estrutura=false;
                    ident--;
                    break;

                default:
                    if (estrutura == true) {
                        ident--;
                    }
                    for (int a = 0; a < ident; a++) {
                        txt += "        ";
                    }
                    txt += "}\n";
                    if (ident > 1) {
                        ident--;
                    }
                    estrutura = false;
                    break;

            }
            if (ident < 0) {
                ident = 0;
            }
        }
        return txt;
    }
}
/*
O que restou do antigo leia:
for(int a=0;a<ident;a++){txt +="        ";}
                    String leia = lista.get(i).getCondicao().replace(",", " ");
               
             String[] str = leia.split(" ");
             ArrayList var = new ArrayList();
                    
                    txt+="scanf(\"";
                  for (int j = 0; j < listaVar.size(); j++) { 
                    for (int k = 0; k < str.length; k++) {
                                      
                        if (str[k].equals(listaVar.get(j).getVar())) {
                            var.add(listaVar.get(j).getVar());
                            String replace;
                            switch (listaVar.get(j).getTipo()) {
                               case "Inteiro":
                                    replace = "%d";
                                    break;
                               case "Real":
                                    replace = "%f";
                                    break;
                               case "Caracter":
                                    replace = "%c";
                                    break;
                               default:
                                    replace = " ";
                                    break;
                            }
                            str[k] = replace;
                         
                       }
                     }
                   }
                       leia = ""; 
                      for (int l = 0; l < str.length; l++) {
                           leia += str[l];
                         }
                    txt += leia + "\",";
                       for (int k = 0; k < var.size(); k++) {
                           txt +="&"+ var.get(k);
                       }
                       txt += ");\n";
                    if(ident>1)ident--;
                   break;
*/
