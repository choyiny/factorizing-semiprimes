package in.choy.math.ia;

public class Main {

    public static void main(String[] args) {

        long n = 938065379; /* integer for factorization */
        long sqrtn = 30628; /* square root of the integer for factorization */
        int ffLooped = 0;
        int bfLooped = 0;
        long bsquared = sqrtn * sqrtn - n;
        final long ffstartTime = System.currentTimeMillis();
        while (Math.sqrt(bsquared)%1 != 0) {
            sqrtn = sqrtn+1; //increase sqrtn by 1
            bsquared = sqrtn * sqrtn - n; //rerun algorithm
            ffLooped++; //count the number of times calculated
        }

        final long ffendTime = System.currentTimeMillis();
        System.out.println("FF: " + (sqrtn - (long)Math.sqrt(bsquared)) + " is a factor");
        System.out.println("FF: " + (sqrtn + (long)Math.sqrt(bsquared)) + " is another factor");
        System.out.println("FF: Total execution time: " + (ffendTime - ffstartTime) + "ms" );
        System.out.println("FF: steps involved: " + ffLooped);

        //Divide n with all odd numbers until finding a factor
        long i = 3;
        final long bfstartTime = System.currentTimeMillis();
        while(!(n%i==0)) {
            i = i + 2;
            bfLooped++;
            }
            final long bfendTime = System.currentTimeMillis();
            System.out.println("BF: " + i + " is a factor");
                System.out.println("BF: " + n/i + " is another factor");
                System.out.println("BF: Total execution time: " + (bfendTime - bfstartTime) + "ms" );
                System.out.println("BF: steps involved: " + bfLooped);
        }
    }