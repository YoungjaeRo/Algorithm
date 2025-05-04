import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1 , 1, 0, 0}; // 상하좌우
	static int[] dy = {0, 0, -1, 1};


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];


		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		BFS(0, 0);

		System.out.println(map[N - 1] [M - 1]);


	}

	static void BFS(int x, int y) {
		Queue<int[]> q= new LinkedList<>();
		q.add(new int[] {x, y}); // 큐에 시작 노드를 넣어주기
		visited[x][y] = true; //  재방문 방지를 위해 true로 바꿔주기

		while(!q.isEmpty()) {
			int[] now = q.poll();
			int cx = now[0];
			int cy = now[1];

			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];


				if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
					if(!visited[nx][ny] && map[nx][ny] == 1) {
						visited[nx][ny] = true;
						map[nx][ny] = map[cx][cy] + 1;
						q.add(new int[] {nx, ny});
					}
				}
			}

		}

	}

}
