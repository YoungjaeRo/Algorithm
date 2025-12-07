import java.io.*;
import java.util.*;

public class Main {
	static int N; // 카드 숫자 개수
	static int M; // 상근이가 구해야할 카드의 개수

	static int[] cards;

	public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder sb = new StringBuilder();

	// 1. 카드 개수 입력
		N = Integer.parseInt(br.readLine());

		cards = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());

		}

		// 이분탐색을 하기위해서, 정렬을 실행
		Arrays.sort(cards);


		M = Integer.parseInt(br.readLine());


		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < M; i++) {
			int x = Integer.parseInt(st.nextToken());

			int count = upperBound(x) - lowerBound(x);
			sb.append(count).append(" ");
		}

		System.out.println(sb);

	}

	// upperBound - lowerBound = 찾으려는 정수의 개수

	//  x 이상이 처음 등장하는 인덱스
	static int lowerBound(int x) {
		int left = 0;
		int right = N;

		while(left < right) { // 둘이 값이 동일해질때, 멈춤
			int mid = (left + right) / 2;

			if(cards[mid] >= x) { // 분기점이 타겟값보다 크거나 같으면, 우측 축의 범위를 좁혀야 한다
				right = mid; // mid도 포함

			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	static int upperBound(int x) {
		int left = 0;
		int right = N;


		while(left < right) {

			int mid = (left + right) / 2;

			if(cards[mid] > x) {
				right = mid; // 후보
			} else {
				left = mid + 1;
			}
		}
		return left;
	}
}
