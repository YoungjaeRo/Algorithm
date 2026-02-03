import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		List<Long> list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			String s = sc.next();
			String reversed = new StringBuilder(s).reverse().toString();
			
			list.add(Long.parseLong(reversed));
		}
		
		Collections.sort(list);
		
		for(long v : list) {
			System.out.println(v);
		}
	}
}
