import Figures.*;

import java.util.Objects;

public class Board {

    private char colorGame;
    private boolean mate = false;

    public void setColorGame(char colorGame) {
        this.colorGame = colorGame;
    }

    public  char getColorGame(){
        return colorGame;
    }

    public boolean getMate()
    {
        return mate;
    }

    private int[] blackKingCoordinates = {7, 4};
    private int[] whiteKingCoordinates = {0, 4};

    private Figure fields[][] = new Figure[8][8];

    public void init(){
        //Готовая расстановка
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
        };
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

    public boolean move_figure(int row, int col, int row1, int col1)
    {
        Figure figure = this.fields[row][col];

        if (figure == null || figure.getColor() != this.colorGame)
            return false;

        char colorOpponent;
        if (colorGame == 'w')
            colorOpponent = 'b';
        else
            colorOpponent = 'w';

        boolean canMove = moveConditionIsCorrect(row, col, row1, col1) || attackConditionIsCorrect(row, col, row1, col1);

        if (!canMove)
            return false;
        else
        {
            Figure figureR1C1 = fields[row1][col1];
            move(row, col, row1, col1);
            if (isShah(colorGame))
            {
                move(row1, col1, row, col);
                fields[row1][col1] = figureR1C1;
                return false;
            }
            else if (isMate(colorOpponent))
            {
                System.out.println("Мат " + ((colorOpponent == 'w') ? "белым" : "чёрным"));
                this.mate = true;
            }
            else if (isShah(colorOpponent))
            {
                System.out.println("Шах " + ((colorOpponent == 'w') ? "белым" : "чёрным"));
            }
            return true;
        }
    }

    private boolean moveConditionIsCorrect(int row, int col, int row1, int col1)
    {
        return this.fields[row][col] != null &&
               this.fields[row1][col1] == null &&
               this.fields[row][col].canMove(row, col, row1, col1) &&
               !isFigureOnWay(row, col, row1, col1);
    }

    private boolean attackConditionIsCorrect(int row, int col, int row1, int col1)
    {
        return this.fields[row][col] != null &&
               this.fields[row1][col1] != null &&
               this.fields[row1][col1].getColor() != this.fields[row][col].getColor() &&
               this.fields[row][col].canAttack(row, col, row1, col1) &&
               !isFigureOnWay(row, col, row1, col1);
    }

    private void move(int row, int col, int row1, int col1)
    {
        Figure figure = this.fields[row][col];

        if (Objects.equals(figure.getName(), "K"))
        {
            if (figure.getColor() == 'w')
            {
                whiteKingCoordinates[0] = row1;
                whiteKingCoordinates[1] = col1;
            }
            else
            {
                blackKingCoordinates[0] = row1;
                blackKingCoordinates[1] = col1;
            }
        }

        this.fields[row1][col1] = figure;
        fields[row][col] = null;
    }

    private boolean isShah(char color)
    {
        int x, y;
        if (color == 'w')
        {
            x = whiteKingCoordinates[0];
            y = whiteKingCoordinates[1];
        }
        else
        {
            x = blackKingCoordinates[0];
            y = blackKingCoordinates[1];
        }
        for (int i = 0; i < 8; ++i)
        {
            for (int j = 0; j < 8; ++j)
            {
                //if (fields[i][j] != null && fields[i][j].getColor() != color && fields[i][j].canAttack(i, j, x, y) && !isFigureOnWay(i, j, x, y))
                if (attackConditionIsCorrect(i, j, x, y))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isMate(char color)
    {
        int x, y;
        if (color == 'w')
        {
            x = whiteKingCoordinates[0];
            y = whiteKingCoordinates[1];
        }
        else
        {
            x = blackKingCoordinates[0];
            y = blackKingCoordinates[1];
        }

        if (isShah(color))
        {
            //может ли король уйти
            for (int i = Math.max(x - 1, 0); i <= Math.min(x + 1, 7); i++)
            {
                for (int j = Math.max(y - 1, 0); j <= Math.min(y + 1, 7); j++)
                {
                    /*if ((fields[i][j] == null && fields[x][y].canMove(x, y, i, j)) ||
                        (fields[i][j].getColor() != color && fields[x][y].canAttack(x, y, i, j)))*/
                    if (moveConditionIsCorrect(x, y, i, j) || attackConditionIsCorrect(x, y, i, j))
                    {
                        Figure figureIJ = fields[i][j];
                        move(x, y, i, j);
                        if (!isShah(color))
                        {
                            move(i, j, x, y);
                            fields[i][j] = figureIJ;
                            return false;
                        }
                        move(i, j, x, y);
                        fields[i][j] = figureIJ;
                    }
                }
            }
            //может ли кто-нибудь защитить короля
            for (int i = 0; i < 8; ++i)
            {
                for (int j = 0; j < 8; ++j)
                {
                    if (fields[i][j] != null && fields[i][j].getColor() == color)
                    {
                        for (int k = 0; k < 8; ++k)
                        {
                            for (int l = 0; l < 8; ++l)
                            {
                                if (moveConditionIsCorrect(i, j, k, l) || attackConditionIsCorrect(i, j, k, l))
                                {
                                    Figure figureKL = fields[k][l];
                                    move(i, j, k, l);
                                    if (!isShah(color))
                                    {
                                        move(k, l, i, j);
                                        fields[k][l] = figureKL;
                                        return false;
                                    }
                                    move(k, l, i, j);
                                    fields[k][l] = figureKL;
                                }
                            }
                        }
                    }
                }
            }
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
