import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 8개의 톱니를 가진, 톱니바퀴가 4개 있음 (번호 1 ~ 4)
	 * 톱니는 N극 아니면 S극임
	 *
	 * 총 K번 회전 시킬 예정 (시계 방향 & 반시계 방향)
	 *
	 * 서로 맞닿아 있는 극이 다르면, 서로 반대로 돌고,
	 * 맞닿아 있는 극이 같으면, 회전하지 않는다.
	 *
	 *
	 * 결론적으로, 맞닿아 있는 극이 서로 다른채, 하나의 톱니가 회전하면, 그 옆 톱니바퀴는 반대로 회전
	 * 그 외 톱니바퀴들고 극이 다르면 반대로 회전하고, 같으면 회전하지 않는다.
	 *
	 * N극은 0, S극은 1
	 *
	 *
	 * K번 회전시킨 이후, 네 톱니바퀴의 합을 구하시오
	 * 1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점
	 * 2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점
	 * 3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점
	 * 4번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점
	 */

	static int K; // 회전 횟수

	// 인덱스 0 :  12시방향
	// 인덱스 2 : 오른쪽 (옆 톱니와 맞닿는 부분)
	// 인덱스 6 : 왼쪽 (옆 톱니와 맞닿는 부분)

	static int[][] gear = new int[4][8];

	static int num; // 회전 시킨 톱니바퀴의 번호
	static int dir; // 회전 방향 (1 : 시계방향, -1 : 반시계 방향)

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 1. 톱니바퀴 상태 입력
		for(int i = 0; i < 4; i++) {
			String line = br.readLine();

			for(int j = 0; j < 8; j++) {
				gear[i][j] = line.charAt(j) - '0';
			}
		}

		// 2. 회전 횟수 입력 받기
		K = Integer.parseInt(br.readLine());

		// 3. k 번 회전 시작
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());

			num = Integer.parseInt(st.nextToken()); // 톱니 번호
			dir = Integer.parseInt(st.nextToken()); // 회전 방향

			// 각 톱니바퀴의 회전 방향을 저장함
			int[] rotate = new int[4]; // 0 안돈다, 1 시계, -1 반시계

			// 톱니 번호
			int idx = num -1; // 0-base 인 배열에 대응하게끔

			rotate[idx] = dir; // 해당 톱니바퀴 인덱스에 방향 정보를 저장


			// 왼쪽 톱니 상태 스캔
			for(int i = idx - 1; i >= 0; i--) {
				if(gear[i][2] != gear[i + 1][6]) {
					//둘의 극이 다르면, i는 i + 1의 반대방향으로 회전한다
					rotate[i] = -rotate[i + 1];

				} else {
					// 극이 같으면, 현상 유지
					break;
				}
			}

			// 오른쪽 톱니 상태 스캔
			for(int i = idx + 1; i < 4; i++) {
				if(gear[i][6] != gear[i -1][2]) {
					// 둘의 극이다르면
					rotate[i] = -rotate[i - 1];
				} else {
					// 극이 같으면, 현상 유지
					break;
				}
			}

			// 본 회전 수행
			for(int i = 0; i < 4; i++) {
				if(rotate[i] != 0) { // 회전해야하는 톱니만 회전 수행
					rotateMain(i, rotate[i]);
				}
			}
		}
		int score = 0;
		if(gear[0][0] == 1) score = score + 1;
		if (gear[1][0] == 1) score += 2;
		if (gear[2][0] == 1) score += 4;
		if (gear[3][0] == 1) score += 8;
		System.out.println(score);
	}

	/**
	 *
	 * @param idx 번의 톱니바퀴를
	 * @param dir 방향으로 회전
	 */

	static void rotateMain(int idx, int dir) {
		if(dir == 1) { // 시계 방향이면, 마지막 칸이 맨 앞으로 넘어오고, 나머지는 우측으로 이동
			int temp = gear[idx][7];

			for(int i = 7; i > 0; i--) {
				gear[idx][i] = gear[idx][i-1]; // 오른쪽으로 한칸씩 밀기
			}
			gear[idx][0] = temp;

		} else if(dir == -1) { // 반시계 방향이면, 맨 앞의 값이 맨 뒤로 이동, 나머지 왼쪽 이동
			int temp = gear[idx][0];

			for(int i = 0; i < 7; i++) {
				gear[idx][i] = gear[idx][i + 1]; // 왼쪽 이동
			}
			gear[idx][7] = temp;
		} else {
			return;
		}
	}
}
