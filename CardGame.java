import java.util.*;
import java.math.BigInteger;
 
/**
 * @author vishal.nagota
 * @desc Facebook Hacker Cup 2013 Round 1 - Card Game. Sort array a in decending order. 
 * Result will be a[0]*(n-1)C(k-1) + a[1]*(n-2)C(k-1).. till n=k 
 * Here problem is with large numbers, so we use binomial with modulus and reduce the number 
 * by computing the remainder at every step, to make sure we avoid handling large numbers
 */
public class CardGame {
  static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		int cases = Integer.parseInt(sc.nextLine());
		for(int caseId = 1; caseId <= cases; caseId++) {
			System.out.print("Case #" + caseId + ": ");
			System.out.println(solveCase());
		}
		sc.close();
	}
	public static long solveCase() {
		int n = sc.nextInt();
		int k = sc.nextInt();
		long md = 1000000007;
		long[] a = new long[n];
		long result = 0;
		for(int i=0;i<n;i++) {
			a[i]=sc.nextInt();
		}
		Arrays.sort(a);
		for(int i=n-1;i>=0;i--) {
			result += a[i]*modBinomial(i,k-1,md);
			if (result>md)
			result = result % md;
			if(i==k-1) 
				break;		
		}
		return result;
	}
	public static long modBinomial(long n, long k, long p) {
	    long numerator = 1; 
	    for (int i=0; i<k; i++) {
		numerator = (numerator * (n-i) ) % p;
	    }
	    long denominator = 1; 
	    for (int i=1; i<=k; i++) {
		denominator = (denominator * i) % p;
	    }
	    return ( numerator * modInverse(denominator,p) ) % p;
	}
 
	public static long modPow(long a, long x, long p) {
	    long res = 1;
	    while(x > 0) {
		if( x % 2 != 0) {
		    res = (res * a) % p;
		}
		a = (a * a) % p;
		x /= 2;
	    }
	    return res;
	}
 
	public static long modInverse(long a, long p) {
	    return modPow(a, p-2, p);
	}
 
}
