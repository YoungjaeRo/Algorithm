import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] time = new int[N][2]; // 각 열 0, 1에 시작시간과 종료시간을 저장

        //time[i][0]은 시작시점을 의미함

        //time[i][1]은 종료시점을 의미함

        for(int i = 0; i < N; i++){
            time[i][0] = sc.nextInt();
            time[i][1] = sc.nextInt();
        }

        //끝나는 순으로 정렬하기 위해 compare을 재정의
        Arrays.sort(time, new Comparator<int[]>() {
            @Override
            public int compare(int[]o1, int[]o2) {
                //종료시간이 같은경우, 시작시간이 더 빠른순으로 정렬해야한다(오름차순)
                if(o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1]; // 오름차순으로 종료시간을 정렬
             }
        });

        int count = 0;
        int prev_end = -1;

        // 현재 회의 종료시간 보다 시작시간이 더 빠른 회의가 비교대상으로 나오면,
        // 현재 회의의 종료시간으로 업데이트학
        // 그리고 카운트 하나 증가
       for(int i = 0; i < N; i++){
           if(time[i][0] >= prev_end){// 다른회의의 시작시간보다 그 전 회의 종료시간이 더 이르다면,
               prev_end = time[i][1];
               count++;
           }
       }
        System.out.println(count);
    }
}
