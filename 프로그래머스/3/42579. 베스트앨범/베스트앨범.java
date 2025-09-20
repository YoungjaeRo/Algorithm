

import java.util.*;
import java.io.*;

public class Solution {

	static class Song{
		int idx;
		int plays;
		Song(int idx, int plays) {
			this.idx = idx;
			this.plays = plays;
		}
	}


	public int[] solution(String[] genres, int[] plays) {
		// 1. 장르별 총 재생수, 장르별 곡 리스트 만들기
		Map<String, Integer> genreTotal = new HashMap<>();

		Map<String, List<Song>> songsByGenre = new HashMap<>();

		for(int i = 0; i < genres.length; i++) {
			String g = genres[i];
			int p = plays[i];

			// 장르 총 재생횟수 추가
			genreTotal.put(g, genreTotal.getOrDefault(g, 0) + p);
			songsByGenre.computeIfAbsent(g, k -> new ArrayList<>()).add(new Song(i, p));
		}

		// 2. 장르를 총 재생수 내림차순으로 정렬
			List<String> orderedGenres = new ArrayList<>(genreTotal.keySet());
			orderedGenres.sort((a, b) -> genreTotal.get(b) - genreTotal.get(a));

			// 3. 각 장르 내에서(재생수 내림차순, 인덱스 오름차순으로 정렬) 한 후, 상위 2곡을 선정
		List<Integer> ans = new ArrayList<>();

		for(String g : orderedGenres) {
			List<Song> list = songsByGenre.get(g);

			list.sort((s1, s2) -> {
				if (s1.plays != s2.plays) {
					return s2.plays - s1.plays; // 재생수 내림차순
				}

				return s1.idx - s2.idx; // 인덱스 오름차순
			});

			for(int k = 0; k < list.size() && k < 2; k++) {
				ans.add(list.get(k).idx);
			}
		}
		
		// 4. 결과 배열로 변환
		int[] answer = new int[ans.size()];
		for(int i = 0; i < ans.size(); i++) {
			answer[i] = ans.get(i);
			
		}
        
        return answer;
	}
}
