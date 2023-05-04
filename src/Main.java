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
                if (countAround(i, j) != sBoard[i][j]) {
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

    private static boolean traverse(int i, int j) {
        //2421342244213321
//        System.out.println(i + "," + j);
        if (j == L) {
            j = 0;
            i++;
            if (i == L) {
                printBoard();
                return true;
            }
        }
        int cnt = countAround(i, j);
        int num = sBoard[i][j];
        if (cnt > num) {
            return false;
        } else if (cnt == num) {
            return traverse(i, j + 1);
        } else {
            int diff = num - cnt;
            if (i == 0) {
                if (j == 0) {
                    if (diff == 4) {
                        boolean[] p = new boolean[]{true, true, true, true};
                        op(true, i, j, p);
                        if (traverse(i, j + 1)) {
                            return true;
                        }
                        op(false, i, j, p);
                    } else if (diff == 2) {
                        boolean[] p = new boolean[4];
                        for (int k = 0; k < 4; k++) {
                            if (!sCorner[i + k / 2][j + k % 2]) {
                                p[k] = true;
                                for (int l = k + 1; l < 4; l++) {
                                    if (!sCorner[i + l / 2][j + l % 2]) {
                                        p[l] = true;
                                        op(true, i, j, p);
                                        if (traverse(i, j + 1)) {
                                            return true;
                                        }
                                        op(false, i, j, p);
                                        p[l] = false;
                                    }
                                }
                                p[k] = false;
                            }
                        }
                    } else {
                        boolean init = diff == 3;
                        boolean[] p = new boolean[]{init, init, init, init};
                        for (int k = 0; k < 4; k++) {
                            if (!sCorner[i + k / 2][j + k % 2]) {
                                p[k] = !init;
                                op(true, i, j, p);
                                if (traverse(i, j + 1)) {
                                    return true;
                                }
                                op(false, i, j, p);
                                p[k] = init;
                            }
                        }
                    }
                } else {
                    if (diff > 2) {
                        return false;
                    } else if (diff == 2) {
                        boolean[] p = new boolean[]{false, true, false, true};
                        op(true, i, j, p);
                        if (traverse(i, j + 1)) {
                            return true;
                        }
                        op(false, i, j, p);
                    } else {
                        boolean[] p = new boolean[]{false, true, false, false};
                        op(true, i, j, p);
                        if (traverse(i, j + 1)) {
                            return true;
                        }
                        op(false, i, j, p);

                        p = new boolean[]{false, false, false, true};
                        op(true, i, j, p);
                        if (traverse(i, j + 1)) {
                            return true;
                        }
                        op(false, i, j, p);
                    }
                }
            } else {
                if (j == 0) {
                    if (diff > 2) {
                        return false;
                    } else if (diff == 2) {
                        boolean[] p = new boolean[]{false, false, true, true};
                        op(true, i, j, p);
                        if (traverse(i, j + 1)) {
                            return true;
                        }
                        op(false, i, j, p);
                    } else {
                        boolean[] p = new boolean[]{false, false, true, false};
                        op(true, i, j, p);
                        if (traverse(i, j + 1)) {
                            return true;
                        }
                        op(false, i, j, p);

                        p = new boolean[]{false, false, false, true};
                        op(true, i, j, p);
                        if (traverse(i, j + 1)) {
                            return true;
                        }
                        op(false, i, j, p);
                    }
                } else {
                    if (diff > 1) {
                        return false;
                    }
                    boolean[] p = new boolean[]{false, false, false, true};
                    op(true, i, j, p);
                    if (traverse(i, j + 1)) {
                        return true;
                    }
                    op(false, i, j, p);
                }
            }
        }
        return false;
    }

    private static void op(boolean add, int i, int j, boolean[] pos) {
        for (int k = 0; k < 4; k++) {
            if (pos[k]) {
                sCorner[i + k / 2][j + k % 2] = add;
            }
        }
    }

    private static int countAround(int i, int j) {
        int res = 0;
        if (sCorner[i][j]) {
            res++;
        }
        if (sCorner[i][j + 1]) {
            res++;
        }
        if (sCorner[i + 1][j]) {
            res++;
        }
        if (sCorner[i + 1][j + 1]) {
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        testMain();
        Scanner sc = new Scanner(System.in);
        while (true) {
            sCorner = new boolean[L + 1][L + 1];
//        String ip = "2421342244213321";
            String ip = sc.nextLine();
            if (ip == null || ip.isEmpty()) {
                break;
            }
            if (ip.length() == L * L) {
                for (int i = 0; i < L * L; i++) {
                    sBoard[i / L][i % L] = ip.charAt(i) - '0';
                }
                traverse(0, 0);
            } else {
                System.out.println("Length error: " + ip.length());
            }
        }
    }

    private static void testMain() {
        assert checkBoard();
        sCorner[0][0] = true;
        assert !checkBoard();
        sBoard[0][0] = 1;
        assert checkBoard();
        sCorner = new boolean[][]{
                {false, true, false, false, false},
                {true, true, false, false, true},
                {true, true, true, false, false},
                {true, true, true, true, false},
                {false, true, true, false, false}
        };
        sBoard = new int[][]{
                {3, 2, 0, 1},
                {4, 3, 1, 1,},
                {4, 4, 3, 1},
                {3, 4, 3, 1}
        };
        assert checkBoard();
        sCorner[0][0] = true;
        assert !checkBoard();
        sBoard[0][0] = 4;
        assert checkBoard();
    }
}