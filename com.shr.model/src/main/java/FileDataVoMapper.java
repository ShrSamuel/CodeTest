
import Exceptions.FileParserException;

import java.util.Collection;
import java.util.LinkedList;

public class FileDataVoMapper {


    private static FileDataVoMapper mapper;

    private FileDataVoMapper() {
    }

    public static FileDataVoMapper getInstance() {
        if (mapper == null) {
            mapper = new FileDataVoMapper();
        }
        return mapper;
    }

    public Collection<FileDataVo> toVo(Collection<String[]> data) {
        Collection<FileDataVo> response = new LinkedList<FileDataVo>();
        for (String[] line : data) {
            response.add(this.toVo(line));
        }
        return response;
    }

    public FileDataVo toVo(String[] data) throws ArrayIndexOutOfBoundsException {
        FileDataVo response = new FileDataVo();
        try {
            Long tsp = Long.parseLong(data[0]);
            response.setTimeStamp(tsp);
            response.setHost1(data[1]);
            response.setHost2(data[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FileParserException();
        }
        return response;
    }

    public Collection<FileDataVo> StringToVO(Collection<String> lines) {
        Collection<FileDataVo> response = new LinkedList<FileDataVo>();
        for (String line : lines) {
            try {
                response.add(this.StringToVO(line));
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("bad line badly formatted ");
            }
        }
        return response;
    }

    public FileDataVo StringToVO(String line) throws FileParserException {
        String[] line_value = null;

        line_value = line.split(" ");


        return toVo(line_value);
    }
}