


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

public class EndlessHostFileReaderImpl extends Thread implements EndlessHostFileReader {
    private BufferedInputStream reader = null;
    private boolean runing = true;
    private OutputGeneratorImpl output = OutputGeneratorImpl.getInstance();
    private CodeTestConfiguration config = CodeTestConfiguration.getInstance();
    private static EndlessHostFileReaderImpl endlessHostFileReader;

    private EndlessHostFileReaderImpl() {
    }

    public static EndlessHostFileReaderImpl getInstance() {
        if (endlessHostFileReader==null);{
            endlessHostFileReader = new EndlessHostFileReaderImpl();

        }
        return endlessHostFileReader;
    }

    public void readFileEndlessly() {
        try {
            reader = new BufferedInputStream(new FileInputStream(config.getConfig(ConstantUtils.FILE_TO_READ)));
        } catch (FileNotFoundException fnfex) {
            System.out.println(fnfex.getMessage());
        }
        this.run();

    }


    public void run() {
        Collection<String> lines = new LinkedList<>();
        String line = new String();
        char x;
        long endTime = this.calculateEndTime();
        while (runing) {
            if (this.checkTime(endTime)) {

                output.generateOutput(lines);
                endTime = this.calculateEndTime();
                lines = new LinkedList<>();

            }
            try {
                if (reader.available() > 0) {
                    if ((x = (char) reader.read()) != '\n') {
                        line += x;
                    } else {
                        lines.add(line);
                        line = new String();
                    }


                } else try {
                    sleep(config.getConfigAsInteger(ConstantUtils.TIME_TO_SLEEP_READ_FILE));
                } catch (InterruptedException iex) {
                    System.out.println(iex.getMessage());

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    boolean checkTime(long endtime) {
        boolean response = true;
        Date date = new Date();
        long time = date.getTime();
        if (time <= endtime) {
            response = false;
        }
        return response;
    }

    long calculateEndTime() {
        Date date = new Date();
        long time = date.getTime();
        time += config.getConfigAsLong(ConstantUtils.READING_TIME);
        return time;
    }

    public void setConfig(CodeTestConfiguration config) {
        this.config = config;
    }

    public void setOutputGenerator(OutputGeneratorImpl output) {
        this.output = output;
    }

    public void stopThread() {
        runing = false;
    }

}
