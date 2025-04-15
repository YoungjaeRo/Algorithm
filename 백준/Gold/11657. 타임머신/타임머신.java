import java.util.*;
import java.io.*;

public class  Main {
	// 가장 빠른 시간, 가장 짧은 거리--> 그래프 문제 (다익스트라, 벨만포드, 위상정렬, 플로이드워셜)

	static final long INF = Long.MAX_VALUE;
	static int N;
	static int M;

	// 벨만 포드는 엣지 중심이기 때문에, 엣지리스트를 만들어준다
	static List<Edge> edges = new ArrayList<>();
	
	// 정답배열
	static long[] distance;

	static class Edge {
		int start;
		int end;
		int cost;

		public Edge(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}


	}
	public static void main(String[] args) throws IOException{
		// 입력 처라
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); //  도시 개수 (노드)
		M = Integer.parseInt(st.nextToken()); // 간선 개수 (엣지)
		

		// 정답배열 초기화
		distance = new long[N + 1];

		// 배열의 모든 요소를 무한대로 초기화
		Arrays.fill(distance, INF);
        distance[1] = 0;
        
		// 간선 정보 입력
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges.add(new Edge(start,end, cost));
		}

		// 벨만포드 수행
		if(bellmanFord()) {
			// 음의 사이클 존재
			System.out.println("-1");
		} else {
			// 1번 도시에서 다른 도시까지의 최단거리를 출력
			for(int i = 2; i <= N; i++) {
				if(distance[i] == INF) {
					System.out.println("-1");
				} else {
					System.out.println(distance[i]);
				}
			}
		}
	}
	
	static boolean bellmanFord() {
		// N- 1번 반복
		for(int i = 1; i < N; i++) {
			for(Edge edge : edges) {
				if(distance[edge.start] != INF && distance[edge.start] + edge.cost < distance[edge.end]) {
					distance[edge.end] = distance[edge.start] + edge.cost;
				}
			}
		}
		
		// 한 번 더 수행해서 음의 사이클을 체크
		for(Edge edge : edges) {
			if(distance[edge.start] != INF && distance[edge.start] + edge.cost < distance[edge.end]) {
				return true; // 음의 사이클이 존재
			}
		}
		return false;
	}
}
