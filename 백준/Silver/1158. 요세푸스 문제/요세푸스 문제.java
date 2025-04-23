import java.util.*;
import java.io.*;


public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();

		Queue<Integer> q = new LinkedList<>();
		List<Integer> result = new ArrayList<>();


		for(int i = 1; i <= n; i++) {
			q.offer(i);
		}

		while(!q.isEmpty()) {
			// K-1번 앞에서 깨내 뒤로 다시 넣기
			for(int i = 1; i < k; i++) {
				q.offer(q.poll()); // 빼고 다시 넣기
			}

			//k 번째는 제거하며 결과에 추가
			result.add(q.poll());

		}

		// 출력 형식 맞추기
		System.out.print("<");
		for(int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i));
			if(i < result.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.print(">");

	}
}
