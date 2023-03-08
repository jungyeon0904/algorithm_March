import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	
	static int max;
	
	static int arr[];
	
	static boolean checked[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		checked = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i <N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0,0);
		System.out.println(max);
	}
	
	static void dfs(int cnt,int total) {
		if(cnt == N-2) {
			if(total > max ) {max = total;}
			return;
		}
		
		for(int i = 1; i<N-1;i++) {
			if(!checked[i]) {
				checked[i] = true;
				int back = 0;
				int top = 0;
				for(int j = i ; checked[j]; j--) {
					back = arr[j-1];
				}
				for(int j = i ; checked[j]; j++) {
					top = arr[j+1];
				}
				dfs(cnt+1,total+top*back);
				checked[i] = false;
			}
		}
	}
}