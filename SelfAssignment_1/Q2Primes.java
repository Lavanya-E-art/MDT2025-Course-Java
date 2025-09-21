//Problem 2: Language Basics//
//Readings: Language Basics : Control Flow Statements// 

//Create a new program that has the following features. Download source (JAVA).//
//(a) Uses labeled continue instead of break.//
//(b) Does not require the isPrime variable.//
//(c) When testing whether an integer is prime, it is sufficient to try and divide by integers up to the square root of the number being tested//
	
SOLUTION OF PROBLEM 2:

public class Q2Primes {
	public static void main(String[] args) {
		int nValues = 50;
		outerLoop: 
		for (int i = 2; i <= nValues; i++) {

			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {

					continue outerLoop;
				}
			}

			System.out.println(i);
		}
	}
}

/*Output for Problem 2:
47
*/
