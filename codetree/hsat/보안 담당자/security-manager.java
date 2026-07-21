import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static String word;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        word = br.readLine();

        if(check()) System.out.println("Yes");
        else System.out.println("No");

    }

    public static boolean check(){
        if(N % 2 == 1) {
            return false;
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if(word.charAt(i) == ')') cnt--;
            else cnt++;
            if(cnt < 0) return false;
        }

        cnt = 0;
        for (int i = N-1; i >=0 ; i--) {
            if(word.charAt(i) == '(') cnt--;
            else cnt++;
            if(cnt < 0) return false;
        }

        return true;
    }

}