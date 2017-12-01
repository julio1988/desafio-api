# desafio-api

Teste Desenvolvedor Back-And
    
# Pré-requisitos

* JDK - última versão do Kit de desenvolvimento Java;
* Maven;
* IDE de sua preferencia (Spring Tool Suite 3.9.0);
* Postgress
* Spring Boot
* PostgreSQL 9.6

# Getting Started

Após a instalação do Postman é preciso exportar o arquivo localizado no diretório do projeto "postman/Desafio UDS.postman_collection.json", para que todas as requisições do caso de uso sejam carregadas e assim facilitar a apresentação da API.

Antes de iniciar a aplicação desafio-api é necessário que o gerenciamento de banco de dados objeto-relacional PostgreSQL seja iniciado, com as seguintes configurações;

* url: jdbc:postgresql://localhost:5432/desafio_uds
* username: postgres
* password:senha10 
Caso queira alterar a configuração do Banco de Dados deverá compilar o código novamente na IDE.

Para iniciar a aplicação sem a utilização de IDE, é preciso utilizar o maven "mvn package" para gerar o jar desafio-api-0.0.1-SNAPSHOT.jar e executar o comando 
"java -jar desafio-api-0.0.1-SNAPSHOT.jar" para iniciá-lo.

Para obter autorização do Spring Security nas requisições do postman para URLs "/pedidos" e "/produtos" é necessário adicinar no header da requisição as seguintes informações;

* Authorization Type Basic Auth 
* username = admin 
* password = admin

A url "/pessoas" não precisa de autorização, caso exporte o arquivo "Desafio UDS.postman_collection.json" no postman já estará configurado a autorização para as outras URLs.  

# Autor

  Julio Cesar Marques de Mendonça - Registrar Pedido
