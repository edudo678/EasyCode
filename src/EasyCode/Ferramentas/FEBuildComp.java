/*
 * A SIGLA FE no ínicio do nome da classe significa FERRAMENTA
 * decidi colocar isso como uma forma de deixar mais organizado e didatico
    -------------
    Atualização: 20/02/2017 - Começo da criação de uma função para procurar errors no código
    -------------
 */
package EasyCode.Ferramentas;

import EasyCode.Pais.Variavel;
import EasyCode.Pais.Bloco;
import java.awt.Color;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


/*
Classe que faz todo o processo de compilação do código
 */
public class FEBuildComp {
    //ESSA VARÍAVEL CONDUZ O CAMINHO DO GCC
    static public String gccPath = "";
    //ESSA VARÍAVEL CONDUZ O CAMINHO QUE SERÁ SALVO O ARQUIVO
    static public String codePath = "";
    //TEXTO QUE VAI SER TRANSFERÍDO PARA O .C
    static public String codigoTraduzido = "";
    
    static public String debugArduino = "";
    
    
    public void compBuild(ArrayList<Bloco> lista, ArrayList<Variavel> listaVar) throws IOException {
        System.out.println("teste");
        setCodePath();
        System.out.println("teste");
        codigoTraduzido = FEConversor.imprime(lista, listaVar, false);
        System.out.println("teste");
        criarArquivo();
        criarEXE();
        execC();
    }

    /*
    Usando o CMD, cria um arquivo C usando o código traduzido em formato dos 
    blocos para o fomarto C. O codePath (caminho do codigo) é usado para criar
    o arquivo C no determinado caminho.
     */
    public void criarArquivo() throws IOException {
        FileWriter arquivo;  
        try {  
            arquivo = new FileWriter(new File(codePath+"\\Arquivo.c"));  
            arquivo.write(codigoTraduzido);
            arquivo.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
    }   

    /*
    Usando o CMD, cria um executável usando um comando do GCC chamado -o, com a 
    sintaxe de:
    "caminho do gcc"\gcc -o "caminho do exe"\"nome do exe" "caminho do codigo"\"nome do codigo";
     */
    private void criarEXE(){  
        Process exec1;  
        try {  
            exec1 = Runtime.getRuntime().exec("gcc -o \""+codePath+"\\Arquivo\" \""+codePath+"\\Arquivo.c\"");  
            if ( exec1.waitFor() == 0)  
                System.out.println("Executado.");  
            else  
                System.out.println("ERRO");  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }
    }

    /*
    Usamos o CMD para executar EXE direto por ele.
    sintaxe:
    "Caminho do EXE"\"nome do EXE.exe"
    Nessa função ela também pega todo retorno do CMD com a compilação do arquivo
    armazenando toda saída em uma String (saida)
     */
    private void execC() {
        Process exec;
        System.out.println("executando");
        try {
            exec = Runtime.getRuntime().exec("cmd /c start cmd /c \"" + codePath + "\\Arquivo.exe\"");

            if (exec.waitFor() == 0) {
                System.out.println("Executado");
            } else {
                System.out.println("teste 2");
                System.out.println("ERRO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return;
    }

    /*
    Função para alterar o caminho do código C
     */
    public void setCodePath(){
        if(codePath.equals("")){
            JButton open = new JButton();
            JButton cancel = new JButton();
            JFileChooser fc = new JFileChooser();
            fc.setBackground(new Color(255, 255,255));
            fc.setCurrentDirectory(new java.io.File("C:/users"));
            fc.setDialogTitle("Escolha a pasta destino");
            
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
            if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION){
                codePath = fc.getSelectedFile().getAbsolutePath();
            }
//            if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION){
//                codePath = fc.getSelectedFile().getAbsolutePath();
//            }
            else {
                codePath = "";
            }
            
        }
    }
    /*
    Função para alterar o caminho do GCC
     */
    public static boolean setGCCPath() {
        // TODO add your handling code here:
        JButton open = new JButton();
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new java.io.File("C:\\Program Files (x86)"));
        fc.setDialogTitle("Altere o caminho do GCC");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
            gccPath = fc.getSelectedFile().getAbsolutePath();
        } else {
            gccPath = "";
            return false;
        }
        
        System.out.println(gccPath);
     
        FileWriter arquivo;
        try {
            arquivo = new FileWriter(new File("conf.txt"));
            arquivo.write(gccPath);
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return true;
    }

    /*
    Função para pegar o código fonte
     */
    public static String getCodigoFonte(ArrayList<Bloco> lista, ArrayList<Variavel> listaVar) {
        FEConversor.imprime(lista, listaVar,false);
        return codigoTraduzido;
    }

    //ArduinoModeON
    public void compBuildArduino(ArrayList<Bloco> lista, ArrayList<Variavel> listaVar) throws IOException, InterruptedException {
        setCodePath();
        codigoTraduzido = FEConversor.imprime(lista, listaVar, true);
        criarArquivoIno();
        System.out.println("code path:" + codePath);
        enviarArduino();
    }
    
    public void criarArquivoIno() throws IOException {
        FileWriter arquivo;  
        try {  
            arquivo = new FileWriter(new File(codePath+"\\Arquivo.ino"));  
            arquivo.write(codigoTraduzido);
            arquivo.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
    } 

    public int enviarArduino() throws IOException, InterruptedException {
        String user = System.getProperty("user.name");
        String board = null;
        String platform = null;
        String pack = null;
        String port = null;
        FileWriter arquivo;
        
        Scanner in = new Scanner(new FileReader("C:\\Users\\" + user + "\\AppData\\Local\\Arduino15\\preferences.txt"));
        
        
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if(line.contains("board=")==true) board = line.substring(6);
            if(line.contains("target_package=")) pack = line.substring(15);
            if(line.contains("target_platform=")) platform = line.substring(16);
            if(line.contains("serial.port.file=")) port = line.substring(17);
            FEBuildComp.gccPath = line;
        }

        System.out.println("board:" + board + "\npack:" + pack + "\nplatform:" + platform + "\nport:" + port);
        Process exec = Runtime.getRuntime().exec("cmd /c start cmd /c arduino\\arduino_debug --upload --board " + pack + ":" + platform + ":" + board + " --port " + port + " --verbose programa\\Arquivo.ino");
        if (exec.waitFor() == 0) {
            System.out.println("Executado.");
        } else {
            System.out.println(exec.getErrorStream().read());
            System.out.println("teste1\nERRO");
            JOptionPane.showMessageDialog(null, "Não foi possível criar o executavel");
            return 0;
        }
        return 0;
    }
    
    
    public static boolean setDebugPath() {
        // TODO add your handling code here:
        JButton open = new JButton();
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new java.io.File("C:/Users"));
        fc.setDialogTitle("Search");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
            debugArduino = fc.getSelectedFile().getAbsolutePath();
        } else {
            debugArduino = "";
            return false;
        }
        

        FileWriter arquivo;
        try {
            arquivo = new FileWriter(new File("conf.txt"));
            arquivo.write("gccPath="+gccPath+"\r\ndebugArduinoPath="+debugArduino);
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
