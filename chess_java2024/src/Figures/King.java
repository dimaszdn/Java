package Figures;

//король
public class King extends Figure {
    public King(char color) { super("K", color); }

    @Override
    public boolean canMove(int row, int col, int row1, int col1)
    {
        return super.canMove(row, col, row1, col1) &&
                (Math.abs(row - row1) <= 1 && Math.abs(col - col1) <= 1);
    }
}
