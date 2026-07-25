import java.util.*;
import java.io.*;

public class Main {

    static class Node implements Comparable<Node>{
        int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o){
            if(this.x == o.x) return Integer.compare(this.y, o.y);
            return Integer.compare(this.x, o.x);
        }

        @Override
        public boolean equals(Object o){
            Node node = (Node) o;
            return this.x == node.x && this.y == node.y;
        }

        @Override
        public int hashCode(){
            return Objects.hash(x, y);
        }


    }

    static int N, M, result;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Set<ArrayList<Node>> battery_set = new HashSet<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = Integer.MIN_VALUE;

        // 5칸 네모 만들기
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                ArrayList<Node> module = new ArrayList<>();
                module.add(new Node(x, y));
                boolean[][] visited = new boolean[N][M];
                visited[x][y] = true;
                backtracking(1, module, visited);
            }
        }

        cal_result();
        System.out.println(result);

    }

    public static void backtracking(int depth, ArrayList<Node> module, boolean[][] visited){
        if(depth == 5){
            ArrayList<Node> temp = new ArrayList<>(module);
            Collections.sort(temp);
            battery_set.add(temp);
            return;
        }

        for (int i = 0; i < module.size(); i++) {
            Node now = module.get(i);
            int x = now.x;
            int y = now.y;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(check(nx, ny) || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                module.add(new Node(nx, ny));
                backtracking(depth + 1, module, visited);
                visited[nx][ny] = false;
                module.remove(module.size() - 1);


            }
        }
    }

    public static boolean check(int nx, int ny){
        return nx < 0 || nx >= N || ny < 0 || ny >= M;
    }

    public static void cal_result(){
        ArrayList<ArrayList<Node>> module_list = new ArrayList<>(battery_set);
        int len = module_list.size();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (battery_check(module_list.get(i), module_list.get(j))){
                    int sum = cal_point(module_list.get(i), module_list.get(j));
                    result = Math.max(result, sum);
                }
            }
        }

    }

    public static boolean battery_check(ArrayList<Node> module1, ArrayList<Node> module2){
        int cnt = 0;
        for (Node node1 : module1) {
            for (Node node2 : module2) {
                if (node1.equals(node2)) {
                    cnt++;
                }
            }
        }
        if(cnt == 2) return true;
        return false;
    }

    public static int cal_point(ArrayList<Node> module1, ArrayList<Node> module2){
        int sum = 0;
        for (Node node : module1) {
            sum += map[node.x][node.y];
        }

        for (Node node : module2) {
            sum += map[node.x][node.y];
        }

        return sum;
    }
}