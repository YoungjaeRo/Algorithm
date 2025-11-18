import java.io.*;
import java.util.*;


public class Main {
	static int N;
	static int M;

	static int[] arr; // 뽑은 숫자들을 저장해놓을 배열
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());


		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M];

		dfs(0);

		System.out.println(sb);

	}

	static void dfs(int depth) { // 0-- >

		//1. 종료 조건 부터
		if(depth == M) {
			for(int i : arr) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}

		for(int i = 1; i <= N; i++) {// 0
			// 같은 숫자를 쓰는 경우
			arr[depth] = i;
			dfs(depth + 1); // dfs(0) --
		}
	}
}
