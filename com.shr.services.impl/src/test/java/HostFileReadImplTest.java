import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;


public class HostFileReadImplTest {

    HostFileReaderImpl hostFileReader;

    @Before
    public void setUp() {
        hostFileReader = HostFileReaderImpl.getInstance();
        CodeTestConfiguration config = Mockito.mock(CodeTestConfiguration.class);
        URL url = Thread.currentThread().getContextClassLoader().getResource("HostTestFile");
        Mockito.when(config.getConfig(ConstantUtils.FILE_TO_READ)).thenReturn(url.getPath());
        hostFileReader.setConfig(config);
    }


    @Test
    public void TestReadFile() throws IOException {
        Collection<FileDataVo> emptyResult = hostFileReader.readFile(16857546l, 48596789l, "noValid");
        assertTrue(emptyResult.isEmpty());
        Collection<FileDataVo> notEmptyResult = hostFileReader.readFile(1565647205599l, 1565647385964l, "Keimy");
        assertFalse(notEmptyResult.isEmpty());
    }



    @Test
    public void TestWholeReadFile() throws IOException {
        hostFileReader.readWholeFile();
    }


}
