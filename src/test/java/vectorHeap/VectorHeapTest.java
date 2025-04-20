package uvg.edu.gt.vectorHeap;

import org.junit.Test;
import static org.junit.Assert.*;
import uvg.edu.gt.vectorHeap.VectorHeap;
import uvg.edu.gt.vectorHeap.Paciente;


public class VectorHeapTest {

    @Test
    public void testAddAndRemove() {
        VectorHeap<Paciente> heap = new VectorHeap<>();

        Paciente p1 = new Paciente("Juan", "dolor de cabeza", 'C');
        Paciente p2 = new Paciente("Ana", "fractura", 'A');
        Paciente p3 = new Paciente("Luis", "fiebre", 'B');

        heap.add(p1);
        heap.add(p2);
        heap.add(p3);

        assertEquals(p2, heap.remove()); // A
        assertEquals(p3, heap.remove()); // B
        assertEquals(p1, heap.remove()); // C
    }

    @Test
    public void testEmptyHeap() {
        VectorHeap<Paciente> heap = new VectorHeap<>();
        assertTrue(heap.isEmpty());
        assertNull(heap.remove());
    }

    @Test
    public void testPeek() {
        VectorHeap<Paciente> heap = new VectorHeap<>();

        Paciente p1 = new Paciente("Juan", "dolor de cabeza", 'C');
        Paciente p2 = new Paciente("Ana", "fractura", 'A');

        heap.add(p1);
        heap.add(p2);

        assertEquals(p2, heap.peek());
    }

    @Test
    public void testSize() {
        VectorHeap<Paciente> heap = new VectorHeap<>();

        assertEquals(0, heap.size());
        heap.add(new Paciente("Juan", "dolor de cabeza", 'C'));
        assertEquals(1, heap.size());
    }
}