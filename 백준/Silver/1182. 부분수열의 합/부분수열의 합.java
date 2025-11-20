import java.util.*;
import java.io.*;


public class Main {
	static int N;
	static int S;
	static int[] arr;
	static int count;

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

	count = 0;


	backtrack(0, 0); // 현재 가리키고 있는 인덱스 번호, 지금까지 더한 수
		
	if(S == 0) {
		count--;
	}

		System.out.println(count);
	}

	static void backtrack(int idx, int cur) {
		// 꼭 종료조건이 있어야함
		if(idx == N) {
			if(cur == S) {
				count++;
			}
			return;
		}

		// 현재 숫자를 선택했을시
		backtrack(idx  + 1, cur + arr[idx]);

		// 현재 숫자를 선택하지 않았을때
		backtrack(idx  + 1, cur);
	}
}
