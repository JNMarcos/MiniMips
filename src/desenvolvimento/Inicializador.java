package desenvolvimento;

import java.io.IOException;
import java.util.ArrayList;

public class Inicializador {
	public static final int INICIO_INSTRUCAO = 0;
	public static final int FIM_INSTRUCAO = 32;
	public static final int FIM_OPCODE = 6;
	public static final String OPCODE_R = "000000"; 
	public static final String OPCODE_J = "000010";
	public static final String OPCODE_JAL = "000011";

	public static void main(String[] args) throws IOException{
		ArrayList<String> instrucoes;
		instrucoes = Leitura.lerArquivo();
		//contém a instrução em binário
		String binario;
		//contém a instrução em instrução humana
		String instrucao = "";

		//contém um segmento da instrução
		String segmento;
		int intervaloInicio;
		int intervaloFim;

		for(int i=0; i<instrucoes.size();i++){
			intervaloInicio = INICIO_INSTRUCAO;
			intervaloFim = FIM_OPCODE;
			binario = Conversor.converterHexBin(instrucoes.get(i));
			segmento = binario.substring(intervaloInicio, intervaloFim);

			if (segmento == OPCODE_R){ // é uma instrução do tipo R
				//FAÇA AQUI PARA IDENTIFICAR QUAL INSTRUÇÃO R É
				//USE O SEGMENTO PARA SELECIONAR AS OUTRAS PARTES DA STRING BINÁRIO
				// USE INTERVALOINICIO E FIM PARA ISSO (PARA ANDAR PELA STRING BINARIO
				// E PASSAR PARA O SEGMENTO)
				//...
				//instrucao = instrucao + ALGUMA COISA;
				//...
				
			} else if (segmento == OPCODE_J || segmento == OPCODE_JAL){ // é uma instrução do tipo J
				//FAÇA AQUI PARA IDENTIFICAR QUAL INSTRUÇÃO J É
				//MESMA COISA DO QUE TEM EM R
				//...
				//instrucao = instrucao + ALGUMA COISA;
				//...
			} else { // é uma instrução do tipo I
				//FAÇA AQUI PARA IDENTIFICAR QUAL INSTRUÇÃO I É
				//MESMA COISA DO QUE TEM EM R
				//...
				//instrucao = instrucao + ALGUMA COISA;
				//...
			}
				System.out.println(binario);
				System.out.println(instrucao);
		}
	}
}
