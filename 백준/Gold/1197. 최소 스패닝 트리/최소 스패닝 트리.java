import java.util.*;
import java.io.*;

public class Main {
	static int V, E;
	static int[] parent;
	static Edge[] edges;
	static int totalWeight;

	static class Edge {
		int u, v, weight;

		public Edge(int u, int v, int weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
		}
	}

	// Find 연산 (경로압축)
	static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}

	// Union 연산
	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA != rootB) parent[rootB] = rootA;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		parent = new int[V + 1];
		edges = new Edge[E];

		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edges[i] = new Edge(u, v, w);
		}

		Arrays.sort(edges, Comparator.comparingInt(e -> e.weight));

		for (Edge edge : edges) {
			if (find(edge.u) != find(edge.v)) {
				union(edge.u, edge.v);
				totalWeight += edge.weight;
			}
		}

		System.out.println(totalWeight);
	}
}
