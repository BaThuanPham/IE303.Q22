import java.util.Arrays;
import java.util.Scanner;

public class Bai3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap so phan tu cua mang: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Nhap cac phan tu:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int[] bestStart = null;
        int bestLen = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int len = j - i + 1;
                int[] sub = Arrays.copyOfRange(arr, i, j + 1);
                Arrays.sort(sub);

                boolean keTiep = true;
                for (int k = 1; k < sub.length; k++) {
                    if (sub[k] != sub[k - 1] + 1) {
                        keTiep = false;
                        break;
                    }
                }

                if (keTiep && len > bestLen) {
                    bestLen = len;
                    bestStart = sub;
                }
            }
        }

        if (bestStart != null) {
            System.out.println(Arrays.toString(bestStart));
        } else {
            System.out.println("Khong ton tai");
        }
    }
}