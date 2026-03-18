import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int S;

	static int[] numbers;
	static int[] pick;
	static int count = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		numbers = new int[N];

		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		backtrack(0, 0);

		// 만드려는 숫자가 0일 경우엔, 아예 아무것도 선택을 안할 수 도 있기 때문에, 이 경우엔 공집합 경우를 빼준다.

		if(S == 0) count--;

		System.out.println(count);

	}

	static void backtrack(int depth, int sum) {
		// 끝까지 다 탐색해봤다면
		if(depth == N) {
			if(sum == S) {
				count++;
			}
			return;
		}

		// 현재 인덱스의 숫자를 선택했을 시
		backtrack(depth + 1, sum + numbers[depth]);

		// 현재 인덱스의 숫자를 선택하지 않았을 시
		backtrack(depth + 1, sum);
	}
}
