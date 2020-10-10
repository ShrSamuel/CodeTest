import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.LinkedList;

public class OutputGeneratorTest {
    OutputGenerator outputGenerator;
    Collection<String> badFormatedCollection;
    Collection<String> formatedCollection;

    @Before
    public void setUp() {

        outputGenerator = OutputGeneratorImpl.getInstance();
        FileDataVoMapper fileDataVoMapper = FileDataVoMapper.getInstance();
        badFormatedCollection = this.generateBadCollection();
        formatedCollection = this.generateCollection();


    }

    @Test
    public void TestOutputGeneratorBad() {
        outputGenerator.generateOutput(badFormatedCollection);
        outputGenerator.generateOutput(formatedCollection);
    }


    private Collection<String> generateBadCollection() {
        Collection<String> col = this.generateCollection();
        String one = "15474878 testHost1";
        col.add(one);
        return col;
    }

    private Collection<String> generateCollection() {
        Collection<String> col = new LinkedList<>();
        String one = "15474878 Jil TestHost2";
        String two = "15474878 testHost1 TestHost2";
        String three = "15474878 testHost1 TesHost2";
        String four = "15474878 testHost1 Jil";
        String five = "15474878 testHost1 TestHost2";
        String six = "15474878 testHost1 TestHost2";
        String seven = "15474878 testHost1 TestHost2";
        col.add(one);
        col.add(two);
        col.add(three);
        col.add(four);
        col.add(five);
        col.add(six);
        col.add(seven);
        return col;

    }
}
