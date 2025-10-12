import java.io.*;

public class Main {

	static int N; // 행

	static int M; // 열

	static int[][] A;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] nm = br.readLine().split(" ");

		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);

		A = new int[N][M];


		// 그래프 초기화
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				A[i][j] = s.charAt(j) - '0'; // 아스키 코드 값 기준으로 연산처리되어서 정수가 나옴
			}
		}

		long ans = -1;

		// 현재 방향 cr, cc(0, 0) 시작
		for(int cr = 0; cr < N; cr++) {
			for(int cc = 0; cc < M; cc++) {


				// 증가 폭
				for(int dx = -N; dx <= N; dx++) { // -4
					for(int dy = -M; dy <= M; dy++) { // -5

						//제자리 정지는 불가함 (아무론 변화없이 제자리)
						if(dx == 0 && dy == 0) {
							continue;
						}

						//  현재 위치의 (cr, cc)와 누적값 val 초기화
						int r = cr;
						int c = cc;

						long val = 0;

						// 그래프 안의 범위일때만~
						while(r >= 0 && r < N && c >= 0 && c < M) {
							val = val * 10 + A[r][c];

							// 만들어진 수가 완전제곱근인지 매번 체크
							if(isPerfectSquare(val)) {
								ans = Math.max(ans, val);
							}

							// 다음 칸으로 넘어가기

							r = r + dx;
							c = c + dy;

						}
					}
				}




			}
		}

		System.out.println(ans);
	}

	static boolean isPerfectSquare(long i) {
		if(i < 0) {
			return false;
		}

		long r = (long)Math.sqrt(i);

		return r * r == i;
	}
}
