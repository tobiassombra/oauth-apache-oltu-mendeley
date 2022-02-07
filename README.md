# oauth-apache-oltu-mendeley
<h4>Aplicação que realiza autenticação Oauth 2.0 usando apache oltu para a plataforma Mendeley</h4>

Esta aplicação tem como objetivo realizar autenticação no padrão Oauth 2.0 usando Apache Oltu. Foi utilizada a plataforma Mendeley como referência, mas é possível aplicar o código em outras APIs que utilizam este padrão de autenticação. Todas as informações de entrada e saída são exibidas no console da IDE.

**NOTA**: A aplicação realiza apenas a autenticação Oauth. Se quiser realizar consultas na plataforma, será necessário desenvolver um código para este fim.

Esta aplicação usa o fluxo **Authorization Code Flow** para autenticação. Mais detalhes sobre o funcionamento deste fluxo podem ser vistos [aqui](https://dev.mendeley.com/reference/topics/authorization_auth_code.html).



**Variáveis e Métodos Importantes**

Segue algumas variáveis e métodos importantes para o bom funcionamento do código.

**Variáveis: Classe MendeleyAuthorizationCode**.

```
private final String CLIENT_ID
```

Responsável por guardar o CLIENT_ID da aplicação. Assim que for registrado no Mendeley, copie e cole o CLIENT_ID nesta variável.

```
private final String REDIRECT_URI
```

Ela foi inicializada com uma página local onde o código de autorização será exibido. Copie a informação contida nesta variável e cole na página de registro de aplicação do Mendeley.

```
private final String CLIENT_SECRET
```

É um código gerado ao registrar uma aplicação no portal do Mendeley. Copie o código gerado e cole nesta variável.



As demais variáveis contidas na aplicação não devem ser alteradas, pois pode comprometer o funcionamento do código.



**Métodos Importantes: Classe MendeleyAuthorizationCode**

```
public void oauthAuthorizationCode()
```

Esse método recebe uma requisição do tipo GET para gerar o código de acesso.

```
public void accessToken()
```

Realiza uma requisição tipo POST usando o código gerado para criar o token de acesso.

```
public void refreshToken()
```

Requisição tipo POST para renovar o token de acesso.



**Informações Importantes**

Para a aplicação funcionar com o Mendeley, é necessário seguir alguns passos:

1 - Acesse a plataforma do  [Mendeley para desenvolvedores](https://dev.mendeley.com/).

2 - Clique no botão **Sign In** mesmo que não tenha conta cadastrada. Na tela de login, insira seu e-mail para realizar o cadastro na plataforma. Se já tiver cadastro, vá para o próximo passo.

3 - Uma vez que o cadastro estiver concluído, volte a página do passo 1 e agora vá na opção **My Apps**.

4 - Aqui será necessário registrar uma aplicação. Use o app deste repositório:

4.1 - Application Name: Coloque o nome da aplicação que desejar

4.2 - Description: Faça uma breve descrição da aplicação.

4.3 - Redirect URL: Copie e cole o valor contido na variável **REDIRECT_URI** que está na classe **MendeleyAuthorizationCode**.

4.4 - Aceite os termos e condições e clique no botão Generate Secret. Copie e cole o código na variável **CLIENT_SECRET** que está na classe **MendeleyAuthorizationCode**. Depois, clique em subtmit

5 - Após a conclusão do registro da aplicação, aparecerá no lado esquerdo as informações dela. Copie o client ID da aplicação e cole na variável **CLIENT_ID** que está na classe **MendeleyAuthorizationCode**.



Depois que esses passos forem concluídos, execute a aplicação e siga todas as informações disponíveis no console.
