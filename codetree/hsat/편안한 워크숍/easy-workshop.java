import java.util.*;
import java.io.*;

public class Main {

    static int N, K, min_h, max_h;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static ArrayList<int[]> arr = new ArrayList<>();

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        int[][][] dp = new int[N][N][K + 1];

        min_h = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());

                map[i][j] = num;

                if (num < min_h) min_h = num;
                if (num > max_h) max_h = num;

                arr.add(new int[]{num, i, j});

                Arrays.fill(dp[i][j], INF);

                dp[i][j][1] = 0;
            }
        }

        Collections.sort(arr, (a1, a2) -> {
            return Integer.compare(a1[0], a2[0]);
        });

        for (int[] now : arr) {
            int x = now[1];
            int y = now[2];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (check(nx, ny)) continue;

                if (map[x][y] < map[nx][ny]) {
                    int diff = map[nx][ny] - map[x][y];

                    for (int len = 1; len < K; len++) {
                        if (dp[x][y][len] == INF) continue;
                        int nextMax = Math.max(dp[x][y][len], diff);
                        dp[nx][ny][len + 1] = Math.min(dp[nx][ny][len + 1], nextMax);
                    }
                }
            }
        }

        int result = INF;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result = Math.min(result, dp[i][j][K]);
            }
        }

        System.out.println(result == INF ? -1 : result);
    }

    public static boolean check(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}