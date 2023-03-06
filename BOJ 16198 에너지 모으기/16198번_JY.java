import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16198 {

	static int N, NN;
	static int[] arr;
	static boolean[] isSelected;
	static int[] choice;
	static int ans = Integer.MIN_VALUE; // 결과값
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 구슬의 개수 3 <= N <= 10
		arr = new int[N];
		st = new StringTokenizer(br.readLine()); // 에너지 구슬 입력
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		NN = N - 2; // 첫 번째와 마지막 에너지 구슬을 제외
		isSelected = new boolean[N]; 
		choice = new int[NN];
		Permutation(0); // 순열
		System.out.println(ans);
	}

	private static void Permutation(int cnt) {
		// 기저 조건
		if(cnt == NN) {
			// 배열 복사
			int[] temp = new int[N];
			for(int i = 0; i < N; i++) {
				temp[i] = arr[i];
			}
			int cal = Calculate(choice, temp); // 고른 순서, 복사한 배열
			ans = ans < cal ? cal : ans;
			return;
		}
		for(int i = 1; i < N-1; i ++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			choice[cnt] = i;
			Permutation(cnt+1);
			isSelected[i] =false;
		}
	}

	private static int Calculate(int[] seq, int[] temp) {
		int res = 0;
		int right, left;
		int size = seq.length;
		for(int i = 0; i < size; i++) {
			// 1. 순서의 인덱스의 자리 양쪽 에너지를 계산한다
			left = seq[i]-1;
			while(temp[left] == 0) {
				left--;
			}
			right = seq[i]+1;
			while(temp[right] == 0) {
				right++;
			}
			res += temp[left] * temp[right];
			temp[seq[i]] = 0;
		}
		return res;
	}
}
