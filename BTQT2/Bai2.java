import java.util.*;

public class Bai2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao 1 chuoi:");
        String input = sc.nextLine();

        System.out.println("\n--- Ket qua ---");
        processPartA(input);
        processPartB(input);
        processPartC(input);
    }

    // a.
    public static void processPartA(String s) {
        int letters = 0, spaces = 0, digits = 0, others = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isLetter(ch)) letters++;
            else if (Character.isDigit(ch)) digits++;
            else if (Character.isWhitespace(ch)) spaces++;
            else others++;
        }

        System.out.println("a. Chu cai: " + letters + ", Khoang trang: " + spaces + 
                           ",\nSo: " + digits + ", Cac ky tu khac: " + others);
    }

    // b.
    public static void processPartB(String s) {
        if (s.isEmpty()) return;

        Map<Character, Integer> counts = new LinkedHashMap<>();
        for (char ch : s.toCharArray()) {
            counts.put(ch, counts.getOrDefault(ch, 0) + 1);
        }

        List<Integer> frequencies = new ArrayList<>(new HashSet<>(counts.values()));
        Collections.sort(frequencies, Collections.reverseOrder());

        if (frequencies.size() < 2) {
            System.out.println("b. Khong tim thay ky tu co so lan xuat hien nhieu thu hai.");
            return;
        }

        int secondHighestFreq = frequencies.get(1);
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == secondHighestFreq) {
                System.out.println("b. Ky tu xuat hien nhieu thu hai: '" + entry.getKey()
                + "' (" + secondHighestFreq + " lan)");
                break;
            }
        }
    }

    // c.
    public static void processPartC(String s) {
        String[] numbers = s.split("[^0-9]+");
        long totalSum = 0;

        for (String numStr : numbers) {
            if (!numStr.isEmpty()) {
                totalSum += Long.parseLong(numStr);
            }
        }

        System.out.println("c. Tong cac so trong chuoi: " + totalSum);
    }
}