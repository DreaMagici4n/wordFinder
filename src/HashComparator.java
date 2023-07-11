import java.util.Comparator;

class HashComparator implements Comparator<Ocorrencia> {
    @Override
    public int compare(Ocorrencia word1, Ocorrencia word2)
    {
        return Integer.compare(word2.getNumOcorrencia(), word1.getNumOcorrencia());
    }
}