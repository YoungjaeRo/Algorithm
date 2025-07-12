
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] stairs = new int[N + 1];


		// 계단들의 값을 배열에 저장
		for(int i = 1; i <= N; i++) {
			stairs[i] = Integer.parseInt(br.readLine()); // 1번째 계단의 점수 입력

		}

		// dp[i] : i번째 계단을 밟고 올라왔을때의 최대 점수

		int[] dp = new int[N + 1];

		// 초기조건 설정
		if(N >= 1) {
			dp[1] = stairs[1]; // 첫계단만 밟은 경우
		}

		if(N >= 2) {
			dp[2] = stairs[1] + stairs[2];

		}

		if(N >= 3) { // 1칸 --> 2칸 or 2칸 --> 1칸 중 최대값으로 세팅
			dp[3] = Math.max(stairs[1] + stairs[3], stairs[2] + stairs[3]);
		}

		for(int i = 4; i <= N; i++) {
			// (1) i-2 → i로 오는 경우 (i-1은 건너뜀) --> 1칸, 2칸
			// (2) i-3 → i-1 → i로 오는 경우 (i-2는 건너뜀) --> 2칸, 1칸

			dp[i] = Math.max(
				dp[i-2] + stairs[i],
				dp[i-3] + stairs[i-1] + stairs[i]
			);
		}

		System.out.println(dp[N]);




	}
}
