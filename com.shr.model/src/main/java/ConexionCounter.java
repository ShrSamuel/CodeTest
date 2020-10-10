public class ConexionCounter implements Comparable<Integer> {
    private Integer conexionsDone = 0;

    public void addOneConexion() {
        conexionsDone = conexionsDone + 1;
    }

    public int getConexionsDone() {
        return conexionsDone;
    }

    public int compareTo(Integer o) {
        return 0;
    }
}
