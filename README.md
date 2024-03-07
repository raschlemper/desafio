# Desafio

O deploy da aplicação pode ser feito de 2 maneiras:

1 - Via Tomcat:

- Executar os comandos do maven:
  - mvn eclipse:eclipse  
  - mvn clean package
    
- Copiar o arquivo .war (target/desafio.war) para o a pasta webapps do tomcat (tomcat/webapps)

2 - Via Docker:

- Instalar o Docker

- Executar os comandos do maven:
  - mvn eclipse:eclipse  
  - mvn clean package

- Executar os comandos do Docker:
  - docker build -t desafio-imagem:latest .
  - docker run -d -p 8080:8080 desafio-imagem:latest
 

O acesso a aplicação é feito pelo navegador no seguinte URL:

- http://localhost:8080/desafio/

