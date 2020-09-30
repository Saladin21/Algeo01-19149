//import java.io.FileInputStream;
import java.util.Scanner;

public class Interpolar{
    public static void inputUser(Matriks x, Matriks y, Float[] X) {
        // KAMUS LOKAL
        Scanner baca = new Scanner(System.in);
        int n = baca.nextInt();
        Matriks.MakeEmpty(x, 1, n + 1);
        Matriks.MakeEmpty(y, 1, n + 1);

        // ALGORITMA
        for (int i = 0; i <= n; i++) {
            x.isi[0][i] = baca.nextFloat();
            y.isi[0][i] = baca.nextFloat();
        }
        X[0] = baca.nextFloat();
        System.out.print(X);
        // tinggal pake fungsi dari SPL
        baca.close();

    }
/*
    public static void inputFile(final Matriks x, final Matriks y, Float X, final String file) {
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
        X = baca.nextFloat();
        // tinggal pake fungsi dari SPL
        baca.close();

    }
*/
    public static void interpolar(Matriks x, Matriks y, Float[] X, Float[] Y) {
        // KAMUS LOKAL
        Matriks M = new Matriks();
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
        Matriks.TulisLayar(M);
        Y[0] = 0f;
        for (j = 0; j < M.KolEff; j++) {
            Y[0] += M.isi[0][j] * Xtemp;
            Xtemp *= X[0];
        }

    }

    public static void output(Float[] Y) {
        System.out.print(Y[0]);
    }

    public static void main(String[] args) {
        // KAMUS
        Matriks x = new Matriks(), y = new Matriks();
        Float[] X = new Float[]{0f}, Y = new Float[]{0f};
        //ALGORITMA
        inputUser(x, y, X);
        interpolar(x, y, X, Y);
        output(Y);
    }
}