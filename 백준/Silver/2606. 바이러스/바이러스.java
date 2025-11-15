import java.io.*;
import java.util.*;


public class Main {
	static int N; // 컴퓨터의 수  (정점)
	static int M; // 몇쌍인지
	
	// 그래프 문제이기 때문에, 인접리스트
	static List<Integer> [] arrlist;
	
	// 방문 배열도 꼭 필요함
	static boolean[] visited;
	
	static int count;
	
public static void main(String[] args) throws Exception {
	BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
	
	N = Integer.parseInt(br.readLine());
	
	arrlist = new ArrayList[N + 1];
	
	visited = new boolean[N + 1];
	
	M = Integer.parseInt(br.readLine());
	
	// 인접 리스트 생성
	for(int i = 1; i <= N; i++) {
		arrlist[i] = new ArrayList<>();
	}
	
	for(int i = 0; i < M; i++) {
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		// 간선 이어주고요
		arrlist[start].add(end);
		arrlist[end].add(start);
	}
	
	// 탐색하면서 dfs로 시작 ㄱㄱ
		
	 	dfs(1);
		System.out.println(count);
	}

static void dfs(int now) {
		visited[now] = true;
		
		for(int next : arrlist[now]) {
			if(!visited[next]) {
				count++;
				dfs(next);
			}
		}
	}
}
