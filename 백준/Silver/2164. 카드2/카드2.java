import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Queue<Integer> queue = new ArrayDeque<>();

        int n = sc.nextInt();

        for(int i = 1; i <= n; i++){
            queue.add(i);
        }

        while(queue.size() > 1) { // 큐에 카드가 한장 남아있을 때 까지 진행
            queue.poll(); // 맨위 카드를 버리기
            queue.add(queue.poll()); // 뽑은 카드를 바로 큐의 뒤로 넣어주기
        }
        System.out.println(queue.poll()); // 큐에 남아있는 마지막 카드를 출력

    }
}
