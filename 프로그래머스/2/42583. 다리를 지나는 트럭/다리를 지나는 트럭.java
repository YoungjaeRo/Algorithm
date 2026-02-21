import java.io.*;
import java.util.*;

class Solution {
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        // 트럭의 무게 대신, 다리의 시간 경과에 따른 큐를 사용한다.
        Queue<Integer> q = new ArrayDeque<>();
        
        // 처음에는 큐에 0만 삽입하기
        for(int i = 0; i < bridge_length; i++) {
            q.offer(0);
        }
        
        int time = 0;
        int idx = 0; // idx 번째 트럭부터 순차적으로 다리에 올라올 예정
        int curWeight = 0;
        
        while(idx < truck_weights.length) {
            
            time++;
            
            // 가장 앞에 있던것이 빠져나감 ㅇㅇ
            int out = q.poll();
            curWeight -= out;
            
            // 이제 트럭중에 하나를 불러올 차례
            int in = truck_weights[idx];
            
            if(curWeight + in <= weight) {
                q.offer(in);
                
                curWeight += in;
                
                idx++; // 다음 트럭으로 넘어가기
            } else { // 무게가 안되면, 그냥 0을 집어넣고, 시간 흘려보내기
                q.offer(0);
            }
        }
        
        // 트럭이 올라간 시간 + 완전히 다 지나는데 걸리는 시간(다리의 길이)를 다 더해주어야한다.
        int ans = time + bridge_length;
        
        return ans;
        
        
        
    }
}