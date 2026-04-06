import java.util.Scanner;
import java.math.BigInteger;

public class Bai1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("===== Bai 1 =====\nNhap so: ");
        int inputNumber = scanner.nextInt();

        int firstDigit = Bai1a.findFirstDigit(inputNumber);
        System.out.println("-- 1a -- Chu so dau tien: " + firstDigit);

        boolean isEvenFound = Bai1b.hasEvenDigit(inputNumber);
        if (isEvenFound) {
            System.out.println("-- 1b -- Co chu so chan");
        } else {
            System.out.println("-- 1b -- Toan chu so le");
        }

        BigInteger product = Bai1c.productOfDivisors(inputNumber);
        System.out.println("-- 1c -- Tich cac uoc so: " + product);

        boolean isPerfect = Bai1d.isPerfect(inputNumber);
        if (isPerfect) {
            System.out.println("-- 1d -- " + inputNumber + " la so hoan thien");
        } else {
            System.out.println("-- 1d -- " + inputNumber + " khong phai la so hoan thien");
        }
        scanner.close();
    }
}
