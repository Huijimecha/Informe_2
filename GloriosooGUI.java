package gloriosoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Persona {
    String ced;
    String nom;
    double est;
    int edad;

    public Persona(String cedula, String nombre, double estatura, int age) {
        this.ced = cedula;
        this.nom = nombre;
        this.est = estatura;
        this.edad = age;
    }
}

public class GloriosooGUI extends JFrame {
    private JPanel panel;
    private JButton ordenarButton;

    public GloriosooGUI() {
        panel = new JPanel();
        ordenarButton = new JButton("Ordenar Personas");

        panel.add(ordenarButton);

        ordenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarPrograma();
            }
        });

        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void ejecutarPrograma() {
        // Obtener la cantidad de personas a registrar mediante un cuadro de diálogo
        String cantPersonasStr = JOptionPane.showInputDialog(this, "Ingrese la cantidad de personas a registrar:");
        int cantPersonas = Integer.parseInt(cantPersonasStr);

        Persona[] personas = new Persona[cantPersonas];

        for (int i = 0; i < cantPersonas; i++) {
            String ced = JOptionPane.showInputDialog(this, "Ingrese cedula de la persona " + (i + 1) + ":");
            String nom = JOptionPane.showInputDialog(this, "Ingrese el nombre de la persona " + (i + 1) + ":");
            String estStr = JOptionPane.showInputDialog(this, "Ingrese estatura de la persona (en milimetros, en el arreglo final aparecerá en metros) " + (i + 1) + ":");
            double est = Double.parseDouble(estStr);
            String edadStr = JOptionPane.showInputDialog(this, "Ingrese edad de la persona " + (i + 1) + ":");
            int edad = Integer.parseInt(edadStr);

            personas[i] = new Persona(ced, nom, est, edad);
        }

        String opcionOrdenamiento = JOptionPane.showInputDialog(this, "Como deseas ordenar los objetos? (estatura/edad):");

        String opcionMetodo = JOptionPane.showInputDialog(this, "Que metodo de ordenamiento deseas utilizar? (burbuja/merge):");

        if (opcionOrdenamiento.equals("estatura")) {
            if (opcionMetodo.equals("burbuja")) {
                bubbleSort(personas, opcionOrdenamiento);
            } else if (opcionMetodo.equals("merge")) {
                mergeSort(personas, 0, personas.length - 1, opcionOrdenamiento);
            } else {
                JOptionPane.showMessageDialog(this, "Opcion de metodo de ordenamiento no valida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (opcionOrdenamiento.equals("edad")) {
            if (opcionMetodo.equals("burbuja")) {
                bubbleSort(personas, opcionOrdenamiento);
            } else if (opcionMetodo.equals("merge")) {
                mergeSort(personas, 0, personas.length - 1, opcionOrdenamiento);
            } else {
                JOptionPane.showMessageDialog(this, "Opcion de metodo de ordenamiento no valida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Opcion de ordenamiento no valida", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Mostrar el resultado final en un cuadro de diálogo
        StringBuilder resultadoFinal = new StringBuilder("Arreglo Final:\n");
        for (Persona persona : personas) {
            resultadoFinal.append("Cedula: ").append(persona.ced).append(", Nombre: ").append(persona.nom).append(", Estatura: ").append(persona.est / 100).append(", Edad: ").append(persona.edad).append("\n");
        }

        JOptionPane.showMessageDialog(this, resultadoFinal.toString(), "Resultado Final", JOptionPane.INFORMATION_MESSAGE);
    }

    private void bubbleSort(Persona[] arr, String opcion) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (opcion.equals("estatura")) {
                    if (arr[j].est > arr[j + 1].est) {
                        Persona temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                } else if (opcion.equals("edad")) {
                    if (arr[j].edad > arr[j + 1].edad) {
                        Persona temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }
    }

    private void mergeSort(Persona[] arr, int l, int r, String opcion) {
        if (l < r) {
            int m = (l + r) / 2;

            mergeSort(arr, l, m, opcion);
            mergeSort(arr, m + 1, r, opcion);

            merge(arr, l, m, r, opcion);
        }
    }

    private void merge(Persona[] arr, int l, int m, int r, String opcion) {
        int n1 = m - l + 1;
        int n2 = r - m;

        Persona[] L = new Persona[n1];
        Persona[] R = new Persona[n2];

        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
        }

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (opcion.equals("estatura")) {
                if (L[i].est <= R[j].est) {
                    arr[k] = L[i];
                    i++;
                } else {
                    arr[k] = R[j];
                    j++;
                }
            } else if (opcion.equals("edad")) {
                if (L[i].edad <= R[j].edad) {
                    arr[k] = L[i];
                    i++;
                } else {
                    arr[k] = R[j];
                    j++;
                }
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GloriosooGUI());
    }
}
