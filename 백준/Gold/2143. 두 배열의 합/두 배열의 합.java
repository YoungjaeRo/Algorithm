import java.io.*;
import java.util.*;

public class Main {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		long T = Long.parseLong(br.readLine());

		int N = Integer.parseInt(br.readLine());

		long[] A = new long[N];

		st = new StringTokenizer(br.readLine());

		// A 쪽 배열 값 주입
		for(int i = 0; i < N; i++) {
			A[i] = Long.parseLong(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());

		long[] B = new long[M];

		st = new StringTokenizer(br.readLine());

		// B쪽 배열 값 주입
		for(int i = 0; i < M; i++) {
			B[i] = Long.parseLong(st.nextToken());
		}

		// 1. A의 모든 연속 부분합 저장
		ArrayList<Long> subA = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			long sum = 0;
			for(int j = i; j < N; j++) {
				sum = sum + A[j];
				subA.add(sum);
			}
		}

		// 2. B의 모든 연속 부분합 저장
		ArrayList<Long> subB = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			long sum = 0;
			for(int j = i; j < M; j++) {
				sum = sum + B[j];
				subB.add(sum);
			}
		}

		// 3. B 부분합 정렬
		Collections.sort(subB);

		// 4. A의 각 부분합에 대해 B에서 보완값 개수 세기
		long answer = 0;

		for(long a : subA) {
			long need = T - a;

			int left = lowerBound(subB, need);
			int right = upperBound(subB, need);

			answer = answer + (right - left);

		}

		System.out.println(answer);
	}

	static int lowerBound(List<Long> list, long need) {
		// need 이상이 가장 처음 나오는 인덱스
		int left = 0;
		int right = list.size();

		while(left < right) {
			int mid = (left + right) / 2;

			if(list.get(mid) >= need) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}

		return left;
	}

	static int upperBound(List<Long> list, long need) {

		// need 초과 가장 처음 나오는 인덱스

		int left = 0;
		int right = list.size();

		while(left < right) {
			int mid = (left + right) / 2;

			if(list.get(mid) > need) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}

		return left;
	}
}
