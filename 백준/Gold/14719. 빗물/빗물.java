import java.io.*;
import java.util.*;

public class Main {

	static int H;
	static int W;

	static int[] height;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		height = new int[W];

		st = new StringTokenizer(br.readLine());

		for(int i  = 0; i < W; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}


		// 가장 왼쪽부터 해당 지점까지 최고 높이
		int[] leftMax = new int[W];

		leftMax[0] = height[0];

		for(int i = 1; i < W; i++) {
			leftMax[i] = Math.max(leftMax[i - 1], height[i]);
		}


		// 가장 오른쪽부터 해당 지점까지의 최고 높이
		int[] rightMax = new int[W];

		rightMax[W - 1] = height[W - 1];

		for(int i = W - 2; i > 0; i--) {
			rightMax[i] = Math.max(rightMax[i + 1], height[i]);
		}


		// 각 i의 지점에서의 차오르는 빗물의 양 구하기

		int answer = 0;

		for(int i = 0; i < W; i++) {
			int water = Math.min(leftMax[i], rightMax[i]);

			int fill = water - height[i];

			if(fill > 0) {
				answer += fill;
			}

		}

		System.out.println(answer);
	}

}
