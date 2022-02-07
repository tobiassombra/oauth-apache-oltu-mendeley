package com.mendeley.authentication;

import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import java.util.Scanner;
/**
 *
 * @author tobiassombra
 */
public class AuthenticationCode {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws OAuthSystemException, IOException, URISyntaxException, OAuthProblemException {
        Scanner entrada = new Scanner(System.in);
        MendeleyAuthorizationCode aut = new MendeleyAuthorizationCode();

        aut.oauthAuthorizationCode();
        aut.accessToken();
        aut.exibirMensagem();

        System.out.println("Digite 1 para testar a renovação do token:");
        int op = entrada.nextInt();
        if (op == 1) {
            System.out.println("Tentando renovar o token");
            aut.refreshToken();
            aut.exibirMensagem();
        }
    }
}
