import java.util.Scanner;

public class Bai5 {

    static Scanner sc = new Scanner(System.in);

    // Input matrix m x n
    static double[][] inputMatrix(int m, int n) {
        double[][] M = new double[m][n];
        System.out.println("Nhap ma tran " + m + "x" + n + ":");
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                System.out.print("M[" + i + "][" + j + "] = ");
                M[i][j] = sc.nextDouble();
            }
        return M;
    }

    // Print matrix
    static void printMatrix(double[][] M) {
        for (double[] row : M) {
            for (double val : row)
                System.out.printf("%8.2f", val);
            System.out.println();
        }
    }

    // a. Find smallest positive even element
    static void findSmallestPositiveEven(double[][] M) {
        double min = Double.MAX_VALUE;
        boolean found = false;
        for (double[] row : M)
            for (double val : row)
                if (val > 0 && val % 2 == 0 && val < min) {
                    min = val;
                    found = true;
                }
        if (found)
            System.out.println("a. Phan tu chan duong nho nhat: " + min);
        else
            System.out.println("a. Khong ton tai phan tu chan duong.");
    }

    // b. Average of minimum elements in each column
    static void avgOfColMinimums(double[][] M) {
        int m = M.length, n = M[0].length;
        double sum = 0;
        System.out.print("b. Phan tu nho nhat tren moi cot: ");
        for (int j = 0; j < n; j++) {
            double min = M[0][j];
            for (int i = 1; i < m; i++)
                if (M[i][j] < min) min = M[i][j];
            System.out.printf("%.2f ", min);
            sum += min;
        }
        System.out.printf("%n   Gia tri trung binh: %.2f%n", sum / n);
    }

    // c. Delete row with largest sum
    static double[][] deleteMaxSumRow(double[][] M) {
        int m = M.length, n = M[0].length;
        int maxRow = 0;
        double maxSum = 0;
        for (int i = 0; i < m; i++) {
            double sum = 0;
            for (double val : M[i]) sum += val;
            if (sum > maxSum) { maxSum = sum; maxRow = i; }
        }
        System.out.println("c. Xoa dong " + maxRow + " (tong = " + maxSum + ")");
        double[][] result = new double[m - 1][n];
        int ri = 0;
        for (int i = 0; i < m; i++)
            if (i != maxRow) result[ri++] = M[i];
        return result;
    }

    // d. Check square, upper/lower triangular
    static void checkTriangular(double[][] M) {
        int m = M.length, n = M[0].length;
        if (m != n) {
            System.out.println("d. M khong phai ma tran vuong.");
            return;
        }
        System.out.println("d. M la ma tran vuong.");
        boolean upper = true, lower = true;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (i > j && M[i][j] != 0) upper = false;
                if (i < j && M[i][j] != 0) lower = false;
            }
        if (upper) System.out.println("   M la ma tran tam giac tren.");
        else if (lower) System.out.println("   M la ma tran tam giac duoi.");
        else System.out.println("   M khong phai ma tran tam giac.");
    }

    // e. Create N (n x m), multiply M x N
    static double[][] multiplyMN(double[][] M) {
        int m = M.length, n = M[0].length;
        System.out.println("e. Tao ma tran N (" + n + "x" + m + "):");
        double[][] N = inputMatrix(n, m);
        double[][] result = new double[m][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < m; j++)
                for (int k = 0; k < n; k++)
                    result[i][j] += M[i][k] * N[k][j];
        System.out.println("   Tich M x N:");
        printMatrix(result);
        return result;
    }

    // f. Transpose of the product matrix
    static void transpose(double[][] P) {
        int m = P.length, n = P[0].length;
        double[][] T = new double[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                T[j][i] = P[i][j];
        System.out.println("f. Chuyen vi ma tran tich:");
        printMatrix(T);
    }

    public static void main(String[] args) {
        System.out.print("Nhap so hang m: ");
        int m = sc.nextInt();
        System.out.print("Nhap so cot n: ");
        int n = sc.nextInt();

        double[][] M = inputMatrix(m, n);
        System.out.println("Ma tran M:");
        printMatrix(M);

        findSmallestPositiveEven(M);
        avgOfColMinimums(M);
        double[][] afterDelete = deleteMaxSumRow(M);
        System.out.println("   Ma tran sau khi xoa:");
        printMatrix(afterDelete);
        checkTriangular(M);
        double[][] product = multiplyMN(M);
        transpose(product);
    }
}