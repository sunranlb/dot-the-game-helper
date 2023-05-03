import java.util.Scanner;

public class Main {
    private static final int L = 4;
    private static final char SOLID = '◉';
    private static final char HOLLOW = '○';
    private static int[][] sBoard = new int[L][L];
    private static boolean[][] sCorner = new boolean[L + 1][L + 1];

    private static boolean checkBoard() {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                int cnt = 0;
                if (sCorner[i][j]) {
                    cnt++;
                }
                if (sCorner[i][j + 1]) {
                    cnt++;
                }
                if (sCorner[i + 1][j]) {
                    cnt++;
                }
                if (sCorner[i + 1][j + 1]) {
                    cnt++;
                }
                if (cnt != sBoard[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    private static void printBoard() {
        int i = 0;
        StringBuilder sb;
        for (; i < L; i++) {
            sb = new StringBuilder();
            int j = 0;
            for (; j < L; j++) {
                sb.append(sCorner[i][j] ? SOLID : HOLLOW);
                sb.append(" - ");
            }
            sb.append(sCorner[i][j] ? SOLID : HOLLOW);
            System.out.println(sb);

            sb = new StringBuilder();
            j = 0;
            for (; j < L; j++) {
                sb.append("| ");
                sb.append(sBoard[i][j]);
                sb.append(' ');
            }
            sb.append('|');
            System.out.println(sb);
        }
        sb = new StringBuilder();
        int j = 0;
        for (; j < L; j++) {
            sb.append(sCorner[i][j] ? SOLID : HOLLOW);
            sb.append(" - ");
        }
        sb.append(sCorner[i][j] ? SOLID : HOLLOW);
        System.out.println(sb);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String ip = sc.nextLine();
            if (ip == null || ip.isEmpty()) {
                break;
            }
            if (ip.length() == L * L) {
                for (int i = 0; i < L * L; i++) {
                    sBoard[i / L][i % L] = ip.charAt(i) - '0';
                }
            }
        }
    }
}