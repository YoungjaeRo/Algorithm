import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int A = in.nextInt();
        int B = in.nextInt();
        int C = in.nextInt();
        int max; // max 변수를 밖으로 빼서 스코프 오류 수정
        
        // 3개의 주사위 숫자가 다 다른 경우
        if (A != B && A != C && B != C) {
            if (A > B) {
                // C > A > B 이라면
                if (C > A) {
                    max = C;
                } else {
                    max = A;
                }
            }
            // B > A 이라면
            else {
                // C > B > A 라면
                if (C > B) {
                    max = C;
                }
                // B > (A, C)
                else {
                    max = B;
                }
            }
            System.out.println(max * 100); // max 변수가 정의된 곳에 출력문 이동
        }
        // 최소 한 쌍 이상의 서로 같은 변수가 존재할 경우
        else {
            // 3개의 변수가 모두 같은 경우
            if (A == B && A == C) {
                System.out.println(10000 + A * 1000);
            } else {
                // A가 B 혹은 C와 같은 경우
                if (A == B || A == C) {
                    System.out.println(1000 + A * 100);
                } else {
                    // B가 C와 같은 경우
                    System.out.println(1000 + B * 100);
                }
            }
        }
    }
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    