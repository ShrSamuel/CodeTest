import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

public class HostFileReaderImpl implements HostFileReader {

    private CodeTestConfiguration config = CodeTestConfiguration.getInstance();

    private static HostFileReaderImpl hostFileReader;

    private HostFileReaderImpl() {
    }

    public static HostFileReaderImpl getInstance() {
        if (hostFileReader == null) {
            hostFileReader = new HostFileReaderImpl();
        }
        return hostFileReader;
    }

    @Override
    public Collection<FileDataVo> readFile(Long timeStamp1, Long timeStamp2, String hostName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(config.getConfig(ConstantUtils.FILE_TO_READ)));
        Collection<String[]> data = new LinkedList<String[]>();
        Boolean breakloop = false;
        Long diference = (timeStamp2 + 5 * 60 * 1000);
        String line;
        while ((line = br.readLine()) != null && !breakloop) {
            String[] lineValues = line.split(" ");
            Long tsp = Long.parseLong(lineValues[0]);

            if (this.validateLine(lineValues, timeStamp1, timeStamp2, hostName)) {
                data.add(lineValues);
            }
            if (tsp >= timeStamp2 && tsp > diference) {
                breakloop = true;
            }
        }
        FileDataVoMapper mapper = FileDataVoMapper.getInstance();
        return mapper.toVo(data);

    }

    @Override
    public void readWholeFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(config.getConfig(ConstantUtils.FILE_TO_READ)));
        Collection<String> data = new LinkedList<String>();
        String line;
        while ((line = br.readLine()) != null) {
            data.add(line);
        }
        OutputGeneratorImpl outputGenerator = OutputGeneratorImpl.getInstance();
        outputGenerator.generateOutput(data);
    }


    private boolean validateLine(String[] values, Long d1, Long d2, String host1) {
        boolean response = false;

        Long tsp = Long.parseLong(values[0]);
        if ((d1 <= tsp) && (tsp <= d2) && host1.equals(values[1])) {
            response = true;
        }
        return response;
    }

    public void setConfig(CodeTestConfiguration config) {
        this.config = config;
    }


}
