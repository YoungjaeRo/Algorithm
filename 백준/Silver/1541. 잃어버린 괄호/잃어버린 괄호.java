import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		// 1. - (마이너스)를 기준으로 큰 덩어리를 분리
		String[] blocks = s.split("-");


		// 2. 각 덩어리 안에서 + (플러스)들을 모든 더한 값으로 치환
		int[] sums = new int[blocks.length];

		for(int i = 0; i < blocks.length; i++) {
			String b = blocks[i];

			int cur = 0;

			for(String x : b.split("\\+")) {
				if(!x.isEmpty()) {
					cur = cur + Integer.parseInt(x);
				}

				sums[i] = cur;	
			}
			
		}

		// 3. 첫 덩어리는 더하고, 나머지는 전부 빼기 (최소한의 수를 만들기 위한 공식)
		int ans = sums[0];
		for(int i = 1; i < sums.length; i++) {
			ans = ans - sums[i];
		}
		System.out.println(ans);


	}
}
