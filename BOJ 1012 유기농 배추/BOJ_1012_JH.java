import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int baechunum;
	
	//상하좌우 사방 탐색
	static int plusX[] = {-1,1,0,0};
	static int plusY[] = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		//테스트 케이스 갯수
		int test_case = Integer.parseInt(br.readLine());
		
		for(int T = 0 ; T < test_case; T++) {
			st = new StringTokenizer(br.readLine());
			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			//배추 밭
			int[][] map = new int[row][col];
			baechunum = 0;
			
			//Bx = 배추의 세로 위치, By = 배추의 가로 위치
			int Bx,By;
			for(int i = 0 ; i < num; i++) {
				st = new StringTokenizer(br.readLine());
				By = Integer.parseInt(st.nextToken());
				Bx = Integer.parseInt(st.nextToken());
				
				//배추의 위치를 1로 지정
				map[Bx][By] = 1;
			}
			
			for(int i = 0 ; i < row; i++) {
				for(int j = 0 ; j < col; j++) {
					if(map[i][j]==1) {
						bfs(map,i,j,row,col);
					}
				}
			}
			
			sb.append(baechunum).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void bfs(int map[][], int x, int y, int row, int col) {
		
		baechunum++;
		
		Queue<Integer[]> queue = new LinkedList<>();
		
		Integer[] temp = {x,y};
		queue.add(temp);
		map[x][y] = 0;
		
		while(!queue.isEmpty()) {
			temp = queue.poll();
			for(int i = 0 ; i < 4; i++) {
				int newx = temp[0]+plusX[i];
				int newy = temp[1]+plusY[i];
				if(newx>=0 && newy >=0 && newx < row && newy < col && map[newx][newy] == 1) {
					queue.add(new Integer[] {newx,newy});
					map[newx][newy] = 0;
				}
			}
		}
	}
}