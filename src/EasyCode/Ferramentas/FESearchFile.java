package EasyCode.Ferramentas;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FESearchFile {

  private String fileNameToSearch;
  private List<String> result = new ArrayList<String>();

  public String getFileNameToSearch() {
	return fileNameToSearch;
  }

  public void setFileNameToSearch(String fileNameToSearch) {
	this.fileNameToSearch = fileNameToSearch;
  }

  public List<String> getResult() {
	return result;
  }

  public static void main(String[] args) {

        result();
	
  }
  
  public static String result () {
       	FESearchFile fileSearch = new FESearchFile();

        //Diretorio / nome do arquivo
	fileSearch.searchDirectory(new File("C:/Dev-Cpp/"), "gcc.exe");

	int count = fileSearch.getResult().size();
	if(count ==0){
             return null;
	}else{
	  List<String> list = fileSearch.getResult();
          String matched = list.get(0);
                return matched.substring(0, matched.length()-7);
        }
  }
  
  public void searchDirectory(File directory, String fileNameToSearch) {

	setFileNameToSearch(fileNameToSearch);

	if (directory.isDirectory() && directory!=null) {
	    search(directory);
	} else {
	    System.out.println(directory.getAbsoluteFile() + " is not a directory!");
	}

  }

  private void search(File file) {

	if (file.isDirectory()) {
	  System.out.println("Searching directory ... " + file.getAbsoluteFile());

            //do you have permission to read this directory?
	    if (file.canRead()) {
                if (file.listFiles()!=null){                    
		for (File temp : file.listFiles()) {
		    if (temp.isDirectory()) {
			search(temp);                        
		    } else {
                        
			if (getFileNameToSearch().equals(temp.getName().toLowerCase())) {
			    result.add(temp.getAbsoluteFile().toString());
		    }
                  
		}
	    }
                }
	 } else {
		System.out.println(file.getAbsoluteFile() + "Permission Denied");
	 }
      }

  }

}