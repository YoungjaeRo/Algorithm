import java.util.*;
import java.io.*;


public class Main {

	static int M; // 가로
	static int N; // 세로
	static int H; // 상자의 수

	// [높이][세로][가로]
	static int[][][] box;

	static int[] dx = { 1, -1,  0,  0,  0,  0}; // 가로 (→, ←)
	static int[] dy = { 0,  0,  1, -1,  0,  0}; // 세로 (↓, ↑)
	static int[] dz = { 0,  0,  0,  0,  1, -1}; // 높이 (위층, 아래층)


	// 익은 토마토 (1)을 미리 큐에 넣기 위한 클래스 정의
	static class Tomato {
		int x;
		int y;
		int z;

		Tomato(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		box = new int[H][N][M];

		Queue<Tomato> queue = new LinkedList<>();



		for(int a = 0; a < H; a++){ // 상자의 개수만큼만 진행
			// 박스에 토마토 정보 저장하기
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				for(int j = 0; j < M; j++) {
					box[a][i][j] = Integer.parseInt(st.nextToken());

					if(box[a][i][j] == 1) {
						queue.offer(new Tomato(j,i,a));
					}
				}
			}
		}

		// bfs 실행하기
		bfs(queue);

		int result = 0;
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (box[h][i][j] == 0) {
						System.out.println(-1);
						return;
					}
					result = Math.max(result, box[h][i][j]);
				}
			}
		}

		System.out.println(result - 1);

	}

	static void bfs(Queue<Tomato> queue) {
		while(!queue.isEmpty()) {
			Tomato t = queue.poll();


			for(int i = 0; i < 6; i++) {
				int nx = t.x + dx[i];
				int ny = t.y + dy[i];
				int nz = t.z + dz[i];



				if(nx >= 0 && nx < M && ny >= 0 && ny < N && nz >= 0 && nz < H) {
					if(box[nz][ny][nx] == 0) {
						box[nz][ny][nx] = box[t.z][t.y][t.x] + 1;
						queue.offer(new Tomato(nx, ny, nz));
					}
				}
			}
		}
	}
}
