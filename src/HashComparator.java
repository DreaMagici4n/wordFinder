import java.util.Comparator;

class HashComparator implements Comparator<Word> {
    @Override
    public int compare(Word word1, Word word2) {
        int numOcorrenciasWord1 = word1.ocorrencias.get(0).numOcorrencias;
        int numOcorrenciasWord2 = word2.ocorrencias.get(0).numOcorrencias;
        
        return Integer.compare(numOcorrenciasWord2, numOcorrenciasWord1);
    }
}