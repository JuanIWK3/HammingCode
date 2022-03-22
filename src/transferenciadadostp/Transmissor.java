package transferenciadadostp;

import java.util.Random;
import java.util.logging.LogManager;

public class Transmissor {
    private String mensagem;

    public Transmissor(String mensagem) {
        this.mensagem = mensagem;
    }

    // convertendo um símbolo para "vetor" de boolean (bits)
    private boolean[] streamCaracter(char simbolo) {

        // cada símbolo da tabela ASCII é representado com 8 bits
        boolean bits[] = new boolean[8];

        // convertendo um char para int (encontramos o valor do mesmo na tabela ASCII)
        int valorSimbolo = (int) simbolo;
        int indice = 7;

        // convertendo cada "bits" do valor da tabela ASCII
        while (valorSimbolo >= 2) {
            int resto = valorSimbolo % 2;
            valorSimbolo /= 2;
            bits[indice] = (resto == 1);
            indice--;
        }
        bits[indice] = (valorSimbolo == 1);

        return bits;
    }

    // não modifique (seu objetivo é corrigir esse erro gerado no receptor)
    private void geradorRuido(boolean bits[]) {
        Random geradorAleatorio = new Random();

        // pode gerar um erro ou não..
        if (geradorAleatorio.nextInt(5) > 1) {
            int indice = geradorAleatorio.nextInt(8);
            bits[indice] = !bits[indice];
        }
    }

    private boolean[] dadoBitsHemming(boolean bits[]) {
        boolean[] hammingBits = new boolean[4];
        boolean[] bitsWithHamming = new boolean[12];

        // * Calcula o valor dos bits de hamming

        hammingBits[0] = bits[0] ^ bits[1] ^ bits[3] ^ bits[4] ^ bits[6];
        hammingBits[1] = bits[0] ^ bits[2] ^ bits[3] ^ bits[5] ^ bits[6];
        hammingBits[2] = bits[1] ^ bits[2] ^ bits[3] ^ bits[7];
        hammingBits[3] = bits[4] ^ bits[5] ^ bits[6] ^ bits[7];

        // * Colocar os bits de hamming e os bits originais nas suas posições

        bitsWithHamming[0] = hammingBits[0];
        bitsWithHamming[1] = hammingBits[1];
        bitsWithHamming[2] = bits[0];
        bitsWithHamming[3] = hammingBits[2];
        bitsWithHamming[4] = bits[1];
        bitsWithHamming[5] = bits[2];
        bitsWithHamming[6] = bits[3];
        bitsWithHamming[7] = hammingBits[3];
        bitsWithHamming[8] = bits[4];
        bitsWithHamming[9] = bits[5];
        bitsWithHamming[10] = bits[6];
        bitsWithHamming[11] = bits[7];

        // * Printa em binário

        System.out.println();
        System.out.println("Enviado: ");
        for (int i = 0; i < bitsWithHamming.length; i++) {
            System.out.print((bitsWithHamming[i]) ? 1 : 0);
        }
        System.out.println();

        return bitsWithHamming;
    }

    public void enviaDado(Receptor receptor) {
        for (int i = 0; i < this.mensagem.length(); i++) {
            boolean[] bits = streamCaracter(this.mensagem.charAt(i));

            boolean[] bitsHemming = dadoBitsHemming(bits);

            geradorRuido(bitsHemming);

            receptor.receberDadoBits(bitsHemming);
        }
    }
}
