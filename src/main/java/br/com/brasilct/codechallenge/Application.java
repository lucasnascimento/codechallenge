package br.com.brasilct.codechallenge;


import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableNeo4jRepositories
public class Application extends Neo4jConfiguration{
	
	@Bean
	GraphDatabaseService graphDatabaseService() {
        return  new GraphDatabaseFactory().newEmbeddedDatabase("data/graphDatabase.db");// new EmbeddedGraphDatabase("graphDatabase.db");
    }
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}