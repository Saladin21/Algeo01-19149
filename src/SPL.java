public class SPL {
    public static Matriks Cramer (Matriks M){
        /**KAMUS LOKAL */
        Matriks Mhsl = new Matriks();
        Matriks M1 = new Matriks();
        Matriks M2 = new Matriks();
        float D1, D2;
        int i, j;


        /**ALGORITMAN */
        Matriks.MakeEmpty(M1, M.BrsEff, M.KolEff-1);
        for (i=M1.FirsIdxBrs;i<=M1.LastIdxBrs;i++){
            for (j=M.FirsIdxBrs;j<=M1.LastIdxBrs;j++){
                M1.isi[i][j] = M.isi[i][j];
            }
        }
        D1 = Matriks.DeterminanKofaktor(M1);

        Matriks.MakeEmpty(Mhsl, 1, M1.KolEff);
        for (j=0;j<=M.KolEff-1;j++){
            Matriks.CopyMatriks(M1, M2);
            for (i=M2.FirsIdxBrs;i<=M2.LastIdxBrs;i++){
                M2.isi[i][j] = M.isi[i][M.LastIdxKol]
            }
            D2 = Matriks.DeterminanKofaktor(M2);
            Mhsl.isi[0][j] = D2/D1;
        }





        return Mhsl;
    }

    
}
