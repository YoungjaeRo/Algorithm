import java.util.*;
import java.io.*;


public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		boolean[] numbers = new boolean[31];

		for(int i = 0; i < 28; i++) {
			int submit = Integer.parseInt(br.readLine());
			numbers[submit] = true; // 제출한 번호는 true로 처리해줌
		}

		StringBuilder sb = new StringBuilder();

		for(int i = 1; i <= 30; i++) {
			if(!numbers[i]) {
				sb.append(i).append('\n');
			}
		}

		System.out.println(sb);
	}
}
