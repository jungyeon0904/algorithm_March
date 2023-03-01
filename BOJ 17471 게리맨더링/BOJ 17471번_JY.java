import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17471 {
	
	static int N, NN; // 구역 수
	static int[] people; // 인구 수
	static int total; // 전체 인구 수
	static byte[][] map; // 구역 인접 행렬
	static boolean[] isSelected; // 선택된 지역구
	static int min, temp; // 결과 최솟값, 임시 최솟값
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine()); // 구역 수 입력
		NN = N + 1; // [0] 인덱스를 사용하지 않기 위해 설정값
		people = new int[NN]; // 인구 수 저장 배열 초기화
		total = 0; // 전체 인구 수 변수 초기화
		st = new StringTokenizer(br.readLine()); // 인구 수 입력
		for(int i = 1; i < NN; i++) {
			people[i] = Integer.parseInt(st.nextToken());
			total += people[i]; // 전체 인구 수 계산
		}
		map = new byte[NN][NN]; // 구역 인접 행렬 초기화
		int num, idx;
		for(int i = 1; i < NN; i++) {
			st = new StringTokenizer(br.readLine()); // 각 구역 별 인접 구역 입력
			num = Integer.parseInt(st.nextToken()); // 각 구역 별 인접 구역 개수
			for(int j = 0; j < num; j++) {
				idx = Integer.parseInt(st.nextToken()); // i구역의 인접 구역
				// 인접 행렬 설정, 무방향 그래프
				map[i][idx] = 1;
				map[idx][i] = 1;
			}
		}
		min = total; // 최소값 결과
		isSelected = new boolean[NN];
		PartSet(1, 0);
		if(min == total) min = -1; // 연결되는게 없었다
		System.out.println(min);
	}


	private static void PartSet(int cnt, int sum) {
		// 기저조건, 모든 지역을 선거구로 선택했다
		if(cnt > N) {
			// 그래프 연결 확인
			if(check(true)) return;
			if(check(false)) return;
			//if(checkB()) return;
			temp = Math.abs(total - (sum*2)); // 유망한 최소값
			if(min > temp) { // 모든 지역구를 포함하는 경우의 수를 배제하기 위함
				min = temp;
			}
			return;
		}
		// A 지역구에 포함
		isSelected[cnt] = true;
		PartSet(cnt+1, sum + people[cnt]);
		// B 지역구에 포함
		isSelected[cnt] = false;
		PartSet(cnt+1, sum);
	}


	private static boolean check(boolean flag) { // true 또는 false의 지역들의 연결을 확인한다
		List<Integer> list = new LinkedList<>(); // 방문해야하는 지역들
		for(int i = 1; i < NN; i++) {
			if(isSelected[i] == flag) {
				list.add(i);
			}
		}
		
		if(list.size() == 0) return true; // 그래프가 없어서 연결 될 수 없다
		
		Queue<Integer> q = new ArrayDeque<>();
		q.add(list.remove(0)); // 큐 삽입과 동시에 방문 체크
		
		int cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			
			for(int i = 1; i < NN; i++) {
				// 인접 체크
				if(map[cur][i] != 1) continue;
				// 방문 여부 확인
				if(!list.contains(i)) continue;
				list.remove(list.indexOf(i)); // 방문 처리
				q.add(i);
			}
			
		}

		if(list.size() == 0) {
			return false; // 모두 연결되어 있다
		}
		else { // list.size() > 0
			return true; // 연결되지 못한 지역이 있다
		}
	}
	
}
