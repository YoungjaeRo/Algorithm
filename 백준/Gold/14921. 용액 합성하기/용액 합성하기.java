import java.io.*;
import java.util.*;

public class Main {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int right = arr.length - 1;

		// 0이랑 가장 가까운 값을 찾아야함 (두 수의 합이기 때문에, 투 포인터로 푸는게 쉽다)
		int bestSum = arr[left] + arr[right];

		// 투포인터는 꼭 left < right
		while(left < right) {
			int sum = arr[left] + arr[right];

			// 0에 더 가까운 합으로 갱신해주기
			if(Math.abs(sum) < Math.abs(bestSum)) {
				bestSum = sum;
			}

			// 합이 음수면, 0과 가까워지기 위해선, 더하는 수가 더 커져야함
			if(sum < 0) {
				left++;
			} else if(sum > 0) {
				right--;
			} else {
				bestSum = 0;
				break;
			}

		}

		System.out.println(bestSum);
	}
}
