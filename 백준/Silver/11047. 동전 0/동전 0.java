import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 동전의 개수를 최저로 쓰기 위해선, 단위가 큰 동전부터 우선해서 사용하면 된다
	 * (그리디) : 매 라운드 마다 최선의 선택을 하는게 더 좋다
	 */

	static int N; // 동전 가지수
	static int K; // 만들어야 하는 금액

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		List<Integer> coins = new ArrayList<>();

		for(int i = 0; i < N; i++) {
			coins.add(Integer.parseInt(br.readLine()));
		}

		// 내림 차순으로 재정렬 해주기
		Collections.sort(coins, Collections.reverseOrder());

		// 환전되는 동전의 수
		int answer = 0;

		// 잔액
		int remaining = K;

		for(int coin : coins) {

			if(coin <= remaining) {
				answer = answer + (remaining / coin);
				remaining = remaining % coin;
			}
		}

		System.out.println(answer);
	}
}
