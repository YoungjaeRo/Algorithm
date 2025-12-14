import java.io.*;
import java.util.*;

public class Main {
	/**
	 *  해당 자연수 N을 연속된 소수로 나타내는 법
	 */

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		if(N < 2) {
			System.out.println(0);
			return;
		}

		/**
		 * 해당 ~ N까지의 범위중, 소수들만 탐색하기 위해, 에라토스테네스의 채를 활용한다
		 */

		// 에라토스테네스의 채 : N 이하의 소수 구하기
		boolean[] isPrime = new boolean[N + 1];
		Arrays.fill(isPrime, true);

		isPrime[0] = false;
		isPrime[1] = false;

		/**
		 * 에라토스테네스의 채
		 */

		// 가장 중요한 부분인데, i 는 sqrt(N)까지만 보면 된다
		for(int i = 2; i * i <= N; i++) {
			if(!isPrime[i]) {
				continue;
			}

			// i * i 부터 시작해서 그 외  + i를 통해 i 배수들을 지워나간다.
			for(int j = i * i; j <= N; j = j + i) {
				isPrime[j] = false;
			}
		}

		// 소수들 리스트에 다 삽입
		ArrayList<Integer> primes = new ArrayList<>();
		for(int i = 2; i <= N; i++) {
			if(isPrime[i]) {
				primes.add(i);
			}
		}

		// 2. 투 포인터를 활용해서, 연속된 소수 합이 N 인 경우를 카운트
		int left = 0;
		int right = 0;
		int sum = 0;
		int count = 0;

		while(true) {
			if(sum >= N) {
				if(sum == N) { // 갑이 일치하, 카운트
					count++;
				}

				// sum이 너무 크거나 같으면, 왼쪽을 줄여서 합을 낮춘다
				sum = sum - primes.get(left); // 왼쪽 줄이기
				left++;

			} else {
				if(right == primes.size()) {
					break;
				}
				sum = sum + primes.get(right);
				right++;
			}
		}

		System.out.println(count);
	}
}
