import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 에라스토테네스 어쩌고 하기
		boolean era[] = new boolean[1000001];

		// 에레스토 테네스 체크
		for (int i = 3; i <= 1000000; i++) {
			if (!era[i]) {
				int idx = i + i;
				while (idx <= 1000000) {
					era[idx] = true;
					idx += i;
				}
			}
		}

		// 0이 입력될때까지 반복
		int N;

		while (true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			boolean flag = false;
			for(int i = 3; i <=N/2; i+=2)
				if(!era[i]&&!era[N-i]) {
					sb.append(N).append(" = ")
					.append(i).append(" + ")
					.append(N-i).append("\n");
					flag = !flag;
					break;
				}
			if (!flag) {
				sb.append("Goldbach's conjecture is wrong.").append("\n");
			}
		}

		System.out.println(sb);
	}

}