import java.util.Scanner;

public class SPL {
    public static int Cramer (Matriks M, Matriks Mhsl){
        /**KAMUS LOKAL */
        Matriks M1 = new Matriks();
        Matriks M2 = new Matriks();
        float D1, D2;
        int i, j, kategori=1; //kategori: 1. solusi tunggal, 4. tidak dapat menggunakan metode yang dipilih


        /**ALGORITMAN */
        Matriks.MakeEmpty(M1, M.BrsEff, M.KolEff-1);
        for (i=M1.FirsIdxBrs;i<=M1.LastIdxBrs;i++){
            for (j=M1.FirsIdxBrs;j<=M1.LastIdxBrs;j++){
                M1.isi[i][j] = M.isi[i][j];
            }
        }
        D1 = Matriks.DeterminanKofaktor(M1);
        if ((M1.BrsEff != M1.KolEff) || D1 ==0){
            kategori = 4;
        }

        if (kategori == 1){
            Matriks.MakeEmpty(Mhsl, 1, M1.KolEff);
            for (j=0;j<=M.KolEff-1;j++){
                Matriks.CopyMatriks(M1, M2);
                for (i=M2.FirsIdxBrs;i<=M2.LastIdxBrs;i++){
                    M2.isi[i][j] = M.isi[i][M.LastIdxKol];
                }
                D2 = Matriks.DeterminanKofaktor(M2);
                Mhsl.isi[0][j] = D2/D1;
            }
        }
        return kategori;
    }

    public static int Invers (Matriks M, Matriks Mhsl){
        /**KAMUS LOKAL */
        Matriks A = new Matriks();
        Matriks B = new Matriks();
        int i, j;
        int kategori = 1; //kategori: 1. solusi tunggal, 4. tidak dapat menggunakan metode yang dipilih
        /**ALGORITMA */
        Matriks.MakeEmpty(A, M.BrsEff, M.KolEff-1);
        Matriks.MakeEmpty(B, M.BrsEff, 1);

        if(A.BrsEff != A.KolEff){
            kategori = 4;
        }
        else{
        
            for (i=A.FirsIdxBrs;i<=A.LastIdxBrs;i++){
                for (j=A.FirstIdxKol;j<=A.LastIdxKol;j++){
                    A.isi[i][j] = M.isi[i][j];
                }
            }

            for (i=B.FirsIdxBrs;i<=B.LastIdxBrs;i++){
                B.isi[i][0] = M.isi[i][M.LastIdxKol];
            }

            A = Matriks.inverseMatriks(A);

            Matriks.KaliMatriks(A, B, Mhsl);
         }

        return kategori;
    }

    public static int Gauss (Matriks M, Matriks Mhsl){
        //kategori: 1. solusi tunggal, 2. banyak solusi, 3. tidak ada solusi
        /**KAMUS LOKAL */
        int i, j, kategori;
        float sum;
        Matriks A = new Matriks();
        /**ALGORITMA */
        Matriks.MakeEselon(M);
        Matriks.MakeEmpty(Mhsl, 1, M.KolEff-1);
        Matriks.MakeEmpty(A, M.BrsEff, M.KolEff-1);

        for (i=A.FirsIdxBrs;i<=A.LastIdxBrs;i++){
            for (j=A.FirstIdxKol;j<=A.LastIdxKol;j++){
                A.isi[i][j] = M.isi[i][j];
            }
        }

        kategori = 1;
        i = A.FirsIdxBrs;
        while ((kategori !=3) && i<=A.LastIdxBrs){
            if (Matriks.AllZero(A, i)){
                if (M.isi[i][M.LastIdxKol]==0){
                    kategori = 2;
                }
                else{
                    kategori = 3;
                }
            }
            else{
                i++;
            }
        }
        if (kategori == 1 || (kategori == 2 && M.BrsEff>M.KolEff-1)){ 
            Mhsl.isi[0][Mhsl.LastIdxKol] = M.isi[M.LastIdxKol-1][M.LastIdxKol];
            for (i=M.LastIdxKol-2;i>=0;i--){
                sum = 0;
                for (j=i+1;j<M.KolEff-1;j++){
                    sum += M.isi[i][j] * Mhsl.isi[0][j];
                    Mhsl.isi[0][i] = (M.isi[i][M.LastIdxKol] - sum) / M.isi[i][i];
                }
            }
            kategori = 1;
        }
        return kategori;
    }

    public static int GaussJordan (Matriks M, Matriks Mhsl){
        /**KAMUS LOKAL */
        int kategori = 1;

        /**ALGORITMA */

        return kategori;
    }

    public static void SPLMenu (){
        /**KAMUS LOKAL */
        Scanner baca = new Scanner(System.in);
        int input = 0;
        int cara = 0;
        int kategori;
        Matriks M = new Matriks();
        Matriks Mhsl = new Matriks();
        char simpan = 'z';
        /**ALGORITMA */
        while (input != 1 && input != 2){
            System.out.println("Pilih input: ");
            System.out.println("1. Keyboard");
            System.out.println("2. File");
            input = baca.nextInt();
        }

        if(input==1){
            System.out.println("Masukkan SPL dalam bentuk Matriks Augmented");
            Matriks.BacaKeyboard(M);
        }
        else{
            Matriks.BacaFile(M);
        }

        while (cara<1 || cara > 4){
            System.out.println("Pilih metode: ");
            System.out.println("1. Eliminasi Gauss");
            System.out.println("2. Eliminasi Gauss-Jordan");
            System.out.println("3. Matriks Balikan");
            System.out.println("4. Kaidah Crammer");
            cara = baca.nextInt();
        }

        if (cara==1){
            kategori = SPL.Gauss(M, Mhsl);
        }
        else if (cara==2){
            kategori = SPL.GaussJordan(M, Mhsl);
        }
        else if(cara==3){
            kategori = SPL.Invers(M, Mhsl);
        }
        else{
            kategori = SPL.Cramer(M, Mhsl);
        }

        if (kategori ==4){
            System.out.println("Tidak dapat menggunakan metode yang dipilih.");
        }
        else if (kategori == 3){
            System.out.println("SPL tidak memiliki solusi.");
        }
        else if (kategori == 2){
            System.out.println("SPL tidak memiliki solusi tunggal.");
        }
        else{
            System.out.println("Solusi SPL berturut-turut adalah: ");
            Matriks.TulisLayar(Mhsl);
            while (simpan != 'y' && simpan != 'n'){
                System.out.println("Apakah ingin menyimpan hasil? (y/n)");
                simpan = baca.next().charAt(0);
            }
            if (simpan == 'y'){
                Matriks.TulisFile(M);
            }

        }


        baca.close();
    }

    public static void main(String[] args) {
        SPL.SPLMenu();
    }
}
