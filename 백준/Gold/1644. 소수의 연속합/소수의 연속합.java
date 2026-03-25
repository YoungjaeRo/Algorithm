import java.io.*;
import java.util.*;

public class Main {
	/**
	 * N 이하의 소수를 구하는 공식인 에라토스테네스의 채를 활용해 풀면 훨씬 쉽다.
	 */

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());


		boolean[] isPrime = new boolean[N + 1];
		Arrays.fill(isPrime, true);

		isPrime[0] = false;
		isPrime[1] = false;

		// 에라토스테네스의 채
		for(int i = 2; i * i <= N; i++) {

			for(int j = i * i; j <= N; j = j + i) {
				isPrime[j] = false;
			}
		}

		List<Integer> primes = new ArrayList<>();

		for(int i = 2; i <= N; i++) {
			if(isPrime[i]) {
				primes.add(i);
			}
		}

		// 투포인터 시작
		int left = 0;
		int right = 0;
		int sum = 0;
		int count = 0;

		while(true) {

			if(sum >= N) {
				if(sum == N) {
					count++;
				}
				
				sum = sum - primes.get(left);
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
