package org.springbus;

public class FoodFill {

    static int MAX_X = 5;
    static int MAX_Y = 5;
    static int[][] area = new int[MAX_X + 1][MAX_Y + 1];

    static void flood_fill(int x, int y, int color) {
        area[x][y] = color;
        if (x > 0 && area[x - 1][y] == 0) flood_fill(x - 1, y, color);
        if (y > 0 && area[x][y - 1] == 0) flood_fill(x, y - 1, color);
        if (x < MAX_X && area[x + 1][y] == 0) flood_fill(x + 1, y, color);
        if (y < MAX_Y && area[x][y + 1] == 0) flood_fill(x, y + 1, color);

    }


    public static void main(String[] args) {
        area[0][0] = 1;
        area[1][0] = 1;
        area[2][0] = 1;
        area[3][0] = 1;
        area[4][0] = 1;
        area[5][0] = 1;

        area[0][0] = 1;
        area[0][1] = 1;
        area[0][2] = 1;
        area[0][3] = 1;
        area[0][4] = 1;
        area[0][5] = 1;

        area[4][4] = 1;

        flood_fill(2, 2, 2);
        for (int i = 0; i < MAX_X; i++) {
            for (int j = 0; j < MAX_Y; j++) {
                System.out.print(area[i][j] + "\t");
            }
            System.out.println("");
        }
    }

}
