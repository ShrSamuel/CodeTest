import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

public class HostFileReaderImpl implements HostFileReader {


    public Collection<FileDataVo> readFile(Long date1, Long date2, String hostName) throws IOException {
        this.test(date1, date2);
        return null;
    }

    public Collection<FileDataVo> readFileEndlessly(Date date1, Date date2) {
        return null;
    }

    private void test(Long dataTime1, Long time2) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Samu\\Desktop\\DOCUMENTACION COCHE NUEVO\\input-file-10000.txt"));
        Collection<String[]> data = new LinkedList<String[]>();
        Boolean breakloop = false;
        Long diference = (time2 + 5 * 60 * 1000);
        String x;
        while ( (x = br.readLine() )!= null && !breakloop) {
           //it will omit first line Array x
            //it return second line as a string
            String[] x_value = x.split(" "); //You can parse string array into int.
            Long tsp = Long.parseLong(x_value[0]);

            if (this.validateLine(x_value, dataTime1, time2)) {
                data.add(x_value);
            }

            if (tsp >= time2 && tsp > diference) {
                breakloop = true;
            }
        }

        Collection<FileDataVo> fileDataVos = FileDataVoParser.getInstance().toVo(data);
        System.out.println(fileDataVos.toString());
    }

    private boolean validateLine(String[] values, Long d1, Long d2) {
        boolean response = false;

        Long tsp = Long.parseLong(values[0]);
        if ((d1 <= tsp) && (tsp <= d2)) {
            response = true;
        }
        return response;

    }


}
