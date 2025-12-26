import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 11053과 똑같이 dp로 LIS 길이를 구한다.
	 * 추가로 "수열 복원"을 위해 prev 배열을 둔다.
	 *
	 * dp[i]   = i가 마지막인 LIS 길이
	 * prev[i] = i 바로 직전에 연결된 인덱스 (없으면 -1)
	 *
	 * prev 배열로 어디서 왔는지를 기록해서
	 * 마지막에서 역추적하여 수열을 복원
	 */

	static int N;
	static int[] arr;
	static int[] dp;
	static int[] prev; // prev[i] = i번째 원소 앞에 오는 LIS 원소의 인덱스

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		dp = new int[N];
		prev = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		// 숫자 입력하기
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 자기 자신으로 길이 1로 초기화
		Arrays.fill(dp, 1);

		// prev 배열은 i 앞에 어떤 원소가 오는지를 저장하는 배열인데, 아직 아무것도 연결되지 않았다는 뜻으로, -1로 초기화
		Arrays.fill(prev, -1);

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < i; j++) {

				// 증가하는 수열의 조건을 만족하고,
				if(arr[j] < arr[i]) {

					// 더 긴 길이로 갱신이 된다면,
					if(dp[j] + 1 > dp[i]) {
						dp[i] = dp[j] + 1;

						/*
						 * 핵심 !!
						 * i번째 원소는 j번째 원소 뒤에 붙어서
						 * LIS를 만든 것이므로,
						 * "i의 직전 원소는 j다" 라는 정보를 저장한다.
						 *
						 * 이 한 줄이 없으면 "복원"이 절대 불가능하다.
						 */
						prev[i] = j;
					}
				}
			}
		}

		/*
		 * LIS의 최대 길이와 그 마지막 인덱스 찾기
		 *
		 * dp 배열에는 "각 위치에서 끝나는 LIS 길이"가 들어있다.
		 * 그중 가장 큰 값이 전체 LIS 길이이며,
		 * 그 값을 가진 인덱스가 LIS의 마지막 원소 위치다.
		 */

		int bestLen = 0; // LIS 가장 긴 길이
		int bestIdx = 0; // LIS이 마지막 원소의 인덱스

		for(int i = 0; i < N; i++) {
			if(dp[i] > bestLen) {
				bestLen = dp[i];
				bestIdx = i;
			}
		}

		/*
		 * prev 배열을 이용해 수열 복원
		 *
		 * bestIdx부터 시작해서 prev를 따라가면
		 * LIS를 "뒤에서부터" 거꾸로(역방향) 얻을 수 있다.
		 */

		ArrayList<Integer> list = new ArrayList<>();
		// 가장 길이가 긴 LIS의 마지막 인덱스
		int cur = bestIdx;

		while(cur != -1) {
			// 현재 원소 추가
			list.add(arr[cur]);

			// 이전 원소로 이동
			cur = prev[cur];
		}

		/*
		 * 지금 lis에는
		 * [마지막 원소 → ... → 첫 원소] 순서로 들어있다.
		 * 우리가 원하는 건 반대이므로 뒤집어 준다.
		 */

		Collections.reverse(list);

		 // 이건 내림차순 정렬Collections.sort(list, Collections.reverseOrder());

		// 출력
		StringBuilder sb = new StringBuilder();

		sb.append(bestLen).append("\n");
		
		for(int x : list) {
			sb.append(x).append(" ");
		}

		System.out.println(sb);

	}

}
