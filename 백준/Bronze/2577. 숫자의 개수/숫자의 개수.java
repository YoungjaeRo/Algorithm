import java.util.*;
import java.io.*;


public class Main {
	static int[] ans = new int[10];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		int multiple = A * B * C;
		
		String strmul = String.valueOf(multiple);
		
		for(int i = 0; i < strmul.length(); i++) {
			char c = strmul.charAt(i);
			ans[c - '0']++;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < ans.length; i++) {
			sb.append(ans[i]).append("\n");
		}
		
		System.out.print(sb);
	}
}
