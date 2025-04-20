import java.io.*;
import java.util.*;

public class Main {
	static int M;
	static Set<Integer> S;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		M = Integer.parseInt(br.readLine());
		S = new HashSet<>();

		for (int i = 0; i < M; i++) {
			String[] input = br.readLine().split(" ");
			String cmd = input[0];
			int x = (input.length == 2) ? Integer.parseInt(input[1]) : 0;

			switch (cmd) {
				case "add":
					S.add(x);
					break;

				case "remove":
					S.remove(x);
					break;

				case "check":
					sb.append(S.contains(x) ? "1\n" : "0\n");
					break;

				case "toggle":
					if (S.contains(x)) S.remove(x);
					else S.add(x);
					break;

				case "all":
					S.clear();
					for (int j = 1; j <= 20; j++) {
						S.add(j);
					}
					break;

				case "empty":
					S.clear();
					break;
			}
		}

		System.out.print(sb);
	}
}
