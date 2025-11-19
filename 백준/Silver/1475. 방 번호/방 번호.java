import java.io.*;
import java.util.*;


public class Main {
	static String N;
	static int[] arr;
	
	static int answer = 0;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String N = br.readLine();

		arr = new int[10]; // 0 ~ 9까지 숫자의 정보를 저장할 예정
		for(char c : N.toCharArray()) {
			arr[c - '0']++;
		}
		
		int sixNine = arr[6] + arr[9];
		int need69 = (sixNine + 1) / 2;
		
		arr[6] = need69;
		arr[9] = need69;
		
		for(int i : arr) {
			answer = Math.max(i, answer);
		}
		
		System.out.println(answer);
	}
}
