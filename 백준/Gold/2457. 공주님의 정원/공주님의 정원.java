import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
public class Main{
		// 꽃이 피어야 하는 시기: 3월 1일 ~ 11월 30일 (매일 꽃이 한가지 이상 피어있어야 함)
		// 심어지는 꽃들의 수는 최대한 적게
		// 꽃이 피는 기간이 겹치는 꽃들 중 최대한 넓은 기간을 덮는 꽃을 선택해야 합니다.
		static class Flower {
			int start;
			int end;
			Flower(int start, int end) {
				this.start = start;
				this.end = end;
			}
		}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 꽃들의 최소 입력개수를 입력받음(최소 4개 꽃 안에서 커버해야함)

		List<Flower> flowers  = new ArrayList<>();
		for(int i = 0; i < N; i++){
			// 시작 월/일을 쪼개서 입력받음
			int startMonth = sc.nextInt();
			int startDay = sc.nextInt();

			int start = startMonth * 100 + startDay;

			// 끝 월/일을 쪼개서 입력받음
			int endMonth = sc.nextInt();
			int endDay = sc.nextInt();

			int end = endMonth * 100 + endDay;

			flowers.add(new Flower(start, end)); // 각 꽃의 시작일과 끝일을 저장
		}

		// 피는 날 기준 오름차순, 같은 날에 피는 경우 지는 날 기준 내림차순 정렬
		flowers.sort((a,b) -> a.start == b.start ? b.end - a.end : a.start - b.start);

		int currentDate = 301;
		int count = 0;
		int i = 0;
		while(currentDate <= 1130) {
			int maxEnd = currentDate;
			while(i < N && flowers.get(i).start <= currentDate){
				maxEnd = Math.max(maxEnd, flowers.get(i).end);
				i++;
			}
			if (maxEnd == currentDate) { // 더 이상 덮을 수 없는 경우
				System.out.println(0);
				return;
				}
			currentDate = maxEnd;
			count++;
			}
		System.out.println(count);
		}
}