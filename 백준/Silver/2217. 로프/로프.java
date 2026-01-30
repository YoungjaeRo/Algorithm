import java.io.*;
import java.util.*;

public class Main {

	/**
	 * 각기 다르게 버틸 수 있는 무게가 다른 로프들이 있다.
	 * 이 로프들을 활용하여 들어올릴 수 있는 물체의 최대 중량을 구하시오
	 *
	 * 로프들이 들어올릴 수 있는 최대 중량
	 * 20, 10 이면 최대 버틸 수 있는 무게는 10 * 20  = 20
	 */
	static int N;

	static Integer[] weights;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		weights = new Integer[N];

		for(int i = 0; i < N; i++) {
			int weight = Integer.parseInt(br.readLine());
			weights[i] = weight;
		}

		// 내림차순 : 강한 로프부터 하나씩 추가해보는 게 직관적
		Arrays.sort(weights, Collections.reverseOrder());

		long answer = 0;

		for(int i = 0; i < N; i++) {
			long weakest = weights[i]; // 현재 선택한 로프중 가장 최약
			long pick = i + 1; // 선택한 로프 개수
			long canLift = weakest * pick;

			answer = Math.max(answer, canLift);
		}

		System.out.println(answer);
	}
}
