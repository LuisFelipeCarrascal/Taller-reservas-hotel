import java.util.Scanner; // Importa la clase Scanner para manejar entradas del usuario

public class hotel { // Definición de la clase principal 'hotel'

    // Declaración de variables estáticas globales para el sistema de reservas
    static boolean[][] habitaciones = new boolean[3][5]; // Matriz para el estado de habitaciones (3 pisos, 5 habitaciones por piso)
    static String[] clientes = new String[15]; // Arreglo para almacenar los nombres de los clientes (máximo 15 clientes)
    static int[] nochesReservadas = new int[15]; // Arreglo para el número de noches reservadas por cliente
    static double[] totalPagar = new double[15]; // Arreglo para almacenar el total a pagar por cliente
    static final double PRECIO_POR_NOCHE = 25.0; // Precio fijo por noche
    static int contadorReservas = 0; // Contador para el número de reservas realizadas

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Crear un objeto Scanner para leer entradas del usuario
        boolean salir = false; // Variable para controlar el ciclo del menú principal

        while (!salir) { // Ciclo que se repite hasta que el usuario decida salir
            // Menú de opciones
            System.out.println("\n Hotel De Reservas ");
            System.out.println("1. Hacer reserva"); // Opción para hacer una reserva
            System.out.println("2. Cancelar reserva"); // Opción para cancelar una reserva
            System.out.println("3. Mostrar reporte de habitaciones"); // Opción para mostrar el estado de las habitaciones
            System.out.println("4. Salir"); // Opción para salir del programa
            System.out.print("Elija una opcion: "); // Mensaje para que el usuario elija una opción
            int opcion = scanner.nextInt(); // Leer la opción elegida por el usuario

            // Ejecutar la opción elegida
            switch (opcion) {
                case 1:
                    registrarReserva(scanner); // Llama al método para registrar una nueva reserva
                    break;
                case 2:
                    cancelarReserva(scanner); // Llama al método para cancelar una reserva
                    break;
                case 3:
                    mostrarReporte(); // Muestra el reporte de habitaciones y reservas
                    break;
                case 4:
                    salir = true; // Cambia el valor de 'salir' a verdadero para terminar el ciclo
                    break;
                default:
                    System.out.println("Opcion invalida."); // Mensaje si la opción ingresada es inválida
                    break;
            }
        }

        scanner.close(); // Cerrar el Scanner al final del programa
    }

    // Método para registrar una nueva reserva
    public static void registrarReserva(Scanner scanner) {
        // Pedir al usuario los detalles de la reserva
        System.out.print("Ingrese el nombre: "); // Mensaje para ingresar el nombre
        String nombre = scanner.next(); // Leer el nombre del cliente
        System.out.print("Ingrese el numero del piso (1 a 3): "); // Mensaje para ingresar el piso
        int piso = scanner.nextInt() - 1; // Ajustar a índice de arreglo (restar 1 para usar 0, 1 o 2)
        System.out.print("Ingrese el numero de habitacion (1 a 5): "); // Mensaje para ingresar el número de habitación
        int numHabitacion = scanner.nextInt() - 1; // Ajustar el número de habitación a índice (restar 1)
        System.out.print("Ingrese el numero de noches: "); // Mensaje para ingresar el número de noches
        int noches = scanner.nextInt(); // Leer el número de noches que desea reservar

        // Verificar que los índices sean válidos
        if (piso < 0 || piso >= habitaciones.length || numHabitacion < 0 || numHabitacion >= habitaciones[piso].length) {
            System.out.println("Piso o habitacion invalida."); // Mensaje si el piso o habitación están fuera de límites
            return; // Salir del método si hay un error
        }

        // Verificar si la habitación está disponible
        if (habitaciones[piso][numHabitacion]) {
            System.out.println("La habitacion no esta disponible."); // Mensaje si la habitación está ocupada
        } else {
            // Registrar la reserva si la habitación está libre
            habitaciones[piso][numHabitacion] = true; // Marcar la habitación como ocupada
            clientes[contadorReservas] = nombre; // Guardar el nombre del cliente
            nochesReservadas[contadorReservas] = noches; // Guardar el número de noches
            totalPagar[contadorReservas] = noches * PRECIO_POR_NOCHE; // Calcular el total a pagar
            contadorReservas++; // Incrementar el contador de reservas
            System.out.println("Reserva registrada."); // Mensaje de confirmación
        }
    }

    // Método para cancelar una reserva existente
    public static void cancelarReserva(Scanner scanner) {
        // Pedir al usuario los detalles de la habitación a cancelar
        System.out.print("Ingrese el numero del piso de la habitacion a cancelar (1 a 3): "); // Mensaje para ingresar el piso
        int piso = scanner.nextInt() - 1; // Ajustar a índice de arreglo (restar 1 para usar 0, 1 o 2)
        System.out.print("Ingrese el numero de habitacion a cancelar (1 a 5): "); // Mensaje para ingresar el número de habitación
        int numHabitacion = scanner.nextInt() - 1; // Ajustar el número de habitación a índice (restar 1)

        // Verificar que los índices sean válidos
        if (piso < 0 || piso >= habitaciones.length || numHabitacion < 0 || numHabitacion >= habitaciones[piso].length) {
            System.out.println("Piso o habitacion invalida."); // Mensaje si el piso o habitación están fuera de límites
            return; // Salir del método si hay un error
        }

        // Verificar si la habitación ya estaba disponible
        if (!habitaciones[piso][numHabitacion]) {
            // Omitir el mensaje que dice que la habitación ya está disponible
        } else {
            // Liberar la habitación si estaba ocupada
            habitaciones[piso][numHabitacion] = false; // Marcar la habitación como libre
            System.out.println("Reserva cancelada. Habitacion liberada."); // Mensaje de confirmación
        }
    }

    // Método para mostrar el reporte de habitaciones y reservas
    public static void mostrarReporte() {
        int ocupadas = 0; // Contador de habitaciones ocupadas
        int disponibles = 0; // Contador de habitaciones disponibles

        // Recorrer la matriz de habitaciones y mostrar su estado
        System.out.println("\n Habitaciones ocupadas y disponibles: "); // Título del reporte
        for (int i = 0; i < habitaciones.length; i++) { // Recorre los pisos
            for (int j = 0; j < habitaciones[i].length; j++) { // Recorre las habitaciones de cada piso
                int numeroHabitacion = (i + 1) * 100 + (j + 1); // Generar número de habitación (por ejemplo, 101, 102, etc.)
                if (habitaciones[i][j]) {
                    ocupadas++; // Incrementa el contador de habitaciones ocupadas
                    System.out.println("Habitacion " + numeroHabitacion + " esta ocupada."); // Mensaje para habitación ocupada
                } else {
                    disponibles++; // Incrementa el contador de habitaciones disponibles
                    System.out.println("Habitacion " + numeroHabitacion + " esta disponible."); // Mensaje para habitación disponible
                }
            }
        }

        // Mostrar el resumen de las reservas registradas
        System.out.println("\n--- Elementos de las reservas ---"); // Título del resumen de reservas
        for (int i = 0; i < contadorReservas; i++) { // Recorre las reservas realizadas
            // Mostrar información sobre cada reserva
            System.out.println("Cliente: " + clientes[i] + ", Noches: " + nochesReservadas[i] + ", Total a pagar: $" + totalPagar[i]);
        }
    }
}

// Luis Felipe Carrascal Ortiz 192391
// Yhoryi David Carrascal Jaimes 192406