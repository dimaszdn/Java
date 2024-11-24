package Figures;

//слон
public class Bishop extends Figure {
    public Bishop(char color) { super("B", color); }

    @Override
    public boolean canMove(int row, int col, int row1, int col1)
    {
        return super.canMove(row, col, row1, col1) &&
                (Math.abs(row - row1) == Math.abs(col - col1));
    }
}
