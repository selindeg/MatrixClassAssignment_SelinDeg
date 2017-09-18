import java.util.InputMismatchException;
import java.util.Scanner;

public class TestMatrix {


    public static void main(String[] args) {

        int choice = 1;
        int row, column = 0;
        Matrix matrix = null;
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        System.out.println("Input an integer row and column number for mxn matrix size:" + "\n");


        try {
            System.out.println("Input m:" + "\n");
            row = input.nextInt();
            System.out.println("Input n:" + "\n");
            column = input.nextInt();
            if (row == 0 || column == 0) {
                System.out.println("Array size can't be 0");
                choice = 0;

            }
            if (choice != 0) {
                matrix = new Matrix(row, column);
                matrix.random(matrix);

                System.out.println("Matrix  A  generated randomly:" + "\n");
                matrix.matrixFormat(matrix);
            }


        } catch (InputMismatchException | NegativeArraySizeException ex) {
            System.out.println("Invalid format for for mxn matrix size");
            choice = 0;

        }


        while (choice != 0) {
            System.out.println("Input your choice for mxn matrix:");
            System.out.println("1- Scalar multiplication");
            System.out.println("2- Trace");
            System.out.println("3- Tranpose");
            System.out.println("4- Matrix Multiplication");
            System.out.println("5- Determinant");
            System.out.println("6- Matrix Inversion");
            System.out.println("7- All Functions");
            System.out.println("Enter 0 to exit" + "\n");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n" + "Scalar Multiplication" + "\n");
                    System.out.println("Enter the number to multiply:" + "\n");
                    try {
                        double scalar = input2.nextDouble();
                        Matrix scalarMatrix = matrix.scalarMul(matrix, scalar);
                        scalarMatrix.matrixFormat(scalarMatrix);
                    } catch (InputMismatchException ex) {
                        System.out.println("Invalid input");
                    }
                    break;

                case 2:
                    System.out.println("\n" + "Trace" + "\n");
                    if (matrix.getRow() != matrix.getColumn()) {
                        System.out.println("This function only valid for nxn square matrices" + "\n");
                    } else {

                        Double trace = matrix.traceTotal(matrix);
                        System.out.println(trace);

                    }
                    break;

                case 3:
                    System.out.println("\n" + "Tranpose" + "\n");
                    Matrix tranposeMatrix = matrix.transpose(matrix);
                    tranposeMatrix.matrixFormat(tranposeMatrix);
                    break;
                case 4:
                    System.out.println("\n" + "Multiplication" + "\n");
                    //Second matrix's size generated according to multiplication rule:
                    // the number of columns in A must equal the number of rows in B
                    Matrix matrix2 = new Matrix(matrix.getColumn(), matrix.getRow());
                    System.out.println("Second Matrix B  generated randomly:" + "\n");
                    matrix2.random(matrix2);
                    matrix2.matrixFormat(matrix2);
                    Matrix multiplyMatrix = matrix.multiply(matrix, matrix2);
                    System.out.println("Multiplication Matrix:" + "\n");
                    multiplyMatrix.matrixFormat(multiplyMatrix);
                    break;
                case 5:
                    System.out.println("\n" + "Matrix Determinant" + "\n");
                    if (matrix.getRow() != matrix.getColumn()) {
                        System.out.println("This function only valid for nxn square matrices" + "\n");
                    } else {

                        System.out.println(matrix.determinant(matrix.getData()));
                    }
                    break;
                case 6:
                    System.out.println("\n" + "Matrix Inversion" + "\n");
                    if (matrix.getRow() != matrix.getColumn()) {
                        System.out.println("This function only valid for nxn square matrices" + "\n");
                    } else {

                        Matrix inverse = matrix.inverseMatrix(matrix.getData());
                        inverse.matrixFormat(inverse);
                    }
                    break;
                case 7:
                    System.out.println("\n" + "Scalar Multiplication" + "\n");
                    Matrix scalarMatrix2 = matrix.scalarMul(matrix, 4);
                    scalarMatrix2.matrixFormat(scalarMatrix2);
                    System.out.println("\n" + "Tranpose" + "\n");
                    Matrix tranposeMatrix2 = matrix.transpose(matrix);
                    tranposeMatrix2.matrixFormat(tranposeMatrix2);
                    System.out.println("\n" + "Multiplication" + "\n");
                    Matrix matrix3 = new Matrix(matrix.getColumn(), matrix.getRow());
                    System.out.println("Second Matrix B randomly generated:" + "\n");
                    matrix3.random(matrix3);
                    matrix3.matrixFormat(matrix3);
                    Matrix multiplyMatrix2 = matrix.multiply(matrix, matrix3);
                    System.out.println("Multiplication Matrix:" + "\n");
                    multiplyMatrix2.matrixFormat(multiplyMatrix2);

                    if (matrix.getRow() != matrix.getColumn()) {
                        System.out.println("Trace/Determinant/Inversion functions only valid for nxn square matrices" + "\n");
                    } else {
                        System.out.println("\n" + "Trace" + "\n");
                        Double trace2 = matrix.traceTotal(matrix);
                        System.out.println(trace2);
                        System.out.println("\n" + "Matrix Determinant" + "\n");
                        System.out.println(matrix.determinant(matrix.getData()));
                        System.out.println("\n" + "Matrix Inversion" + "\n");
                        matrix.matrixFormat(matrix.inverseMatrix(matrix.getData()));
                    }


                    break;
                case 0:
                    break;
                default:
                    System.out.println("Selection only valid for 1-7");
                    break;
            }
        }


    }
}
