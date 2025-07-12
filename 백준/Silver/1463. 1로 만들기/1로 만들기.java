import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int X = Integer.parseInt(br.readLine());

		int[] dp = new int[X + 1]; // dp[i]: i를 1로 만드는 최소 연산 횟수

		dp[1] = 0; // 1은 이미 해당 목표(1)에 도달했기 때문에, 

		// 2부터 X까지 모든 숫자에 대해 최소 연산횟수 구하기
		for(int i = 2; i <= X; i++) {
			dp[i] = dp[i-1] + 1;
			
			// 2로 나누어 떨어지면 나누는 연산도 고려
			if(i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);
				
			}
			
			// 3으로 나누어 떨어지면 나누는 연산도 고려
			if(i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);
			}
		}

		System.out.println(dp[X]);


	}
}
