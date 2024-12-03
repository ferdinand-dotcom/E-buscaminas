import java.util.Scanner;

public class Buscaminas {

    private static Tablero tablero;

    public static void main(String[] args) {
        tablero = new Tablero();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            tablero.mostrarTablero();
            System.out.println("Introduce las coordenadas de la casilla (A-J para filas, 1-10 para columnas): ");
            String input = scanner.nextLine();
            if (input.length() != 2) {
                System.out.println("Entrada inválida. Por favor ingresa dos caracteres.");
                continue;
            }
            char fila = input.charAt(0);
            int columna = Integer.parseInt(String.valueOf(input.charAt(1))) - 1;

            if (fila < 'A' || fila > 'J' || columna < 0 || columna > 9) {
                System.out.println("Coordenadas fuera de rango.");
                continue;
            }

            boolean victoria = tablero.descubrirCasilla(fila - 'A', columna);
            if (!victoria) {
                tablero.mostrarTablero();
                System.out.println("¡Has perdido! Has descubierto una mina.");
                break;
            }

            if (tablero.verificarVictoria()) {
                tablero.mostrarTablero();
                System.out.println("¡Felicidades! Has ganado el juego.");
                break;
            }
        }

        scanner.close();
    }
}
