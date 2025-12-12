import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 게임에는 총 N개의 레벨
	 * 레벨을 클리어할때마다, 점수가 주어진다
	 * 실수로 쉬운 레벨이, 어려운 레벨보다 점수를 더 많이 받는 경우를 만들어버림
	 *
	 * 이를 해결해고자, 특정 레벨의 점수를 감소시키려고 한다.
	 * 이렇게 해서, 각 레벨을 클리어 할때, 주는 점수가 증가하게끔 (오름차순으로)
	 *
	 * 각 레벨을 클리어할때 얻는 점수가 주어졌을떄, 몇번 감소시키면 되는지를 구하는 프로그램을 구하시오
	 *
	 * 1만큼 감소시키는것이 1번이다
	 *
	 * 점수 내리는 것을 최소한으로 하는 방법을 찾으시오
	 *
	 * 투포인터로 하면 되나
	 */

	static int N;

	static int[] arr;

	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N];


		// 점수 입력
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		// 점수가 앞 -- > 뒤로 갈 수록 오름차순이라는건,
		// 뒤 -- >  앞으로 갈 수록 내림차순이라는것이다

		// 맨뒤 인덱스는 냅두고
		for(int i = N - 2; i >= 0; i--) {
			int cur = arr[i];

			int next = arr[i + 1];

			// 계속해서 탐색했을때, cur < next여야 정상임

			// 근데 오류가 난 점수 구간이라면,

			if(cur >= next) {
				int newCur = next - 1; // 가장 최소한으로 줄여나갈 예정이라서
				answer = answer + (cur - newCur); // 줄인 횟수 업데이트 해주고
				arr[i] = newCur; // 현재의 인덱스에 줄인 값으로 업데이트
			}
		}

		System.out.println(answer);

	}
}
