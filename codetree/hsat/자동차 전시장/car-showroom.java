import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K;
    static int[] people;
    static int[][] dist;
    static ArrayList<Integer>[] adj;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        people = new int[K];
        dist = new int[K][N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adj[x].add(y);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(st.nextToken());
            people[i] = num;
            dijkstra(i, num);
        }

        int max_dist = INF;
        for (int i = 1; i <= N; i++) {
            int temp = 0;
            for (int j = 0; j < K; j++) {
                temp = Math.max(temp, dist[j][i]);
            }
            if (max_dist > temp) max_dist = temp;

        }

        if(max_dist == INF) System.out.println(-1);
        else System.out.println(max_dist);


    }

    public static void dijkstra(int idx, int start){
        Arrays.fill(dist[idx], INF);
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        dist[idx][start] = 0;

        while (!q.isEmpty()){
            int now = q.poll();
            for(int next : adj[now]){
                if(dist[idx][next] > dist[idx][now] + 1){
                    dist[idx][next] = dist[idx][now] + 1;
                    q.add(next);
                }
            }
        }
    }

}