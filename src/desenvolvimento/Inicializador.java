package desenvolvimento;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Inicializador {
	public static final int INICIO_INSTRUCAO = 0;
	public static final int FIM_INSTRUCAO = 32;
	public static final int FIM_OPCODE = 6;
	public static final int TAMANHO_REG = 5;
	public static final String OPCODE_R = "000000"; 
	public static final String OPCODE_J = "000010";
	public static final String OPCODE_JAL = "000011";

	public static void main(String[] args) throws IOException{
		String opcode, funcao, rd, rs, rt, sa, immediate, target; 
		Conversor conversor = new Conversor();
		Info info = new Info();
		ArrayList<String> instrucoes;
		instrucoes = Leitura.lerArquivo();
		//contém a instrução em binário
		String binario;
		//contém a instrução em instrução humana
		String instrucao = "";


		PrintWriter writer = new PrintWriter("saida.txt", "UTF-8");
		
		for(int i=0; i<instrucoes.size()-1;i++){
			binario = conversor.converterHexBin(instrucoes.get(i));
			opcode = binario.substring(INICIO_INSTRUCAO, FIM_OPCODE);
			

			if (opcode.equals(OPCODE_R)){
				funcao = info.funcoesR.get(binario.substring(26,32) + "");
				rs = info.registradores.get(binario.substring(6,11) + "");
				rt = info.registradores.get(binario.substring(11,16)+ "");
				rd = info.registradores.get(binario.substring(16,21)+ "");
				sa = info.registradores.get(binario.substring(21,26)+ "");
				
				if(funcao.equals("add") || funcao.equals("sub") || funcao.equals("slt") || funcao.equals("and") || funcao.equals("or") || funcao.equals("xor") || funcao.equals("nor") || funcao.equals("addu") || funcao.equals("subu")){
					writer.println(funcao +" "+ rd +", "+ rs +", "+rt);
				}else if(funcao.equals("mult") || funcao.equals("multu") || funcao.equals("div") || funcao.equals("divu") ){
					writer.println(funcao +" "+ rs +", "+ rt);
				}else if(funcao.equals("mfhi") || funcao.equals("mflo")){
					writer.println(funcao +" "+ rd);
				}else if(funcao.equals("sll") || funcao.equals("srl") || funcao.equals("sra")){
					writer.println(funcao +" "+ rd +", "+ rs +", "+sa);
				}else if(funcao.equals("sllv") || funcao.equals("srlv") || funcao.equals("srav")){
					writer.println(funcao +" "+ rd +", "+ rt +", "+rs);
				}else if(funcao.equals("jr")){
					writer.println(funcao +" "+ rs);
				}
			} else if (opcode.equals(OPCODE_J)  || opcode.equals(OPCODE_JAL) ){ // é uma instrução do tipo J
				opcode = info.opcodesJ.get(binario.substring(0,6) + "");
				String jump = conversor.converterBinDecimal(binario.substring(6,32)); 
				writer.println(opcode +" "+jump);
				
			}else { 
				opcode = info.opcodesI.get(binario.substring(0,6) + "");
				rs = info.registradores.get(binario.substring(6,11) + "");
				rt = info.registradores.get(binario.substring(11,16)+ "");
				if(opcode.equals("addiu") || opcode.equals("addu") || opcode.equals("subu") || opcode.equals("subiu")){
					immediate = conversor.converterBinDecimal(binario.substring(16,32));
				}else{
					immediate = conversor.converterBinDec(binario.substring(16,32));
				}
				
				if(opcode.equals("lui")){
					writer.println(opcode +" "+ rt +" "+ immediate);
				}else if(opcode.equals("addi") || opcode.equals("slti") || opcode.equals("andi") || opcode.equals("ori") || opcode.equals("xori") || opcode.equals("addiu")){
					writer.println(opcode +" "+ rt +", "+ rs +", "+immediate);
				}else if(opcode.equals("bltz")){
					writer.println(opcode +" "+ rs +" "+ immediate);
				}else if(opcode.equals("beq") || opcode.equals("bne")){
					writer.println(opcode +" "+ rs +", "+ rt +", "+immediate);
				}else if(opcode.equals("lb") || opcode.equals("lbu") || opcode.equals("sb") || opcode.equals("lw") || opcode.equals("sw")){
					writer.println(opcode +" "+ rt +", "+ immediate +"("+rs+")");
				}
			}
			if(i == instrucoes.size()-2){
				writer.println("syscal");
			}
		}
		writer.close();
	}
}
