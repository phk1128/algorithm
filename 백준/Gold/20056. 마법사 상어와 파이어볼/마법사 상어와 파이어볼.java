import java.util.*;
import java.io.*;

public class Main{
	static int map[][][];
	static Queue<Integer[]> fireball =new LinkedList<>();
	static int dy[] = {-1,-1,0,1,1,1,0,-1};
	static int dx[] = {0,1,1,1,0,-1,-1,-1};
	static Queue<Integer> direction[][];
	public static void main(String[] args) throws IOException{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());

		//맵에 3가지 정보를 담는다. 파이어볼의 개수, 파이어볼의 합계질량, 파이어볼의 합계속도
		map = new int[N][N][3];
		
		//해당 위치의 방향의 짝수, 홀수 여부를 알기 위함
		direction=new LinkedList[N][N];
	
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				direction[i][j]=new LinkedList<>();
			}
		}

		//파이어볼의 정보를 queue에 담는다.
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			fireball.add(new Integer[] {r-1,c-1,m,s,d});
		}

		//파이어볼을 이동시키고
		//파이어볼의 위치를 조정한다.
		for(int i=0;i<K;i++) {
			moveFireball();
			replaceFireball();
		}

		//k번 함수가 시행되면 파이어볼의 개수를 세어준다
		int sum = countFireball();


		//합계 출력
		System.out.println(sum);


	}
	
	//파이어볼을 이동시키는 함수
	public static void moveFireball() {
		
		while(!fireball.isEmpty()) {
			Integer temp[]= fireball.poll();
			int r = temp[0];
			int c = temp[1];
			int m = temp[2];
			int s = temp[3];
			int d = temp[4];
			
			//1칸씩 이동시키지 않고, 나머지 연산으로 다음 위치를 구한다.
			//(1칸씩 이동시킨다면 시간 초과가 날 것으로 예상)
			r += (dy[d] * s);
			c += (dx[d] * s);
			r%=map.length;
			c%=map.length;
			
			//다음 위치가 음수가 된 경우는 따로 처리
			if(r<0) {
				r = Math.abs(r);
				r = map.length - r;
			}
			if(c<0) {
				c=Math.abs(c);
				c=map.length - c;
			}
			
			map[r][c][0] += 1; //개수 
			map[r][c][1] += m; //질량
			map[r][c][2] += s; //속도


			//방향을 저장해준다.
			direction[r][c].add(d);

		}


	}
	//파이어볼의 위치 조정
	public static void replaceFireball() {

		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length;j++) {
				int sum = map[i][j][0];  //파이어볼 개수
				int m = map[i][j][1]; //파이어볼 합계 질량
				int s = map[i][j][2]; //파이어볼 합계 속력
		
				//해당 위치에 파이어볼이 1개라면 그냥 다시 똑같이 add해준다.
				if(map[i][j][0]==1) {
					int now = direction[i][j].poll();
					fireball.add(new Integer[] {i,j,m,s,now});
				}
				
				//해당 위치에 파이어볼이 2개 이상이라면
				else if(map[i][j][0]>1) {
					//새로운 크기, 속도
					int newM = m / 5;
					int newS= s / sum ;
					
					//기본 방향은 0,2,4,6
					int dir[]= {0,2,4,6};
					boolean findOdd= false;
					boolean findEven=false;
					//해당 좌표에 저장된 방향을 전부 poll해준다.
				
					while(!direction[i][j].isEmpty()) {
						int poll=direction[i][j].poll();
						if(poll%2==0) findEven = true;
						else findOdd = true;
					}
					
					//홀수도 찾고, 짝수도 찾았다면 1,3,5,7로 조정
					if(findOdd==true&&findEven==true) {
						dir[0] = 1;
						dir[1] = 3;
						dir[2] = 5;
						dir[3] = 7;
					}

					//만약 새로운 합계가 0이라면 추가해주지 않아도 된다.
					//그러므로 1이상인 경우에만 추가해준다.
					if(newM!= 0) {
						for(int k=0;k<4;k++) {
							fireball.add(new Integer[] {i,j,newM,newS,dir[k]});
						}

					}
					

				}

				//해당 맵의 파이어볼을 없애준다.
				map[i][j][0] = 0;
				map[i][j][1] = 0;
				map[i][j][2] = 0;
			
				
			}
		}

	}
	//파이어볼을 세어주는 함수
	public static int countFireball() {

		int sum = 0;
		///temp[2]가 질량이므로 질량을 다 더해준다.
		while(!fireball.isEmpty()) {
			Integer temp[]= fireball.poll();
			sum += temp[2];
		}

		return sum;
	}

}