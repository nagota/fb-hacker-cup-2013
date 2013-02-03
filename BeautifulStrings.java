import java.util.*;
/**
 * @author vishal.nagota
 * @desc facebook hackers cup 2013
 * Qualification round - problem one : Beautiful Strings. 
 * Algo: Count the frequency of each letter, assign the value 26 to the most frequent, 25 to next one, etc
 */
public class BeautifulStrings {
  static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		int cases = Integer.parseInt(sc.nextLine());
		for(int caseId = 1; caseId <= cases; caseId++) {
			System.out.print("Case #" + caseId + ": ");
			System.out.println(solveCase());
		}
		sc.close();
	}
	public static int solveCase() {
		String s = sc.nextLine();
		String lower = s.toLowerCase();
		char[] c = lower.toCharArray();
		int[] freq = new int[26];
		for(int i = 0; i< c.length;i++) {
			if(c[i] <= 122) {
				if(c[i] >= 97) 
					freq[(c[i]-97)]++;   
			}        
		}
	      	Arrays.sort(freq);
		// reverse the array
		for(int i=0;i<freq.length/2;i++) {
		     // swap the elements
		     int temp = freq[i];
		     freq[i] = freq[freq.length-(i+1)];
		     freq[freq.length-(i+1)] = temp;
		}
		int result = 0;
		int count = 26;
		for(int i=0;i<freq.length;i++) {
			if(freq[i]>0) 
				result += freq[i]*count;
			else 
				break;
			count--;	
		}
		return result;
	}
}
