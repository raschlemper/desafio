# Use uma imagem base com Tomcat
FROM tomcat:latest

# Copie o arquivo WAR gerado pelo Maven para o diretório webapps do Tomcat
COPY target/desafio.war /usr/local/tomcat/webapps/

# Adicione o comando stop ao script de entrada do Tomcat
RUN echo "catalina.sh stop" >> /usr/local/tomcat/bin/catalina-stop.sh && \
    chmod +x /usr/local/tomcat/bin/catalina-stop.sh

# Exponha a porta 8080 para acessar o aplicativo
EXPOSE 8080

# Defina um comando padrão a ser executado quando o contêiner for iniciado
RUN /usr/local/tomcat/bin/catalina-stop.sh