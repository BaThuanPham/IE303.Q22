public class Bai1d {
    public static boolean isPerfect(int number) {
        if (number <= 1) {
            return false;
        }

        int sumOfDivisors = 0;

        for (int i = 1; i < number; i++) {
            if (number % i == 0) {
                sumOfDivisors += i;
            }
        }
        return sumOfDivisors == number;
    }
}
