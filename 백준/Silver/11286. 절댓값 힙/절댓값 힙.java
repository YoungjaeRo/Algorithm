import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int n = Integer.parseInt(br.readLine());

       PriorityQueue<Integer> myqueue = new PriorityQueue<>((o1,o2) -> {
           //절대값 작은 데이터 먼저
           // 절대값이 같은 경우, 음수 출력


           //절댓값 기준으로 정렬하기
           int first_abs = Math.abs(o1);
           int second_abs = Math.abs(o2);
           if(first_abs == second_abs){ // 절대값이 같은 경우 음수 우선
               return o1 > o2 ? 1 : -1;
           }

           return first_abs - second_abs; // 절댓값이 작은 데이터 우선
       });

       for(int i = 0; i < n; i++){
           int request = Integer.parseInt(br.readLine());
           if(request == 0){
               if(myqueue.isEmpty()){
                   System.out.println("0");
               }else {
                   System.out.println(myqueue.poll());
               }
           } else {
               myqueue.add(request);
           }

       }
    }
}