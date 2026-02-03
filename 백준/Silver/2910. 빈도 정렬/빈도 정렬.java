import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 해당 문제에서 알아야할것은
	 *
	 * 숫자값
	 * 등장횟수
	 * 처음 등장한 위치(순서)
	 */
	static int N;
	static int C;

	static class Number {
		int value; // 숫자 값
		int freq; // 빈도수
		int order; // 처음 등장한 순서


		Number(int value, int order) {
			this.value = value;
			this.freq = 1;
			this.order = order;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		// 숫자의 빈도를 저장해 놓을 MAP
		Map<Integer, Number> map = new HashMap<>();

		st = new StringTokenizer(br.readLine());

		// 숫자 정보 주입
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			// 처음 들어오는 값이라면,
			if(!map.containsKey(num)) {
				map.put(num, new Number(num, i));
			} else {
				// 이미 들어있는 값이라면, 빈도수만 +1 증가
				map.get(num).freq++;
			}
		}

		// 정렬하기 위해서는 리스트로 변환이 필요
		List<Number> list = new ArrayList<>(map.values());

		// 위 문제의 조건대로 정렬하기(커스텀)
		Collections.sort(list, (a, b) -> {
			// 1. 빈도수대로 정렬
			if(a.freq != b.freq) {
				return b.freq - a.freq;
			} else {
				return a.order - b.order;
			}
		});

		StringBuilder sb = new StringBuilder();
		for(Number n : list) {
			for(int i = 0; i < n.freq; i++) {
				sb.append(n.value).append(" ");
			}
		}

		System.out.println(sb);
		
	}
}
