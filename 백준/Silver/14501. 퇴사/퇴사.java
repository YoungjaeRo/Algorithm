import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] T; // 소요시간
	static int[] P; // 소요비용

	static int best = 0; // 최대 수익 (dfs 를 재귀호출하면서 계속해서 갱신됨)

	public static void dfs(int day, int money) {

		if(day > N) { // N + 1일째 되는 날에는 퇴사를 한다

			// 수익갱신후 종료
			best = Math.max(best, money);
			return;
		}

		// 오늘 상당을 할 수 있으면, 다음 호출은 상담이 끝난 다음 날로 점프
		if(day + T[day] <= N + 1) {
			dfs(day + T[day], money + P[day]);
		}


		// 2) 오늘(day) 상담을 "안 한다" 선택지 (항상 가능)
		//    다음 날로 한 칸 전진, 수익 변화는 없음
		dfs(day + 1, money);
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		// 배열 준비 :
		T = new int[N + 2]; // 1번 부터 인덱스 사용, N + 1 까지 안전하게
		P = new int[N + 2];


		// 각 상담 소요 시간과 비용 초기화
		for(int i = 1; i <=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		// 1일차 부터 시작하기 당연히 지금 벌어놓은 돈은 0
		dfs(1, 0);

		System.out.println(best);
	}
}
