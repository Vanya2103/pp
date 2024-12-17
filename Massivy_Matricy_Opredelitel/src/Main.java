import java.util.Scanner;

public class Main {
    public static double calculateDeterminant(double[][] matrix, int n) {
        double det = 1.0;
        for (int i = 0; i < n; i++) {
            int maxRow = i;
            if (matrix[i][i] == 0) {
                return 0;
            }
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(matrix[k][i]) > Math.abs(matrix[maxRow][i])) {
                    maxRow = k;
                }
            }
            if (i != maxRow) {
                det *= -1;
            }
            double[] temp = matrix[i];
            matrix[i] = matrix[maxRow];
            matrix[maxRow] = temp;
            for (int k = i + 1; k < n; k++) {
                double factor = matrix[k][i] / matrix[i][i];
                for (int j = i; j < n; j++) {
                    matrix[k][j] -= factor * matrix[i][j];
                }
            }
            det *= matrix[i][i];
        }
        return det;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Size:");
        int n = in.nextInt();
        double[][] matrix = new double[n][n];
        System.out.println("Enter elements:");
        for(int i =0;i<n;i++){
            for(int j =0;j<n;j++){
                matrix[i][j]=in.nextDouble();
            }
        }
        double determinant = calculateDeterminant(matrix,n);
        System.out.println("Determinant: " + determinant);
    }
}