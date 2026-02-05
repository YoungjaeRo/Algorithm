import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 정렬기준
	 * 1. 국어 점수 내림차순
	 * 2. 국어 점수가 같으면, 영어 점수 오름차순
	 *
	 * 3. 위 두 점수가 같으면, 수학 점수 내림차순
	 *
	 * 4. 모든 점수가 같으면, 이름이 사전순으로 오름차순
	 */

	static int N;

	static class Person implements Comparable<Person> {
		String name;
		int literature;
		int english;
		int math;

		Person(String name, int literature, int english, int math) {
			this.name = name;
			this.literature = literature;
			this.english = english;
			this.math = math;
		}


		@Override
		public int compareTo(Person o) {
			if(this.literature != o.literature) {
				return Integer.compare(o.literature, this.literature);

			} else if(this.english != o.english) {
				return Integer.compare(this.english, o.english);

			} else if(this.math != o.math) {
				return Integer.compare(o.math, this.math);

			} else {
				return this.name.compareTo(o.name);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		// 사람의 정보를 저장해놓을 리스트
		ArrayList<Person> list = new ArrayList<>();

		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			String name = st.nextToken();
			int literature = Integer.parseInt(st.nextToken());
			int english = Integer.parseInt(st.nextToken());
			int math = Integer.parseInt(st.nextToken());
			list.add(new Person(name, literature, english, math));
		}

		Collections.sort(list);
		for(Person cur : list) {
			sb.append(cur.name).append("\n");
		}

		System.out.println(sb.toString());
	}
}
