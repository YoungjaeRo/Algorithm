import java.io.*;
import java.util.*;

public class Main {
	/**
	 * N개의 자연수와 자연수 M이 주어졌을때,
	 * 아래의 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오
	 * 
	 * N개의 자연수중 M개를 고른다 depth == M
	 * 
	 * 같은 수를 여러번 골라도 된다 ㅇㅇ vistied나 불필요
	 * 
	 * 고른 수열은 비내림차순 (오름차순)으로 정렬후 백트래킹 시작
	 * 
	 * 같은 숫자가 입력이 되므로 prev가 필요함
	 * 
	 * 순서가 중요하지 않기 때문에, 조합임
	 * 
	 */
	
	static int N;
	static int M;
	
	static int[] arr;
	
	static int[] pick;
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		pick = new int[M];
		
		st = new StringTokenizer(br.readLine());
		
		// 숫자 입력받기
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
		}
		
		Arrays.sort(arr); // 오름차순으로 정렬해줌
		
		backtrack(0, 0); // depth = 0, start = 0;

		System.out.println(sb.toString());
	}
	
	static void backtrack(int depth, int start) {
		if(depth == M) {
			for(int n : pick) {
				sb.append(n).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		// 같은 depth에서 같은 수로 뻗어나가는 것 방지용 변수
		
		int prev = -1;
		
		for(int i = start; i < N; i++) {
			
			if(arr[i] == prev) {
				continue;
			}

			prev = arr[i]; // prev값 업데이트 해주기
			
			pick[depth] = arr[i];
			
			
			// 중복해서 선택이 가능하기 때문에, start는 그대로
			backtrack(depth + 1, i);
		}
	}
}
