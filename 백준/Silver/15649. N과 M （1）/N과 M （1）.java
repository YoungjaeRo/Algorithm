import java.util.*;
import java.io.*;


public class Main {
	static int N;
	static int M;
	
	static int[] arr; // 뽑은 숫자들을 저장할 배열 (길이 M)
	
	static boolean[] visited; // 숫자 사용 여부 체크 (1 ~ N)
	
	public static void main(String[] args) throws Exception {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine());

		        N = Integer.parseInt(st.nextToken());
		        M = Integer.parseInt(st.nextToken());
		        
		        arr = new int[M];
		        
		        visited = new boolean[N + 1]; // 1 ~ N까지 쓰기 위해서        
		        
		        dfs(0);
	}
	
	static void dfs(int depth) {
		if(depth == M) { // 숫자를 다 사용했으면,
			
			for(int i = 0; i < M; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		
		
		for(int i = 1; i <= N; i++) { // 1 ~ N까지 숫자 중에서 이번 자리(depth)에 올 숫자 고르기
			if(visited[i]) {
				continue;
			}
			
			//i 를 이번에 사용할 예정
			visited[i] = true;
			arr[depth] = i;
			
			// 다음 자리 수 채우러 감
			dfs(depth + 1);
			visited[i] = false; // 백트래킹
			
		}
	}
	

}
