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

Após a instalação do Postman exportar o arquivo localizado no projeto postman/Desafio UDS.postman_collection.json, para 
que todas as requisições do caso de uso sejam carregadas e assim facilitar a apresentação da API.

Antes de iniciar a aplicação desafio-api é necessário que o gerenciamento de banco de dados objeto-relacional PostgreSQL seja iniciado, com as seguintes configurações;

* url: jdbc:postgresql://localhost:5432/desafio_uds
* username: postgres
* password:senha10 
Caso queira alterar a configuração do Banco de Dados deverá compilar o código novamente na IDE.

Para iniciar a apicaçãoa sem a utilização de IDE poderá utilizar um terminar do sistema operacional acessar o diretório onde se localiza a aplicação desafio-api e encontrar o jar desafio-api-0.0.1-SNAPSHOT,jar

Para obter autorização do Spring Security para as requisições das url "/pedidos" e "/produtos" é necessário adicinar no header da requisição as seguintes informações;

* Authorization Type Basic Auth 
* username = admin 
* password = admin

A url "/pessoas" não possui autorização, caso export o arquivo "Desafio UDS.postman_collection.json" no postman já estará configurado a autorização.  

# Autor

  Julio Cesar Marques de Mendonça - Registrar Pedido

