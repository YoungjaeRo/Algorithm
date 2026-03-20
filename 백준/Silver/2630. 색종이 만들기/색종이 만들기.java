import java.io.*;
import java.util.*;

public class Main {

	static int N;

	static int[][] paper;

	// 0은 하얀색, 1은 파란색
	static int[] cnts = new int[2];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		paper = new int[N][N];

		StringTokenizer st;

		// 종이 정부 주입
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());


			for(int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		// 0, 0에서부터 N크기 만큼 분할 정복 시행
		divide(0, 0, N);

		System.out.println(cnts[0]);
		System.out.println(cnts[1]);
	}


	static void divide(int x, int y, int size) {
		if(isSame(x, y, size)) {
			int color = paper[x][y];

			if(color == 0) {
				cnts[0]++;
			} else {
				cnts[1]++;
			}
			return;
		}

		int nextSize = size / 2;

		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				divide(x + i * nextSize, y + j * nextSize, nextSize);
			}
		}
	}

	static boolean isSame(int x, int y, int size) {
		int first = paper[x][y];

		for(int i = x; i < x + size; i++) {
			for(int j = y; j < y + size; j++) {
				if(paper[i][j] != first) {
					return false;
				}
			}
		}
		return true;
	}
}
