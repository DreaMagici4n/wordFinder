public class Ocorrencia {
    private String filePath;
    private int numOcorrencias = 0;

    public String getFilePath ()
    {
        return this.filePath;
    }

    public int getNumOcorrencia ()
    {
        return this.numOcorrencias;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public void setNumOcorrencias (int numOcorrencias)
    {
        this.numOcorrencias = numOcorrencias;
    }
    
    public void incremmentOcorrencia ()
    {
        this.numOcorrencias++;
    }
}
