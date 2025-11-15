import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		StringBuilder answer = new StringBuilder(); // 최종 정답

		StringBuilder word = new StringBuilder(); // 뒤집을 단어 버퍼

		boolean isTag = false;

		for(char c : s.toCharArray()) {

			if(c == '<') {
				answer.append(word.reverse()); //  버퍼에 담아놨던 것들 reverse
				word.setLength(0); // 비워주고

				isTag = true;

				answer.append(c); // < 을 붙여줌

			} else if(c == '>') {
				isTag = false;
				answer.append(c); // >을 붙여줌

			} else if(isTag) { // 태그 안에 있는것은 뒤집힘의 대상이 아니기 때문에
				answer.append(c);

			} else { //  뒤집힘 대상들
				if(c == ' ') {
					answer.append(word.reverse());
					word.setLength(0);

					answer.append(' ');
				} else {
					word.append(c);
				}
			}
		}
		
		answer.append(word.reverse());

		System.out.println(answer.toString());
	}
}
