public class Casilla {
    private boolean mina;
    private int minasAdyacentes;
    private boolean descubierta;
    private boolean marcada;

    public Casilla() {
        this.mina = false;
        this.minasAdyacentes = 0;
        this.descubierta = false;
        this.marcada = false;
    }

    public boolean tieneMina() {
        return mina;
    }

    public void colocarMina() {
        this.mina = true;
    }

    public int getMinasAdyacentes() {
        return minasAdyacentes;
    }

    public void setMinasAdyacentes(int minasAdyacentes) {
        this.minasAdyacentes = minasAdyacentes;
    }

    public boolean estaDescubierta() {
        return descubierta;
    }

    public void descubrir() {
        this.descubierta = true;
    }

    public void marcar() {
        this.marcada = true;
    }

    public boolean estaMarcada() {
        return marcada;
    }

    @Override
    public String toString() {
        if (marcada)
            return "F"; // "F" para bandera
        if (descubierta)
            return mina ? "*" : String.valueOf(minasAdyacentes);
        return "■"; // Celda cubierta
    }
}
