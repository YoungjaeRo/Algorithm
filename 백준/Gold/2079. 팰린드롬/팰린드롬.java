import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int N = s.length();
		int[] dp = new int[N+1];
		Arrays.fill(dp, 2001);
		dp[0] = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= i; j++) {
				if(isPalindrome(j,i,s)) {
					dp[i+1] = Math.min(dp[i+1],dp[j]+1); 
				}else {
					dp[i+1] = Math.min(dp[i+1],dp[i]+1);
				}
			}
		}
		System.out.println(dp[N]);
	}
	public static boolean isPalindrome(int leftIdx, int rightIdx, String s) {
		while(leftIdx<=rightIdx) {
			if(s.charAt(leftIdx) != s.charAt(rightIdx)) return false;
			leftIdx++;
			rightIdx--;
		}
		return true;
	}
}