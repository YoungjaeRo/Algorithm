import java.io.*;
import java.util.*;

public class Main {
	static class Query {
		String guess; //  문자열로 보관함
		int s;
		int b;

		Query(String guess, int s, int b) {
			this.guess  = guess;
			this.s = s;
			this.b = b;
		}
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());


		// 예측값 저장해놓기
		List<Query> list = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String guess = st.nextToken();
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list.add(new Query(guess, s, b));
		}


		// 숫자야구에서 가능한 후보 다 저장해놓기
		List<String> numbers = new ArrayList<>();
		for(int i = 1; i <= 9; i++) {
			for(int j = 1; j <= 9; j++) {
				if(i == j) {
					continue;
				}

				for(int z = 1; z <=9; z++) {
					if(z == i || z == j) {
						continue;
					}

					numbers.add("" + i + j + z);
				}
			}
		}


		int answer = 0;

		// 각 후보에 대해 모든 질의를 통과하는지 확인

		outer:
		for(String num : numbers) {
			for(Query q : list) {
				int[] sb = countSB(num, q.guess);
				if(sb[0] != q.s || sb[1] != q.b) {
					continue outer;
				}
			}
			
			// 모든 질의 통과했으면,
			answer++;
			
		}

		System.out.println(answer);
	}

	public static int[] countSB(String num, String guess) {
		int strike = 0;
		int ball = 0;

		for(int i = 0; i < 3; i++) {

			// 스트라이크 계산하기
			if (num.charAt(i) == guess.charAt(i)) {
				strike++;
			}
		}

			for(int i = 0; i < 3; i++) {
				char g = guess.charAt(i);

				if(num.indexOf(g) >= 0) {
					ball++;
				}
			}

			ball = ball - strike;

			return new int[] {strike, ball};

	}
}
