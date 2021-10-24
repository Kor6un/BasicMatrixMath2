// Задание
// Дана матрица A порядка n×n. Развернуть матрицу в одномерную последовательность по часовой стрелке, начиная с верхнего левого угла.
//
// Формат файла: 1-я строка - размер матрицы (например, для матрицы 3*3 следует указать 3).
//               2-я и псоледующие строки - числа, разделенные пробелом
//
// Пример файла (матрица 3*3):
// 3
// 1 2 3
// 4 5 6
// 7 8 9
//



import java.util.Scanner;
import java.io.*;
public class BasicMatrixMath {

    static Scanner sc = new Scanner(System.in);

    public static int checkConsOrFile() {
        final int MAX = 2;
        final int MIN = 1;
        int choice = 0;
        boolean isIncorrect;
        do {
            System.out.print("Введите 1, если вводить/выводить в консоли, и 2, если в файле: ");
            isIncorrect = false;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Ошибка ввода. Повторите ввод.");
                isIncorrect = true;
            }
            if ((!isIncorrect) && (choice > MAX || choice < MIN)) {
                System.out.println("Число выходит за допустимые пределы. Повторите ввод.");
                isIncorrect = true;
            }
        } while (isIncorrect);
        return choice;
    }

    public static int inputMatrixSizeCons() {
        final int MAX_LIMIT = 10;
        final int MIN_LIMIT = 1;
        boolean isIncorrect;
        int size = 0;
        do {
            isIncorrect = false;
            System.out.format("Введите размерность матрицы NxN (%d <= size <= %d%s",MIN_LIMIT, MAX_LIMIT, "): ");
            try {
                size = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода. Повторите ввод.");
                isIncorrect = true;
            }
            if ((!isIncorrect) && (size > MAX_LIMIT || size < MIN_LIMIT)) {
                System.out.println("Число выходит за допустимые пределы.Повторите ввод.");
                isIncorrect = true;
            }
        } while (isIncorrect);
        return size;
    }

    public static int[][] inputFromCons(int[][] matrix, final int size) {
        final int MAX_ELEM = 100;
        final int MIN_ELEM = 0;
        boolean isIncorrect;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                do {
                    System.out.format("Введите элемент %d-ой строки %d-ого столбца(%d <= elem <= %d): ", i + 1, j + 1, MIN_ELEM, MAX_ELEM);
                    isIncorrect = false;
                    try {
                        matrix[i][j] = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        isIncorrect = true;
                        System.out.println("Ошибка ввода. Повторите ввод.");
                    }
                    if ((!isIncorrect) && (matrix[i][j] > MAX_ELEM ||
                            matrix[i][j] < MIN_ELEM)) {
                        isIncorrect = true;
                        System.out.format("Элемент не входит в диапазон %d <= elem <= %d\n", MIN_ELEM, MAX_ELEM);
                    }
                } while (isIncorrect);
            }
        }
        System.out.println();
        return matrix;
    }

    public static void outputToCons(int[][] matrix, final int size) {
        System.out.println("Начальная матрица\n");
        for (int i = 0; i < size; i++) {
            System.out.print('\t');
            for (int j = 0; j < size; j++) {
                System.out.format("%d\t",matrix[i][j]);
            }
            System.out.println("  ");
        }
        int i = 0;
        int j = 0;
        int k = size;
        System.out.println("\nРезультат\n");
        System.out.print('\t');
        do
        {
            for (; j < k; j++) {
                System.out.format("%d  ", matrix[i][j]);
            }
            j--;
            i++;
            for (; i < k; i++) {
                System.out.format("%d  ", matrix[i][j]);
            }
            i--;
            j--;
            for (; j >= size - k; j--) {
                System.out.format("%d  ", matrix[i][j]);
            }
            j++;
            i--;
            k--;
            for (; i >= size - k; i--) {
                System.out.format("%d  ", matrix[i][j]);
            }
            i++;
            j++;
        } while (k > 0);
    }

    public static String inputPath() {
        boolean isIncorrect;
        String path;
        do {
            System.out.print("Введите ссылку на файл: ");
            isIncorrect = false;
            path = sc.nextLine();
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("Файл не найден! Введите правильную ссылку");
                isIncorrect = true;
            }
        } while (isIncorrect);
        return path;
    }

    public static int exceptSize() {
        final int MAX_LIMIT = 10;
        final int MIN_LIMIT = 1;
        boolean isIncorrect;
        int num = 0;
        Scanner sc = new Scanner(System.in);
        do {
            isIncorrect = false;
            System.out.print("Размер матрицы неверно записан, введите его через консоль: ");
            try {
                num = Integer.parseInt(sc.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Ошибка ввода. Повторите ввод.");
                isIncorrect = true;
            }
            if ((!isIncorrect) && (num > MAX_LIMIT || num < MIN_LIMIT)) {
                System.out.println("Число выходит за допустимые пределы. Повторите ввод.");
                isIncorrect = true;
            }
        } while(isIncorrect);
        return num;
    }

    public static int readSizeInFile(String path) throws
            FileNotFoundException {
        int temp;
        Scanner fileReader = new Scanner(new File(path));
        try {
            temp = fileReader.nextInt();
        } catch(Exception e) {
            temp = exceptSize();
        }
        return temp;
    }

    public static boolean checkMatrix(String path, int size) throws
            FileNotFoundException {
        int i = 0;
        String temp2;
        boolean checker;
        int temp;
        Scanner fileReader = new Scanner(new File(path));
        temp2 = fileReader.nextLine();
        while(fileReader.hasNext()) {
            try {
                temp = fileReader.nextInt();
                i++;
            } catch(NumberFormatException e) {
                System.out.println("Ошибка ввода матрицы. Введите её через консоль.");
                return false;
            }
        }
        if (i < size * size || i > size * size) {
            System.out.println("Неверная матрица в файле. Введите её через консоль.");
            checker = false;
        } else {
            checker = true;
        }
        return checker;
    }

    public static int[][] inputFromFile(final String path, int[][] matrix,
                                        final int strings) throws FileNotFoundException {
        String temp;
        Scanner fileReader = new Scanner(new File(path));
        temp = fileReader.nextLine();
        for (int i = 0; i < strings; i++) {
            for (int j = 0; j < strings; j++) {
                matrix[i][j] = fileReader.nextInt();
            }
        }
        fileReader.close();
        return matrix;
    }

    public static boolean checkFile(final String path, final int size,
                                    int[][] matrix) {
        try{
            outputToFile(path, size, matrix);
            return true;
        } catch (IOException e) {
            System.out.println("Ошибка! Отказано в доступе. Измените параметры файла!");
            return false;
        }
    }

    public static void outputToFile(final String path, final int size,
                                    int[][] matrix) throws IOException {
        FileWriter writer = new FileWriter(path);
        writer.write("Начальная матрица\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                writer.write(String.valueOf(matrix[i][j]));
                writer.write('\t');
            }
            writer.write('\n');
        }
        int i = 0;
        int j = 0;
        int k = size;
        writer.write("\nРезультат\n");
        do
        {
            for (; j < k; j++) {
                writer.write(String.valueOf(matrix[i][j]));
                writer.write("  ");
            }
            j--;
            i++;
            for (; i < k; i++) {
                writer.write(String.valueOf(matrix[i][j]));
                writer.write("  ");
            }
            i--;
            j--;
            for (; j >= size - k; j--) {
                writer.write(String.valueOf(matrix[i][j]));
                writer.write("  ");
            }
            j++;
            i--;
            k--;
            for (; i >= size - k; i--) {
                writer.write(String.valueOf(matrix[i][j]));
                writer.write("  ");
            }
            i++;
            j++;
        } while (k > 0);
        System.out.println("Данные успешно записаны в файл!\n");
        writer.close();

    }

    public static void outputProcess(int[][] matrix, int length){
        final int CONSOLE = 1;
        boolean check;
        if (checkConsOrFile() == CONSOLE) {
            outputToCons(matrix, length);
        } else {
            do {
                check =  checkFile(inputPath(), length, matrix);
            } while (!check);
        }
    }

    public static void checkMatrixInFile(int size, String path) throws
            FileNotFoundException {
        int[][] matrix = new int[size][size];
        if (checkMatrix(path, size)) {
            matrix = inputFromFile(path, matrix, size);
            outputProcess(matrix, size);
        } else {
            int length = inputMatrixSizeCons();
            int[][] matrixA = new int[length][length];
            matrixA = inputFromCons(matrixA, length);
            outputProcess(matrixA, length);
        }

    }
    public static void main(String[] args) throws IOException {
        System.out.println("Программа разворачивает матрицу в одномерную последовательность по часовой стрелке, начиная из верхнего левого угла.");
        final int CONSOLE = 1;
        if (checkConsOrFile() == CONSOLE) {
            int length = inputMatrixSizeCons() ;
            int[][] matrixA = new int[length][length];
            matrixA = inputFromCons(matrixA, length);
            outputProcess(matrixA, length);
        } else {
            String path = inputPath();
            int size = readSizeInFile(path);
            checkMatrixInFile(size, path);
        }
        sc.close();
    }
}
