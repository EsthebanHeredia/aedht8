package uvg.edu.gt.vectorHeap;

public class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String sintoma;
    private char prioridad; // A (más urgente) a E (menos urgente)

    public Paciente(String nombre, String sintoma, char prioridad) {
        this.nombre = nombre;
        this.sintoma = sintoma;
        this.prioridad = prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSintoma() {
        return sintoma;
    }

    public char getPrioridad() {
        return prioridad;
    }

    @Override
    public int compareTo(Paciente otro) {
        // 'A' < 'B' < 'C' ... así que comparar letras directamente funciona
        return Character.compare(this.prioridad, otro.prioridad);
    }

    @Override
    public String toString() {
        return nombre + ", " + sintoma + ", " + prioridad;
    }
}