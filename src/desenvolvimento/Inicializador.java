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
		int[] registradores = new int[32];
		int hi = 0;
		int lo = 0;
		
		String[] registradoresStr = new String[32];
		String hiStr = "0";
		String loStr = "0";
		
		String opcode, funcao, rd, rs, rt, sa, immediate, target;
		int rdInt, rsInt, rtInt, saInt, immediateInt, targetInt;
		Conversor conversor = new Conversor();
		Info info = new Info();
		ArrayList<String> instrucoes;
		instrucoes = Leitura.lerArquivo();
		String binario;
		String instrucao = "";
		
		for(int i=0; i<registradores.length; i++){
			registradores[i] = 5;
			registradoresStr[i] = "0";
		}

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
				rsInt = Integer.parseInt(rs);
				rtInt = Integer.parseInt(rt);
				rdInt = Integer.parseInt(rd);
				saInt = Integer.parseInt(sa);

				
				if(funcao.equals("add") || funcao.equals("sub") || funcao.equals("slt") || funcao.equals("and") || funcao.equals("or") || funcao.equals("xor") || funcao.equals("nor") || funcao.equals("addu") || funcao.equals("subu")){
					writer.println(funcao +" $"+ rd +", $"+ rs +", $"+rt);
				}else if(funcao.equals("mult") || funcao.equals("multu") || funcao.equals("div") || funcao.equals("divu") ){
					writer.println(funcao +" $"+ rs +", $"+ rt);
				}else if(funcao.equals("mfhi") || funcao.equals("mflo")){
					writer.println(funcao +" $"+ rd);
				}else if(funcao.equals("sll") || funcao.equals("srl") || funcao.equals("sra")){
					writer.println(funcao +" $"+ rd +", $"+ rs +", "+sa);
				}else if(funcao.equals("sllv") || funcao.equals("srlv") || funcao.equals("srav")){
					writer.println(funcao +" $"+ rd +", $"+ rt +", $"+rs);
				}else if(funcao.equals("jr")){
					writer.println(funcao +" $"+ rs);
				}
				
				switch (funcao){
				case "add":
					registradores[rdInt] = registradores[rsInt] + registradores[rtInt];
					break;
				case "sub":
					registradores[rdInt] = registradores[rsInt] - registradores[rtInt];
					break;
				case "slt":
					if(registradores[rsInt]<registradores[rtInt]){
						registradores[rdInt] = 1;
					}else{
						registradores[rdInt] = 0;
					}
					break;
				case "or":
					if(registradores[rsInt] == 1  || registradores[rtInt] == 1){
						registradores[rdInt] = 1;
					}else{
						registradores[rdInt] = 0;
					}
					break;
				case "xor":
					if((registradores[rsInt] == 0  && registradores[rtInt] == 1) || (registradores[rsInt] == 1  && registradores[rtInt] == 0)){
						registradores[rdInt] = 1;
					}else{
						registradores[rdInt] = 0;
					}
					break;
				case "nor":
					if(registradores[rsInt] == 1  || registradores[rtInt] == 1){
						registradores[rdInt] = 0;
					}else{
						registradores[rdInt] = 1;
					}
					break;
				case "addu":
					registradores[rdInt] = registradores[rsInt] + registradores[rtInt];
					if (registradores[rdInt] < 0){
						registradores[rdInt] = -1 * registradores[rdInt];
					}
					break;
				case "subu":
					registradores[rdInt] = registradores[rsInt] - registradores[rtInt];
					if (registradores[rdInt] < 0){
						registradores[rdInt] = -1 * registradores[rdInt];
					}
					break;
				case "mult":
					hi = registradores[rsInt] * registradores[rtInt]; 
					lo = hi;
					break;
				case "multu":
					hi = registradores[rsInt] * registradores[rtInt];
					if (hi < 0){
						hi = -1 * hi;
					}
					lo = hi;
					break;
				case "div":
					if(registradores[rtInt] != 0)
						lo = registradores[rsInt]/registradores[rtInt];
					if(registradores[rtInt] != 0)
						hi =  registradores[rsInt]%registradores[rtInt];
					break;
				case "divu":
					if(registradores[rtInt] != 0)
						lo = registradores[rsInt]/registradores[rtInt];
					if(registradores[rtInt] != 0){
						hi =  registradores[rsInt]%registradores[rtInt];
						if (hi < 0){
							hi= -1 * hi;
						}
					}
					break;
				case "mfhi":
					registradores[rdInt] = hi;
					break;
				case "mflo":
					registradores[rdInt] = lo;

					break;
				case "sll":
					int intermediarioSLL = registradores[rtInt];
					for (long j = 0; j < saInt; j++){
						intermediarioSLL = intermediarioSLL * 2;
					}
					registradores[rdInt] = intermediarioSLL;
					break;
				case "srl": //não mantém sinal
					int intermediarioSRL = registradores[rtInt];
					if (registradores[rtInt] < 0){
						intermediarioSRL= -1 * registradores[rtInt];
					}
					for (long j = 0; j < saInt; j++){
						intermediarioSRL = intermediarioSRL / 2;
					}
					registradores[rdInt] = intermediarioSRL;
					break;
				case "sra": //mantém sinal
					int intermediarioSRA = registradores[rtInt];
					for (long j = 0; j < saInt; j++){
						intermediarioSRA = intermediarioSRA / 2;
					}
					registradores[rdInt] = intermediarioSRA;
					break;
				case "sllv":
					int intermediarioSLLV = registradores[rtInt];
					for (long j = 0; j < (registradores[rsInt] % 4294967296L); j++){
						intermediarioSLLV = intermediarioSLLV * 2;
					}
					registradores[rdInt] = intermediarioSLLV;
					break;
				case "srlv": //não mantém sinal
					int intermediarioSRLV = registradores[rtInt];
					if (registradores[rsInt] < 0){
						intermediarioSRLV = -1 * registradores[rtInt];
					}
					for (long j = 0; j < (registradores[rsInt] % 4294967296L); j++){
						intermediarioSRLV = intermediarioSRLV / 2;
					}
					registradores[rdInt] = intermediarioSRLV;
					break;
				case "srav": //mantém sinal
					int intermediarioSRAV = registradores[rtInt];
					for (long j = 0; j < (registradores[rsInt] % 4294967296L); j++){
						intermediarioSRAV = intermediarioSRAV / 2;
					}
					registradores[rdInt] = intermediarioSRAV;
					break;
				case "jr":
					break;
				
			}
				
			} else if (opcode.equals(OPCODE_J)  || opcode.equals(OPCODE_JAL) ){ // é uma instrução do tipo J
				opcode = info.opcodesJ.get(binario.substring(0,6) + "");
				String jump = conversor.converterBinDecimal(binario.substring(6,32)); 
				writer.println(opcode +" "+jump);
				
			}else { 
				opcode = info.opcodesI.get(binario.substring(0,6) + "");
				rs = info.registradores.get(binario.substring(6,11) + "");
				rt = info.registradores.get(binario.substring(11,16)+ "");
				rsInt = Integer.parseInt(rs);
				rtInt = Integer.parseInt(rt);
				
				if(opcode.equals("addiu") || opcode.equals("addu") || opcode.equals("subu") || opcode.equals("subiu")){
					immediate = conversor.converterBinDecimal(binario.substring(16,32));
					immediateInt = Integer.parseInt(immediate);
				}else{
					immediate = conversor.converterBinDec(binario.substring(16,32));
					immediateInt = Integer.parseInt(immediate);
				}
				
				if(opcode.equals("lui")){
					writer.println(opcode +" $"+ rt +" "+ immediate);
				}else if(opcode.equals("addi") || opcode.equals("slti") || opcode.equals("andi") || opcode.equals("ori") || opcode.equals("xori") || opcode.equals("addiu")){
					writer.println(opcode +" $"+ rt +", $"+ rs +", "+immediate);
				}else if(opcode.equals("bltz")){
					writer.println(opcode +" $"+ rs +" "+ immediate);
				}else if(opcode.equals("beq") || opcode.equals("bne")){
					writer.println(opcode +" $"+ rs +", $"+ rt +", "+immediate);
				}else if(opcode.equals("lb") || opcode.equals("lbu") || opcode.equals("sb") || opcode.equals("lw") || opcode.equals("sw")){
					writer.println(opcode +" $"+ rt +", "+ immediate +"($"+rs+")");
				}
				
				switch (opcode){
				case "lui":
					registradores[rtInt] = immediateInt;
					break;
				case "addi":
					registradores[rtInt] = registradores[rsInt] + immediateInt;
					break;
				case "slti":
					if(registradores[rsInt]<immediateInt){
						registradores[rtInt] = 1;
					}else{
						registradores[rtInt] = 0;
					}
					break;
				case "andi":
					if(registradores[rsInt] == 1 && immediateInt == 1){
						registradores[rtInt] = 1;
					}else{
						registradores[rtInt] = 0;
					}
					break;
				case "ori":
					if(registradores[rsInt] == 1  || immediateInt == 1){
						registradores[rtInt] = 1;
					}else{
						registradores[rtInt] = 0;
					}
					break;
				case "xori":
					if((registradores[rsInt] == 0  && immediateInt == 1) || (registradores[rsInt] == 1  && immediateInt == 0)){
						registradores[rtInt] = 1;
					}else{
						registradores[rtInt] = 0;
					}
					break;
				case "addiu":
					registradores[rtInt] = registradores[rsInt] + immediateInt;
					break;
				case "bltz":
					//jump
					break;
				case "beq":
					//jump
					break;
				case "bne":
					//jump
					break;
				case "lb":
					break;
				case "lbu":
					break;
				case "sb":
					break;
				case "lw":
					break;
				case "sw":
					break;				
			    }
				
			}

			String impressao = "";
			for(int x=0; x<registradores.length; x++){
				impressao = impressao + "$"+Integer.toString(x)+"="+Integer.toString(registradores[x])+";";

			}writer.println(impressao);
			
			if(i == instrucoes.size()-2){
				writer.println("syscal");
			}
		}
		writer.close();
		System.out.println("end");
	}
}
