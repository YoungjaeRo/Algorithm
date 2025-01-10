import java.util.*;
import java.io.*;
public class Main {
	static final int VOWEL_CNT = 5;
	static Map<Character,Integer> map = new HashMap<Character,Integer>(); 
	static int N; 
	static String[] arr;
	static int[][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new String[N];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine();
		}
		map.put('A', 1);
		map.put('E', 2);
		map.put('I', 3);
		map.put('O', 4);
		map.put('U', 5);
		// 처음 시작을 index = 0으로 표현하기 위해 dp칸을 한칸 더 크게 설정, map의 값도 1부터 설정
		dp = new int[VOWEL_CNT+1][1 << N];
		for (int i = 0; i < VOWEL_CNT+1; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(recursion(0,0));
	}
	
	public static int recursion(int lastIdx, int status) {
		if(dp[lastIdx][status] != -1) return dp[lastIdx][status];
		
		dp[lastIdx][status] = 0;
		for (int i = 0; i < N; i++) {
			String s = arr[i];
			if((status & 1 << i) == 0 
					// 처음 시작(lastIdx가 0)에는 아무 값이나 다 가능
					&& (lastIdx == 0 || lastIdx == map.get(s.charAt(0)))) {
				dp[lastIdx][status] = Math.max(dp[lastIdx][status],
						s.length() + recursion(map.get(s.charAt(s.length()-1)), status | 1 << i));
			}
		}
        
		return dp[lastIdx][status];
	}
}