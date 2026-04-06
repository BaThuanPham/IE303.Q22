public class Bai1a {
    public static int findFirstDigit(int inputNumber) {
        int number = Math.abs(inputNumber);
        int digit = 0;

        while (number > 0) {
            digit = number % 10;
            number /= 10;
        }

        return digit;
    }
}
