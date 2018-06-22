import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class SVM {
    double[] omega;
    double b;

    public int[] vectorize(BufferedImage bi) {
        int n = (bi.getHeight() / 16) * (bi.getWidth() / 16);
        int[] arr = new int[n];
        int k = 0;
        for (int i = 0; i < bi.getHeight(); i += 16) {
            for (int j = 0; j < bi.getWidth(); j += 16) {
                arr[k] = bi.getRGB(j, i);
                k++;
            }
        }
        return arr;
    }


    public void optimize(double[][] vector, int[] classVector, double h, double eps) {
        boolean work = true;
        int n = vector.length;
        int l = vector[0].length;
        double[] lamda = new double[n];
        for (int i = 0; i < n; i++) {
            lamda[i] = 1.;
        }
        double[][] func = new double[n][l];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < l; j++) {
                func[i][j] = scalar(vector[i], vector[j]) * classVector[i] * classVector[j];
            }
        }
        double[] gradient = new double[n];
        do {
            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = i; j < l; j++) {
                        if (k == i && i == j) {
                            gradient[k] = 1 - lamda[i] * 2 * func[i][j];
                            continue;
                        }
                        if (k == i) {
                            gradient[k] += lamda[j] * func[i][j];
                            continue;
                        }
                        if (k == j) {
                            gradient[k] += lamda[i] * func[i][j];
                            continue;
                        }
                    }
                }
            }
            gradient = productToNumber(gradient, h);
            double[] newlamda = differenceVector(lamda, gradient);
            double[] diff = differenceVector(newlamda, lamda);
            if (norm(diff) < eps) {
                work = false;
                lamda = newlamda;
                continue;
            }
            lamda = newlamda;
        } while (work);
        double[] sum = productToNumber(vector[0], lamda[0] * classVector[0]);
        for (int i = 1; i < n; i++) {
            double[] k = productToNumber(vector[0], lamda[0] * classVector[0]);
            sum = summVector(sum, k);
        }
        omega = sum;
    }

    int classificate(double[] vector) {
        if (scalar(omega, vector) > 0) {
            return 1;
        }
        return 0;
    }

    private double scalar(double[] x, double[] y) {
        double res = 0;
        for (int i = 0; i < x.length; i++) {
            res += x[i] * y[i];
        }
        return res;
    }

    private double[] productToNumber(double[] vector, double number) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] *= number;
        }
        return vector;
    }

    private double[] differenceVector(double[] x, double[] y) {
        double[] diffVector = new double[x.length];
        for (int i = 0; i < diffVector.length; i++) {
            diffVector[i] = x[i] - y[i];
        }
        return diffVector;
    }

    private double norm(double[] vector) {
        double sum = 0;
        for (int i = 0; i < vector.length; i++) {
            sum += vector[i] * vector[i];
        }
        return Math.sqrt(sum);
    }


    private double[] summVector(double[] x, double[] y) {
        double[] sum = new double[x.length];
        for (int i = 0; i < sum.length; i++) {
            sum[i] = x[i] + y[i];
        }
        return sum;
    }

    private boolean saveSetToFile(double[][] set, int[] classVector, File file) throws IOException {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
            String lineSeparator = System.lineSeparator();
            for (int i = 0; i < set.length; i++) {
                for (int j = 0; j < set[0].length; j++) {
                    if (j == set[0].length - 1) {
                        bw.write(set[i][j] + ":");
                    }
                    bw.write(set[i][j] + ",");
                }
                bw.write(classVector[i] + ";" + lineSeparator);
            }
            bw.flush();
            bw.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
