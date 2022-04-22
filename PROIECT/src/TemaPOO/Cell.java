package TemaPOO;

public class Cell {


    int Ox, Oy;
    int celltype;
    CellElement cellelement;
    int state;
    public Cell (int Ox, int Oy, int celltype, CellElement cellelement) {

        this.Ox = Ox;
        this.Oy = Oy;
        this.celltype = celltype;
        this.cellelement = cellelement;
        this.state = 0;

    }
    public String toString() {

        return "(" + Ox + " " + Oy + " " + celltype + ")";

    }

}
