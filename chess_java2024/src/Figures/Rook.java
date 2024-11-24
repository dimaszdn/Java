package Figures;

//ладья
public class Rook extends Figure
{
    public Rook(char color) { super("R", color); }

    @Override
    public boolean canMove(int row, int col, int row1, int col1)
    {
        return ((col == col1 && row != row1) ||
                (col != col1 && row == row1)) &&
                super.canMove(row, col, row1, col1);
    }
}