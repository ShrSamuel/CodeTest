import java.io.IOException;
import java.util.Collection;
import java.util.Date;

public interface HostFileReader {




    Collection<FileDataVo> readFile(Long date1, Long date2,String hostName) throws IOException;

    void readWholeFile() throws IOException;
}
