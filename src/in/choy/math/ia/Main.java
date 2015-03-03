package in.choy.math.ia;

public class Main {

    public static void main(String[] args) {

        long n = 670088347; /* integer for factorization */
        int looped = 0;

        //Divide n with all odd numbers until finding a factor
	    for (long i=3; i < n; i=i+2) {
            final long startTime = System.currentTimeMillis();
            looped += 1; //count steps
                if (n%i==0){ //n mod i = 0 (test if it is an integer)
                System.out.println(i + " is a factor");
                System.out.println(n/i + " is another factor");
                final long endTime = System.currentTimeMillis();
                System.out.println("Total execution time: " + (endTime - startTime) + "ms" );
                System.out.println("steps involved: " + looped);
                    break;
            }
        }
    }
}