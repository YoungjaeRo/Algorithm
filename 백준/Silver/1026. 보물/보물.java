import java.util.*;
import java.io.*;

public class Main {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		Integer [] a = new Integer[N];
		Integer [] b = new Integer[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		 st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}

		// A는 오름차순
		Arrays.sort(a);

		//B는 내림차순
		Arrays.sort(b, Collections.reverseOrder());


		int answer = 0;
		for(int i = 0; i < N; i++) {
			int mul = a[i] * b[i];
			answer = answer + mul;
		}

		System.out.println(answer);
	}
}
