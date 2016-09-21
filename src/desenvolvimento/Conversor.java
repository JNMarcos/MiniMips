package desenvolvimento;

import java.util.Hashtable;

/**
 * @author JN
 *
 */

public class Conversor {
	private static Hashtable<String, String> tabelaConversorHexBin = new Hashtable<>();

	/*public static void main(String[] args){
		Conversor conv = new Conversor();//0x24410064
		String exemploHex = new String("0x0c100000");
		String conversao = converterHexBin(exemploHex);
		System.out.println(conversao);
	}*/
	
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
}
