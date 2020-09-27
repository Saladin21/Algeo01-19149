import java.util.Scanner;

public class Matriks {
    int BrsMax = 99;
    int KolMax = 99;
    float[][] isi = new float[BrsMax+1][KolMax+1];
    int BrsEff = 0;
    int KolEff = 0;
    int FirsIdxBrs = 0;
    int FirstIdxKol = 0;
    int LastIdxBrs;
    int LastIdxKol;
    int NbElmt;

    public static void MakeEmpty(Matriks M, int nb, int nk) { //Membuat matriks dengan ukuran nb x nk
        M.BrsEff = nb;
        M.KolEff = nk;
        M.NbElmt = M.BrsEff * M.KolEff;
        M.LastIdxBrs = M.BrsEff - 1 + M.FirsIdxBrs;
        M.LastIdxKol = M.KolEff -1 + M.FirstIdxKol;        
    }

    public static void BacaKeyboard(Matriks M){
        /**KAMUS LOKAL */
        Scanner baca = new Scanner(System.in);
        float elemen;
        int nb, nk,i,j;
        /**ALGORITMA */
        System.out.print("Masukkan jumlah baris: ");
        nb = baca.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        nk = baca.nextInt();
        Matriks.MakeEmpty(M, nb, nk);
        System.out.println("Masukkan matriks: ");

        for (i=M.FirsIdxBrs;i<=M.LastIdxBrs;i++){
            for (j=M.FirstIdxKol; j<=M.LastIdxKol;j++){
                elemen = baca.nextInt();
                M.isi[i][j] = elemen;
            }
        }
        baca.close();
    }

    public static void TulisLayar (Matriks M){
        /**KAMUS LOKAL */
        int i, j;
        /**ALGORITMA */
        for (i=M.FirsIdxBrs;i<=M.LastIdxBrs;i++){
            for (j=M.FirstIdxKol; j<=M.LastIdxKol;j++){
                System.out.print(M.isi[i][j] + " ");
            }
            System.out.println("");
        }
    }


    public static boolean IsSegitigaAtas(Matriks M){ //Mengecek apakah Matriks M matriks segitiga atas
        /**KAMUS LOKAL */
        boolean SegiTigaAtas;
        int i, j;
        /**ALGORITMA */
        if (M.BrsEff==M.KolEff){
            SegiTigaAtas = true;
            i = M.FirsIdxBrs + 1;
            while (SegiTigaAtas && i<=M.LastIdxBrs){
                j = M.FirstIdxKol;
                while (SegiTigaAtas && j<i){
                    if(M.isi[i][j] != 0){
                        SegiTigaAtas = false;
                    }
                    else{
                        j++;
                    }
                }
                if(SegiTigaAtas){
                    i++;
                }
            }
        }
        else{
            SegiTigaAtas = false;
        }
        return SegiTigaAtas;
    }

    public static boolean IsSegitigaBawah(Matriks M){ //Mengecek apakah Matriks M matriks segitiga bawah
        /**KAMUS LOKAL */
        boolean SegiTigaBawah;
        int i, j;
        /**ALGORITMA */
        if (M.BrsEff==M.KolEff){
            SegiTigaBawah = true;
            i = M.LastIdxBrs - 1;
            while (SegiTigaBawah && i>=M.FirsIdxBrs){
                j = M.LastIdxKol;
                while (SegiTigaBawah && j>i){
                    if(M.isi[i][j] != 0){
                        SegiTigaBawah = false;
                    }
                    else{
                        j--;
                    }
                }
                if(SegiTigaBawah){
                    i--;
                }
            }
        }
        else{
            SegiTigaBawah = false;
        }
        return SegiTigaBawah;
    }



    /*OBE*/
    public static void transposeMatriks(Matriks M){
        /**KAMUS LOKAL */
        int i, j;
        float temp;
        /**ALGORITMA */
        for (i=M.FirsIdxBrs; i<=M.LastIdxBrs; i++){
            for (j=M.FirstIdxKol; j<=M.LastIdxKol; j++){
                temp = M.isi[i][j];
                M.isi[i][j] = M.isi[j][i];
                M.isi[j][i] = temp;
            }
        }
    }
    public static void TukarBrs(Matriks M, int r1, int r2){ //Menukar elemen baris r1 dan r2
        /**KAMUS LOKAL */
        int j;
        float temp;
        /**ALGORITMA */
        for (j=M.FirstIdxKol;j<=M.LastIdxKol;j++){
            temp = M.isi[r1][j];
            M.isi[r1][j] = M.isi[r2][j];
            M.isi[r2][j] = temp;
        }
    }

    public static void KaliBrs(Matriks M, int i, float x){ //Mengalikan baris i dengan x
        /**KAMUS LOKAL */
        int j;
        /**ALGORITMA */
         for(j=M.FirstIdxKol;j<=M.LastIdxKol;j++){
            M.isi[i][j] = M.isi[i][j] * x;
         }

    }

    public static void TambahBrs(Matriks M, int r1, int r2, float x){   //Menambahkan tiap elemen r1 dengan x kali elemen r2
        /**KAMUS LOKAL */
        int j;
        /**ALGORITMA */
        for (j=M.FirstIdxKol; j<=M.LastIdxKol ; j++){
            M.isi[r1][j] += M.isi[r2][j]*x;
        }
    }

    //Asumsi Matriks berukuran nxn atau NeffBrs == NeffKol
    public static float DeterminanKofaktor(Matriks M){
        /**KAMUS LOKAL */
        int i,j,k;
        int sign;
        Matriks Mnxt = new Matriks();
        float det;
        int i2, j2;

        /**ALGORITMA */
        if(M.NbElmt == 4){
            return ((M.isi[0][0] * M.isi[1][1]) - (M.isi[0][1] * M.isi[1][0]));
        } else{
            sign = 1;
            det = 0;
            for (i=M.FirsIdxBrs ; i<=M.LastIdxBrs; i++){
                Matriks.MakeEmpty(Mnxt, M.BrsEff-1, M.KolEff-1);
                i2 = -1;
                for (j=M.FirsIdxBrs; j<=M.LastIdxBrs; j++){
                    i2++;
                    if (j==0){
                        j++;
                    }
                    j2 = -1;
                    for (k=M.FirstIdxKol; k<=M.LastIdxKol; k++){
                        j2++;
                        if (k == i){
                            k++;
                        }
                        Mnxt.isi[i2][j2] = M.isi[j][k];
                    }
                }
                det += M.isi[0][i] * Matriks.DeterminanKofaktor(Mnxt) * sign;
                sign *= -1;
            }
        }
        return det;
    }

    public static Matriks buangBrKolMatriks(Matriks M, int a, int b){
        /**KAMUS LOKAL */
        int j,k;
        Matriks Mnxt = new Matriks();
        int i2, j2;

        /**ALGORITMA */
        MakeEmpty(Mnxt, M.BrsEff-1, M.KolEff-1);
        i2 = -1;
        for (j=M.FirsIdxBrs; j<=M.LastIdxBrs; j++){
            i2++;
            if (j==a){
                j++;
            }
            j2 = -1;
            for (k=M.FirstIdxKol; k<=M.LastIdxKol; k++){
                j2++;
                if (k == b){
                    k++;
                }
                Mnxt.isi[i2][j2] = M.isi[j][k];
            }
        }
        return Mnxt;
    }

    //Asumsi Matriks berukuran nxn atau NeffBrs == NeffKol
    public static Matriks matriksKofaktor(Matriks M){
        /**KAMUS LOKAL */
        int i, j;
        Matriks Mkof = new Matriks();
        int sign = 1;
        /**ALGORITMA */
        Matriks.MakeEmpty(Mkof, M.BrsEff, M.KolEff);
        for (i=M.FirsIdxBrs; i<=M.LastIdxBrs; i++){
            for (j=M.FirstIdxKol; j<=M.LastIdxKol; j++){
                Mkof.isi[i][j] = Matriks.DeterminanKofaktor(Matriks.buangBrKolMatriks(M, i, j)) * sign;
                if (Matriks.DeterminanKofaktor(Matriks.buangBrKolMatriks(M, i, j))==0 && sign ==-1){
                    Mkof.isi[i][j] = 0;
                }
                sign *= -1;
            }
        }
        return Mkof;
    }

    //Asumsi Matriks berukuran nxn atau NeffBrs == NeffKol
    public static Matriks inverseMatriks(Matriks M){
        /**KAMUS LOKAL */
        int i, j;
        float determinan;
        /**ALGORITMA */
        Matriks matriksInverse = new Matriks();
        Matriks matriksAdj = new Matriks();
        Matriks.MakeEmpty(matriksInverse, M.BrsEff, M.KolEff);
        matriksAdj = Matriks.matriksKofaktor(M);
        Matriks.transposeMatriks(matriksAdj);
        determinan = Matriks.DeterminanKofaktor(M);
        for (i=M.FirsIdxBrs; i<=M.LastIdxBrs; i++){
            for (j=M.FirstIdxKol; j<=M.LastIdxKol; j++){
                matriksInverse.isi[i][j] = matriksAdj.isi[i][j] / determinan ;
            }
        }
        return matriksInverse;
    }

    public static void main(String[] args) {
        Matriks M = new Matriks();
        Matriks.BacaKeyboard(M);
        Matriks.TulisLayar(M);
        System.out.print("\n");
        Matriks.TukarBrs(M, 0, 1);
        Matriks.TulisLayar(M);
        System.out.print("\n");
        float determinan = Matriks.DeterminanKofaktor(M);
        System.out.printf("%.2f", determinan);
        System.out.print("\n");
        Matriks.TulisLayar(Matriks.buangBrKolMatriks(M, 0, 0));
        System.out.print("\n");
        Matriks.TulisLayar(Matriks.matriksKofaktor(M));
        System.out.print("\n");
        Matriks.TulisLayar(Matriks.inverseMatriks(M));
    }

}


