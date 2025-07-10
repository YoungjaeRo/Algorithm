import java.io.*;
import java.util.*;

public class Main {

	// 사무실 크기 및 맵 정보
	static int N, M;
	static int[][] office;

	// CCTV 목록 저장
	static ArrayList<CCTV> cctvs = new ArrayList<>();

	// 최소 사각지대 개수 저장
	static int minBlindSpot = Integer.MAX_VALUE;

	// 방향 벡터: ↑, →, ↓, ← 순서
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	// CCTV 종류별 감시 가능한 방향 조합
	// cctvDir[타입][회전 경우][감시 방향들]
	static int[][][] cctvDir = {
		{}, // 0번 없음 (빈 칸)
		{{0}, {1}, {2}, {3}},                         // 1번 CCTV: 한 방향
		{{0, 2}, {1, 3}},                             // 2번 CCTV: 양방향 (↑↓), (→←)
		{{0, 1}, {1, 2}, {2, 3}, {3, 0}},             // 3번 CCTV: 직각 (↑→), ...
		{{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}}, // 4번 CCTV: 세 방향
		{{0, 1, 2, 3}}                                // 5번 CCTV: 네 방향 모두
	};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력: 사무실 크기
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 사무실 초기화
		office = new int[N][M];

		// 맵 입력 받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				office[i][j] = Integer.parseInt(st.nextToken());

				// CCTV(1~5)라면 리스트에 추가
				if (office[i][j] >= 1 && office[i][j] <= 5) {
					cctvs.add(new CCTV(i, j, office[i][j]));
				}
			}
		}

		// 각 CCTV가 가질 수 있는 방향 조합 수 저장 (진법용 base)
		int[] base = new int[cctvs.size()];
		for (int i = 0; i < cctvs.size(); i++) {
			base[i] = cctvDir[cctvs.get(i).type].length;
		}

		// 총 경우의 수 = 모든 CCTV 방향 조합의 곱
		int total = 1;
		for (int b : base) total *= b;

		// 모든 조합 순회 (진법처럼)
		for (int num = 0; num < total; num++) {
			// 원본 맵 복사
			int[][] copiedMap = copyMap(office);

			int temp = num;

			// 각 CCTV별 방향 인덱스를 계산
			for (int i = 0; i < cctvs.size(); i++) {
				CCTV cctv = cctvs.get(i);
				int type = cctv.type;

				// CCTV i번의 방향 인덱스 (진법 자리수처럼 계산)
				int dirIdx = temp % base[i];
				temp /= base[i];

				// 해당 방향 조합에 포함된 모든 방향을 감시
				for (int d : cctvDir[type][dirIdx]) {
					watch(copiedMap, cctv.x, cctv.y, d);
				}
			}

			// 감시가 끝난 뒤, 사각지대 개수 계산 후 최솟값 갱신
			minBlindSpot = Math.min(minBlindSpot, countBlindSpot(copiedMap));
		}

		// 정답 출력
		System.out.println(minBlindSpot);
	}

	// 감시 함수: 한 방향으로 끝까지 가며 감시 영역을 표시
	static void watch(int[][] map, int x, int y, int dir) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];

		while (0 <= nx && nx < N && 0 <= ny && ny < M && map[nx][ny] != 6) {
			// 감시 가능 영역이면 -1로 표시 (0일 경우만 덮음)
			if (map[nx][ny] == 0) map[nx][ny] = -1;
			nx += dx[dir];
			ny += dy[dir];
		}
	}

	// 사각지대(0) 개수 세기
	static int countBlindSpot(int[][] map) {
		int cnt = 0;
		for (int[] row : map)
			for (int val : row)
				if (val == 0) cnt++;
		return cnt;
	}

	// 맵 깊은 복사 (감시 영향 방지용)
	static int[][] copyMap(int[][] map) {
		int[][] newMap = new int[N][M];
		for (int i = 0; i < N; i++)
			newMap[i] = map[i].clone();  // 깊은 복사
		return newMap;
	}

	// CCTV 클래스: 위치(x, y)와 타입 저장
	static class CCTV {
		int x, y, type;
		CCTV(int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}
}
