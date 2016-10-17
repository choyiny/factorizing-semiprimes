IB Math HL IA - Hacking RSA (kinda)
===============
Featured on [The Mathematics IA: Earning Full Marks on HL or SL Mathematics Explorations: Ideal for the INTERNATIONAL BACCALAUREATE DIPLOMA](http://www.amazon.com/gp/product/B00U4UE8EA/) as a method to increase personal engagement and a proof of authenticity.

I've created this java program as part of my International Baccalaureate Mathematics Higher Level Internal Assessment. This is a proof of concept for factorizing public keys and crack the RSA cryptographic algorithm.

Usage
----------------
Replace n with the semiprime you wish to factorize.

How does it work?
----------------
This script uses 2 methods to factorize semiprimes.
- Brute Force Method
- Fermat's Factorization Method
- Pollard's Rho Method

Limitations
----------------
The code can only factorize the maximum of a JAVA_INT, 2^31 - 1.

Sample output
----------------
````
n = 397927
FF: 433 is a factor
FF: 919 is another factor
FF: Total execution time: 0ms
FF: steps involved: 22
BF: 433 is a factor
BF: 919 is another factor
BF: Total execution time: 0ms
BF: steps involved: 215
PF: 919 is a factor
PF: 433 is another factor
PF: steps involved: 51
PF: Total execution time: 2ms
````
