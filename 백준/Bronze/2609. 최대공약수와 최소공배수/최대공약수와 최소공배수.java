import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer strToken = new StringTokenizer(br.readLine(), " ");
        int a=Integer.parseInt(strToken.nextToken());
        int b=Integer.parseInt(strToken.nextToken());
        StringBuilder sb = new StringBuilder();
        int gcd=gcd(a,b);
        sb.append(gcd+"\n");
        sb.append(lcm(a,b,gcd)+"\n");
        System.out.println(sb);
    }

    //최대공약수를 구하는 알고리즘
    static int gcd(int x, int y) {
        if (y == 0) return x;
        else return gcd(y, x % y);
    }

    //최소공배수를 구하는 알고리즘
    static int lcm(int a, int b, int gcd) {
        return a * b / gcd;
    }
}