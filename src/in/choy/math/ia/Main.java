package in.choy.math.ia;

public class Main {
    static long n = 938065379; /* integer for factorization */
    static double aa = Math.ceil(Math.sqrt(n));
    static long a = (long)aa;
    static int ffLooped = 0;
    static int bfLooped = 0;
    static double bsquared = a * a - n;

    public static boolean sqrtnisEven(double sqrtn) {
        if (sqrtn%2==0)
            return true;
        return false;
    }

    public static boolean aIsEven() {
        double nn = (long)n;
        if((nn+1)%4==0)
            return true;
        return false;
    }
    public static void theLoop() {
        while (Math.sqrt(bsquared)%1 != 0) {
            a = a + 2; //increase sqrtn by 2
            bsquared = a * a - n; //rerun algorithm
            ffLooped++; //count the number of times calculated
        }
    }

    public static void main(String[] args) {
        final long ffstartTime = System.currentTimeMillis();
        if (aIsEven()) { //a is even
            if(sqrtnisEven(a)) {
                theLoop();
            } else {
                a++;
                theLoop();
            }
        } else { //a is odd
            if(sqrtnisEven(a)) {
            a++;
                theLoop();
            } else {
                theLoop();
            }
        }

        final long ffendTime = System.currentTimeMillis();
        System.out.println("FF: " + (a - (long)Math.sqrt(bsquared)) + " is a factor");
        System.out.println("FF: " + (a + (long)Math.sqrt(bsquared)) + " is another factor");
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