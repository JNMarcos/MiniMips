package desenvolvimento;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Leitura {
	  public static ArrayList<String> lerArquivo() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("entrada.txt"));
		ArrayList<String> instrucoes = new ArrayList<String> ();
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		    	instrucoes.add(line);
		        line = br.readLine();
		    }

		} finally {
		    br.close();
		}
		return instrucoes;
	}

}
