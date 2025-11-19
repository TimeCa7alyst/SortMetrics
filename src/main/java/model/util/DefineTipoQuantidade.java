package model.util;

public class DefineTipoQuantidade {

    enum Quantidade {
        PEQUENA,
        MEDIA,
        GRANDE
    }

    Quantidade quantidade;

    public void defineTipo(int quant) {

        if ((quant > 0) && (quant < 10)) {
            quantidade = Quantidade.PEQUENA;
        } else if ((quant > 10 && (quant < 5000))) {
            quantidade = Quantidade.MEDIA;
        } else {
            quantidade = Quantidade.GRANDE;
        }
    }

    public Quantidade getTipoQuantidade() {
        return quantidade;
    }
}
