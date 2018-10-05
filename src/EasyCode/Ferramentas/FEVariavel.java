/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EasyCode.Ferramentas;

import EasyCode.Main;
import static EasyCode.Main.listaVar;
import EasyCode.Pais.Bloco;
import EasyCode.Pais.Variavel;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Bluetooth
 */
public class FEVariavel {

    public static Variavel[] criarVariavel(ArrayList<Bloco> lista,JTextField txtVar,String tipo){
        boolean ok = true;
        if (!listaVar.isEmpty()) {
          for (Variavel v : listaVar) {
            if (txtVar.getText().trim().equals(v.getVar())) {
                    JOptionPane.showMessageDialog(null, "Duas variáveis não podem ter o mesmo nome!");
                    ok = false;
            }
          }
            if (!Character.isAlphabetic(txtVar.getText().trim().charAt(0))) { //VERIFICA SE O NOME COMECA COM NUMERO OU CARACTERE ESPECIAL
                 JOptionPane.showMessageDialog(null, "Nomes de variáveis só podem começar por letra!");
                   ok = false;
            }
                if (txtVar.getText().trim().contains(" ")) { //VERIFICA SE O NOME TEM MAIS DE UMA PALAVRA
                 JOptionPane.showMessageDialog(null, "Nomes de variáveis só podem conter uma única palavra!");
                    ok = false;
            }
            if (txtVar.getText().equals("")) { //VERIFICA SE O NOME É VAZIO
                 JOptionPane.showMessageDialog(null, "O nome não pode ser vazio!");
                 ok = false;
            }
        } 
        
        if (ok == true) {
            String[] conjunto = new String[txtVar.getText().split(",").length];
            Variavel[] vars = new Variavel[conjunto.length];
            int cont=0;
            conjunto = txtVar.getText().split(",");
            Variavel var = null;
            for(int i=0;i<conjunto.length;i++){
                var = new Variavel();
                var.setTipo(tipo);
                var.setVar(conjunto[i]);
                
                lista.add(var);
                vars[cont] = var;
                cont++;
                listaVar.add(var);
            }
            return vars;
        }
      return null;     
    }
    static public boolean validarTexto(String text){
        if(text.contains("+") ||
           text.contains("-") ||
           text.contains("=") ||
           text.contains(" ") ||
           text.contains("|") ||
           text.contains("/") ||
           text.contains("!")){
            return false;
        }
        return true;
    }
    

}
