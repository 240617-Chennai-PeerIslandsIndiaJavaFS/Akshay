import java.util.Scanner;

public class NoDuplicationMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = scanner.nextInt();


        int[][] matrix = new int[rows][cols];


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean alreadyPresent;
                int value;


                do {
                    alreadyPresent = false;
                    System.out.print("Enter value for row " + (i + 1) + ", column " + (j + 1) + ": ");
                    value = scanner.nextInt();


                    if (repeatingValue(matrix, value, i, j)) {
                        alreadyPresent = true;
                        System.out.println("Value already exists in the matrix. Enter another value.");
                    }

                } while (alreadyPresent);


                matrix[i][j] = value;
            }
        }


        System.out.println("Matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }


    private static boolean repeatingValue(int[][] matrix, int value, int currentRow, int currentCol) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == value && (i != currentRow || j != currentCol)) {
                    return true;
                }
            }
        }
        return false;
    }
}
