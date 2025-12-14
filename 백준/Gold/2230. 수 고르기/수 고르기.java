import java.io.*;
import java.util.*;


public class Main {
	static int N; // 숫자 개수
	static int M; // M보다 차이가 커야함

	static int[] numbers;

	static int minDiff = Integer.MAX_VALUE;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		numbers = new int[N];

		// 숫자 저장하기
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(br.readLine().trim());
		}

			// 이중 for 문을 돌리면, 시간초과가 나기때문에, 투포인터로 문제를 해결한다

			// 1. 배열을 정렬한다.
			Arrays.sort(numbers);

			// 2. 투포인터 시작
			int leftPoint = 0;
			int rightPoint = 0;
			int answer = Integer.MAX_VALUE;

			while(rightPoint < N) {
				int diff = numbers[rightPoint] - numbers[leftPoint];

				if(diff < M) { // 차이가 작기 때문에, 더 늘려야한다
					rightPoint++;
				} else { // diff가 M 이상이라면, 후보군에 등록됨
					answer = Math.min(answer, diff);
					leftPoint++;  // 범위 줄여보기
				}

				// left가 right를 넘어가면 right를 따라가게 해서 diff 음수 방지 + 진행 보장
				if(leftPoint > rightPoint) {
					rightPoint = leftPoint;
				}
			}

		System.out.println(answer);

	}
}
