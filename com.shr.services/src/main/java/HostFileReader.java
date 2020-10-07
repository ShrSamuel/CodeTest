import java.io.IOException;
import java.util.Collection;
import java.util.Date;

public interface HostFileReader {




    Collection<FileDataVo> readFile(Long date1, Long date2,String hostName) throws IOException;

    Collection<FileDataVo> readFileEndlessly(Date date1, Date date2);
}
