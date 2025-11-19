import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;

	static int[] a;
	static int[] b;

	public static void main(String[] args) throws  Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		a = new int[N];
		b = new int[M];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}

		int i = 0;
		int j = 0;

		while(i < N && j < M) {
			if(a[i] <= b[j]) {
				sb.append(a[i]).append(" ");
				i++;
			} else {
				sb.append(b[j]).append(" ");
				j++;
			}
		}

		// 나머지
		while(i < N) {
			sb.append(a[i]).append(" ");
			i++;
		}

		while(j < M) {
			sb.append(b[j]).append(" ");
			j++;
		}

		System.out.println(sb);
	}
}
