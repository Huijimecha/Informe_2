package gloriosoo;

import java.util.Scanner;

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

public class Gloriosoo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Ingrese la cantidad de personas a registrar:");
        int cantPersonas = input.nextInt();

        Persona[] personas = new Persona[cantPersonas];

        for (int i = 0; i < cantPersonas; i++) {
            System.out.println("Ingrese cedula de la persona " + (i + 1) + ":");
            String ced = input.next();
            System.out.println("Ingrese el nombre de la persona " + (i + 1) + ":");
            String nom = input.next();
            System.out.println("Ingrese estatura de la persona (en milimetros, en el arreglo final aparecerá en metros) " + (i + 1) + ":");
            double est = input.nextDouble();
            System.out.println("Ingrese edad de la persona " + (i + 1) + ":");
            int edad = input.nextInt();

            personas[i] = new Persona(ced, nom, est, edad);
        }

        System.out.println("Como deseas ordenar los objetos? (estatura/edad):");
        String opcionOrdenamiento = input.next();

        System.out.println("Que metodo de ordenamiento deseas utilizar? (burbuja/merge):");
        String opcionMetodo = input.next();

        if (opcionOrdenamiento.equals("estatura")) {
            if (opcionMetodo.equals("burbuja")) {
                bubbleSort(personas, opcionOrdenamiento);
            } else if (opcionMetodo.equals("merge")) {
                mergeSort(personas, 0, personas.length - 1, opcionOrdenamiento);
            } else {
                System.out.println("Opcion de metodo de ordenamiento no valida");
            }
        } else if (opcionOrdenamiento.equals("edad")) {
            if (opcionMetodo.equals("burbuja")) {
                bubbleSort(personas, opcionOrdenamiento);
            } else if (opcionMetodo.equals("merge")) {
                mergeSort(personas, 0, personas.length - 1, opcionOrdenamiento);
            } else {
                System.out.println("Opcion de metodo de ordenamiento no valida");
            }
        } else {
            System.out.println("Opcion de ordenamiento no valida");
        }

        System.out.println("\nArreglo Final:");
        for (Persona persona : personas) {
            System.out.println("Cedula: " + persona.ced + ", Nombre: " + persona.nom + ", Estatura: " + persona.est / 100 + ", Edad: " + persona.edad);
        }
    }

    public static void bubbleSort(Persona[] arr, String opcion) {
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

    public static void mergeSort(Persona[] arr, int l, int r, String opcion) {
        if (l < r) {
            int m = (l + r) / 2;

            mergeSort(arr, l, m, opcion);
            mergeSort(arr, m + 1, r, opcion);

            merge(arr, l, m, r, opcion);
        }
    }

    public static void merge(Persona[] arr, int l, int m, int r, String opcion) {
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
}
