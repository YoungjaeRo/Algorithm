import java.util.*;
import java.io.*;

public class Main {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();

		StringBuilder ans = new StringBuilder(); // 최종 답안

		StringBuilder word = new StringBuilder(); // <> 태크 밖의 뒤집을 단어들을 모아놓는 스트링 빌더


		boolean inTag = false; // 현재 포인터가 태그 안에 들어와 있는지

		for(int i = 0; i < s.length(); i++) {

			// 태그를 시작하는 <를 만나면
			if(s.charAt(i) == '<') {
				//  태그 시작 직전까지의 단어들은 먼저 뒤집어 붙인다
				if(word.length() > 0) {
					ans.append(word.reverse());
					word.setLength(0);
				}

				// 이제 부터는 태그 안에 들어와있음  reverse  대상자 아님
				inTag = true;
				ans.append(s.charAt(i));

			} else if(s.charAt(i) == '>') {
				inTag = false;
				ans.append(s.charAt(i));   // '>' 자체는 그대로

			} else if(inTag) { // 태그안 : 어떤 문자든 그대로 복사함
				ans.append(s.charAt(i));

			} else {
				// 태그 밖의 구간(뒤집기 대상)

				if(s.charAt(i) == ' ') {
					// 공백은 단어의 공백임, 지금까지 모인 단어를 뒤집고, 공백도 그대로,
					ans.append(word.reverse());
					word.setLength(0);
					ans.append(' ');
				} else {
					//단어를 빌더에 계속 모음
					word.append(s.charAt(i));
				}
			}

		}

		// 문자열이 끝났는데, 단어 버퍼가 남아있으면, 마저 뒤집기
		if(word.length() > 0) {
			ans.append(word.reverse());

		}
		System.out.println(ans);

	}
}
