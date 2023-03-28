import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		
		boolean checked[][] = new boolean[row+2][col+2];
		int map[][] = new int[row+2][col+2];
		
		for(int i = 1 ; i <= row; i++) {
			String[] temp = br.readLine().split("");
			for(int j = 1 ; j <= col; j++) {
				if(temp[j-1].equals("-")) {
					map[i][j] = 1;
				}
				if(temp[j-1].equals("|")) {
					map[i][j] = 2;
				}
			}
		}
		
		int total = 0;
		
		for(int i = 1 ; i <= row; i++) {
			for(int j = 1 ; j <= col ; j++) {
				if(!checked[i][j]) {
					total++;
					
					checked[i][j] = true;
					
					// 탐색 지역이 - 일때
					if(map[i][j] == 1) {
						int plusY = 1;
						while(true) {
							//오른쪽으로 탐색
							int newy = j + plusY;
							if(map[i][newy]!=1) {break;}
							checked[i][newy] = true;
							plusY++;
						}
					}
					// 탐색 지역이 | 일떄
					if(map[i][j] == 2) {
						int plusX = 1;
						while(true) {
							//오른쪽으로 탐색
							int newx = i + plusX;
							if(map[newx][j]!=2) {break;}
							checked[newx][j] = true;
							plusX++;
						}
					}
				}
			}
		}
		System.out.println(total);
	}
}