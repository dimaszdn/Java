import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[][] mas = {{1}, {67, 0, 90, 2, 1, 75}, {12, 6}};
        System.out.println(Arrays.toString(task_7(mas)));
    }

    static String task_1(String str)
    {
        String sub = "", maxSub = "";
        for (int i = 0; i < str.length(); ++i)
        {
            int indexInSub = sub.indexOf(str.charAt(i));
            if (indexInSub == -1)
                sub += str.charAt(i);
            else
            {
                if (sub.length() > maxSub.length())
                    maxSub = sub;
                sub = sub.substring(indexInSub + 1) + str.charAt(i);
            }
        }
        if (sub.length() > maxSub.length())
            maxSub = sub;
        return maxSub;
    }

    static int[] task_2(int[] mas1, int[] mas2)
    {
        int[] res = new int[mas1.length + mas2.length];
        int i = 0, j = 0;
        while (i < mas1.length && j < mas2.length)
        {
            if (mas1[i] < mas2[j])
            {
                res[i + j] = mas1[i];
                i++;
            }
            else
            {
                res[i + j] = mas2[j];
                j++;
            }
        }
        if (i < mas1.length)
        {
            while (i < mas1.length)
            {
                res[i + j] = mas1[i];
                i++;
            }
        }
        if (j < mas2.length)
        {
            while (j < mas2.length)
            {
                res[i + j] = mas2[j];
                j++;
            }
        }
        return res;
    }

    static int task_3(int[] mas)
    {
        Arrays.sort(mas);
        System.out.println(Arrays.toString(mas));
        int maxSum = 0;
        int sum = mas[0];
        for (int i = 1; i < mas.length; ++i)
        {
            // начало последовательности последовательных элементов определяет отсутствие соседа слева
            if (mas[i] - 1 == mas[i - 1])
                sum += mas[i];
            else
            {
                maxSum = Integer.max(maxSum, sum);
                sum = mas[i];
            }
        }
        maxSum = Integer.max(maxSum, sum);
        return maxSum;
    }

    static int[][] task_4(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] rotated = new int[cols][rows];
        for (int r = 0; r < rows; ++r)
            for (int c = 0; c < cols; ++c)
                rotated[c][rows - 1 - r] = matrix[r][c];
        return rotated;
    }

    static int[] task_5(int[] mas, int target)
    {
        for (int i = 0; i < mas.length - 1; ++i)
        {
            for (int j = i + 1; j < mas.length; ++j)
            {
                if (mas[i] + mas[j] == target)
                    return new int[]{mas[i], mas[j]};
            }
        }
        return null;
    }

    static int task_6(int[][] matrix)
    {
        int sum = 0;
        for (int r = 0; r < matrix.length; ++r)
            for (int c = 0; c < matrix[r].length; ++c)
                sum += matrix[r][c];
        return sum;
    }

    static int[] task_7(int[][] matrix)
    {
        int[] result = new int[matrix.length];
        for (int r = 0; r < matrix.length; ++r)
        {
            int max = Integer.MIN_VALUE;
            for (int c = 0; c < matrix[r].length; ++c)
            {
                max = Integer.max(max, matrix[r][c]);
            }
            result[r] = max;
        }
        return result;
    }

    static int[][] task_8(int[][] matrix)
    {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] rotated = new int[cols][rows];
        for (int r = 0; r < rows; ++r)
            for (int c = 0; c < cols; ++c)
                rotated[cols - 1 - c][r] = matrix[r][c];
        return rotated;
    }
}