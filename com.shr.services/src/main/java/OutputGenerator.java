import java.util.Collection;

public interface OutputGenerator {
    public void generateOutput(Collection<String> linesRead);

    public void setFileDataVoMapper(FileDataVoMapper fileDataVoMapper);
}
