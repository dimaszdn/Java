package Figures;

//пешка
public class Pawn extends Figure {
    public Pawn(char color) { super("P", color); }


    @Override
    public boolean canMove(int row, int col, int row1, int col1)
    {
        if (row == 1 && this.getColor() == 'w' || row == 6 && this.getColor() == 'b') // первый ход
        {
            return ((((row + 2 == row1) || (row + 1 == row1)) && this.getColor() == 'w') ||
                    (((row - 2 == row1) || (row - 1 == row1)) && this.getColor() == 'b')) &&
                    (col == col1) &&
                    super.canMove(row, col, row1, col1);
        }
        else
        {
            return (((row + 1 == row1) && this.getColor() == 'w') ||
                    ((row - 1 == row1) && this.getColor() == 'b')) &&
                    (col == col1) &&
                    super.canMove(row, col, row1, col1);
        }
    }


    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        return Math.abs(col - col1) == 1 &&
                ((row - row1 == 1 && this.getColor() == 'b') ||
                (row - row1 == -1 && this.getColor() == 'w')) &&
                super.canMove(row, col, row1, col1);
    }
}
