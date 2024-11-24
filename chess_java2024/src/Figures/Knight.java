package Figures;

//конь
public class Knight extends Figure {
    public Knight(char color) { super("k", color);}

    @Override
    public boolean canMove(int row, int col, int row1, int col1)
    {
        return super.canMove(row, col, row1, col1) &&
                ((Math.abs(row - row1) == 2 && Math.abs(col - col1) == 1) ||
                (Math.abs(row - row1) == 1 && Math.abs(col - col1) == 2));
    }
}
