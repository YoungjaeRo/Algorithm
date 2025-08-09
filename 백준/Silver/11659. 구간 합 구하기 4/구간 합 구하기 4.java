// 일일이 계산하면 너무 오래걸리기 때문에, 누적합(prefix sum)을 활용해서 문제를 해결
import java.util.*;
import java.io.*;


/**
 * prefix[i] = A[1] + A[2] + ... + A[i]
 * 즉, 처음부터 i번째까지의 합을 미리 저장해 두는 배열
 * 관례상 prefix[0] = 0으로 둡니다 (인덱스 편하려고)
 */
public class Main {

	static int N;
	static int M;
	static long[] prefix; // 누적합 배열 0은 0번으로 비워두기 (인덱스 편리용)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		prefix = new long[N + 1];
		prefix[0] = 0;

		
		//  누적합 채우기
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int x = Integer.parseInt(st.nextToken());
			prefix[i + 1] = prefix[i] + x;
		}

		// 질의처리
		StringBuilder sb = new StringBuilder();
		for(int j = 0; j < M; j++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			long sum = prefix[end] - prefix[start - 1];
			sb.append(sum).append("\n");
		}

		System.out.println(sb.toString());

	}

}
