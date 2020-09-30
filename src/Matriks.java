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
                elemen = baca.nextFloat();
                M.isi[i][j] = elemen;
            }
        }
    }

    public static void BacaFile(Matriks M){
        /**KAMUS LOKAL */
        Scanner baca = new Scanner(System.in);
        String namaFile;
        /**ALGORITMA */
        System.out.print("Masukkan nama file yang akan dibaca: ");
        namaFile = baca.nextLine();


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

    public static void TulisFile (Matriks M){
        /**KAMUS LOKAL */

        /**ALGORITMA */
    }

    public static void CopyMatriks(Matriks M1, Matriks M2){ //Menyalin elemen M ke M2
        /**KAMUS LOKAL */
        int i, j;

        /**Algoritma */
        M2.BrsEff = M1.BrsEff;
        M2.KolEff = M1.KolEff;
        M2.NbElmt = M2.BrsEff * M2.KolEff;
        M2.LastIdxBrs = M2.BrsEff - 1 + M2.FirsIdxBrs;
        M2.LastIdxKol = M2.KolEff -1 + M2.FirstIdxKol;
        
        for (i=M2.FirsIdxBrs;i<=M2.LastIdxBrs;i++){
            for (j=M2.FirstIdxKol;j<=M2.LastIdxKol;j++){
                M2.isi[i][j] = M1.isi[i][j];
            }
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

    public static boolean IsEselon (Matriks M){ //Mengecek apakah matriks M adalah matriks eselon
        /**KAMUS LOKAL */
        boolean eselon, foundLone;
        int i, j, leadingOne;
        /**ALGORITMA */
        eselon = true;
        leadingOne = -1;
        i = M.FirsIdxBrs;
        while (eselon && i<=M.LastIdxBrs){
            foundLone = false;
            j = M.LastIdxKol;
            while (eselon && i<=M.LastIdxKol && !foundLone){
                if (M.isi[i][j]==0){
                    j++;
                }
                else if (M.isi[i][j] == 1){
                    if (j>leadingOne){
                        leadingOne = j;
                        foundLone = true;
                    }
                    else{
                        eselon = false;
                    }
                }
                else{
                    eselon = false;
                }

            }
            if (eselon){
                i++;
            }
        }
        return eselon;

    }

    public static void MakeEselon(Matriks M){ //Mengubah matriks M menjadi matriks eselon dengan OBE
        /**KAMUS LOKAL */
        int k, i, j, n, max;
        float lead;
        float pengali;
        /**ALGORITMA */
        if(!IsEselon(M)){
            if (M.BrsEff<M.KolEff-1){
            M.BrsEff = M.KolEff -1;
            M.LastIdxBrs = M.BrsEff -1;
            }
            n = M.KolEff-1;
            for (k=0;k<n;k++){
                max = k;
                for (i=k+1;i<=M.LastIdxBrs;i++){
                    if (Math.abs(M.isi[i][k])>(Math.abs(M.isi[max][k]))){
                        max = i;
                    }
                }
                Matriks.TukarBrs(M, max, k);

                for (i=k+1;i<=M.LastIdxBrs;i++){
                    pengali = M.isi[i][k] / M.isi[k][k];
                    Matriks.TambahBrs(M, i, k, (pengali*-1));
                }                
            }

            i = M.FirsIdxBrs;
            while (i<=M.LastIdxBrs){
                j = M.FirstIdxKol;
                lead = 0;
                while (lead==0 && j<=M.LastIdxKol){
                    if (M.isi[i][j]!=0){
                        lead = M.isi[i][j];
                    }
                    else{
                        j++;
                    }
                }
                if(lead != 0){
                    Matriks.KaliBrs(M, i, (1/lead));
                }
                i++;
            } 

        }

    }


    public static void MakeReducedEselon(Matriks M){
        /**KAMUS LOKAL */
        int k, i, j, n, max;
        float lead;
        float pengali;
        /**ALGORITMA */
        if(!IsEselon(M)){
            if (M.BrsEff<M.KolEff-1){
            M.BrsEff = M.KolEff -1;
            M.LastIdxBrs = M.BrsEff -1;
            }
            n = M.KolEff-1;
            for (k=0;k<n;k++){
                max = k;
                for (i=k+1;i<=M.LastIdxBrs;i++){
                    if (Math.abs(M.isi[i][k])>(Math.abs(M.isi[max][k]))){
                        max = i;
                    }
                }
                Matriks.TukarBrs(M, max, k);

                for (i=M.FirsIdxBrs;i<=M.LastIdxBrs;i++){
                    if(i != k){
                        pengali = M.isi[i][k] / M.isi[k][k];
                        Matriks.TambahBrs(M, i, k, (pengali*-1));
                    }
                }                
            }

            i = M.FirsIdxBrs;
            while (i<=M.LastIdxBrs){
                j = M.FirstIdxKol;
                lead = 0;
                while (lead==0 && j<=M.LastIdxKol){
                    if (M.isi[i][j]!=0){
                        lead = M.isi[i][j];
                    }
                    else{
                        j++;
                    }
                }
                if(lead != 0){
                    Matriks.KaliBrs(M, i, (1/lead));
                }
                i++;
            } 

        }

    }

    /*OBE*/
    public static void transposeMatriks(Matriks M){
        /**KAMUS LOKAL */
        int i, j;
        float temp;
        /**ALGORITMA */
        for (i=M.FirsIdxBrs; i<=M.LastIdxBrs; i++){
            for (j=M.FirstIdxKol; j<i; j++){
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

    public static float DeterminanReduksiBaris(Matriks M){
        /**KAMUS LOKAL */
        float det, bagi;
        int i, j, k, l, p, q;
        int idxfb0, tuker, tanda;
        boolean bukan0;
        /**ALGORITMA */
        if (Matriks.IsSegitigaAtas(M) || Matriks.IsSegitigaBawah(M)){
            det = M.isi[0][0];
            for (i=M.FirstIdxKol+1; i<=M.LastIdxBrs; i++){
                det *= M.isi[i][i];
            }
            return det;
        } else{
            j = 0;
            tuker = 0;
            while (j<=M.LastIdxKol){
                k = j;
                bukan0 = false;
                while(k<=M.LastIdxBrs && bukan0==false){
                    if (M.isi[k][j] != 0){
                        bukan0 = true;
                    } else{
                        k++;
                    }
                }
                if (bukan0 == false){
                    det = 0;
                    return det;
                } else{
                    idxfb0 = k;
                    if (idxfb0 > j){
                        Matriks.TukarBrs(M, idxfb0, j);
                        tuker ++;
                    }
                    l = j+1;
                    while (l<=M.LastIdxKol){
                        if (M.isi[l][j] != 0){
                            bagi = (M.isi[l][j] / M.isi[j][j]) * -1;
                            Matriks.TambahBrs(M, l, j, bagi);
                        }
                        l++;
                    }
                }
                j++;
            }
            det = M.isi[0][0];
            q = 0;
            tanda = -1;
            while (q <= tuker){
                tanda *= -1;
                q++;
            }
            for (p = 1; p<=M.LastIdxKol; p++){
                det *= M.isi[p][p];
            }
            return det*tanda;
        }
    }

    public static float JumlahBrs(Matriks M, int i){
        //KAMUS LOKAL
        float hasil = 0;
        //ALGORITMA
        for(int j=0; j<M.KolEff; j++){
            hasil += M.isi[i][j];
        }
        return hasil;
    }

    public static float JumlahKol(Matriks M, int j){
        //KAMUS LOKAL
        float hasil = 0;
        //ALGORITMA
        for(int i=0; i<M.BrsEff; i++){
            hasil += M.isi[i][j];
        }
        return hasil;
    }

    public static Matriks KaliAntarBrs(Matriks M1, Matriks M2, int i1, int i2){
        // I.S Kolom Efektif M1 dam M2 sama
        //KAMUS LOKAL
        Matriks M = new Matriks();
        //ALGORITMA
        MakeEmpty(M, 1, M1.KolEff);
        for(int j=0; j<M1.KolEff; j++){
            M.isi[0][j] = M1.isi[i1][j]*M2.isi[i2][j];
        }
        return M;
    }

    public static Matriks KaliAntarKol(Matriks M1, Matriks M2, int j1, int j2){
        // I.S Baris Efektif M1 dam M2 sama
        //KAMUS LOKAL
        Matriks M = new Matriks();
        //ALGORITMA
        MakeEmpty(M, M1.BrsEff, 1);
        for(int i=0; i<M1.BrsEff; i++){
            M.isi[i][0] = M1.isi[i][j1]*M2.isi[i][j2];
        }
        return M;
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
        Matriks.TulisLayar(M);
        System.out.print("\n");
        Matriks.TulisLayar(Matriks.buangBrKolMatriks(M, 0, 0));
        System.out.print("\n");
        Matriks.TulisLayar(Matriks.matriksKofaktor(M));
        System.out.print("\n");
        Matriks.TulisLayar(Matriks.inverseMatriks(M));
        System.out.print("\n");
        float determinanbrs = Matriks.DeterminanReduksiBaris(M);
        System.out.printf("Determinan menggunakan reduksi baris: %.2f \n", determinanbrs);
        System.out.print("\n");
    }

}


