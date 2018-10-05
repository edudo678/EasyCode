/*
Classe pai de todos os blocos que herda as principais variaveis de identificação de um bloco.
 */
package EasyCode.Pais;

import javax.swing.JPanel;

/**
 *
 * @author CCE
 */
public class Bloco extends JPanel {
    private String nome;
    private String funcao;

    public Bloco () {
    }
    
    public Bloco(String nome, String funcao) {
        this.nome = nome;
        this.funcao = funcao;
    }
     
    public String getPino() {
        return null;
    }

    public String getModo() {
        return null;
    }

    public String getNome() {
        return nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    
    public String getVariavel(){
        return null;
    }
    
     public JPanel componentes (){
        return null;
    }
      public String getCondicao () {
        return null;
    }
      
     public String getAte (){
         return null;
     }
  
     public String getRecebe (){
         return null;
     }
     
     public String getVar (){
         return null;
     } 
     
     public String getTipo (){
         return null;
     }
       public String getN1 (){
         return null;
     }  
       public String getN2 (){
         return null;
     }   
      public String getVal (){
         return null;   
     }  
}
