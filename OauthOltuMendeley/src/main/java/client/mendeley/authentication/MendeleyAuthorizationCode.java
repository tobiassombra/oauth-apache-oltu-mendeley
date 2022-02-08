package client.mendeley.authentication;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import java.util.Scanner;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

public class MendeleyAuthorizationCode {

    private final String ENDPOINT = "https://api.mendeley.com/oauth/authorize";
    private final String CLIENT_ID = "11884";
    private final String REDIRECT_URI = "http://localhost:63342/OauthOltuMendeley/OauthOltuMendeley.main/client/mendeley/url/index.html";
    private final String RESPONSE_TYPE = "code";
    private final String SCOPE = "all";
    private final String CLIENT_SECRET = "8Bf2DsCF7sRw7M0y";
    private final String TOKEN_LOCATION = "https://api.mendeley.com/oauth/token";
    private String code = "";
    private OAuthClientRequest request;
    OAuthAccessTokenResponse oauthResponse;

    private Scanner scan = new Scanner(System.in);

    public void oauthAuthorizationCode() throws OAuthSystemException, IOException, URISyntaxException {
        //Pega o code para gerar o token
        //Requisição tipo GET
        request = OAuthClientRequest
                .authorizationLocation(ENDPOINT)
                .setClientId(CLIENT_ID)
                .setRedirectURI(REDIRECT_URI)
                .setResponseType(RESPONSE_TYPE)
                .setScope(SCOPE)
                .buildQueryMessage();

        System.out.println("Tentando conectar " + request.getLocationUri());

        URI uri = new URI(request.getLocationUri());
        Desktop.getDesktop().browse(uri);
        System.out.println("NOTA: Se der erro 404 após a tela de login, atualize a página clicando no link e pressionando ENTER. Atualizar pelo f5 não funciona");
        System.out.println("Após fazer login, copie o código(?code=...) gerado no final do link que está no topo do navegador e cole em sua aplicação.");
        System.out.println("Insira o código gerado:");
        this.code = scan.nextLine();

    }

    public void accessToken() throws OAuthSystemException, OAuthProblemException {
        //Obtendo o access token
        //Requisição tipo POST
        request = OAuthClientRequest
                .tokenLocation(TOKEN_LOCATION)
                .setGrantType(GrantType.AUTHORIZATION_CODE)
                .setCode(this.code)
                .setRedirectURI(REDIRECT_URI)
                .setClientId(CLIENT_ID)
                .setClientSecret(CLIENT_SECRET)
                .buildBodyMessage();

        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        oauthResponse = oAuthClient.accessToken(request);

    }

    public void refreshToken() throws OAuthSystemException, OAuthProblemException {
        //Requisição do tipo POST para renovar o token de acesso
        request = OAuthClientRequest
                .tokenLocation(TOKEN_LOCATION)
                .setGrantType(GrantType.REFRESH_TOKEN)
                .setRefreshToken(oauthResponse.getRefreshToken())
                .setRedirectURI(REDIRECT_URI)
                .setClientId(CLIENT_ID)
                .setClientSecret(CLIENT_SECRET)
                .buildBodyMessage();

        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        oauthResponse = oAuthClient.accessToken(request);

    }

    public void exibirMensagem() {
        //Exibe mensagens básicas do token gerado
        System.out.println("Access Token: " + oauthResponse.getAccessToken());
        System.out.println("Expira em: " + oauthResponse.getExpiresIn());
        System.out.println("Refresh Token: " + oauthResponse.getRefreshToken());
        System.out.println("Tipo do Token: " + oauthResponse.getTokenType());
    }
}
