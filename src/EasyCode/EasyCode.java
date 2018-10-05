/*
Essa classe inicia o programa, caso o programa não tenha um caminho do GCC
ele abrira a classe Inicializacao pra obter um caminho do GCC;
 */
package EasyCode;

import EasyCode.Ferramentas.FEBuildComp;
import EasyCode.Ferramentas.FESearchFile;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


/**
 *
 * @author CCE
 */
public class EasyCode {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
               
        try{    
            
            Scanner in = new Scanner(new FileReader("conf.txt"));
            while (in.hasNextLine()) {
                String line = in.nextLine();
                FEBuildComp.gccPath = line;   
                
            }
            
        } catch (FileNotFoundException e){
       
            Inicializacao inicio = new Inicializacao();
            inicio.setVisible(true);
            return;
            
        }
        
        Main main = new Main();
        main.setVisible(true);
        
    }
    
}
