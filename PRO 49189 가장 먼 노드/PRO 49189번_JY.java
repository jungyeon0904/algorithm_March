import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class PRO49189 {

	public static void main(String[] args) {
		int n = 6;
		int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
		int ans = 0;
		ans = solution(n, edge);
		System.out.println(ans);

	}

	static public int solution(int n, int[][] edge) {
        int answer = 0;
        int N = n + 1;
        ArrayList<Integer>[] adjList = new ArrayList[N]; // [0] 인덱스를 쓰지 않음
        for(int i = 0; i < N; i++){ // adjList 초기화
            adjList[i] = new ArrayList<>();
        }
        int end = edge.length;
        for(int i = 0; i < end; i++){ // adjList 입력
            // 무방향 그래프
            adjList[edge[i][0]].add(edge[i][1]);
            adjList[edge[i][1]].add(edge[i][0]);
        }
        
        boolean[] visited = new boolean[N]; // 방문 배열
        Queue<Integer> q = new ArrayDeque<>(); // 큐 생성
        q.offer(1); // 시작 정점
        visited[1]= true;
        
        int cur, size, count;
        while(!q.isEmpty()){
            
            answer = q.size();
            size = q.size();
            
            while(size-- > 0){
                cur = q.poll();
                
                for(int vertex : adjList[cur]){
                    if(visited[vertex]) continue;
                    visited[vertex] = true;
                    q.offer(vertex);
                }
            }
            
        }
        return answer;
    }
}
