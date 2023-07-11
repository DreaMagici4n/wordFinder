import java.util.ArrayList;
import java.util.List;

public class Word {
    private String word;
    private List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();

    public String getWord()
    {
        return this.word;
    }

    public List<Ocorrencia> getOcorrencias()
    {
        return this.ocorrencias;
    }

    public void setWord (String word)
    {
        this.word = word;
    }
    
    public void addOcorrencia (Ocorrencia object)
    {
        this.ocorrencias.add(object);
    }
}