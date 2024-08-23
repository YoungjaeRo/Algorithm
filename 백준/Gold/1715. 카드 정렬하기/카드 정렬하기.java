
import java.util.*;
import java.io.*;
public class Main {
        public static void main(String[] args)throws IOException{

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            int N = Integer.parseInt(br.readLine()); // 카드 묶음의 수 저장

            PriorityQueue<Integer> pq = new PriorityQueue<>();


            int sum = 0;

            for(int i = 0; i < N; i++){

                int data = Integer.parseInt(br.readLine());

                pq.add(data);

            }

            if(pq.size() == 1){
                System.out.println(0);
                return;
            }

            while(!pq.isEmpty()){
                int a = pq.poll();
                int b = pq.poll();
                sum = sum + a;
                sum = sum + b;

                if(!pq.isEmpty()){
                    pq.add(a + b);
                }
            }
            System.out.println(sum);

        }
    }

