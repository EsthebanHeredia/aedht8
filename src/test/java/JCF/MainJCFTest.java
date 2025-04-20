package uvg.edu.gt.JCF;

import uvg.edu.gt.vectorHeap.Paciente;
import org.junit.Test;

import java.util.PriorityQueue;

import static org.junit.Assert.*;

public class MainJCFTest {

    @Test
    public void testAddAndPoll() {
        PriorityQueue<Paciente> cola = new PriorityQueue<>();

        Paciente p1 = new Paciente("Juan", "dolor de cabeza", 'C');
        Paciente p2 = new Paciente("Ana", "fractura", 'A');
        Paciente p3 = new Paciente("Luis", "fiebre", 'B');

        cola.add(p1);
        cola.add(p2);
        cola.add(p3);

        assertEquals(p2, cola.poll()); // A
        assertEquals(p3, cola.poll()); // B
        assertEquals(p1, cola.poll()); // C
    }

    @Test
    public void testPeek() {
        PriorityQueue<Paciente> cola = new PriorityQueue<>();

        Paciente p1 = new Paciente("Carlos", "mareo", 'D');
        Paciente p2 = new Paciente("Sofía", "herida", 'B');

        cola.add(p1);
        cola.add(p2);

        assertEquals(p2, cola.peek());
    }

    @Test
    public void testIsEmptyAndSize() {
        PriorityQueue<Paciente> cola = new PriorityQueue<>();

        assertTrue(cola.isEmpty());
        cola.add(new Paciente("Pedro", "dolor", 'C'));
        assertEquals(1, cola.size());
    }
}