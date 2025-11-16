import java.util.*;

import java.io.*;


public class Main {
	/**
	 * 1. 길이가 다르면, 길이 오름차순
	 * 2. 길이가 같으면, A의 모든 자리스의 합과 B의 모든 자리수의 합을 비교해서 작은합(숫자인것만 더함)
	 * 3. 사전순으로 비교
	 */
	
	static int N;
	
	static int sumDigits(String str) {
		int sum = 0;
		
		for(int i = 0; i < str.length(); i++) {
			
			char c = str.charAt(i);
			
			// 문자들은 걸러냄
			if(c >= '0' && c <= '9') {
				sum = sum + (c - '0');
			}
			
		}
		return sum;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		String[] arr = new String[N];
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = br.readLine();
			
		}
		
		// 정렬시작, 배열이니까, Arrays.sort() 가능함
		Arrays.sort(arr, (a, b) -> {
			// 길이 오름차순
			if(a.length() != b.length()) {
				return a.length() - b.length();
				
			}
			
			// 2. 숫자 합 오름차순
			int sumA = sumDigits(a);
			int sumB = sumDigits(b);
			
			if(sumA != sumB) {
				return sumA - sumB;
			}
			
			// 사전 순 오름차순
			return a.compareTo(b);
			
		});
		
		for(String ans : arr) {
			System.out.println(ans);
		}
	}
}
