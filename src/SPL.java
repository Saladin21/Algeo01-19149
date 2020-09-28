public class SPL {
    public static void Cramer (Matriks M, Matriks Mhsl){
        /**KAMUS LOKAL */
        Matriks M1 = new Matriks();
        Matriks M2 = new Matriks();
        float D1, D2;
        int i, j;


        /**ALGORITMAN */
        Matriks.MakeEmpty(M1, M.BrsEff, M.KolEff-1);
        for (i=M1.FirsIdxBrs;i<=M1.LastIdxBrs;i++){
            for (j=M1.FirsIdxBrs;j<=M1.LastIdxBrs;j++){
                M1.isi[i][j] = M.isi[i][j];
            }
        }
        D1 = Matriks.DeterminanKofaktor(M1);

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

    public static void Gauss (Matriks M, Matriks Mhsl){
        /**KAMUS LOKAL */
        int i, j;
        float sum;
        /**ALGORITMA */
        Matriks.MakeEselon(M);
        Matriks.TulisLayar(M);
        Matriks.MakeEmpty(Mhsl, 1, M.KolEff-1);

        for (i=M.LastIdxBrs;i>=0;i--){
            sum = 0;
            for (j=i+1;j<M.BrsEff;j++){
                sum += M.isi[i][j] * Mhsl.isi[0][j];
                Mhsl.isi[0][i] = (M.isi[i][M.LastIdxKol] - sum) / M.isi[i][i];
            }
        }
    }

    public static void main(String[] args) {
        Matriks M = new Matriks();
        Matriks M2 = new Matriks();
        Matriks.BacaKeyboard(M);
        SPL.Gauss(M, M2);
        Matriks.TulisLayar(M2);

    }
}
