import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //일단 문자의 입력을 받아야하기 때문에, 효율이 제일 좋은 BufferedReader을 사용한다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReaderd을 읽을때, 무조건 throws IOException을 명시해주어야한다.
        String s = br.readLine();

        // 스택 선언 제네릭 타입 String
        // 단어를 뒤집기 위해서 문자들을 저장하는 스택
        // check: 현재 태그 내부에 있는지 여부를 저장하는 부울 변수

        Stack<Character> stack = new Stack<>();
        boolean inTag = false; // 태그 내부인지 체크 <의 시작과 >의 끝을 알리기 위한 장치


        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (currentChar == '<') {
                print(stack);
                inTag = true;
                System.out.print(currentChar); // 태그 시작문자(<)를 출력
            } else if (currentChar == '>') { // 태그가 닫힌다면
                inTag = false;
                System.out.print(currentChar);
            } else if (inTag) {
                System.out.print(currentChar); // 태그안에 들어왔으므로 스택에 저장하지 않고 그대로 출력
            } else { // 태크 외부 문자라면
                if (currentChar == ' ') {//공백문자라면
                    print(stack); //이전 단어를 뒤집어서 출력
                    System.out.print(currentChar);
                } else { //그냥 일반단어라면 스택에 저장
                    stack.push(currentChar);

                }
            }
        }


        print(stack);}
    public static void print(Stack<Character> stack){
        while(!stack.isEmpty()){ // 스택이 비어있는가?의 반대
            System.out.print(stack.pop()); // 스택이 안비어져 있으면 스택에서 계속 문자를 추출
        }
    }

}