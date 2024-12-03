import java.util.Random;

public class Tablero {
    private static final int FILAS = 10;
    private static final int COLUMNAS = 10;
    private Casilla[][] casillas = new Casilla[FILAS][COLUMNAS];
    private int minasRestantes;

    public Tablero() {
        // Inicializar las casillas
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                casillas[i][j] = new Casilla();
            }
        }
        minasRestantes = 10; // Número de minas
        colocarMinas();
        calcularMinasAdyacentes();
    }

    // Colocar minas aleatoriamente en el tablero
    private void colocarMinas() {
        Random random = new Random();
        int minasColocadas = 0;
        while (minasColocadas < minasRestantes) {
            int fila = random.nextInt(FILAS);
            int columna = random.nextInt(COLUMNAS);
            if (!casillas[fila][columna].tieneMina()) {
                casillas[fila][columna].colocarMina();
                minasColocadas++;
            }
        }
    }

    // Calcular el número de minas adyacentes para cada casilla
    private void calcularMinasAdyacentes() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (!casillas[i][j].tieneMina()) {
                    int minasAdyacentes = 0;
                    for (int fila = -1; fila <= 1; fila++) {
                        for (int col = -1; col <= 1; col++) {
                            if (i + fila >= 0 && i + fila < FILAS && j + col >= 0 && j + col < COLUMNAS) {
                                if (casillas[i + fila][j + col].tieneMina()) {
                                    minasAdyacentes++;
                                }
                            }
                        }
                    }
                    casillas[i][j].setMinasAdyacentes(minasAdyacentes);
                }
            }
        }
    }

    public Casilla obtenerCasilla(int fila, int columna) {
        return casillas[fila][columna];
    }

    public boolean descubrirCasilla(int fila, int columna) {
        Casilla casilla = casillas[fila][columna];
        if (casilla.estaDescubierta() || casilla.estaMarcada())
            return false;
        casilla.descubrir();
        if (casilla.tieneMina()) {
            return false; // Juego terminado si hay mina
        }
        if (casilla.getMinasAdyacentes() == 0) {
            // Si no hay minas adyacentes, descubrir casillas adyacentes
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (fila + i >= 0 && fila + i < FILAS && columna + j >= 0 && columna + j < COLUMNAS) {
                        descubrirCasilla(fila + i, columna + j);
                    }
                }
            }
        }
        return true;
    }

    public boolean verificarVictoria() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (!casillas[i][j].tieneMina() && !casillas[i][j].estaDescubierta()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void mostrarTablero() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(casillas[i][j] + " ");
            }
            System.out.println();
        }
    }
}
