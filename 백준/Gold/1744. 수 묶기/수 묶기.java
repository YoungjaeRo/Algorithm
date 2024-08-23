import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // /카드의 수 저장
        //양수 끼리의 묶음, 음수끼리의 묶음, 1, 0으로 나누어서 저장한다
        //양수는 내림차순으로 정렬해 가장 높은 두 수를 묶어서 곱해주고 나머지는 그냥 더해주는 전략
        // 음수는 오름차순으로 정렬해, 가장 작은 음수 두개를 묶어 곱해주고 나머지는 그냥 더해준다
        // 1은 곱하기 보단 더하는게 더 큰 수를 만드는데 도움이 되므로 그냥 더해줌
        // 0도 그냥 더해줌

        PriorityQueue<Integer> pluspq = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순으로 정렬하기

        PriorityQueue<Integer> minuspq = new PriorityQueue<>();

        int one = 0;
        int zero = 0;

        for(int i = 0; i < N; i++){
            int data = sc.nextInt();
            if(data > 1){
                pluspq.add(data);

            } else if (data == 1){
                one++;

            } else if (data == 0){
                zero++;

            } else {
                minuspq.add(data);
            }
        }
        int sum = 0;

        //양수 묶음 처리하기
        while(pluspq.size() > 1){
            int first = pluspq.remove(); // 큐에서 가장 앞에 있는 값을 가져오고 그 요소를 제거 (.poll()이랑 같은 역할)
            int second = pluspq.remove();
            sum = sum + first * second;
        }
        if(!pluspq.isEmpty()) {
            sum = sum + pluspq.remove();
        }

        //음수 묶음 처리하기
        while(minuspq.size() > 1) {
            int first = minuspq.remove();
            int second = minuspq.remove();
            sum = sum + first * second;
        }
        if(!minuspq.isEmpty()) {
            if(zero == 0){ // zero가 한개도 없으면
                sum = sum + minuspq.remove();
            }
        }
        // 마지막으로 1처리하기
        sum = sum + one;
        System.out.println(sum);

    }
}
