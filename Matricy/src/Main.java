import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class MatrixOperations {
    private double[][] matrix;  // Объект матрицы
    private int rows;
    private int cols;

    // Конструктор для загрузки матрицы из файла
    public MatrixOperations(String fileName) throws Exception {
        loadMatrix(fileName);
    }

    // Метод для чтения матрицы из файла
    private void loadMatrix(String fileName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String[] dimensions = reader.readLine().split(" ");
        this.rows = Integer.parseInt(dimensions[0]);
        this.cols = Integer.parseInt(dimensions[1]);

        this.matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] line = reader.readLine().split(" ");
            for (int j = 0; j < cols; j++) {
                this.matrix[i][j] = Double.parseDouble(line[j]);
            }
        }
        reader.close();
    }

    // Метод для получения матрицы
    public double[][] getMatrix() {
        return matrix;
    }

    // Метод для получения количества строк
    public int getRows() {
        return rows;
    }

    // Метод для получения количества столбцов
    public int getCols() {
        return cols;
    }

    // Функция для вывода матрицы
    private void printMatrix(double[][] matrix) {
        System.out.println("Результат операции:");
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    // Функция для вывода результата
    private void printResult(String message, double result) {
        System.out.println(message + result);
    }

    // Перегрузка метода для вывода целочисленного результата
    private void printResult(String message, int result) {
        System.out.println(message + result);
    }

    // Метод для вычисления определителя
// Метод для вычисления определителя матрицы
    public double determinant() {
        if (rows != cols) {
            System.out.println("Определитель можно вычислить только для квадратных матриц.");
        }

        double[][] temp = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(matrix[i], 0, temp[i], 0, cols); // Копируем данные
        }

        double det = 1.0; // Начальное значение определителя
        for (int i = 0; i < rows; i++) {
            // Поиск строки с максимальным элементом
            int maxRow = i;
            for (int k = i + 1; k < rows; k++) {
                if (Math.abs(temp[k][i]) > Math.abs(temp[maxRow][i])) {
                    maxRow = k;
                }
            }

            // Если максимальная строка не совпадает с текущей, меняем их местами
            if (i != maxRow) {
                det *= -1; // Изменение знака определителя
                double[] tempRow = temp[i];
                temp[i] = temp[maxRow];
                temp[maxRow] = tempRow;
            }

            // Проверка на нулевой элемент
            if (temp[i][i] == 0) {
                return 0; // Определитель равен 0
            }

            // Приведение к верхнетреугольному виду
            for (int k = i + 1; k < rows; k++) {
                double factor = temp[k][i] / temp[i][i];
                for (int j = i; j < cols; j++) {
                    temp[k][j] -= factor * temp[i][j];
                }
            }

            det *= temp[i][i];
        }

        return det;
    }

    // Задача 4: Вычисление определителя
    public void task4() {
        try {
            double res = determinant();
            printResult("Определитель: ", res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Задача 1: Нахождение максимума в отсортированных строках
    public void task1() {
        int max = findMaxInSortedRows();
        printResult("MAX: ", max);
    }

    // Вспомогательные методы для задачи 1
    private boolean isSortedDesc(double[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] < arr[i + 1])
                return false;
        }
        return true;
    }

    private boolean isSortedAsc(double[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1])
                return false;
        }
        return true;
    }

    private int findMaxInSortedRows() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < rows; i++) {
            if (isSortedDesc(matrix[i]) || isSortedAsc(matrix[i])) {
                for (int j = 0; j < cols; j++) {
                    if (matrix[i][j] > max)
                        max = (int) matrix[i][j];
                }
            }
        }
        return max;
    }

    // Задача 2: Подсчет строк с элементами от -10 до -1
    public void task2() {
        int count = countValidRows();
        printResult("Количество строк, являющихся перестановкой чисел от -1 до -10: ", count);
    }

    // Метод для подсчета строк, удовлетворяющих условию (используется в задаче 2)
    private int countValidRows() {
        int count = 0;
        for (int i = 0; i < rows; i++) {
            if (checkRow(matrix[i])) {
                count++;
            }
        }
        return count;
    }

    private boolean checkRow(double[] row) {
        if (row.length < 1 || row.length > 10) {
            return false;
        }

        Set<Double> set = new HashSet<>();
        for (double value : row) {
            if (value < -10 || value > -1) {
                return false; // значения вне диапазона
            }
            set.add(value); // добавляем число в множество
        }

        if (set.size() != 10) {
            return false; // все числа должны быть уникальными
        }

//        for (int i = -10; i <= -1; i++) {
//            if (!set.contains((double) i)) {
//                // Если длина строки меньше 10, то пропускаем проверку наличия всех чисел
//                if (row.length == 10) {
//                    return false; // если строка длиной 10, а числа отсутствуют, возвращаем false
//                }
//            }
//        }

        return true;
    }


    // Задача 3: Операции с двумя матрицами
    public void task3(MatrixOperations secondMatrixOperations) {
        double[][] secondMatrix = secondMatrixOperations.getMatrix();
        int n = rows;

        if (n != secondMatrixOperations.getRows() || n != secondMatrixOperations.getCols()) {
            System.out.println("Матрицы должны быть квадратными и одинакового размера.");
            return;
        }

        // Инвертируем первую матрицу (транспонирование)
        double[][] transposedMatrix = reverse(matrix, n);

        // Вычисляем произведение элементов второй матрицы по строкам
        double[] res = calculateProduct(secondMatrix, n);

        // Добавляем результат произведения к элементам первой матрицы
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                transposedMatrix[i][j] += res[i];
            }
        }

        // Транспонируем обратно первую матрицу
        transposedMatrix = reverse(transposedMatrix, n);

        // Вывод результата
        printMatrix(transposedMatrix);
    }

    // Метод для транспонирования матрицы (используется в задаче 3)
    private double[][] reverse(double[][] arr, int n) {
        double[][] transposed = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                transposed[j][i] = arr[i][j];
            }
        }
        return transposed;
    }

    // Метод для вычисления произведения строк матрицы (используется в задаче 3)
    private double[] calculateProduct(double[][] arr, int n) {
        double[] res = new double[n];
        for (int i = 0; i < n; i++) {
            res[i] = 1;
            for (int j = 0; j < n; j++) {
                res[i] *= arr[i][j];
            }
        }
        return res;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите номер задачи (1, 2, 3 или 4):");
        int taskNumber = in.nextInt();

        MatrixOperations matrixOperations = null;

        switch (taskNumber) {
            case 1:
                matrixOperations = new MatrixOperations("file1.txt");
                matrixOperations.task1();
                break;
            case 2:
                matrixOperations = new MatrixOperations("file2.txt");
                matrixOperations.task2();
                break;
            case 3:
                matrixOperations = new MatrixOperations("file3.txt");  // Первая матрица

                System.out.println("Введите имя файла для второй матрицы:");
                String secondFileName = in.next();
                MatrixOperations secondMatrixOperations = new MatrixOperations(secondFileName); // Вторая матрица
                matrixOperations.task3(secondMatrixOperations);
                break;
            case 4:
                matrixOperations = new MatrixOperations("file4.txt");
                matrixOperations.task4();
                break;
            default:
                System.out.println("Неверный номер задачи.");
        }
    }
}
