import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        selectATask();
    }

    static void selectATask()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер задачи: ");
        int num = in.nextInt();

        switch (num)
        {
            case 1:
                syracuseSequence();
                break;
            case 2:
                sumOfAlternatingSeries();
                break;
            case 3:
                searchTreasure();
                break;
            case 4:
                logisticMaximin();
                break;
            case 5:
                isTwiceEven();
                break;
        }
        in.close();
    }

    static void syracuseSequence()
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int countSteps = 0;
        while (n != 1)
        {
            countSteps++;
            if (n % 2 == 0)
                n = n / 2;
            else
                n = 3 * n + 1;
        }

        System.out.println("OUTPUT: " + countSteps);
        in.close();
    }

    static void sumOfAlternatingSeries()
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int sum = 0, sign = 1;
        for (int i = 0; i < n; ++i)
        {
            sum += sign * in.nextInt();
            sign *= (-1);
        }

        System.out.println("OUTPUT: " + sum);
        in.close();
    }

    static void searchTreasure()
    {
        Scanner in = new Scanner(System.in);
        int treasureX = in.nextInt(), treasureY = in.nextInt();
        int startX = 0, startY = 0, countInstructions = 0, min = Integer.MAX_VALUE;
        while (true)
        {
            if (startX == treasureX && startY == treasureY)
                min = Integer.min(countInstructions, min);

            String direction = in.next();
            if (Objects.equals(direction, "стоп"))
                break;
            int countSteps = in.nextInt();

            if (Objects.equals(direction, "север"))
                startY += countSteps;
            else if (Objects.equals(direction, "юг"))
                startY -= countSteps;
            else if (Objects.equals(direction, "запад"))
                startX -= countSteps;
            else if (Objects.equals(direction, "восток"))
                startX += countSteps;
            countInstructions++;
        }

        System.out.println("OUTPUT: " + min);
        in.close();
    }

    static void logisticMaximin()
    {
        Scanner in = new Scanner(System.in);
        int countRoads = in.nextInt();
        int max = Integer.MIN_VALUE, roadNum = 0;
        for (int i = 0; i < countRoads; ++i)
        {
            int minHeight = Integer.MAX_VALUE;
            int countTunnels = in.nextInt();
            for (int j = 0; j < countTunnels; ++j)
            {
                int height = in.nextInt();
                if (minHeight > height)
                    minHeight = height;
            }
            if (minHeight > max)
            {
                max = minHeight;
                roadNum = i + 1;
            }
        }
        System.out.println("OUTPUT: " + roadNum + " " + max);
        in.close();
    }

    static void isTwiceEven()
    {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        int sumDigits = 0, productDigits = 1;
        while (num > 0)
        {
            int digit = num % 10;
            sumDigits += digit;
            productDigits *= digit;
            num /= 10;
        }

        if (sumDigits % 2 == 0 && productDigits % 2 == 0)
            System.out.println("Является дважды четным");
        else
            System.out.println("Не является дважды четным");
        in.close();
    }
}