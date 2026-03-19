import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.*;

public class BTTH1 {

    // Bai 3 class
    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Bai 3 class
    static int orientation(Point p, Point q, Point r) {
        return (q.y - p.y) * (r.x - q.x) -
               (q.x - p.x) * (r.y - q.y);
    }

    // Bai 3 class
    static int distance(Point p1, Point p2) {
        return (p1.x - p2.x)*(p1.x - p2.x) +
            (p1.y - p2.y)*(p1.y - p2.y);
    }

    // Bai 3 class
    static void convexHull(Point[] points, int n) {
        if (n < 3) return;

        List<Point> hull = new ArrayList<>();

        int l = 0;
        for (int i = 1; i < n; i++) {
            if (points[i].x < points[l].x)
                l = i;
        }

        int p = l, q;
        do {
            hull.add(points[p]);
            q = (p + 1) % n;

            for (int i = 0; i < n; i++) {
            if (orientation(points[p], points[i], points[q]) > 0 ||
                (orientation(points[p], points[i], points[q]) == 0 &&
                distance(points[p], points[i]) > distance(points[p], points[q]))) {
                    q = i;
                }
            }

            p = q;

        } while (p != l);

        System.out.println("Cac diem ngoai (tram canh bao):");
        for (Point pt : hull) {
            System.out.println(pt.x + " " + pt.y);
        }
    }

    static List<Integer> best = new ArrayList<>();

    static void backtrack(int[] arr, int n, int k, int index, List<Integer> current, int sum) {
        if (sum > k) return;

        if (sum == k) {
            if (current.size() > best.size()) {
                best = new ArrayList<>(current);
            }
            return;
        }

        if (index == n) return;

        // choose element
        current.add(arr[index]);
        backtrack(arr, n, k, index + 1, current, sum + arr[index]);

        // not choose element
        current.remove(current.size() - 1);
        backtrack(arr, n, k, index + 1, current, sum);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Bai 1
        System.out.println("Bai 1:");
        System.out.println(" ");

        System.out.print("Nhap ban kinh r: ");
        double r = scanner.nextDouble();

        int n = 1000000; // so diem random
        int inside = 0;

        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            double x = (rand.nextDouble() * 2 * r) - r;
            double y = (rand.nextDouble() * 2 * r) - r;

            if (x * x + y * y <= r * r) {
                inside++;
            }
        }

        double area = (double) inside / n * (2 * r) * (2 * r);

        System.out.println("Dien tich xap xi: " + area);

        // Bai 2
        System.out.println("---");
        System.out.println("Bai 2:");
        System.out.println(" ");

        inside = 0;

        for (int i = 0; i < n; i++) {
            double x = rand.nextDouble() * 2 - 1;
            double y = rand.nextDouble() * 2 - 1;

            if (x * x + y * y <= 1) {
                inside++;
            }
        }

        double pi = 4.0 * inside / n;
        System.out.println("Pi xap xi: " + pi);
        
        // Bai 3
        System.out.println("\n---\nBai 3:\n");

        System.out.print("Nhap so diem: ");
        int m = scanner.nextInt();

        Point[] points = new Point[m];

        for (int i = 0; i < m; i++) {
            System.out.print("Nhap x y: ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points[i] = new Point(x, y);
        }

        convexHull(points, m);

        // ===== Bai 4 =====
        System.out.println("\n---\nBai 4:\n");

        System.out.print("Nhap n va k: ");
        int n4 = scanner.nextInt();
        int k = scanner.nextInt();

        int[] arr = new int[n4];

        System.out.println("Nhap day so:");
        for (int i = 0; i < n4; i++) {
            arr[i] = scanner.nextInt();
        }

        best.clear();
        backtrack(arr, n4, k, 0, new ArrayList<>(), 0);

        if (best.isEmpty()) {
            System.out.println("Khong co day con thoa man");
        } else {
            for (int x : best) {
                System.out.print(x + " ");
            }
            System.out.println();
        }

        scanner.close();
    
    }
}