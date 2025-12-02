import java.io.*;
import java.util.*;

public class Main {
	static int N, M, x, y, K;
	static int[][] map;
	static int[] dice = new int[7]; // 1~ 6번 까지만 사용한다.

	// 1. 동, 서, 남, 북  (index : 1, 2, 3, 4)
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, 1, -1, 0, 0};

	public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		// 지도 값 입력받기
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < K; i++) {
			int command = Integer.parseInt(st.nextToken());

			int nx = x + dx[command];
			int ny = y + dy[command];


			// 범위 밖이면 무시하기
			if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
				continue;
			}

			// 이동
			x = nx;
			y = ny;

			// 주사위 굴리기
			if(command == 1) {
				rollEast();
			} else if (command == 2) {
				rollWest();
			} else if(command == 3) {
				rollNorth();
			} else {
				rollSouth();
			}

			// 지도칸과 바닥 숫자 교환
			if(map[x][y] == 0) {
				map[x][y] = dice[6];
			} else {
				dice[6] = map[x][y];
				map[x][y] = 0;
			}

			// 윗면 출력
			sb.append(dice[1]).append("\n");
		}
        
        System.out.println(sb);
	}

	// 동쪽으로 돌리기
	static void rollEast() {
		int temp = dice[1];
		dice[1] = dice[4];
		dice[4] = dice[6];
		dice[6] = dice[3];
		dice[3] = temp;
	}

	// 서
	static void rollWest() {
		int temp = dice[1];
		dice[1] = dice[3];
		dice[3] = dice[6];
		dice[6] = dice[4];
		dice[4] = temp;
	}
	
	// 북
	static void rollNorth() {
		int temp = dice[1];
		dice[1] = dice[5];
		dice[5] = dice[6];
		dice[6] = dice[2];
		dice[2] = temp;
	}

	// 남
	static void rollSouth() {
		int temp = dice[1];
		dice[1] = dice[2];
		dice[2] = dice[6];
		dice[6] = dice[5];
		dice[5] = temp;
	}


}
