

import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        int[] A = new int[n]; // 수열 배열 생성
        int[] ans = new int[n]; // 정답 배열 생성

        String[] strings = bf.readLine().split(" ");
        for(int i = 0; i < n; i++){
            A[i] = Integer.parseInt(strings[i]); // 수열 배열에 입력된 숫자를 다 추가
        }

        // Stack 활용
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // 수열 배열의 값이 아니라 인덱스를 넣을 것이다. 즉 인덱스 0~3을 스택에 투입

       for(int i = 1; i < n; i++){ // 스택이 비어 있지 않고 현재 수열이 스택 top 인덱스가 가리키는 수보다 클 경우 (즉 오큰수의 후보 가 될수 있을때)
           while(!stack.isEmpty() && A[stack.peek()] < A[i]) {
               ans[stack.pop()] = A[i];

           } // 가장 위의 인덱스 값보다 수열의 크기가 작다면
           stack.push(i);

       }
       while(!stack.isEmpty()){
           ans[stack.pop()] = -1; //스택에 남아았는 나머지 인덱스들을 꺼내고 그 인덱스 값에 -1을 대입
       }
       BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
       for(int i = 0; i < n; i++){
           bw.write(ans[i] + " ");
       }
       bw.write("\n");
       bw.flush();
    }
}
