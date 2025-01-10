import java.io.*;
import java.util.StringTokenizer;


public class Main {
    //fullMask : 모든 수 방문 비트마스킹
    //result : 결과값
    static int fullMask, result, n, k;
    //중복 연산을 배제하기 위한 메모이제이션
    static boolean[] visited;
    static long[] cards;
    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        //입력값 저장
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        //fullMask Setting
        fullMask = (1 << k) - 1;
        cards = new long[k];
        visited = new boolean[fullMask+1];
        st = new StringTokenizer(br.readLine()," ");
        //카드 정보 저장
        for(int i=0;i<k;i++){
            cards[i] = Long.parseLong(st.nextToken());
        }
        //포함배제 원리 및 최소 공배수를 이용한 백트래킹 진행
        for(int i=0;i<k;i++){
            search(1, 1 << i, cards[i]);
        }
        //최소 한 번은 나눠떨어지는 수의 개수 BufferedWriter 저장
        bw.write(String.valueOf(result));
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
    //백트래킹 및 메모이제이션을 통해서 나눠떨어지는 수 찾는 함수
    static void search(int depth, int bitMask, long value){
        //이미 연산한 경우
        if(visited[bitMask]){
            return;
        }
        //연산 확인
        visited[bitMask] = true;
        //홀수 개의 최소 공배수 일 때
        if(depth % 2 == 1){
            result += n / value;
        }else{		//짝수 개의 최소 공부새우리 때
            result -= n / value;
        }
        //모든 수를 사용하거나, 최소 공배수가 n보다 커질 때
        if(bitMask == fullMask || value > n){
            return;
        }

        //사용할 수 탐색
        for(int i=0;i< k;i++){
            //이미 사용했던 수면 넘기기
            if((bitMask & (1 << i)) != 0){
                continue;
            }
            //재귀 진행
            search(depth+1, bitMask | (1 << i), lcm(value, cards[i]));
        }
    }
    //점화식을 이용한 최소 공배수 구하는 함수
    static long lcm(long value, long card){
        if(value > card){
            return value * card / gcd(value, card);
        }else{
            return value * card / gcd(card, value);
        }
    }
    //유클리드 호재법을 이용한 최대 공약수 구하는 함수
    static long gcd(long a, long b){
        if(b == 0){
            return a;
        }
        return gcd(b, a % b);
    }
}