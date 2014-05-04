Solução Proposta
================

Frameworks utilizados:

* Spring Boot - http://projects.spring.io/spring-boot/
* Sprin Data Neo4J - http://projects.spring.io/spring-data-neo4j/
* Neo4J - http://www.neo4j.org/

Após clonado:

1. mvn clean package
2. java -jar target/codechallenge-0.0.1-SNAPSHOT.war

Para testar no browser acesse:

1. http://localhost:8080/route/{STATION_ID_ORIGIN}/{STATION_ID_DESTINATIO}/
2. http://localhost:8080/route/{STATION_ID_ORIGIN}/{STATION_ID_DESTINATIO}/shortest/
3. http://localhost:8080/route/{STATION_ID_ORIGIN}/{STATION_ID_DESTINATIO}/shortest/totaltime

Exemplo:
1. http://localhost:8080/route/49/285/
2. http://localhost:8080/route/49/285/shortest/
3. http://localhost:8080/route/49/285/shortest/totaltime



code challenge
==============

Você foi contratado para construir um webservice REST para o metro de Londres. Você recebeu os arquivos em [1] para alimentar o banco de dados desse serviço. 

* Você deve criar um método para importar os arquivos existentes e uma estrutura para armazenar os dados, a importação deve ser feita apenas uma vez. 

Ao finalizar a primeira parte, as seguintes funcionalidades foram pedidas pelo time de **mobile** para que eles possam construir uma aplicação para auxiliar no deslocamento dos passageiros. 

1. Um método que liste um caminho (contendo todas as estações) qualquer entre a estação X e a estação Y 
2. Um método que liste o menor caminho (contendo todas as estações) (considerando a quantidade de paradas como requisito para o menor caminho) entre a estação X e a estação Y
3. Um método que calcule o tempo aproximando da viagem no item 2, considerando que ao passar de uma estação adjacente a próxima o passageiro gaste 3 minutos e ao trocar de linha gaste 12 minutos. 

Observações: 

* Tanto o desenho da arquitetura do serviço assim como os testes unitários fazem parte da resolução do teste. 
* O retorno do webservice REST deve ser em XML ou JSON 
* O código deve ser hospedado em um repositório forkado a partir desse no github. 
* Ao terminar o teste mande um email para techjobs@brasilct.com com seu curriculum e o link para seu repositório.


Recursos: 
[1] https://commons.wikimedia.org/wiki/London_Underground_geographic_maps/CSV (para facilitar os arquivos foram inseridos no respositório.) 

