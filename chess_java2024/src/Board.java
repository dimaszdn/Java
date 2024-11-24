import Figures.*;

public class Board {

    private char colorGame;

    public void setColorGame(char colorGame) {
        this.colorGame = colorGame;
    }

    public  char getColorGame(){
        return colorGame;
    }

    private Figure fields[][] = new Figure[8][8];

    public void init(){
        this.fields[1][3] = new Pawn('w');
        this.fields[2][3] = new Queen('b');
        this.fields[3][3] = new Queen('b');
        /*//Готовая расстановка
        this.fields[0] = new Figure[]{
                new Rook('w'),new Knight('w'),new Bishop('w'),new Queen('w'),
                new King('w'),new Bishop('w'),new Knight('w'),new Rook('w')
        };
        this.fields[1] = new Figure[]{
                new Pawn('w'),new Pawn('w'),new Pawn('w'),new Pawn('w'),
                new Pawn('w'),new Pawn('w'),new Pawn('w'),new Pawn('w')
        };
        this.fields[6] = new Figure[] {
                new Pawn('b'),new Pawn('b'),new Pawn('b'),new Pawn('b'),
                new Pawn('b'),new Pawn('b'),new Pawn('b'),new Pawn('b')
        };
        this.fields[7] = new Figure[]{
                new Rook('b'),new Knight('b'),new Bishop('b'),new Queen('b'),
                new King('b'),new Bishop('b'),new Knight('b'),new Rook('b')
        };*/
    }

    public String getCell(int row, int col){
        Figure figure = this.fields[row][col];
        if (figure ==null){
            return "    ";
        }
        return  " "+figure.getColor()+figure.getName()+" ";
    }

    public void print_board(){
        System.out.println(" +----+----+----+----+----+----+----+----+");
        for (int row = 7; row > -1 ; row --){
            System.out.print(row);
            for (int col=0; col<8; col++){
                System.out.print("|"+getCell(row, col));
            }
            System.out.println("|");
            System.out.println(" +----+----+----+----+----+----+----+----+");
        }

        for(int col=0; col< 8; col++){
            System.out.print("    "+col);
        }
    }

    public boolean move_figure(int row, int col, int row1, int col1){
      Figure figure = this.fields[row][col];

      boolean flag1 = figure != null &&
                      this.fields[row1][col1] == null &&
                      figure.getColor() == this.colorGame &&
                      figure.canMove(row, col, row1, col1) &&
                      !isFigureOnWay(row, col, row1, col1);

      if (flag1){
          this.fields[row1][col1] = figure;
          this.fields[row][col] = null;
          return true;
      }
      else  if (figure.canAttack(row, col, row1, col1) && this.fields[row1][col1] != null && this.fields[row1][col1].getColor() != this.fields[row][col].getColor()){
          this.fields[row1][col1] = figure;
          this.fields[row][col] = null;
          return true;
      }
      return false;
    }

    private boolean isFigureOnWay(int row, int col, int row1, int col1)
    {
        boolean isFigure = false;
        Figure figure = fields[row][col];
        switch (figure.getName())
        {
            case "P":
                if ((row == 1 && figure.getColor() == 'w' ||
                    row == 6 && figure.getColor() == 'b') &&
                    Math.abs(row - row1) == 2 &&
                    (figure.getColor() == 'w' && fields[row + 1][col] != null) ||
                    (figure.getColor() == 'b' && fields[row - 1][col] != null))

                    isFigure = true;
                break;

            case "R": // для ладьи
                if (col == col1) // ход по вертикали
                {
                    for (int i = Integer.min(row, row1) + 1; i < Integer.max(row, row1); ++i)
                    {
                        if (fields[i][col] != null)
                        {
                            isFigure = true;
                            break;
                        }
                    }
                }
                else if (row == row1) // ход по горизонтали
                {
                    for (int i = Integer.min(col, col1) + 1; i < Integer.max(col, col1); ++i)
                    {
                        if (fields[row][i] != null)
                        {
                            isFigure = true;
                            break;
                        }
                    }
                }
                break;

            case "B": //для слона
                int lengthPathForBishop = Math.abs(row - row1); // row - row1 == col - col1
                for (int i = 1; i < lengthPathForBishop; ++i)
                {
                    if (row < row1 && col < col1 && fields[row + i][col + i] != null)  //I четверть
                    {
                        isFigure = true;
                        break;
                    }
                    else if (row < row1 && col > col1 && fields[row + i][col - i] != null) // II четверть
                    {
                        isFigure = true;
                        break;
                    }
                    else if (row > row1 && col > col1 && fields[row - i][col - i] != null) // III четверть
                    {
                        isFigure = true;
                        break;
                    }
                    else if (row > row1 && col < col1 && fields[row - i][col + i] != null) // IV четверть
                    {
                        isFigure = true;
                        break;
                    }
                }
                break;

            case "Q": //для ферзя
                if (col == col1) // ход по вертикали
                {
                    for (int i = Integer.min(row, row1) + 1; i < Integer.max(row, row1); ++i)
                    {
                        if (fields[i][col] != null)
                        {
                            isFigure = true;
                            break;
                        }
                    }
                }
                else if (row == row1) // ход по горизонтали
                {
                    for (int i = Integer.min(col, col1) + 1; i < Integer.max(col, col1); ++i)
                    {
                        if (fields[row][i] != null)
                        {
                            isFigure = true;
                            break;
                        }
                    }
                }
                else
                {
                    int lengthPathForQueen = Math.abs(row - row1); // row - row1 == col - col1
                    for (int i = 1; i < lengthPathForQueen; ++i)
                    {
                        if (row < row1 && col < col1 && fields[row + i][col + i] != null)  //I четверть
                        {
                            isFigure = true;
                            break;
                        }
                        else if (row < row1 && col > col1 && fields[row + i][col - i] != null) // II четверть
                        {
                            isFigure = true;
                            break;
                        }
                        else if (row > row1 && col > col1 && fields[row - i][col - i] != null) // III четверть
                        {
                            isFigure = true;
                            break;
                        }
                        else if (row > row1 && col < col1 && fields[row - i][col + i] != null) // IV четверть
                        {
                            isFigure = true;
                            break;
                        }
                    }
                }
                break;
        }
        return isFigure;
    }
}
