import java.io.*;
import java.util.*;
public class Main {
	/**
	 * 순서가 중요하기 때문에, 순열이며, 백트래킹중 원복이 필요하다
	 */
	static int n;
	static int k;

	static int[] arr;
	static boolean[] visited;

	static HashSet<String> set = new HashSet<>();


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();

		arr = new int[n];
		visited = new boolean[n];

		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		dfs(0, "");

		System.out.println(set.size());

	}
	// idx : 지금까지 몇 장 뽑았는지
	// cur : 지금까지 이어 붙인 문자열 (숫자로 봐도 되지만 문자열이 편함)
	static void dfs(int idx, String cur) {

		// k장을 다 뽑았으면 Set에 추가하고 종료
		if(idx == k) { // 종료 조건 꼭!
			set.add(cur);
			return;
		}

		// 아직 더 뽑아야한다면, 안쓴 카드들 중 하나를 선택
		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(idx + 1, cur + arr[i]); // 현재 문자열 뒤에 카드 값 이어 붙이기
				visited[i] = false;

			}
		}
	}
}
