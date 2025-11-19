import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int left = 1;
		int right = 1;
		int answer = 0;
		int sum = 0;

		while(true) {
			if(sum >= M) {
				if(sum == M) {
					answer++;
				}
				sum = sum - arr[left];
				left++;

			} else { // sum < M이면
				if(right > N) {
					break;
				}

				sum = sum + arr[right];
				right++;
			}

		}

		System.out.println(answer);
	}
}
