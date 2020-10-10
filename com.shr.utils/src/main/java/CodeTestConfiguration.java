import javax.security.auth.login.Configuration;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Code test configuration.
 */
public  class CodeTestConfiguration {


    private static CodeTestConfiguration ConfigInstance;
    private static final Map<String, String> ConfigurationMap = new HashMap<String, String>();


    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CodeTestConfiguration getInstance() {

        if (ConfigInstance == null) {
            ConfigInstance = new CodeTestConfiguration();
            try {
                loadConfig();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return ConfigInstance;
    }

    private static void loadConfig() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(ConstantUtils.CONFIGURATION_ROUTE));
        String x;
        while ((x = br.readLine()) != null) {
            String[] x_value = x.split(";");
            ConfigurationMap.put(x_value[0], x_value[1]);
        }

    }

    /**
     * Gets config.
     *
     * @param config the config
     * @return the config
     */
    public String getConfig(String config) {
        return ConfigurationMap.get(config);
    }

    /**
     * Gets config as long.
     *
     * @param config the config
     * @return the config as long
     */
    public Long getConfigAsLong(String config) {
        return Long.parseLong(ConfigurationMap.get(config));
    }

    /**
     * Gets config as integer.
     *
     * @param config the config
     * @return the config as integer
     */
    public Integer getConfigAsInteger(String config) {
        return Integer.parseInt(ConfigurationMap.get(config));
    }


}
