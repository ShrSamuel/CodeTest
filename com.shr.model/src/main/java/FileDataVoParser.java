import java.util.Collection;
import java.util.LinkedList;

public class FileDataVoParser {

    private static FileDataVoParser instance = new FileDataVoParser();


    public static FileDataVoParser getInstance() {
        return instance;
    }


    public Collection<FileDataVo> toVo(Collection<String[]> data) {
        Collection<FileDataVo> response = new LinkedList<FileDataVo>();
        for (String[] line : data) {
            FileDataVo fileDataVo = new FileDataVo();
            Long tsp = Long.parseLong(line[0]);
            fileDataVo.setTimeStamp(tsp);
            fileDataVo.setHost1(line[1]);
            fileDataVo.setHost2(line[2]);
            response.add(fileDataVo);
        }
        return response;
    }
}
