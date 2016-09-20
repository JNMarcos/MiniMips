package desenvolvimento;

import java.io.IOException;
import java.util.ArrayList;

public class Inicializador {
	 public static void main(String[] args) throws IOException{
		 ArrayList<String> instrucoes = new ArrayList();
		 instrucoes = Leitura.lerArquivo();
		 String bin = Info.converterHexBin(instrucoes.get(0));
		 
		 for(int i=0; i<instrucoes.size();i++){
			String binario = Conversor.converter(instrucoes.get(i).substring(instrucoes.get(i).indexOf("x") + 1, instrucoes.get(i).length()));
			System.out.println(binario);
		 }
	 }
}
