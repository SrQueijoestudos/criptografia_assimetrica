import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;
import java.util.Scanner;

public class CriptografiaRSA {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        // Gera o par de chaves (pública e privada)
        KeyPairGenerator gerador = KeyPairGenerator.getInstance("RSA");
        gerador.initialize(2048);
        KeyPair parDeChaves = gerador.generateKeyPair();

        PublicKey chavePublica = parDeChaves.getPublic();
        PrivateKey chavePrivada = parDeChaves.getPrivate();

        // Usuário digita a mensagem
        System.out.print("Digite a mensagem que deseja criptografar: ");
        String mensagem = scanner.nextLine();

        // Criptografa usando a chave pública
        Cipher cifra = Cipher.getInstance("RSA");
        cifra.init(Cipher.ENCRYPT_MODE, chavePublica);
        byte[] mensagemCriptografada = cifra.doFinal(mensagem.getBytes());

        // Descriptografa usando a chave privada
        cifra.init(Cipher.DECRYPT_MODE, chavePrivada);
        byte[] mensagemDescriptografada = cifra.doFinal(mensagemCriptografada);

        // Exibe os resultados
        System.out.println("\n===== RESULTADO =====");
        System.out.println("Mensagem original: " + mensagem);
        System.out.println("Mensagem criptografada:");
        System.out.println(Base64.getEncoder().encodeToString(mensagemCriptografada));
        System.out.println("Mensagem descriptografada: " + new String(mensagemDescriptografada));

        scanner.close();
    }
}