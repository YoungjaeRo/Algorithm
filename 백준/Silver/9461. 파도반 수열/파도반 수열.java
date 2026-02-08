import java.io.*;
import java.util.*;

public class Main {

	static int T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());

			long[] dp = new long[N + 1];

			dp[1] = 1;

			if(N >= 2) dp[2] = 1;
			if(N >= 3) dp[3] = 1;
			if(N >= 4) dp[4] = 2;
			if(N >= 5) dp[5] = 2;

			for(int i = 6; i <= N; i++) {
				dp[i] = dp[i - 1] + dp[i - 5];
			}

			System.out.println(dp[N]);

		}
	}
}
