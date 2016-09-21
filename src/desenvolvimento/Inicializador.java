package desenvolvimento;

import java.io.IOException;
import java.util.ArrayList;

public class Inicializador {
	 public static void main(String[] args) throws IOException{
		 ArrayList<String> instrucoes;
		 instrucoes = Leitura.lerArquivo();
		 String binario;
		 
		 for(int i=0; i<instrucoes.size();i++){
			binario = Conversor.converterHexBin(instrucoes.get(i));
			System.out.println(binario);
		 }
	 }
}
