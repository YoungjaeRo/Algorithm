import java.io.*;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] A;
	static int[] B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		A = new int[N];
		B = new int[M];

		// A 배열 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		// B 배열 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		// 투 포인터 병합
		int i = 0, j = 0;
		StringBuilder sb = new StringBuilder();

		while (i < N && j < M) {
			if (A[i] <= B[j]) {
				sb.append(A[i++]).append(" ");
			} else {
				sb.append(B[j++]).append(" ");
			}
		}

		// A 배열 남은 값
		while (i < N) {
			sb.append(A[i++]).append(" ");
		}

		// B 배열 남은 값
		while (j < M) {
			sb.append(B[j++]).append(" ");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
