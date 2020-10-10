

import Exceptions.FileParserException;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;


/**
 * The type Output generator.
 */
public class OutputGeneratorImpl implements OutputGenerator {

    private static FileDataVoMapper fileDataVoMapper;
    private static OutputGeneratorImpl outputGenerator;

    /**
     * Instantiates a new Output generator.
     */
    private OutputGeneratorImpl() {

        fileDataVoMapper.getInstance();
    }

    public static OutputGeneratorImpl getInstance() {
        if (outputGenerator == null) {
            outputGenerator = new OutputGeneratorImpl();
        }
        return outputGenerator;
    }


    /**
     * Generate output.
     *
     * @param linesRead the lines read
     */
    public void generateOutput(Collection<String> linesRead) {
        HashSet<String> conectedToHost = new LinkedHashSet<>();
        HashSet<String> conectedFromHost = new LinkedHashSet<>();
        HashMap<String, ConexionCounter> hostConexions = new HashMap<>();
        String hostToview = CodeTestConfiguration.getInstance().getConfig(ConstantUtils.HOST_TO_READ);
        for (String line : linesRead) {
            try {
                FileDataVo data = fileDataVoMapper.StringToVO(line);

                if (data.getHost1().equalsIgnoreCase(hostToview)) {
                    conectedToHost.add(data.getHost2());
                }
                if (data.getHost2().equalsIgnoreCase(hostToview)) {
                    conectedFromHost.add(data.getHost1());
                }
                this.addHostConexion(hostConexions, data.getHost1());
                this.addHostConexion(hostConexions, data.getHost2());
            } catch (FileParserException ex) {

            }
        }

        this.showOutputs(conectedToHost, conectedFromHost, hostConexions);

    }


    private void addHostConexion(HashMap<String, ConexionCounter> hostConexions, String host) {
        if (!hostConexions.containsKey(host)) {
            hostConexions.put(host, new ConexionCounter());
            hostConexions.get(host).addOneConexion();
        }
    }

    private void showOutputs(HashSet<String> conectedToHost, HashSet<String> conectedFromHost, HashMap<String, ConexionCounter> hostConexions) {
        String mostConectedHost = this.getMostConectedHost(hostConexions);
        String hostReaded = CodeTestConfiguration.getInstance().getConfig(ConstantUtils.HOST_TO_READ);
        System.out.println("Conexions made To " + hostReaded + " in the last hour: ");
        System.out.println(conectedToHost.toString());
        System.out.println("Conexions made From " + hostReaded + " in the last hour: ");
        System.out.println(conectedFromHost.toString());
        System.out.println("Most conected host in the las hour " + mostConectedHost);

    }

    private String getMostConectedHost(HashMap<String, ConexionCounter> hostConexions) {
        String host = hostConexions.entrySet().stream().max(
                (entry1, entry2) -> entry1.getValue().getConexionsDone() > entry2.getValue().getConexionsDone() ? 1 : -1).get().getKey();
        return host;
    }

    public void setFileDataVoMapper(FileDataVoMapper fileDataVoMapper) {
        this.fileDataVoMapper = fileDataVoMapper;
    }
}
