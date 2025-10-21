package entities;

public class DefineTipo {

    enum Tipo {
        PEQUENA,
        MEDIA,
        GRANDE
    }

    Tipo tipo;


    public void defineTipo(int quant) {

        if ((quant > 0) && (quant < 10)) {
            tipo = Tipo.PEQUENA;
        } else if ((quant > 10 && (quant < 5000))) {
            tipo = Tipo.MEDIA;
        } else {
            tipo = Tipo.GRANDE;
        }
    }

    public Tipo getTipo() {
        return tipo;
    }
}
