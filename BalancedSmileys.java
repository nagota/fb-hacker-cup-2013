import java.util.*;
import java.util.regex.*;
 
/**
 * @author vishal.nagota
 * @desc facebook hackers cup 2013
 * Qualification round - problem two : Balanced Smileys. 
 * Count all possible smileys - (n). Check current string for balanced smiley, if valid retun true. 
 * replace n smileys with blank string and check string. Replace (n-1) smileys to form n variations and check each variation for validity
 * Keep on replacing n-k smileys and checking each variation till you return true or reach k=n-1.
 */
public class BalancedSmileys {
  static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		int cases = Integer.parseInt(sc.nextLine());
		for(int caseId = 1; caseId <= cases; caseId++) {
			String s = sc.nextLine();
			System.out.print("Case #" + caseId + ": ");
			System.out.println(solveCase(s));			
		}
		sc.close();
	}
 
	public static String solveCase(String s) {
		if(s.length()==0) { 
			return "YES";
		}
		String[] tokens = s.split("(?=(:\\(|:\\)))|(?<=(:\\(|:\\)))");	
		List<Integer> tokenIndex = new ArrayList<>();
		int i = 0;
		int totalSmileyTokens = 0;
		for(String token: tokens) {
			if(token.equals(":)") || token.equals(":(")) {
				tokenIndex.add(i);
				totalSmileyTokens++;
			}
			i++;
		}
		String origStr = replaceBracs(s);
		if (checkValidString(origStr)) {
			return "YES";
		}
		if(totalSmileyTokens>0) {
			for(i=totalSmileyTokens;i>=1;i--) {
				String[] variations= getVariations(tokens,totalSmileyTokens,tokenIndex, i);
				for(String variation : variations) {
					if(variation!=null) {
						variation = replaceBracs(variation);
						if (checkValidString(variation)) {
							return "YES";
						}
					}
				}
			}
		} 
		return "NO";
	}
	public static String[] getVariations(String[] tokens, int totalSmileyTokens, List<Integer> tokenIndex, int tokensToRemove) {
		List<Set<Integer>> subTokenIndexesToRemove = getSubTokenIndexesToRemove(tokenIndex,tokensToRemove);
 		String[] variations = new String[subTokenIndexesToRemove.size()];
		int i=0;
		for(Set<Integer> subTokenIndexToRemove: subTokenIndexesToRemove) {
			try {
				variations[i]= joinTokens(tokens,subTokenIndexToRemove);
			} catch (Exception e) {
			}
			i++;
		}
		return variations;
	}
	
	public static String joinTokens(String[] tokens, Set<Integer> subTokenIndexToRemove) {
		String retrnStr = "";
		int i = 0;
		for(String token: tokens) {
			if(!subTokenIndexToRemove.contains(i))
				retrnStr+=token;
			i++;
		}
		return retrnStr;		
	}
 
	public static String replaceBracs(String s) {
		while (true) {
			int startIndex = s.indexOf("(");
			int endIndex = s.indexOf(")");
			if(startIndex>=0 && endIndex>0 && endIndex>startIndex) {
				s = s.replaceFirst("\\(","");
				s = s.replaceFirst("\\)","");
			} else {
				break;
			}
		}
		return s;
	}
 
	public static boolean checkValidString(String str) {
		//str = str.replaceAll(":\\)","");
		//str = str.replaceAll(":\\(","");
		char[] c = str.toCharArray();
		for(int i = 0; i< c.length;i++) {
			if(!((c[i] <= 122 && c[i] >= 97) || c[i]==32 || c[i]==58)) {
				return false;
			}     
		}
		return true;
	}
 
	private static void getSubTokenIndexesToRemove(List<Integer> superSet, int k, int idx, Set<Integer> current,List<Set<Integer>> solution) {
	    if (current.size() == k) {
		solution.add(new HashSet<>(current));
		return;
	    }
	    if (idx == superSet.size()) return;
	    Integer x = superSet.get(idx);
	    current.add(x);
	    getSubTokenIndexesToRemove(superSet, k, idx+1, current, solution);
	    current.remove(x);
	    getSubTokenIndexesToRemove(superSet, k, idx+1, current, solution);
	}
 
	public static List<Set<Integer>> getSubTokenIndexesToRemove(List<Integer> superSet, int k) {
	    List<Set<Integer>> res = new ArrayList<>();
	    getSubTokenIndexesToRemove(superSet, k, 0, new HashSet<Integer>(), res);
	    return res;
	}
	
}
