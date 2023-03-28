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
		
		int cardnum = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		//섞는 순서
		int D[] = new int[cardnum+1];
		//카드 배치 
		int arr[] = new int[cardnum+1];
		//옳긴 후 카드 배치
		int brr[] = new int[cardnum+1];
		
		
		//초기 배치 삽입
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= cardnum; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//섞는 순서 받아오기
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= cardnum; i++) {
			D[i] = Integer.parseInt(st.nextToken());
		}
		
		//T번 시행
		for(int t = 0 ; t < T; t++) {
			for(int i = 1 ; i <= cardnum; i++) {
				brr[D[i]] = arr[i]; 
			}
			
			for(int i = 1; i <= cardnum; i++) {
				arr[i] = brr[i];
			}
		}
		
		for(int i = 1 ; i <= cardnum; i++) {
			System.out.print(arr[i]+ " ");
		}
	}
}