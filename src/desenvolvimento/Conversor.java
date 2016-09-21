package desenvolvimento;

import java.util.Hashtable;

/**
 * @author JN
 *
 */

public class Conversor {
	private static Hashtable<String, String> tabelaConversorHexBin = new Hashtable<>();

	public static void main(String[] args){
		Conversor conv = new Conversor();//0x24410064
		String exemploHex = new String("0x10fcafe0");
		String conversao = "1101010100101010"; //converterHexBin(exemploHex);
		//System.out.println(conversao);
		conversao = converterBinDec(conversao);
		System.out.println(conversao);
	}

	public Conversor(){
		tabelaConversorHexBin.put("0", "0000");
		tabelaConversorHexBin.put("1", "0001");
		tabelaConversorHexBin.put("2", "0010");
		tabelaConversorHexBin.put("3", "0011");
		tabelaConversorHexBin.put("4", "0100");
		tabelaConversorHexBin.put("5", "0101");
		tabelaConversorHexBin.put("6", "0110");
		tabelaConversorHexBin.put("7", "0111");
		tabelaConversorHexBin.put("8", "1000");
		tabelaConversorHexBin.put("9", "1001");
		tabelaConversorHexBin.put("a", "1010");
		tabelaConversorHexBin.put("b", "1011");
		tabelaConversorHexBin.put("c", "1100");
		tabelaConversorHexBin.put("d", "1101");
		tabelaConversorHexBin.put("e", "1110");
		tabelaConversorHexBin.put("f", "1111");
	}

	//retorna a expressão que está em hexadecimal para binário
	public static String converterHexBin(String numeroHex){

		String numBin = "";
		//começa do índice 2 pq o 0x do início das instruções em hexa são 
		// desconsiderados
		for (int i = 2; i < 10; i++){ 
			numBin = numBin + tabelaConversorHexBin.get(numeroHex.charAt(i) + "");

		}
		return numBin;
	}

	//complemento a dois
	public static String converterBinDec(String numeroBin){
		String numDec = "";
		boolean isPos = true;
		int numDecimal = 0;

		String numBinP; 
		if (numeroBin.charAt(0) != '0'){
			numBinP = converterBinBinPos(numeroBin);
			isPos = false;
		} else {
			numBinP = numeroBin;
		}
		
		for (int i = numBinP.length() - 1; i >=0; i--){
			numDecimal = (int) (numDecimal + Integer.parseInt(numBinP.charAt(i) + "") * Math.pow(2, (numBinP.length() - 1) - i));
		}

		if (isPos == false){
			numDec = "- " + numDecimal;
		} else{
			numDec = "" + numDecimal;
		}
		
		return numDec;
	}
	
	private static String converterBinBinPos (String numeroBin){
		String numBin = "";
		String numPos = "";
		boolean isVaiUm = true;
		boolean isPodeSair = false;
		
		//inverte todos os números (complemento a um)
		numBin = numeroBin.replace('1', 'x');
		numBin = numBin.replace('0', '1');
		numBin = numBin.replace('x', '0');
		
		// soma um ao número achado anteriormente (agora, complemento a dois)
		for (int i = numBin.length() - 1; i >= 0; i--){
			if (numBin.charAt(i) == '0'){
				if (isVaiUm == true){
					numPos = "1" + numPos;
					isPodeSair = true;
				} else{
					numPos = "0" + numPos;
					isPodeSair = true;
				}
			} else{
				if (isVaiUm == true){
					numPos = "0" + numPos;
				} else{
					numPos = "1" + numPos;
					isPodeSair = true;
				}
			}
			
			if (isPodeSair){
				numPos = numBin.substring(0, i) + numPos;
				break;
			}
		}
		System.out.println("complemento a dois: " + numPos);
		return numPos;
	}
}
