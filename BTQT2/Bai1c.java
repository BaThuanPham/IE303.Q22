import java.math.BigInteger;

public class Bai1c {
    public static BigInteger productOfDivisors(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        }

        int num = Math.abs(n);
        BigInteger product = BigInteger.ONE;

        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                product = product.multiply(BigInteger.valueOf(i));

                int pairedDivisor = num / i;
                if (pairedDivisor != i) {
                    product = product.multiply(BigInteger.valueOf(pairedDivisor));
                }
            }
        }

        return product;
    }
}
