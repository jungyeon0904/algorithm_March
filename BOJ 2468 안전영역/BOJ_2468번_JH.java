import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	
	static int map[][];
	
	static int heightmax = 0;
	
	static boolean checked[][];
	
	static boolean brr[][];
	
	static int max = 0;
	
	//상하좌우
	static int plusX[] = {-1,1,0,0};
	static int plusY[] = {0,0,-1,1};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > heightmax) heightmax = map[i][j];
			}
		}
		
		
		//물을 채워가면서 탐색
		for(int i = 0; i < heightmax ; i++) {
			//각 높이마다 방문한 값 초기화
			checked = new boolean[N][N];
			
			//각 높이마다 sum값 초기화
			int sum = 0;
			
			//침수 시키기
			for(int a = 0; a <N; a++){
				for(int b = 0 ; b< N ; b++) {
					if(map[a][b] <= i) {
						checked[a][b] = true;
					}
				}
			}
			
			//이어지지 않은 영역은 dfs로 순회하며 안전지대 1개 추가
			for(int a = 0; a<N ; a++) {
				for(int b = 0 ; b< N; b++) {
					if(!checked[a][b]) {
						dfs(a,b);
						sum++;
					}
				}
			}
			if(sum > max) max = sum;
		}
		
		System.out.println(max);
	}
	
	
	//dfs 탐색
	static void dfs(int x, int y) {

		checked[x][y] =true;
		
		int newx;
		int newy;
		
		for(int i = 0 ; i < 4 ;i++) {
			newx = x+plusX[i];
 			newy = y+plusY[i];
			if(newx<0 || newy <0 || newx >=N || newy>=N) continue;
			if(checked[newx][newy]) continue;
			dfs(newx,newy);
		}
		
		return;
	}
}