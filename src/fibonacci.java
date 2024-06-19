public class fibonacci {

    public static void main(String[] args) {
        int n = 10;
        System.out.println("fibonacci for " + n + ":");
        for (int i = 0; i <= n; i++) {
            System.out.print(fib(i) + " ");
        }
    }

    public static int fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fib(n-1) + fib(n - 2); // Recursive case
        }
    }
}
