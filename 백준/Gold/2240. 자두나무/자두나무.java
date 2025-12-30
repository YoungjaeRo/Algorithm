import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 매초마다, 두개의 나무 중 하나의 나무에서 열매가 떨어짐
	 * 열매가 떨어지는 순간, 자두가 나무 아래에 서있으면, 자두는 그 열매를 먹을 수 있다
	 *
	 * 자두는 하나의 나무에 서있다가, 빠르게 다른 나무 아래로 이동할 수 있다
	 * 하지만 체력적인 이슈로 많이 움직일 수는 없다
	 *
	 * 과일 자두는 T초 동안 떨어지게 된다
	 *
	 * 사람 자두는 최대 W 번 만 움직이고 싶다
	 *
	 * 매초마다, 둘중 어느 나무에서 자두가 떨어질지에 대한 정보가 주어졌을때, 자두가 받을 수 있는
	 * 자두의 개수를 구하시오
	 *
	 * 해당 문제가 DP 인 이유
	 * 1. 현재 상태가 미래에 영향을 끼친다
	 * 2. 최대 또는 최소를 구한다
	 * 3. 제한된 자원이 있다
	 */

	static int T; // 자두가 떨어지는데 걸리는 시간
	static int W; // 자두의 나무간 이동 횟수

	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken()); // 시간
		W = Integer.parseInt(st.nextToken()); // 최대 이동 횟수

		// T초동안 자두가 어디로 떨어지는지 저장하는 배열

		int[] drop = new int[T + 1];

		for(int i = 1; i <= T; i++) {
			drop[i] = Integer.parseInt(br.readLine());
		}

		/**
		 * dp[t][w]
		 * t초까지 진행했을때, w번을 이동해서 먹을 수 있는 자두의 최대의 개수
		 *
		 * 위치는 따로 저장하지 않음
		 * w가 짝수면 1번나무
		 * w가 홀수면 2번나무
		 */

		int[][] dp = new int[T + 1][W + 1];

		// 시간은 1초부터 시작
		for(int t = 1; t <= T; t++) {

			// 이동 횟수는 0 ~ W까지 가능
			for(int w = 0; w <= W; w++) {
				/**
				 * 현재 위치 결정하기
				 *  처음 위치는 1번 나무
				 */

				int curTree;

				if(w % 2 == 0) {
					curTree = 1; // 1번나무
				} else {
					curTree = 2; // 2번나무
				}

				/**
				 * 이번 초에 자두를 먹을 수 있는지 확인하기
				 * 현재 위치 == 떨어지는 나무면  + 1
				 */

				int gain = 0;
				if(curTree == drop[t]) {
					gain = 1;
				}

				/**
				 * 움직이지 않는 경우
				 * 이동횟수 그대로
				 * 위치 그대로
				 */

				// t초까지 진행했을때, w번을 이동해서 먹을 수 있는 자두의 최대의 개수
				dp[t][w] = dp[t - 1][w] + gain;

				/**
				 * 움직이는 경우
				 * 이동횟수  + 1
				 *
				 */

				if(w > 0) {
					dp[t][w] = Math.max(dp[t][w], dp[t-1][w-1] + gain);
					}
				}
			}

		/**
		 * 마지막 시간 T에서
		 * 이동 횟수 0 ~ W중 최댓값이 정답
		 */

		int answer = 0;
		for(int w = 0; w <= W; w++) {
			if(dp[T][w] > answer) {
				answer = dp[T][w];
			}
		}

		System.out.println(answer);
		
		}
			
	}
