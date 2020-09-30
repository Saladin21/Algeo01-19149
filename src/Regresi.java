import java.util.Scanner;
//import java.io.*;

public class Regresi{
    public static void inputUser(Matriks x, Matriks y, Matriks X){
        //KAMUS LOKAL
        Scanner baca = new Scanner(System.in);
        int k, n;
        //ALGORITMA
        k = baca.nextInt();
        n = baca.nextInt();
        Matriks.MakeEmpty(x, n, k);
        Matriks.MakeEmpty(y, n, 1);
        Matriks.MakeEmpty(X, 1, k);
        for(int i = 0; i<n; i++){
            for(int j = 0; j<k; i++){
                x.isi[i][j] = baca.nextFloat();
            }
            y.isi[i][0] = baca.nextFloat();
        }
        for(int i = 0; i<k; i++){
            X.isi[0][i] = baca.nextFloat();
        }
        baca.close();
    }
/*
    public static void inputFile(Matriks x, Matriks y, Matriks X, String file){
        //KAMUS LOKAL
        FileInputStream data = new FileInputStream(file);
        Scanner baca = new Scanner(data);
        int k, n;
        //ALGORITMA
        k = baca.nextInt();
        n = baca.nextInt();
        Matriks.MakeEmpty(x, n, k);
        Matriks.MakeEmpty(y, n, 1);
        Matriks.MakeEmpty(X, 1, k);
        for(int i = 0; i<n; i++){
            for(int j = 0; j<k; i++){
                x.isi[i][j] = baca.nextFloat();
            }
            y.isi[i][0] = baca.nextFloat();
        }
        for(int i = 0; i<k; i++){
            X.isi[0][i] = baca.nextFloat();
        }
        baca.close();
    }
*/
    public static void regresi(Matriks x, Matriks y, Matriks X, Float[] Y){
        //KAMUS LOKAL
        Matriks M = new Matriks();
        Matriks M1 = new Matriks();
        int i, j;
        //ALGORITMA
        Matriks.MakeEmpty(M, x.KolEff+1, x.KolEff+2);

        M.isi[0][0] = x.BrsEff;
        for(j = 1; j<M.LastIdxKol; j++){
            M.isi[0][j] = Matriks.JumlahKol(x, j-1);
        }
        M.isi[0][M.LastIdxKol] = Matriks.JumlahKol(y, 0);

        for(i = 1; i<M.BrsEff; i++){
            M.isi[i][0] = Matriks.JumlahKol(x, i-1);
            for(j = 1; j<M.KolEff-1; j++){
                M.isi[i][j] = Matriks.JumlahKol(Matriks.KaliAntarKol(x, x, i-1, j-1), 0);
            }
            M.isi[i][M.KolEff-1] = Matriks.JumlahKol(Matriks.KaliAntarKol(x, y, i-1, 0), 0);
        }

        SPL.Cramer(M, M1);

        Y[0] = 0f;
        for (j = 0; j < M.KolEff; j++) {
            Y[0] += M1.isi[0][j]*X.isi[0][j];
        }
    }

    public static void output(Float[] Y){
        System.out.print(Y[0]);
    }
}