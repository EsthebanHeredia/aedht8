// Esta clase se utilizó de forma adicional a las pruebas unitarias para probar su funcionamiento.

package uvg.edu.gt.vectorHeap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainVectorHeap {
    public static void main(String[] args) {
        VectorHeap<Paciente> colaPacientes = new VectorHeap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    String sintoma = partes[1].trim();
                    char prioridad = partes[2].trim().charAt(0);
                    Paciente paciente = new Paciente(nombre, sintoma, prioridad);
                    colaPacientes.add(paciente);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        System.out.println("Orden de atención de pacientes:");
        while (!colaPacientes.isEmpty()) {
            System.out.println(colaPacientes.remove());
        }
    }
}