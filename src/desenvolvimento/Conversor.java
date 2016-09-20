package desenvolvimento;

import java.math.BigInteger;

public class Conversor {
	public static String converter(String s){
		return new BigInteger(s, 16).toString(2);
	}
}
