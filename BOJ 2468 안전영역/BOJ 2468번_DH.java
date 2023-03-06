import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()), max = 0;
		int[][] map = new int[n][n];
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int k = 0; k <= 100; k++) {
			Queue<int[]> q = new LinkedList<>();
			boolean[][] visited = new boolean[n][n];
			int count = 0;
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] <= k) {
						continue;
					}
					
					if (visited[i][j]) {
						continue;
					}
					
					q.offer(new int[] {i, j});
					visited[i][j] = true;
					
					while (!q.isEmpty()) {
						int[] now = q.poll();
						
						for (int d = 0; d < 4; d++) {
							int nr = now[0] + dr[d], nc = now[1] + dc[d];
							
							if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
								continue;
							}
							
							if (map[nr][nc] <= k) {
								continue;
							}
							
							if (visited[nr][nc]) {
								continue;
							}
							
							q.offer(new int[] {nr, nc});
							visited[nr][nc] = true;
						}
					}
					
					count += 1;
				}
			}
			
			if (count == 0) {
				break;
			}
			
			max = Math.max(count, max);
		}
		
		System.out.println(max);
	}
} 
