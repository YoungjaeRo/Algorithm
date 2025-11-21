import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static Member[] members ;

	static class Member {
		int age;
		String name;
		int idx;

		Member (int age, String name, int idx) {
			this.age = age;
			this.name = name;
			this.idx = idx;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		members = new Member[N];


		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();

			members[i] = new Member(age, name, i);

		}

		Arrays.sort(members, (a, b) -> {
			if(a.age != b.age) {
				return a.age - b.age;
			}

			return a.idx - b.idx;
		});


		for(int i = 0; i < N; i++) {
			sb.append(members[i].age).append(" ").append(members[i].name).append("\n");
		}

		System.out.println(sb.toString());
	}
}
