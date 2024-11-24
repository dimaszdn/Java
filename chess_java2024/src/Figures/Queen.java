package Figures;

//ферзь
public class Queen extends Figure {
    public Queen(char color) { super("Q", color); }

    @Override
    public boolean canMove(int row, int col, int row1, int col1)
    {
        return super.canMove(row, col, row1, col1) &&
                ((col == col1 && row != row1) ||
                (col != col1 && row == row1) ||
                (Math.abs(row - row1) == Math.abs(col - col1)));
    }
}
