package entities;

public class DefineTipo {

    enum Tipo {
        PEQUENA,
        MEDIA,
        GRANDE
    }

    Tipo tipo;


    public void defineTipo(int quant) {

        if ((quant > 0) && (quant < 20)) {
            tipo = Tipo.PEQUENA;
        } else if ((quant > 20 && (quant < 400))) {
            tipo = Tipo.MEDIA;
        } else {
            tipo = Tipo.GRANDE;
        }
    }

    public Tipo getTipo() {
        return tipo;
    }
}
