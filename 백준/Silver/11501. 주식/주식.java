import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine()); // 날 수
			int[] prices = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}

			long profit = 0;      // 총 이익
			int maxPrice = 0;     // 오른쪽에서부터 본 최대 가격

			for(int i = N - 1 ; i >= 0; i--) {
				if(prices[i]> maxPrice) {  // 새로운 최대값 발견 → 이 날을 '팔기 좋은 날'로 삼음
					maxPrice = prices[i];

				} else {// maxPrice가 더 크면, 오늘 사서 나중에 maxPrice에 판다고 가정
					profit = profit + (maxPrice - prices[i]);
				}
			}

			sb.append(profit).append("\n");
		}
		System.out.println(sb);
	}
}
