import java.io.*;
import java.util.*;

public class Main {

	static int N; // 길이
	static int S; // 목표 합

	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		arr = new int[N];

		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;
		int sum = 0;
		int minLen = Integer.MAX_VALUE;
		
		for(int right = 0; right < N; right++) {
			sum = sum + arr[right];
			
			while(sum >= S) {
				minLen = Math.min(minLen, right - left + 1);
				
				// 더 짧은 길이 도전
				sum = sum - arr[left];
				left++;
				
			}
			
		}

		System.out.println(minLen == Integer.MAX_VALUE ? 0 : minLen);
		
	}
}
