package desenvolvimento;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Inicializador {
	public static final int INICIO_INSTRUCAO = 0;
	public static final int FIM_INSTRUCAO = 32;
	public static final int FIM_OPCODE = 6;
	public static final String OPCODE_R = "000000"; 
	public static final String OPCODE_J = "000010";
	public static final String OPCODE_JAL = "000011";

	public static void main(String[] args) throws IOException{
		String funcao, rd, rs, rt, sh; 
		Conversor conversor = new Conversor();
		Info info = new Info();
		ArrayList<String> instrucoes;
		instrucoes = Leitura.lerArquivo();
		//contém a instrução em binário
		String binario;
		//contém a instrução em instrução humana
		String instrucao = "";
		
		PrintWriter writer = new PrintWriter("saida.txt", "UTF-8");
		  

		//contém um segmento da instrução
		String segmento;
		int intervaloInicio;
		int intervaloFim;

		for(int i=0; i<instrucoes.size()-1;i++){
			intervaloInicio = INICIO_INSTRUCAO;
			intervaloFim = FIM_OPCODE;
			binario = conversor.converterHexBin(instrucoes.get(i));
			segmento = binario.substring(intervaloInicio, intervaloFim);
			funcao = info.funcoesR.get(binario.substring(26,32) + "");
			rs = info.registradores.get(binario.substring(6,11) + "");
			rt = info.registradores.get(binario.substring(11,16)+ "");
			rd = info.registradores.get(binario.substring(16,21)+ "");
			sh = info.registradores.get(binario.substring(21,26)+ "");
			
			System.out.println(sh);
			if (segmento.equals(OPCODE_R)){
				if(funcao.equals("add") || funcao.equals("sub") || funcao.equals("slt") || funcao.equals("and") || funcao.equals("or") || funcao.equals("xor") || funcao.equals("nor") || funcao.equals("addu") || funcao.equals("subu")){
					writer.println(funcao +" "+ rd +" "+ rs +" "+rt);
				}else if(funcao.equals("mult") || funcao.equals("multu") || funcao.equals("div") || funcao.equals("divu") ){
					writer.println(funcao +" "+ rs +" "+ rt);
				}else if(funcao.equals("mfhi") || funcao.equals("mflo")){
					writer.println(funcao +" "+ rd);
				}else if(funcao.equals("sll") || funcao.equals("srl") || funcao.equals("sra")){
					writer.println(funcao +" "+ rd +" "+ rs +" "+sh);
				}else if(funcao.equals("sllv") || funcao.equals("srlv") || funcao.equals("srav")){
					writer.println(funcao +" "+ rd +" "+ rt +" "+rs);
				}else if(funcao.equals("jr")){
					writer.println(funcao +" "+ rs);
				}
			} else if (segmento.equals(OPCODE_J)  || segmento.equals(OPCODE_JAL) ){ // é uma instrução do tipo J
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
				//System.out.println(binario);
				//System.out.println(instrucao);
		}
		writer.close();
	}
}
