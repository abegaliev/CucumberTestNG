package utilities;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class Num {

	private static Random random = new Random();
	 
	
	/**
	 * Returns random  number(integer) between 0 and 
	 * upper range(exclusive). 
	 * 
	 * @param upper. Must be positive.
	 * @return int value
	 * 				- between 0 and upperRange (exclusive)
	 * 
	 * @throws IllegalArgumentException if upper range is not positive.
	 */
	public static int getRandomInt(int upper) {
		if(upper < 0 ) {
			throw new IllegalArgumentException("Upper range Must be positive: "+ upper );
		}
		return (random.nextInt(upper));
	}
	
	
	/**
	 * Returns random  number(integer) between lower(inclusive) and 
	 * upper range(exclusive). 
	 *
	 * @param lower - must be positive.
	 * @param upper - must be greater than lower.
	 * @return integer
	 * 
	 * @throws IllegalArgumentException if upper range is less than lower range.
	 */
	public static int getRandomInt(int lower, int upper) {
		if(upper < lower || upper == lower) {
			throw new IllegalArgumentException("Upper range must be greater than lower range: " + lower +" == "+upper );
		}
		int num = random.nextInt((upper - lower) + 1) + lower;
		return num;
	}
	
	
	/**
	 * Returns random double value between 0 and 
	 * upper range(exclusive). 
	 * 
	 * @param upper. Must be positive.
	 * @return int value
	 * 				- between 0 and upperRange (exclusive)
	 * 
	 * @throws IllegalArgumentException if upper range is not positive.
	 */
	public static double getRandomDouble(double upper) {
		double num =  Math.round((ThreadLocalRandom.current().nextDouble(upper))*100.0) /100.0;
		return num;
	}
	
	
	/**
	 * Returns random  number(integer) between lower(inclusive) and 
	 * upper range(exclusive). 
	 *
	 * @param lower - must be positive.
	 * @param upper - must be greater than lower.
	 * @return integer
	 * 
	 * @throws IllegalArgumentException if upper range is less than lower range.
	 */
	public static double getRandomDouble(double lower, double upper) {
		double num = Math.round((ThreadLocalRandom.current().nextDouble(lower, upper))*100.0) /100.0;
		return num;
	}
	
	

}
