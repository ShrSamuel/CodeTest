public class FileDataVo {

    private long timeStamp;
    private String host1;
    private String host2;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getHost1() {
        return host1;
    }

    public void setHost1(String host1) {
        this.host1 = host1;
    }

    public String getHost2() {
        return host2;
    }

    public void setHost2(String host2) {
        this.host2 = host2;
    }

    @Override
    public String toString() {
        return "FileDataVo{" +
                "timeStamp=" + timeStamp +
                ", host1='" + host1 + '\'' +
                ", host2='" + host2 + '\'' +
                '}';
    }
}
