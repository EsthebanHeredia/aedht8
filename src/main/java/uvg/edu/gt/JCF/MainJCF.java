package uvg.edu.gt.JCF;

import uvg.edu.gt.vectorHeap.Paciente;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class MainJCF {
    public static void main(String[] args) {
        PriorityQueue<Paciente> colaPacientes = new PriorityQueue<>();

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

        System.out.println("Orden de atención de pacientes (Java Collections):");
        while (!colaPacientes.isEmpty()) {
            System.out.println(colaPacientes.poll());
        }
    }
}
