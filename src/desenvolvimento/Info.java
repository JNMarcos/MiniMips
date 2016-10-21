/**
 * 
 */
package desenvolvimento;

import java.util.Hashtable;

/**
 * @author JN
 *
 */
public class Info {
	Hashtable<String, String> funcoesR = new Hashtable<>();
	Hashtable<String, String> opcodesI = new Hashtable<>();
	Hashtable<String, String> opcodesJ = new Hashtable<>();
	Hashtable<String, String> registradores = new Hashtable<>();
	
	Info(){
		//adição das funções tipos R
		funcoesR.put("100000", "add");
		funcoesR.put("100010", "sub");
		funcoesR.put("101010", "slt");
		funcoesR.put("100100", "and");
		funcoesR.put("100101", "or");
		funcoesR.put("100110", "xor");
		funcoesR.put("100111", "nor");
		funcoesR.put("010000", "mfhi");
		funcoesR.put("010010", "mflo");
		funcoesR.put("100001", "addu");
		funcoesR.put("100011", "subu");
		funcoesR.put("011000", "mult");
		funcoesR.put("011001", "multu");
		funcoesR.put("011010", "div");
		funcoesR.put("011011", "divu");
		funcoesR.put("000000", "sll");
		funcoesR.put("000010", "srl");
		funcoesR.put("000011", "sra");
		funcoesR.put("000100", "sllv");
		funcoesR.put("000110", "srlv");
		funcoesR.put("000111", "srav");
		funcoesR.put("001000", "jr");
		
		//adição dos opcodes de I
		opcodesI.put("001111", "lui");
		opcodesI.put("001000", "addi");
		opcodesI.put("001010", "slti");
		opcodesI.put("001100", "andi");
		opcodesI.put("001101", "ori");
		opcodesI.put("001110", "xori");
		opcodesI.put("100011", "lw");
		opcodesI.put("101011", "sw");
		opcodesI.put("000001", "bltz");
		opcodesI.put("000100", "beq");
		opcodesI.put("000101", "bne");
		opcodesI.put("001001", "addiu");
		opcodesI.put("100000", "lb");
		opcodesI.put("100100", "lbu");
		opcodesI.put("101000", "sb");
		
		//adição do opcode de J
		opcodesJ.put("000010", "j");
		opcodesJ.put("000011", "jal");
		
		// adição dos registradores
		registradores.put("00000", "0");
		registradores.put("00001", "1");
		registradores.put("00010", "2");
		registradores.put("00011", "3");
		registradores.put("00100", "4");
		registradores.put("00101", "5");
		registradores.put("00110", "6");
		registradores.put("00111", "7");
		registradores.put("01000", "8");
		registradores.put("01001", "9");
		registradores.put("01010", "10");
		registradores.put("01011", "11");
		registradores.put("01100", "12");
		registradores.put("01101", "13");
		registradores.put("01110", "14");
		registradores.put("01111", "15");
		registradores.put("10000", "16");
		registradores.put("10001", "17");
		registradores.put("10010", "18");
		registradores.put("10011", "19");
		registradores.put("10100", "20");
		registradores.put("10101", "21");
		registradores.put("10110", "22");
		registradores.put("10111", "23");
		registradores.put("11000", "24");
		registradores.put("11001", "25");
		registradores.put("11010", "26");
		registradores.put("11011", "27");
		registradores.put("11100", "28");
		registradores.put("11101", "29");
		registradores.put("11110", "30");
		registradores.put("11111", "31");
	}

}
