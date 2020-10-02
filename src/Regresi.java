import java.util.Scanner;
import java.io.*;

public class Regresi{
    public static void inputUser(Matriks x, Matriks y, Matriks X) {
        //KAMUS LOKAL
        int k, n;
        //ALGORITMA
        System.out.print("Masukkan banyak variabel x: ");
        k = Menu.baca.nextInt();
        System.out.print("Masukkan banyak titik: ");
        n = Menu.baca.nextInt();
        Matriks.MakeEmpty(x, n, k);
        Matriks.MakeEmpty(y, n, 1);
        Matriks.MakeEmpty(X, 1, k);
        for(int i = 0; i<n; i++){
            System.out.print("Masukkan nilai titik ke-"+(i+1)+": ");
            for(int j = 0; j<k; j++){
                x.isi[i][j] = Menu.baca.nextFloat();
            }
            y.isi[i][0] = Menu.baca.nextFloat();
        }
        System.out.print("Masukkan nilai X dari titik yang akan di taksir: ");
        for(int i = 0; i<k; i++){
            X.isi[0][i] = Menu.baca.nextFloat();
        }
        Menu.baca.close();
    }

    public static void inputFile(Matriks x, Matriks y, Matriks X, String file) throws FileNotFoundException{
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
            for(int j = 0; j<k; j++){
                x.isi[i][j] = baca.nextFloat();
            }
            y.isi[i][0] = baca.nextFloat();
        }
        for(int i = 0; i<k; i++){
            X.isi[0][i] = baca.nextFloat();
        }
        baca.close();
    }

    public static void regresi(Matriks x, Matriks y, Matriks M1, Matriks X){
        //KAMUS LOKAL
        Matriks M = new Matriks();
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
    }

    public static void output(Matriks M, Matriks X, Float[] Y){
        // KAMUS LOKAL
        int j;
        //ALGORITMA
        System.out.print("persamaan regresi yang terbentuk: y = ");
        System.out.printf("%.2f", M.isi[0][0]);
        Y[0] = M.isi[0][0];
        for (j = 1; j < M.BrsEff; j++) {
            System.out.printf(" + %.2fx%d", M.isi[j][0], j);
            Y[0] += M.isi[j][0]*X.isi[0][j-1];
        }
        System.out.print("\n");
        System.out.print("Berdasarkan regresi linier berganda, hampiran dari nilai y adalah " + Y[0] + "\n");
    }

    public static void outputFile(Matriks M, Matriks X, Float[] Y, String File) {
        // KAMUS LOKAL
        int j;
        PrintWriter file;
        //ALGORITMA
        try{
            file = new PrintWriter(File);

            System.out.print("persamaan regresi yang terbentuk: y = ");
            file.print("persamaan regresi yang terbentuk: y = ");

            System.out.printf("%.2f", M.isi[0][0]);
            file.printf("%.2f", M.isi[0][0]);

            Y[0] = M.isi[0][0];
            for (j = 1; j < M.BrsEff; j++) {
                System.out.printf(" + %.2fx%d", M.isi[j][0], j);
                file.printf(" + %.2fx%d", M.isi[j][0], j);

                Y[0] += M.isi[j][0]*X.isi[0][j-1];
            }
            System.out.print("\n");
            file.print("\n");

            System.out.print("Berdasarkan regresi linier berganda, hampiran dari nilai y adalah " + Y[0] + "\n");
            file.print("Berdasarkan regresi linier berganda, hampiran dari nilai y adalah " + Y[0] + "\n");

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
        Matriks x = new Matriks(), y = new Matriks(), X = new Matriks();
        Float[] Y = new Float[]{0f};
        //ALGORITMA
        inputUser(x, y, X);
        regresi(x, y, X, Y);
        output(Y);
    }
*/
}