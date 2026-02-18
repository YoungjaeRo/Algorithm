import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 암호를 만드는데,
	 * 서로 다른 L개의 알파벳 소문자들로 구성
	 *
	 * a,e,i,o,u에서 최소 한개
	 *
	 * 최소 두개는 나머지 자음에서
	 *
	 * 알파벳이 오름차순으로 ㅇㅇ
	 *
	 * 일단은 조합문제임
	 *
	 * C개 중 L개를 선택해서 문제를 구하시오 ㅇㅇ
	 */

	static int C;
	static int L;

	static char[] chars;
	static char[] pick;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		chars = new char[C];
		pick = new char[L];

		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < C; i++) {
			chars[i] = st.nextToken().charAt(0); // 이렇게 해야, String에서 char로 변형이 된다
		}

		Arrays.sort(chars);

		backtrack(0, 0);

		System.out.println(sb);
	}


	static boolean isVowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}

	static void backtrack(int depth, int start) {
		if(depth == L) {
			int vowel = 0;
			int consonant = 0;

			for(char p : pick) {
				if(isVowel(p)) {
					vowel++;
				} else {
					consonant++;
				}
			}

			if(vowel >= 1 && consonant >= 2) {
				sb.append(pick).append("\n");
			}
			return;
		}


		for(int i = start; i < chars.length; i++) {
			pick[depth] = chars[i];

			backtrack(depth + 1, i + 1);
		}
	}
}
