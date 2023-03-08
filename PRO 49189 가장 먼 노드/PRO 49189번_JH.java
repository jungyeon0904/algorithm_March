import java.util.LinkedList;
import java.util.Queue;

class Solution{    
    	static class Data{
		int to;
		Data link;
		public Data(int to, Solution.Data link) {
			super();
			this.to = to;
			this.link = link;
		}
	}
	
	static Data arr[];
    
    static boolean checked[];
    
    public int solution(int n, int[][] edge) {
        
        arr = new Data[n+1];
        checked = new boolean[n+1];
        
        int answer = 0;
        for(int i = 0 ; i< edge.length; i++){
            arr[edge[i][0]] = new Data(edge[i][1], arr[edge[i][0]]);
            arr[edge[i][1]] = new Data(edge[i][0], arr[edge[i][1]]);
        }
        
        answer = bfs(n);
        
        return answer;
    }
    
    static int bfs(int n){
        int max =0 ;
        
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(1);
        
        checked[1] = true;
        
        int now;
        
        while(!queue.isEmpty()){
            max = queue.size();
            for(int t = 0 ; t < max; t++){
                now = queue.poll();
                Data temp = arr[now];
                for(;temp!=null  ;temp = temp.link){
                    if(!checked[temp.to]){
                        queue.add(temp.to);
                        checked[temp.to] = true;
                    }
                }    
            }
        }
        return max;
    }
}