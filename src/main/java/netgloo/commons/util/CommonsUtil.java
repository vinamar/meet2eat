package netgloo.commons.util;

import java.util.Random;

public class CommonsUtil {
	
	
	//static util class to generate 6 digit random code
	public static long generate6DigitRandCode(){
		  //generate temp sms code
		  Random rand = new Random();
		  long smscode =  100000 + rand.nextInt(899999);
		  return smscode;
	}
}
