

import java.io.IOException;
import java.util.Collection;

public class Main {

    public static void main(String[] args) throws IOException {

        try {
            int option = Integer.parseInt(args[0]);
            switch (option) {

                case 1:
                    HostFileReader hostFileReader = HostFileReaderImpl.getInstance();
                    hostFileReader.readWholeFile();
                    break;
                case 2:
                    EndlessHostFileReader endlessHostFileReader = EndlessHostFileReaderImpl.getInstance();
                    endlessHostFileReader.readFileEndlessly();
                    break;
                case 3:
                    executecase1(args);
                    break;
                default:
                    System.out.println("Not valid option");
                    break;
            }

            //    HostFileReader hostFileReader = HostFileReaderImpl.getInstance();
        } catch (Exception ex) {
            System.out.println("Not valid option, you should select 1 to read a complete file or 2 to read a file endlessly every hour 3 to get the cost conexsions between 2 timestamps");
        }
    }


    private static void executecase1(String[] args) {
        HostFileReader hostFileReader = HostFileReaderImpl.getInstance();
        Long timestamp1 = 0l;
        Long timestamp2 = 0l;
        if (args.length < 3) {
            System.out.println("ERROR: You must insert: timestamp 1, timestamp 2 and a hostname");

        } else {
            try {

                timestamp1 = Long.parseLong(args[0]);
                timestamp2 = Long.parseLong(args[1]);
                Collection<FileDataVo> fileDataCollection = hostFileReader.readFile(timestamp1, timestamp2, args[2]);


            } catch (Exception exception) {
                exception.getMessage();
            }
        }
    }
}