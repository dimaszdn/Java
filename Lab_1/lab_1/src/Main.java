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
                break;
            case 4:
                logisticMaximin();
                break;
            case 5:
                isTwiceEven();
                break;
        }
    }

    static void syracuseSequence()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите число: ");
        int n = in.nextInt();
        in.close();

        int countSteps = 0;
        while (n != 1)
        {
            countSteps++;
            if (n % 2 == 0)
                n = n / 2;
            else
                n = 3 * n + 1;
        }

        System.out.println("Требуемое количество шагов: " + countSteps);
    }

    static void sumOfAlternatingSeries()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите количество чисел: ");
        int n = in.nextInt();

        System.out.println("Введите " + n + " чисел: ");

        int sum = 0, sign = 1;
        for (int i = 0; i < n; ++i)
        {
            sum += sign * in.nextInt();
            sign *= (-1);
        }

        System.out.println("Сумма знакочередующегося ряда: " + sum);
        in.close();
    }

    static void searchTreasure()
    {

    }

    static void logisticMaximin()
    {

    }

    static void isTwiceEven()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите целое положительное *** число: ");
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
    }
}