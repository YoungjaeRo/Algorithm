import java.util.*;

class Solution
{
    public int solution(int []A, int []B)
    {
        Arrays.sort(A); // 한쪽은 오름차순

		Arrays.sort(B); // B는 내림차순으로 할것이기 때문에, 맨뒤에서 고름

		int n = A.length;
		int sum = 0;
		
		for(int i = 0; i < n; i++) {
			sum = sum + A[i] * B[n - 1 -i];
		}
		int answer = sum;
		return answer;
    }
}