package transferenciadadostp;

import java.util.Scanner;

public class TransferenciaDadosTP {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("informe o dado a ser transmitido: ");
        // criando receptor e transmissor com a informação a ser enviada
        Transmissor seuSmartphone = new Transmissor(in.nextLine());
        Receptor umServidorQualquer = new Receptor();

        // "enviando" a informação do "smartphone" ao "servidor"
        seuSmartphone.enviaDado(umServidorQualquer);

        // mensagem recebida no "servidor"
        System.out.println("Mensagem: " + umServidorQualquer.getMensagem());

        in.close();

    }
}
