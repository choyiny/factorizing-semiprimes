package in.choy.math.ia;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.*;

public class Main {
    
    static int n = 397927; /* integer for factorization */
    static BigInteger nBI = BigInteger.valueOf(n);
    static int ffLooped = 0;
    static int bfLooped = 0;
    static int pfLooped = 0;
    static int esLooped = 0;
    static BigInteger two=new BigInteger("2");
    static BigInteger four=new BigInteger("4");

    public static int inputSemiPrime() {
       int semiPrime = 0;
       String errorMessage = "";
       do {
        try {
             semiPrime = Integer.parseInt(JOptionPane.showInputDialog(errorMessage + "\nEnter a semiPrime"));
             errorMessage = "";
           } catch(NumberFormatException nfe) {
             errorMessage = "Invalid input. Please enter an integer";
           }
        } while(!errorMessage.isEmpty());
        return semiPrime;
    }

    public static boolean sqrtnisEven(double sqrtn) {
        if (sqrtn % 2 == 0) {
            return true;
        }
        return false;
    }

    public static boolean sqrtnisEvenBI(BigInteger sqrtn) {
        if (sqrtn.divide(two).compareTo(BigInteger.ZERO) == 0) {		//sqrtn %2 == 0
            return true;
        }
        return false;
    }
    
    public static boolean aIsGonnaBeEven() {
        if ((n + 1) % 4 == 0) {
            return true;
        }
        return false;
    }

	public static boolean aIsGonnaBeEvenBI() {
        if ((nBI.add(BigInteger.ONE)).mod(four).compareTo(BigInteger.ZERO)==0) {	//(n+1)%4==0
            return true;
        }
        return false;
    }
    
    public static boolean chkInt(BigInteger bsquared) {
        if ((sqr(new BigDecimal(bsquared)).remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO))==0) {
            return true;
        }
        return false;
    }
    
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void bruteForceMethodBI(BigInteger num) {
      BigInteger TWO = new BigInteger("2");
      BigInteger i = new BigInteger("3");

      final long bfStartTime = System.currentTimeMillis();
      while (true) {
        BigInteger tmp0 = num.mod(i);
        if (tmp0 == BigInteger.ZERO) {
          break;
        }
        i = i.add(TWO);
        bfLooped++;
      }
      final long bfEndTime = System.currentTimeMillis();

      System.out.println("BF: " + i + " is a factor");
      System.out.println("BF: " + num.divide(i) + " is another factor");
      System.out.println("BF: Total execution time: " + (bfEndTime - bfStartTime) + "ms");
      System.out.println("BF: steps involved: " + bfLooped);
    }

    public static void bruteForceMethod(int num) {
        long i = 3;
        final long bfStartTime = System.currentTimeMillis();
        while (!(num % i == 0)) {
            i = i + 2;
            bfLooped++;
        }
        final long bfEndTime = System.currentTimeMillis();
        System.out.println("BF: " + i + " is a factor");
        System.out.println("BF: " + num / i + " is another factor");
        System.out.println("BF: Total execution time: " + (bfEndTime - bfStartTime) + "ms");
        System.out.println("BF: steps involved: " + bfLooped);
    }

	public static BigDecimal sqr(BigDecimal v) {					//Babylonian Method to find square root with BigDecimal
		BigDecimal two=new BigDecimal("2.0");
		BigDecimal initial=BigDecimal.ZERO;	
		BigDecimal x=v.divide(two,RoundingMode.FLOOR);				//initial estimate of square root value
		while(!initial.equals(x)) {
			initial=x;
			x=v.divide(initial,100,RoundingMode.HALF_UP);
			x=x.add(initial);
			x=x.divide(two,100,RoundingMode.HALF_UP);
		}	
		return x;
	}

	public static void fermatsFactorizationMethodBI(BigInteger num) {
		final long ffStartTime = System.currentTimeMillis();
	    
		BigInteger a = sqr(new BigDecimal(num)).toBigInteger();		//Square root of num 
		BigInteger bsquared = a.multiply(a).subtract(num);			// a*a-num
		if(aIsGonnaBeEvenBI()) {									// chk if a is even  
			if(sqrtnisEvenBI(a)) {									// sqrt of n is even
				while (chkInt(bsquared)) {							// chk if bsquared is a perfect Integer
					a=a.add(two);
					bsquared= a.multiply(a).subtract(num);
					ffLooped++;
				}
			} else {
				a=a.add(BigInteger.ONE);							//a++
				while (chkInt(bsquared)) {											
					a=a.add(two);
					bsquared= a.multiply(a).subtract(nBI);
					ffLooped++;
				}
			}
		} else {																	
			if(sqrtnisEvenBI(a)) {									// chk if bsquared is a perfect Integer
				a=a.add(BigInteger.ONE);							//a++
				while (chkInt(bsquared)) {
					a=a.add(two);
					bsquared= a.multiply(a).subtract(num);
					ffLooped++;
				}
			} else {
				while (chkInt(bsquared)) {
					a=a.add(two);
					bsquared= a.multiply(a).subtract(nBI);
					ffLooped++;
				}
			}
		}
		final long ffEndTime = System.currentTimeMillis();
        System.out.println("FF: " + (a.subtract(sqr(new BigDecimal(bsquared)).toBigInteger())) + " is a factor");
									//a-Math.sqrt(bsquared)
        System.out.println("FF: " + (a.add(sqr(new BigDecimal(bsquared)).toBigInteger())) + " is another factor");
									//a+Math.sqrt(bsquared)
        System.out.println("FF: Total execution time: " + (ffEndTime - ffStartTime) + "ms");
        System.out.println("FF: steps involved: " + ffLooped);
}
	
    public static void fermatsFactorizationMethod(int num) {
        final long ffStartTime = System.currentTimeMillis();
        int a = (int) Math.ceil(Math.sqrt(num));
        int bsquared = a * a - num;
        if (aIsGonnaBeEven()) { //a is even
            if (sqrtnisEven(a)) { //initial sqrtn is even
                while (Math.sqrt(bsquared) % 1 != 0) {
                    a = a + 2; //increase sqrtn by 2
                    bsquared = a * a - num; //rerun algorithm
                    ffLooped++; //count the number of times calculated
                }
            } else {
                a++;
                while (Math.sqrt(bsquared) % 1 != 0) {
                    a = a + 2; //increase sqrtn by 2
                    bsquared = a * a - n; //rerun algorithm
                    ffLooped++; //count the number of times calculated
                }
            }
        } else { //a is odd
            if (sqrtnisEven(a)) { //initial sqrtn is even
                a++;
                while (Math.sqrt(bsquared) % 1 != 0) {
                    a = a + 2; //increase sqrtn by 2
                    bsquared = a * a - num; //rerun algorithm
                    ffLooped++; //count the number of times calculated
                }
            } else {
                while (Math.sqrt(bsquared) % 1 != 0) {
                    a = a + 2; //increase sqrtn by 2
                    bsquared = a * a - n; //rerun algorithm
                    ffLooped++; //count the number of times calculated
                }
            }
        }
        final long ffEndTime = System.currentTimeMillis();
        System.out.println("FF: " + (a - (long) Math.sqrt(bsquared)) + " is a factor");
        System.out.println("FF: " + (a + (long) Math.sqrt(bsquared)) + " is another factor");
        System.out.println("FF: Total execution time: " + (ffEndTime - ffStartTime) + "ms");
        System.out.println("FF: steps involved: " + ffLooped);
    }

    public static void pollardsRhoMethodBI(BigInteger n) {
  		final long pfStartTime = System.currentTimeMillis();

      // initialize variables like the int version
  		BigInteger p = BigInteger.ZERO;
  		BigInteger x = BigInteger.ONE;

  		while (true) {

        // compare p to 1
  			int res = p.compareTo(BigInteger.ONE);
  			if (res>0) {
  				break;
  			}

        // this is the same as x++
  			x = x.add(BigInteger.ONE);

        // here the formula is
        // x^4 + x^2 + 1
        // then mod that by n ( % n )
        // then gcd(n)
  			p = x.pow(4).add(x.pow(2)).add(BigInteger.ONE).mod(n).gcd(n);
        pfLooped++;
  		}
  		final long pfEndTime = System.currentTimeMillis();
      System.out.println("PF: " + p + " is a factor");
      System.out.println("PF: " + n.divide(p) + " is another factor");
      System.out.println("PF: steps involved: " + pfLooped);
      System.out.println("PF: Total execution time: " + (pfEndTime - pfStartTime) + "ms");
  	}


    public static void pollardsRhoMethod(int num) {
        final long pfStartTime = System.currentTimeMillis();
        int p = 0; //result of gcd
        int x = 1; //value to increase
        while (p <= 1) {
            x++;
            p = gcd((x * x * x * x + x * x + 1) % num, num); //(x^5+x^2+1)modn, num
            pfLooped++;
        }
        final long pfEndTime = System.currentTimeMillis();
        System.out.println("PF: " + p + " is a factor");
        System.out.println("PF: " + num / p + " is another factor");
        System.out.println("PF: steps involved: " + pfLooped);
        System.out.println("PF: Total execution time: " + (pfEndTime - pfStartTime) + "ms");
    }

	public static void sieveMethod(int num) {				
		
		
		/*	This method uses the sieve of eratosthenes with 
		 *  a little trick i learned from here :-
		 *  http://codeforces.com/blog/entry/7262 
		 *  The limitation for this method is that the biggest
		 * 	integer can only be the size of 10^7
		 * 	i.e. the maximum allocatable size to an array/list   */
		
		final long esStartTime = System.currentTimeMillis();
		int []smallestPrime=new int[num];
		esLooped=Sieve(num,smallestPrime);
		if(num!=smallestPrime[num])
		{
			int i=smallestPrime[num];
			System.out.println("ES: " + i + " is a factor");
			System.out.println("ES: " + num / i + " is another factor");
		}
		else
		{
			System.out.println("ES: The number is a prime");
		}
		final long esEndTime = System.currentTimeMillis();
		System.out.println("ES: Total execution time: " + (esEndTime - esStartTime) + "ms");
		System.out.println("ES: steps involved: " + esLooped);
	}
	
	
	public static int Sieve(int max,int smallestPrime[]) {
		int steps=0;
		boolean visited[]=new boolean[max];
		for (int even = 2; even < max; even += 2)	smallestPrime[even] = 2;	//even numbers have smallest prime factor 2
		
		for (int i = 3; i < max; i += 2) {
		
			if (!visited[i]) {
				smallesPrime[i] = i;
				for (int j = i; (j*i) < max; j += 2) {
					if (!visited[j*i]) {
						visited[j*i] = true;
						smallestPrime[j*i] = i;		//update smallest prime factor of number j*i as i
					}
				}
				steps++;
			}
		}
		return steps;
	}


    public static void main(String[] args) {
        n = inputSemiPrime();
        fermatsFactorizationMethod(n);
        bruteForceMethod(n);
        pollardsRhoMethod(n);
	sieveMethod(n);
        System.out.println("BigInteger start");
        int bfLooped = 0;
        BigInteger bn = new BigInteger(Integer.toString(n));
        bruteForceMethodBI(bn);
        pollardsRhoMethodBI(bn);
        fermatsFactorizationMethodBI(bn);
    }
}
