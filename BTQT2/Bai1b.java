public class Bai1b {
    public static boolean hasEvenDigit(int inputNumber) {
        int number = Math.abs(inputNumber);

        while (number > 0) {
            int digit = number % 10;
            if (digit % 2 == 0) {
                return true;
            }
            number /= 10;
        }

        return false;
    }
}
