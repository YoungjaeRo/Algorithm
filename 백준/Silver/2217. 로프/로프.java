import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// 로프 알고리즘:
		// 예시: 루프들이 최대한 들어올릴 수 있는 중량
		// 26, 10 두 개의 로프를 사용하면 10*2 = 20 이 버틸 수 있는 최대 중량이다.

		// 1. 30 하나만 사용할 때 30
		// 2. 30, 26 두개 사용할 때 52
		// 3. 30, 26, 10 세개 사용할 때 30
		// 4. 30, 26, 10, 5 모두 사용할 때 20

		// 즉, 중량이 큰 로프부터 하나씩 꺼내서 계산후, 최댓값을 구하는게 좋다

		// 로프의 개수
		int N = Integer.parseInt(br.readLine());

		// 각 무게들을 저장할 배열을 생성
		Integer[] ropes = new Integer[N];

		for(int i = 0; i < N; i++) { // 무게들을 입력받고 저장하기
			ropes[i] = Integer.parseInt(br.readLine());
		}
		// 내림차순으로 정렬하기
		Arrays.sort(ropes, Collections.reverseOrder());
		int total = 0;
		for(int i = 0; i < N; i++){
			total = Math.max(total, ropes[i] * (i + 1));
		}
		System.out.println(total);
	}
}
