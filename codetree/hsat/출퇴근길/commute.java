import java.util.*;
import java.io.*;

public class Main {

    static int N, M, S, T;
    static boolean[] start, end, reverse_start, reverse_end;
    static ArrayList<Integer>[] adj, reverse_adj;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N+1];
        reverse_adj = new ArrayList[N+1];
        start = new boolean[N+1];
        end = new boolean[N+1];
        reverse_start = new boolean[N+1];
        reverse_end = new boolean[N+1];

        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
            reverse_adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            reverse_adj[b].add(a);
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        bfs(S, T, start, adj);
        bfs(T, -1, reverse_start, reverse_adj);
        bfs(T, S, end, adj);
        bfs(S, -1, reverse_end, reverse_adj);

        int result = 0;
        for (int i = 1; i <= N; i++) {
            if(i == S || i == T) continue;
            if(start[i] && end[i] && reverse_start[i] && reverse_end[i]) result++;
        }

        System.out.println(result);
    }

    public static void bfs(int x, int y, boolean[] visited, ArrayList<Integer>[] graph){
        Queue<Integer> q = new ArrayDeque<>();
        visited[x] = true;
        q.add(x);

        while (!q.isEmpty()){
            int now = q.poll();
            if(now == y) continue;
            for (int next : graph[now]){
                if(!visited[next]){
                    visited[next] = true;
                    q.add(next);
                }
            }
        }

    }


}