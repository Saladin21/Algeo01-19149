import java.io.*;
import java.util.Scanner;

public class Interpolar{
    public static void inputUser(Matriks x, Matriks y, Float[] X) {
        // KAMUS LOKAL
        System.out.print("Masukkan derajat polinom: ");
        int n = Menu.baca.nextInt();
        Matriks.MakeEmpty(x, 1, n + 1);
        Matriks.MakeEmpty(y, 1, n + 1);

        // ALGORITMA
        for (int i = 0; i <= n; i++) {
            System.out.print("Masukkan nilai titik ke-"+i+": ");
            x.isi[0][i] = Menu.baca.nextFloat();
            y.isi[0][i] = Menu.baca.nextFloat();
        }
        System.out.print("Masukkan nilai X dari titik yang akan di taksir: ");
        X[0] = Menu.baca.nextFloat();
    }

    public static void inputFile(Matriks x, Matriks y, Float[] X, String file) throws FileNotFoundException {
        // KAMUS LOKAL
        FileInputStream data = new FileInputStream(file);
        Scanner baca = new Scanner(data);
        int n = baca.nextInt();
        Matriks.MakeEmpty(x, 1, n + 1);
        Matriks.MakeEmpty(y, 1, n + 1);

        // ALGORITMA
        for (int i = 0; i <= n; i++) {
            x.isi[0][i] = baca.nextFloat();
            y.isi[0][i] = baca.nextFloat();
        }
        X[0] = baca.nextFloat();
        // tinggal pake fungsi dari SPL
        baca.close();

    }

    public static void interpolar(Matriks x, Matriks y, Matriks M, Float[] X, Float[] Y) {
        // KAMUS LOKAL
        Matriks Mtemp = new Matriks();
        float Xtemp = 1f;
        int i, j;
        // ALGORITMA
        Matriks.MakeEmpty(Mtemp, x.KolEff, x.KolEff + 1);
        for (i = 0; i < Mtemp.BrsEff; i++) {
            Mtemp.isi[i][0] = 1;
            for (j = 1; j < Mtemp.KolEff - 1; j++) {
                Mtemp.isi[i][j] = Mtemp.isi[i][j - 1] * x.isi[0][i];
            }
            Mtemp.isi[i][Mtemp.LastIdxKol] = y.isi[0][i];
        }
        SPL.Cramer(Mtemp, M);
        Y[0] = 0f;
        for (j = 0; j < M.KolEff; j++) {
            Y[0] += M.isi[0][j] * Xtemp;
            Xtemp *= X[0];
        }

    }

    public static void output(Matriks M, Float[] X, Float[] Y) {
        // KAMUS LOKAL
        float Xtemp = 1f;
        int j;
        //ALGORITMA
        System.out.print("persamaan polinom yang terbentuk: y = ");
        Y[0] = 0f;
        for (j = 0; j < M.BrsEff; j++) {
            if(j==0){
                System.out.printf("%.2f", M.isi[j][0]);
            }
            else{
                System.out.printf(" + %.2fx^%d", M.isi[j][0], j);
            }
            Y[0] += M.isi[j][0] * Xtemp;
            Xtemp *= X[0];
        }
        System.out.print("\n");
        System.out.print("nilai dari y untuk x = "+ X[0] +" adalah " + Y[0] + "\n");
    }

    public static void outputFile(Matriks M, Float[] X, Float[] Y, String File) {
        // KAMUS LOKAL
        float Xtemp = 1;
        int j;
        PrintWriter file;
        //ALGORITMA
        try{
            file = new PrintWriter(File);
            Y[0] = 0f;
            System.out.print("persamaan polinom yang terbentuk: y = ");
            file.print("persamaan polinom yang terbentuk: y = ");
            for (j = 0; j < M.BrsEff; j++) {
                if(j==0){
                    System.out.printf("%.2f", M.isi[j][0], j);
                    file.printf("%.2f", M.isi[j][0], j);
                }
                else{
                    System.out.printf(" + %.2fx^%d", M.isi[j][0], j);
                    file.printf(" + %.2fx^%d", M.isi[j][0], j);
                }
                Y[0] += M.isi[j][0] * Xtemp;
                Xtemp *= X[0];
            }
            System.out.print("\n");
            file.print("\n");
            System.out.print("nilai dari y untuk x = "+ X[0] +" adalah " + Y[0] + "\n");
            file.print("nilai dari y untuk x = "+ X[0] +" adalah " + Y[0] + "\n");
            file.close();
        }
        catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }
/*
    public static void main(String[] args) throws FileNotFoundException {
        // KAMUS
        Matriks x = new Matriks(), y = new Matriks();
        Float[] X = new Float[]{0f}, Y = new Float[]{0f};
        //ALGORITMA
        inputUser(x, y, X);
        interpolar(x, y, X, Y);
        output(Y);
    }
*/
}