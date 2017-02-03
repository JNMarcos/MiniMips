package desenvolvimento;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class Inicializador {
	public static final int INICIO_INSTRUCAO = 0;
	public static final int FIM_INSTRUCAO = 32;
	public static final int FIM_OPCODE = 6;
	public static final int TAMANHO_REG = 5;
	public static final String OPCODE_R = "000000"; 
	public static final String OPCODE_J = "000010";
	public static final String OPCODE_JAL = "000011";


	public static void main(String[] args) throws IOException{
		//
		int[] registradores = new int[32];
		int[] memoria = new int[32];
		int hi = 0;
		int lo = 0;

		int caixa;

		String opcode, funcao, rd, rs, rt, sa, immediate, target;
		int rdInt, rsInt, rtInt, saInt, immediateInt, targetInt;

		Conversor conversor = new Conversor();
		Info info = new Info();

		ArrayList<String> instrucoes = Leitura.lerArquivo();
		String binario;
		String instrucao = "";
		String impressaoReg = "";
		String jump;

		for(int i=0; i < registradores.length; i++){
			registradores[i] = 0;
			memoria[i] = 0;
		}

		PrintWriter writer = new PrintWriter("saida.txt", "UTF-8");

		/*for(int PC = 0; PC< instrucoes.size()*4; PC+=4){
			System.out.println(instrucoes.get(PC/4));
			binario = conversor.converterHexBin(instrucoes.get(PC/4));
		 */

		for(int i = 0; i< instrucoes.size(); i++){
			System.out.println(instrucoes.get(i));
			binario = conversor.converterHexBin(instrucoes.get(i));
			opcode = binario.substring(INICIO_INSTRUCAO, FIM_OPCODE);


			if (opcode.equals(OPCODE_R)){
				funcao = info.funcoesR.get(binario.substring(26,32) + "");
				rs = info.registradores.get(binario.substring(6,11) + "");
				rt = info.registradores.get(binario.substring(11,16)+ "");
				rd = info.registradores.get(binario.substring(16,21)+ "");
				sa = info.registradores.get(binario.substring(21,26)+ "");

				//passando para inteiro
				rsInt = Integer.parseInt(rs);
				rtInt = Integer.parseInt(rt);
				rdInt = Integer.parseInt(rd);
				saInt = Integer.parseInt(sa);

				if (funcao != null){
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
						lo = 0;
						break;
					case "multu":
						hi = registradores[rsInt] * registradores[rtInt];
						if (hi < 0){
							hi = -1 * hi;
						}
						lo = 0;
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
						for (long j = 0; j < (registradores[rsInt] % 4294967296L/4); j++){
							intermediarioSLLV = intermediarioSLLV * 2;
						}
						registradores[rdInt] = intermediarioSLLV;
						break;
					case "srlv": //não mantém sinal
						int intermediarioSRLV = registradores[rtInt];
						if (registradores[rsInt] < 0){
							intermediarioSRLV = -1 * registradores[rtInt];
						}
						for (long j = 0; j < (registradores[rsInt] % 4294967296L/4); j++){
							intermediarioSRLV = intermediarioSRLV / 2;
						}
						registradores[rdInt] = intermediarioSRLV;
						break;
					case "srav": //mantém sinal
						int intermediarioSRAV = registradores[rtInt];
						for (long j = 0; j < (registradores[rsInt] % 4294967296L/4); j++){
							intermediarioSRAV = intermediarioSRAV / 2;
						}
						registradores[rdInt] = intermediarioSRAV;
						break;
					case "jr":
						jump = "" + registradores[Integer.parseInt(conversor.converterBinDecimal((binario.substring(26,32))))];
						break;
					}
				} else {
					System.out.println("syscall");
					writer.println("syscal");
				}

			} else if (opcode.equals(OPCODE_J)){
				opcode = info.opcodesJ.get(binario.substring(0,6) + "");
				jump = conversor.converterBinDecimal(binario.substring(6,32)); //imediato
				i = Integer.parseInt(jump);
				//PC = Integer.parseInt(jump);
				writer.println(opcode + " " + jump);
			} else if (opcode.equals(OPCODE_JAL) ){
				opcode = info.opcodesJ.get(binario.substring(0,6) + "");
				registradores[31] = i + 1; //marca a próxima instrução após sair do jal
				jump = conversor.converterBinDecimal(binario.substring(6,32)); 
				i = Integer.parseInt(jump);
				//PC = Integer.parseInt(jump);
				writer.println(opcode +" "+ jump);
			} else { 
				opcode = info.opcodesI.get(binario.substring(0,6) + "");
				rs = info.registradores.get(binario.substring(6,11) + "");
				rt = info.registradores.get(binario.substring(11,16)+ "");
				rsInt = Integer.parseInt(rs);
				rtInt = Integer.parseInt(rt);

				if(opcode.equals("addiu") || opcode.equals("addu") || opcode.equals("subu") || opcode.equals("subiu")){
					immediate = conversor.converterBinDecimal(binario.substring(16,32));//sem complemento
					immediateInt = Integer.parseInt(immediate);
				}else if (opcode.equals("lb")){
					if (binario.substring(16,32).charAt(0) == '1'){
						immediate = conversor.converterBinDec(binario.substring(16,32));//com complemento
						immediateInt = Integer.parseInt(immediate);
					} else {
						immediate = conversor.converterBinDecimal(binario.substring(16,32));//sem complemento
						immediateInt = Integer.parseInt(immediate);
					}
				}else{
					immediate = conversor.converterBinDec(binario.substring(16,32));//com complemento
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
					if (registradores[rsInt] < 0){
						i = i + immediateInt;
					}
					break;
				case "beq":
					if (registradores[rsInt] == registradores[rtInt]){
						i = i + immediateInt;
					}
					break;
				case "bne":
					if (registradores[rsInt] != registradores[rtInt]){
						i = i + immediateInt;
					}
					break;
				case "lb":
					caixa = memoria[Inicializador.carregarByte(Integer.toBinaryString(registradores[rsInt]), immediateInt, 0)];
					registradores[rtInt] = caixa;
					break;
				case "lbu":
					immediate = conversor.converterBinDecimal(binario.substring(16,32));//sem complemento
					immediateInt = Integer.parseInt(immediate);
					caixa = memoria[Inicializador.carregarByte(Integer.toBinaryString(registradores[rsInt]), immediateInt, 0)];
					registradores[rtInt] = caixa;
					break;
				case "sb":
					break;
				case "lw":
					caixa = memoria[Inicializador.carregarByte(Integer.toBinaryString(registradores[rsInt]), immediateInt, 1)];
					registradores[rtInt] = caixa;
					break;
				case "sw":
					break;				
				}
			}

			impressaoReg = "";
			for(int x = 0; x < registradores.length; x++){
				impressaoReg = impressaoReg + "$"+Integer.toString(x)+"="+Integer.toString(registradores[x])+";";
			}
			writer.println(impressaoReg);
		}
		writer.close();
		System.out.println("end");
	}

	public static int carregarByte(String numeroBin, int imed, int tipo){//tipo = 0 com sinal, tipo = 1 sem sinal
		Conversor c = new Conversor();
		int dif = 32 - numeroBin.length();
		System.out.println(numeroBin + "  " + numeroBin.length() + "  " + dif);

		for (int k = 0; k < dif; k++){
			numeroBin = "0" + numeroBin;
			System.out.println(numeroBin);
		}
		//System.out.println(imed);
		String auxiliar = "";

			for (int k = imed; k <= imed + 8; k++){
				System.out.println(auxiliar);
				auxiliar = numeroBin.charAt(k) + auxiliar;
			}
			//for (int k = 0; k < 32; k++){
				//System.out.println(auxiliar);
				//auxiliar = numeroBin.charAt(k) + auxiliar;
			//}

		System.out.println(auxiliar + "  j");
		return Integer.parseInt(auxiliar);
	}
}
