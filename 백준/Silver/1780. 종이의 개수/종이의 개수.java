import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] paper;

	// 같은 영역임을 확인했을때, 해당 색종이의 개수를 저장해놓을 배열
	static int[] count = new int[3];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		paper = new int[N][N];

		// 종이 정보 입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 0,0부터 N사이즈로 분할과 정복 시행
		divide(0, 0, N);

		System.out.println(count[0]);
		System.out.println(count[1]);
		System.out.println(count[2]);

	}

	static void divide(int x, int y, int size) {
		// 같은 영역이면 count 해주기
		if(isSame(x, y, size)) {
			int value = paper[x][y];

			// -1, 0, 1 값을 인덱스 0, 1, 2 로 저장해줌
			count[value + 1]++;
		} else {
			int nextSize = size / 3;

			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {

					divide(x + i * nextSize, y + j * nextSize, nextSize);
				}
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
