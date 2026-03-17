import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] ropes;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		ropes = new int[N];

		for(int i = 0; i < N; i++) {
			ropes[i] = Integer.parseInt(br.readLine());
		}

		// 오름차순으로 정렬하기
		Arrays.sort(ropes);

		// 최대값
		int max = Integer.MIN_VALUE;

		for(int i = 0; i < N; i++) {

			int possibleWeigth = ropes[i] * (N - i);

			max = Math.max(max, possibleWeigth);
		}

		System.out.println(max);
	}
}
