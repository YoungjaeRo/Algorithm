import java.io.*;
import java.util.*;

public class Main {
	/**
	 * N개의 마을이 위치해 있다,
	 * i 번째 마을은 X[i]에 위치해 있으며, A[i]명의 사람이 살고 있다
	 *
	 * 해당 마을을 위해 우체국을 하나 세우고자 하는데,
	 * 각 사람들까지의 거리의 합이 최소가 되는 곳에 우체국을 세우기로 했다.
	 *
	 * 각 마을까지의 거리의 합이 아니라, 각 사람까지의 거리의 합임을 유의하시오
	 *
	 * 해당 비용을 최소로 만드는 p?
	 * 누적 인구가, 전체 인구의 절반을 처음 넘는 지점의 위치
	 *
	 *
	 */

	static int N;

	static class Village implements Comparable<Village> {
		long x; // 인덱스
		long a; // 사람

		Village(long x, long a) {
			this.x = x;
			this.a = a;
		}

		// 인덱스 기준 오름차순 정렬하기
		@Override
		public int compareTo(Village o) {
			return Long.compare(this.x, o.x);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		Village[] villages = new Village[N];

		long totalPeoples = 0; // 전체 인구의 합

		StringTokenizer st;

		// 마을 정보 입력하기
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			long x = Long.parseLong(st.nextToken());
			long a = Long.parseLong(st.nextToken());

			villages[i] = new Village(x, a);
			totalPeoples += a;
		}

		// 인덱스 기준으로 정렬하기
		Arrays.sort(villages);

		long target = (totalPeoples + 1) / 2; // 절반 올림 구하기
		
		// 왼쪽 부터 누적 인구를 더해가면서, target 이상이 되는 첫 지점을 찾기
		
		long prefix = 0;
		
		for(int i = 0; i < N; i++) {
			prefix = prefix + villages[i].a;
			
			if(prefix >= target) {
				System.out.println(villages[i].x);
				return;
			}
		}

	}
}
