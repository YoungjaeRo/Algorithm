import java.util.*;
import java.io.*;


public class Main {
	static int N; // 1 ~ N까지의 자연수
	static int M; // M개를 고름 그중
	static int[] arr; // 답을 저장해놓을 배열
	static StringBuilder sb  = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[M];
		
		dfs(0);
		System.out.println(sb);
	}
	
	
	
	
	static void dfs(int depth) {
		
		if(depth == M) { // 종료 조건
			for(int i = 0; i < M; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			
			// 같은 숫자를 쓰는 경우
			arr[depth] = i;
			dfs(depth + 1);
		}
	}
}
