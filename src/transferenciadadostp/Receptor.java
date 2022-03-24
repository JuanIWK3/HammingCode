package transferenciadadostp;

import java.util.ArrayList;

public class Receptor {

    // mensagem recebida pelo transmissor
    private String mensagem;

    public Receptor() {
        // mensagem vazia no inicio da execução
        this.mensagem = "";
    }

    public String getMensagem() {
        return mensagem;
    }

    private void decodificarDado(boolean bits[]) {
        int codigoAscii = 0;
        int expoente = bits.length - 1;

        // converntendo os "bits" para valor inteiro para então encontrar o valor tabela
        // ASCII
        for (int i = 0; i < bits.length; i++) {

            if (bits[i]) {
                codigoAscii += Math.pow(2, expoente);
            }
            expoente--;
        }

        System.out.println((char) codigoAscii);

    }

    private void decoficarDadoHemming(boolean bits[]) {
        boolean[] originalBits = new boolean[8];
        boolean p0 = false;
        boolean p1 = false;
        boolean p2 = false;
        boolean p3 = false;

        // * Printa em binário

        // System.out.println("Recebido: ");
        // for (int i = 0; i < bits.length; i++) {
        // System.out.print((bits[i]) ? 1 : 0);
        // }
        // System.out.println();

        // * Checa erros

        if (bits[0] != bits[2] ^ bits[4] ^ bits[6] ^ bits[8] ^ bits[10]) {
            p0 = true;
        }
        if (bits[1] != bits[2] ^ bits[5] ^ bits[6] ^ bits[9] ^ bits[10]) {
            p1 = true;
        }
        if (bits[3] != bits[4] ^ bits[5] ^ bits[6] ^ bits[11]) {
            p2 = true;
        }
        if (bits[7] != bits[8] ^ bits[9] ^ bits[10] ^ bits[11]) {
            p3 = true;
        }

        // * Coloca os bits da mensagem nas suas posições

        int count = 0;

        for (int i = 0; i < bits.length; i++) {
            if (i != 0 && i != 1 && i != 3 && i != 7) {
                originalBits[count] = bits[i];
                count++;
            }            
        }

        // Hard Coded
        // originalBits[0] = bits[2];
        // originalBits[1] = bits[4];
        // originalBits[2] = bits[5];
        // originalBits[3] = bits[6];
        // originalBits[4] = bits[8];
        // originalBits[5] = bits[9];
        // originalBits[6] = bits[10];
        // originalBits[7] = bits[11];

        if (!p0 && !p1 && !p2 && !p3) {
            // System.out.println("Nao foram detectados erros");
        } else {
            // System.out.print("Simbolo Recebido: ");
            // decodificarDado(originalBits);
        }

        // * Corrige os Erros

        if (p0 && p1 && p2) {
            // System.out.println("erro no bit da posicao 6");
            bits[6] = !bits[6];
        } else if (p0 && p1 && p3) {
            // System.out.println("erro no bit da posicao 10");
            bits[10] = !bits[10];
        } else if (p0 && p1) {
            // System.out.println("erro no bit da posicao 2");
            bits[2] = !bits[2];
        } else if (p1 && p2) {
            // System.out.println("erro no bit da posicao 5");
            bits[5] = !bits[5];
        } else if (p2 && p3) {
            // System.out.println("erro no bit da posicao 11");
            bits[11] = !bits[11];
        } else if (p0 && p3) {
            // System.out.println("erro no bit da posicao 8");
            bits[8] = !bits[8];
        } else if (p0 && p2) {
            // System.out.println("erro no bit da posicao 4");
            bits[4] = !bits[4];
        } else if (p0) { // * Nao afeta o simbolo
            // System.out.println("erro no bit da posicao 3");
            bits[3] = !bits[3];
        } else if (p1) { // * Nao afeta o simbolo
            // System.out.println("erro no bit da posicao 1");
            bits[1] = !bits[1];
        } else if (p2) { // * Nao afeta o simbolo
            // System.out.println("erro no bit da posicao 3");
            bits[3] = !bits[3];
        } else if (p3) { // * Nao afeta o simbolo
            // System.out.println("erro no bit da posicao 7");
            bits[7] = !bits[7];
        }

        // * Coloca os bits da mensagem nas suas posições

        originalBits[0] = bits[2];
        originalBits[1] = bits[4];
        originalBits[2] = bits[5];
        originalBits[3] = bits[6];
        originalBits[4] = bits[8];
        originalBits[5] = bits[9];
        originalBits[6] = bits[10];
        originalBits[7] = bits[11];

        // System.out.print("Simbolo: ");
        // decodificarDado(originalBits);

        // * Printa em binário

        // System.out.println("Corrigido: ");
        // for (int i = 0; i < bits.length; i++) {
        // System.out.print((bits[i]) ? 1 : 0);
        // }
        // System.out.println();
        // System.out.println();

        int codigoAscii = 0;
        int expoente = originalBits.length - 1;

        // * Convertendo os "bits" para valor inteiro para então encontrar o valor
        // * tabela ASCII

        for (int i = 0; i < originalBits.length; i++) {
            if (originalBits[i]) {
                codigoAscii += Math.pow(2, expoente);
            }
            expoente--;
        }

        // * Concatenando cada simbolo na mensagem original

        this.mensagem += (char) codigoAscii;
    }

    public void receberDadoBits(boolean bits[]) {
        decoficarDadoHemming(bits);
    }
}
