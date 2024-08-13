import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 사용자의 입력을 한줄 씩 읽어오기 위해, BufferedReader을 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 문자열을 쉽게 조작하기 위해 StringBuilder를 사용
        StringBuilder sb = new StringBuilder();

        // br.readLine()을 통해 한줄을 읽어오고, 정수로 변환
        int n = Integer.parseInt(br.readLine());
        int back = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();

            switch (s){
                case "push" :
                    int x = Integer.parseInt(st.nextToken());
                    queue.add(x);
                    back = x;
                    break;

                case "pop" :
                    if(queue.isEmpty()){
                        sb.append(-1).append("\n");
                    } else {
                        sb.append(queue.poll()).append("\n");
                    }
                    break;

                case "size" :
                    sb.append(queue.size()).append("\n");
                    break;

                case "empty" :
                    if(queue.isEmpty()){
                        sb.append(1).append("\n");
                    } else {
                        sb.append(0).append("\n");
                    }
                    break;

                case "front" :
                    if(queue.isEmpty()){
                        sb.append(-1).append("\n"); // 오타 수정
                    } else {
                        sb.append(queue.peek()).append("\n");
                    }
                    break;

                case "back" :
                    if(queue.isEmpty()){
                        sb.append(-1).append("\n");
                    } else {
                        sb.append(back).append("\n");
                    }
                    break; // break 추가

                default:
                    //모든 case에 해당하지 않는 경우, 실행되는 코드이다.
                    break;
            }
        }
        System.out.println(sb);
    }
}
