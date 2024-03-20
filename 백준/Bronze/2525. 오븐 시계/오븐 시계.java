import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int A = in.nextInt(); // 시
        int B = in.nextInt(); // 분
        
        int C = in.nextInt(); // 조리시 걸리는 시간
        
        int min = 60 * A + B; // 시 -> 분
         min = min + C;
        
        int hour = (min / 60) % 24;
        int minute = min % 60;
        System.out.println(hour + " " + minute);
    }
}