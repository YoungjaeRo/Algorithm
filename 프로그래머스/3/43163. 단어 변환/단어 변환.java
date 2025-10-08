import java.util.*;

class Solution {
   static class Node {
		String word;
		int step; // begin에서 여기까지 온 변환 횟수
		Node(String word, int step) {
			this.word = word;
			this.step = step;
		}
	}

	public int solution(String begin, String target, String[] words) {
		// 1) target이 words에 없으면 변환 불가
		boolean hasTarget = false;
		for (String w : words) {
			if (w.equals(target)) { hasTarget = true; break; }
		}
		if (!hasTarget) return 0;

		// 2) BFS 준비
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[words.length];
		q.offer(new Node(begin, 0));

		// 3) BFS 전체 탐색
		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (cur.word.equals(target)) {
				return cur.step; // 최초 도달 = 최단
			}

			for (int i = 0; i < words.length; i++) {
				if (!visited[i] && oneDiff(cur.word, words[i])) {
					visited[i] = true; // 큐에 넣을 때 방문 체크
					q.offer(new Node(words[i], cur.step + 1));
				}
			}
		}

		// 4) 끝까지 못 찾음
		return 0;
	}

	public boolean oneDiff(String a, String b) {
		if (a.length() != b.length()) return false;

		int diff = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				diff++;
				if (diff > 1) return false; // 두 글자 이상 다르면 컷
			}
		}

		// 딱 한 글자만 다르면 true
		if (diff == 1) {
			return true;
		} else {
			return false; // 0개(같음) or 2개 이상
		}
	}
}