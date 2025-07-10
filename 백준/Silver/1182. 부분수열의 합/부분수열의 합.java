import java.util.*;
import java.io.*;

public class Main {
	static int N, S;
	static int[] arr;
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 첫줄 N, S
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		// 수열 입력받기
		st = new StringTokenizer(br.readLine());

		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());

		}

		// DFS 시작
		dfs(0, 0);

		// 합이 0일때, 공집합도 포함되므로 제외
		if(S == 0) {
			count--;

		}

		System.out.println(count);
	}


	// idx는 현재 몇번째 숫자를 보고있는지 , sum은 지금까지 고른 숫자들의 합
	// DFS로 부분수열을 구성하면서 합이 S인지 확인
	static void dfs(int idx, int sum) {
		if(idx == N) { // 모든 수를 다 탐색했을때,
			if(sum == S) { //합이 S이면 카운트  + 1
				count++;
			}
			return;
		}
		
		// 현재 원소를 선택
		dfs(idx + 1, sum + arr[idx]);
		
		// 현재 원소를 띄어넘고 다른 조합을 탐색
		dfs(idx + 1, sum);

	}
}
