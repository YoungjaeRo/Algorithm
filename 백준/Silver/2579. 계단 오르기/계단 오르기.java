import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		int N = Integer.parseInt(br.readLine()); // 계단점수

		int[] a = new int[N + 1];  // 1- based

		for(int i = 1; i <= N; i++) {
			a[i] = Integer.parseInt(br.readLine());
		}

		// N이 1,2,3 일때는, 예외적으로 바로 처리함
		if(N == 1) {
			System.out.println(a[1]);
			return;
		} else if(N == 2) {
			System.out.println(a[1] + a[2]);
			return;
		} else if(N == 3) {
			System.out.println(Math.max(a[1] + a[3], a[2] + a[3]));
			return;
		}

		int[] dp = new int[N + 1];

		// 기저값
		dp[1] = a[1];
		dp[2] = a[1] + a[2];
		dp[3] = Math.max(a[1] + a[3], a[2] + a[3]);

		for(int i = 4; i <= N; i++) {
			dp[i] = Math.max(dp[i-2] + a[i], dp[i -3] + a[i-1] + a[i]);
		}

		System.out.println(dp[N]);

	}
}
