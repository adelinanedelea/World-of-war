package TemaPOO;

import java.util.ArrayList;
import java.util.Random;

public class Grid extends ArrayList<ArrayList<Cell>> {
    int lungime, latime;
    int numberOfPersonaj;
    Cell current;
    private Grid (int lungime, int latime) {
        this.lungime = lungime;
        this.latime = latime;
    }
    //generare test
    public static Grid test() {
        Grid g = new Grid ( 5, 5);
        ArrayList<Cell> array = new ArrayList<Cell>();
        Cell cell;
        cell = new Cell(0, 0, 0 , null);
        array.add(0, cell);
        cell = new Cell (0, 1, 0, null);
        array.add(1, cell);

        cell = new Cell(0, 2, 0, null);
        array.add(2, cell);
        Shop shop = new Shop();
        cell = new Cell(0, 3, 2, shop);
        array.add(3, cell );
        cell = new Cell(0, 4, 0, null);
        array.add(4, cell);
        g.add(0, array);
        array = new ArrayList<Cell>();
        cell = new Cell(1, 0, 0 , null);
        array.add(0, cell);
        cell = new Cell (1, 1, 0, null);
        array.add(1, cell);
        cell = new Cell(1, 2, 0, null);
        array.add(2, cell);
        shop = new Shop();
        cell = new Cell(1, 3, 2, shop);
        array.add(3, cell );
        cell = new Cell(1, 4, 0, null);
        array.add(4, cell);
        g.add(1, array);
        array = new ArrayList<Cell>();
        shop = new Shop();
        cell = new Cell(2, 0, 2 , shop);
        array.add(0, cell);
        cell = new Cell (2, 1, 0, null);
        array.add(1, cell);
        cell = new Cell(2, 2, 0, null);
        array.add(2, cell);
        cell = new Cell(2, 3, 0, null);
        array.add(3, cell );
        cell = new Cell(2, 4, 0, null);
        array.add(4, cell);
        g.add(2, array);
        array = new ArrayList<Cell>();
        cell = new Cell(3, 0, 0, null);
        array.add(0, cell);
        cell = new Cell (3, 1, 2, null);
        array.add(1, cell);
        cell = new Cell(3, 2, 0, null);
        array.add(2, cell);
        cell = new Cell(3, 3, 0, null);
        array.add(3, cell );
        Enemy e = new Enemy();
        cell = new Cell(3, 4, 1,  e);
        array.add(4, cell);
        g.add(3, array);
        array = new ArrayList<Cell>();
        cell = new Cell(4, 0, 0, null);
        array.add(0, cell);
        cell = new Cell (4, 1, 0, null);
        array.add(1, cell);

        cell = new Cell(4, 2, 0, null);
        array.add(2, cell);

        cell = new Cell(4, 3, 0, null);
        array.add(3, cell );
        cell = new Cell(4, 4, 3, null);
        array.add(4, cell);
        g.add(4, array);
        array = new ArrayList<Cell>();
        g.current = g.get(0).get(0);
        return g;
    }
    //generare harta
    public static Grid generare (int latime, int lungime) {
        Grid g = new Grid(latime, lungime);
        Random rand = new Random();
        int x, y;

        for (int i = 0; i < latime; ++i) {
            ArrayList<Cell> array = new ArrayList<Cell>();
            for (int j = 0; j < lungime; ++j) {
                if ( i == 0 && j == 0 ) {
                    Cell cell = new Cell (i, j, 0, null);
                    array.add(j, cell);
                }
                else {
                if ( i == latime - 1 && j == lungime - 1) {
                    Cell cell = new Cell(i, j, 3, null);
                    array.add(j, cell);
                }
                else {
                    if (i % 2 == 0 && j % 2 == 0) {
                        Shop shop = new Shop();
                        Cell cell = new Cell(i, j, 2, shop);
                        array.add(j, cell);
                    } else {
                        if (i % 3 == 0) {
                            Enemy enemy = new Enemy();
                            Cell cell = new Cell(i, j, 1, enemy);
                            array.add(j, cell);
                        } else {
                            int xx;
                            xx = rand.nextInt(4);
                            if (xx == 1) {
                                Enemy enemy = new Enemy();
                                Cell cell = new Cell(i, j, 1, enemy);
                                array.add(j, cell);
                            } else {
                                if (xx == 2) {
                                    Shop shop = new Shop();
                                    Cell cell = new Cell(i, j, 2, shop);
                                    array.add(j, cell);
                                } else {
                                    Cell cell = new Cell(i, j, xx, null);
                                    array.add(j, cell);
                                }
                            }

                        }
                    }
                }
                }
                g.add(i, array);
            }
        }
        g.current = g.get(0).get(0);
        return g;
    }
    public void goNorth() {
        int x, y;
        x = current.Ox;
        y = current.Oy;
        --x;
        if ( x < 0) {

        }
        else {
            current = this.get(x).get(y);
        }
    }
    public void goEast() {
        int x, y;
        x = current.Ox;
        y = current.Oy;
        ++y;
        if ( y >= lungime) {

        }
        else {
            current = this.get(x).get(y);
        }
    }
    public void goSouth() {
        int x, y;
        x = current.Ox;
        y = current.Oy;
        ++x;
        if ( x >= latime) {

        }
        else {
            current = this.get(x).get(y);
        }
    }
    public void goWest() {
        int x, y;
        x = current.Ox;
        y = current.Oy;
        --y;
        if (y < 0) {

        }
        else {
            current = this.get(x).get(y);
        }
    }
}
