import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    
	//들어올린 박스 체크, 박스 무게
	static boolean boxchecked[];
	static int box[];
	
	//힘을 잃은 집게 체크, 집게 힘
	static boolean pickerchecked[];
	static int picker[];
	
	//정렬을 위한 우선 순위 큐
	static PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO Auto-generated method stub
			return o2-o1;
		}
	});
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	
    	//집게 설정
    	int picknum = Integer.parseInt(br.readLine());
    	
    	picker = new int[picknum];
    	pickerchecked = new boolean[picknum];
    	int pickmax = 0;
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for(int i = 0 ; i < picknum; i++) {
    		picker[i] = Integer.parseInt(st.nextToken());
    		if(picker[i] > pickmax) pickmax = picker[i];
    	}
    	
    	//박스 설정
    	int boxnum = Integer.parseInt(br.readLine());
    	
    	box = new int[boxnum];
    	boxchecked = new boolean[boxnum];
    	int boxmax = 0;
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0 ; i < boxnum; i++) {
    		queue.add(Integer.parseInt(st.nextToken()));
    	}
    	for(int i = 0 ; i < boxnum; i++) {
    		box[i] = queue.poll();
    	}
    	boxmax = box[0];
    	
    	
    	//기저조건
    	if(boxmax > pickmax) {
    		System.out.println(-1);
    		return;
    	}
    	
    	int time = 0;
    	int total = 0;
    	while(total < boxnum) {
    		time++;
    		for(int i = 0 ; i < picknum; i++) {
    			if(!pickerchecked[i]) {
    				for(int j = 0 ; j <= boxnum; j++) {
    					//잡을 게 없으면
    					if(j == boxnum) {
    						pickerchecked[i] = true; 
    						break;
    					}
    					if(picker[i]>=box[j] && !boxchecked[j]) {
    						total++;
    						boxchecked[j] = true;
    						break;
    					}
    				}
    			}
    		}
    	}
    	
    	System.out.println(time);
	}
}