import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 정점의 갯수
	static int V;

	// 인접리스트 구현
	static class Data {
		int to;
		Data link;

		public Data(int to, Main.Data link) {
			this.to = to;
			this.link = link;
		}
	}

	// 가중치
	static int weight[];

	// 인접리스트 구현
	static Data arr[];

	// 최솟값
	static int min = Integer.MAX_VALUE;

	// 선택된 정점
	static boolean isselected[];

	// 체크배열
	static boolean checked[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		V = Integer.parseInt(br.readLine());

		weight = new int[V + 1];
		arr = new Data[V + 1];
		checked = new boolean[V + 1];
		isselected = new boolean[V + 1];

		// 가중치 값 입력 받기
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= V; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}

		// 간선 정보 입력 받기
		for (int i = 1; i <= V; i++) {
			st = new StringTokenizer(br.readLine());

			// 연결된 간선 갯수 만큼 인접 리스트에 연결
			int N = Integer.parseInt(st.nextToken());
			for (int j = 1; j <= N; j++) {
				int to = Integer.parseInt(st.nextToken());
				// 연결된 간선 정보를 인접 리스트에 입력
				arr[i] = new Data(to, arr[i]);
			}
		}

		perm(1);

		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else {
			System.out.println(min);
		}
	}

	static void perm(int cnt) {

		// 기저조건
		if (cnt == V + 1) {
			checked = new boolean[V+1];
			bfs();
			return;
		}

		isselected[cnt] = true;
		perm(cnt + 1);
		isselected[cnt] = false;
		perm(cnt + 1);
	}

	static void bfs() {

		int A1[] = new int[V + 1];
		int B1[] = new int[V + 1];

		int Aidx = 0;
		int Bidx = 0;
		for (int i = 1; i <= V; i++) {
			if (isselected[i]) {
				A1[Aidx++] = i;
			}
			if (!isselected[i]) {
				B1[Bidx++] = i;
			}
		}

		// 한쪽 몰빵 나오면 끝내기
		if (Aidx == 0 || Aidx == V) {
			return;
		}

		Queue<Data> queue = new LinkedList<>();

		// 첫번째 구획 순회
		queue.add(arr[A1[0]]);
		checked[A1[0]] = true;

		Data now;
		// BFS로 연결된 간선 모두 들리기
		while (!queue.isEmpty()) {
			now = queue.poll();

			for (; now != null; now = now.link) {
				for (int i = 0; i < Aidx; i++) {
					if (now.to == A1[i] && !checked[now.to]) {
						queue.offer(arr[now.to]);
						checked[now.to] = true;
					}
				}
			}
		}

		// 두번째 구획 순회
		queue.add(arr[B1[0]]);
		checked[B1[0]] = true;

		// BFS로 연결된 간선 모두 들리기
		while (!queue.isEmpty()) {
			now = queue.poll();
			for (; now != null; now = now.link) {
				for (int i = 0; i < Bidx; i++) {
					if (now.to == B1[i] && !checked[now.to]) {
						queue.offer(arr[now.to]);
						checked[now.to] = true;
					}
				}
			}
		}

		// 방문하지 못한 곳이 있다면 2개로 분리 불가하다는 뜻
		for (int i = 1; i <= V; i++) {
			if (!checked[i])
				return;
		}

		int localmin = 0;

		int Asum = 0;
		for (int i = 0; i < Aidx; i++) {
			Asum += weight[A1[i]];
		}
		int Bsum = 0;
		for (int i = 0; i < Bidx; i++) {
			Bsum += weight[B1[i]];
		}

		localmin = Math.abs(Asum - Bsum);

		if (min > localmin) {
			min = localmin;
		}

		return;
	}
}