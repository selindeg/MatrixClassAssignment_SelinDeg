import java.util.Random;

public class Matrix {

    private int row;
    private int column;
    private double[][] data;

    public Matrix(int m, int n) {
        this.row = m;
        this.column = n;
        data = new double[m][n];
    }


    public Matrix(int row, int column, double[][] data) {
        this.row = row;
        this.column = column;
        this.data = data;
    }


    //Getter and Setters
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public double getElement(Matrix A, int row, int column) {
        return A.data[row][column];
    }

    //Initialize Matrix randomly, element by element
    public Matrix random(Matrix A) {
        Random generator = new Random();
        for (int i = 0; i < A.getRow(); i++) {
            for (int j = 0; j < A.getColumn(); j++) {
                //range is optional
                A.data[i][j] = generator.nextInt(10);
            }
        }
        return A;
    }

    //Scalar Multiplication
    public Matrix scalarMul(Matrix B, double scalar) {

        Matrix scalarMatrix = new Matrix(B.getRow(), B.getColumn());

        for (int i = 0; i < B.row; i++)
            for (int j = 0; j < B.column; j++)
                //multiply each element with scalar
                scalarMatrix.data[i][j] = scalar * B.data[i][j];

        return scalarMatrix;
    }

    //Trace
    public double traceTotal(Matrix B) {

        double traceValue = 0;
        for (int i = 0; i < B.getRow(); i++)
            for (int j = 0; j < B.getColumn(); j++)
                //Diagonal value condition
                if (i == j) {
                    traceValue += B.data[i][j];

                }

        return traceValue;
    }

    //Matrix Multiplication
    public Matrix multiply(Matrix A, Matrix B) {

        double[][] a = A.getData();
        double[][] b = B.getData();
        int a_row = a.length;
        int a_column = a[0].length;
        int b_row = b.length;
        int b_column = b[0].length;
        //Multiply array size:First Matrix's row,Second Matrix's column
        double[][] c = new double[a_row][b_column];

        // A's row
        for (int i = 0; i < a_row; i++)
            //B's column
            for (int j = 0; j < b_column; j++)
                //To sum index
                for (int k = 0; k < a_column; k++)
                    // A's row * B's column
                    c[i][j] += a[i][k] * b[k][j];

        //Set Multiply Matrix
        Matrix C = new Matrix(A.getRow(), B.getColumn(), c);
        return C;
    }

    //Tranpose
    public Matrix transpose(Matrix A) {
        //Transpose array size:(B.column, B.row)
        Matrix B = new Matrix(A.column, A.row);
        for (int i = 0; i < A.row; i++)
            for (int j = 0; j < A.column; j++)
                //Position of array elements replace i,j->j,i
                B.data[j][i] = A.data[i][j];
        return B;
    }

    //To display matrix properly row by row
    public void matrixFormat(Matrix matrix) {

        for (int i = 0; i < matrix.getRow(); i++) {
            for (int j = 0; j < matrix.getColumn(); j++) {
                if (j == matrix.getColumn() - 1) {
                    System.out.println(" " + matrix.getElement(matrix, i, j) + "\n");
                } else
                    System.out.print(" " + matrix.getElement(matrix, i, j));
            }

        }

    }

    //Find determinant using Cofactor Method
    public double determinant(double[][] data) {

        if (data.length == 1) {
            //Array size 1,determinant equals to element
            return data[0][0];
        } else if (data.length == 2) {
            double det = 0;
            //Array size 2,{ { { a, b}, { c, d } }
            // determinant equals to a*d-b*c
            det = (data[0][0] * data[1][1]) - (data[0][1] * data[1][0]);
            return det;
        } else {
            double det = 0;
            for (int j = 0; j < data.length; j++) {
                //recursive:find determinant using Cofactor,
                //First row is selected,find minor matrix
                if (j % 2 == 0) {
                    //determinant formula
                    det += 1 * data[0][j] * determinant(minor(data, 0, j));
                } else {
                    det += -1 * data[0][j] * determinant(minor(data, 0, j));
                }
            }
            return det;
        }
    }

    //Method for minor matrix
    private double[][] minor(double[][] data, int i, int j) {
        //minor matrix size=matrixsize-1
        double[][] minorMatrix = new double[data.length - 1][data.length - 1];
        // index for minor matrix position:
        int rowMinor = 0, columnMinor = 0;
        for (int k = 0; k < data.length; k++) {
            double[] row = data[k];
            //  if it is not first row
            if (k != i) {
                for (int l = 0; l < row.length; l++) {
                    if (l != j) {
                        minorMatrix[rowMinor][columnMinor++] = row[l];
                    }
                }
                rowMinor++;
                columnMinor = 0;
            }
        }
        return minorMatrix;
    }

    public Matrix inverseMatrix(final double[][] a) {
        double diagonalValue, notDiagonalValue;
        double[][] tempMatrix = new double[a.length][a.length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                tempMatrix[i][j] = a[i][j];
            }
        }
        Matrix inverseMatrix = new Matrix(tempMatrix.length, tempMatrix.length);


        //Gauss Jordan Method :[ A | I ] ===> [ I | Inverse of A ]

        //creating unit matrix
        double[][] unit = new double[tempMatrix.length][tempMatrix.length];
        for (int i = 0; i < tempMatrix.length; i++) {
            for (int j = 0; j < tempMatrix.length; j++) {
                //if not diagonal it should be 0
                if (i != j) {
                    unit[i][j] = 0;

                } else {
                    //if diagonal it should be 1
                    unit[i][i] = 1;

                }

            }
        }

        //Turn A matrix into I matrix, make same operations to I matrix too.

        //divide each row to its diagonal
        for (int i = 0; i < tempMatrix.length; i++) {
            diagonalValue = tempMatrix[i][i];
            for (int j = 0; j < tempMatrix.length; j++) {
                //Diagonal should be 1  to convert  unit matrix
                unit[i][j] = unit[i][j] / diagonalValue;
                tempMatrix[i][j] = tempMatrix[i][j] / diagonalValue;

            }
            for (int z = 0; z < tempMatrix.length; z++) {
                //Not Diagonal position
                if (z != i) {
                    //element which is not diagonal
                    notDiagonalValue = tempMatrix[z][i];
                    for (int m = 0; m < tempMatrix.length; m++) {
                        //Not diagonal values should be 0 so substract,make same operations to unit matrix
                        unit[z][m] = unit[z][m] - (unit[i][m] * notDiagonalValue);
                        tempMatrix[z][m] = tempMatrix[z][m] - (tempMatrix[i][m] * notDiagonalValue);

                    }


                }

            }

        }
        //after operations I ->inverse of A
        inverseMatrix.setData(unit);

        return inverseMatrix;
    }

}

