import java.util.*;
import java.io.*;
public class Main {

	static int n;
	static int d;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken()); // 배열 크기
			int d = Integer.parseInt(st.nextToken()); // 회전하는 각도

			int[][] a = new int[n][n];

			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());

				for(int j = 0; j < n; j++) {
					a[i][j] = Integer.parseInt(st.nextToken());

				}
			}

			// 시계 방향으로 회전해야하는 횟수 (45도 기준)
			int times = ((d % 360) + 360) % 360 / 45;
			
			StringBuilder sb = new StringBuilder();
			
			// times 번 45도 회전을 반복함
			for(int t = 0;  t < times; t++) {
				a = rotate45Clockwise(a); // 회전한 b의 배열을 받게됨
			}

			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					sb.append(a[i][j]);

					if(j < n-1) {
						sb.append(" ");
					}
				}
				sb.append("\n");
			}

			System.out.print(sb);
		}
	}

	static int[][] rotate45Clockwise(int[][] a) {
		int n = a.length;
		int mid = n / 2;

		// b를 새로 만들어서 동시에 이동
		int[][] b = new int[n][n];

		// 일단은 전체복사
		for(int i = 0; i < n; i++) {
			System.arraycopy(a[i], 0, b[i], 0, n);

		}



		// 1. 번 주대각이 가운데 열로
		for(int i = 0; i < n; i++) {
			b[i][mid] = a[i][i];
		}

		// 2. 가운데 열이 부대각으로
		for(int i = 0; i < n; i++) {
			b[i][n -1 -i] = a[i][mid];
		}

		// 3. 부대각이 가운데 행으로
		for(int i = 0; i < n; i++) {
			b[mid][n -1 -i] = a[i][n -1 -i];
		}

		// 4. 가운데 행이 주 대각으로
		for(int i = 0; i < n; i++) {
			b[i][i] = a[mid][i];
		}
		return b;
	}
}
