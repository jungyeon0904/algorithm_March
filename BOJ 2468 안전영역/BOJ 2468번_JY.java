import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	static int ans;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행열 개수
		map = new int[N][N];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); // 지도 입력
				min = min > map[i][j] ? map[i][j] : min;
				max = max < map[i][j] ? map[i][j] : max;
			}
		}
		ans = 1; // 최소값, 모두 잠기지 않는 경우
		int count = 0;
		for(int m = min; m < max; m++) {
			visited = new boolean[N][N]; // false 초기화
			count = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					// 물에 잠기지 않으면서 방문하지 않은 영역
					if(map[i][j] > m && !visited[i][j]) {
						bfs(i, j, m);
						count++;
					}
				}
			}
			ans = ans < count ? count : ans;
		}
		System.out.println(ans);
	}

	private static void bfs(int i, int j, int limit) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {i, j}); 
		visited[i][j] = true; // 방문 체크
		
		int nx, ny;
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			
			for(int d = 0; d < 4; d++) {
				nx = cur[0] + dr[d];
				ny = cur[1] + dc[d];
				// 범위 체크
				if(nx < 0 || nx >= N) continue;
				if(ny < 0 || ny >= N) continue;
				// 방문 체크
				if(visited[nx][ny]) continue;
				// 시나리오 체크, 잠김
				if(map[nx][ny] <= limit) continue;
				q.offer(new int[] {nx, ny, limit}); // 큐에 넣기
				visited[nx][ny] = true; // 방문 체크
			}
		}
	}

}
