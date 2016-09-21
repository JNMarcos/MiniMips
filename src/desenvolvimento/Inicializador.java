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
		//cont�m a instru��o em bin�rio
		String binario;
		//cont�m a instru��o em instru��o humana
		String instrucao = "";

		//cont�m um segmento da instru��o
		String segmento;
		int intervaloInicio;
		int intervaloFim;

		for(int i=0; i<instrucoes.size();i++){
			intervaloInicio = INICIO_INSTRUCAO;
			intervaloFim = FIM_OPCODE;
			binario = Conversor.converterHexBin(instrucoes.get(i));
			segmento = binario.substring(intervaloInicio, intervaloFim);

			if (segmento == OPCODE_R){ // � uma instru��o do tipo R
				//FA�A AQUI PARA IDENTIFICAR QUAL INSTRU��O R �
				//USE O SEGMENTO PARA SELECIONAR AS OUTRAS PARTES DA STRING BIN�RIO
				// USE INTERVALOINICIO E FIM PARA ISSO (PARA ANDAR PELA STRING BINARIO
				// E PASSAR PARA O SEGMENTO)
				//...
				//instrucao = instrucao + ALGUMA COISA;
				//...
				
			} else if (segmento == OPCODE_J || segmento == OPCODE_JAL){ // � uma instru��o do tipo J
				//FA�A AQUI PARA IDENTIFICAR QUAL INSTRU��O J �
				//MESMA COISA DO QUE TEM EM R
				//...
				//instrucao = instrucao + ALGUMA COISA;
				//...
			} else { // � uma instru��o do tipo I
				//FA�A AQUI PARA IDENTIFICAR QUAL INSTRU��O I �
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
