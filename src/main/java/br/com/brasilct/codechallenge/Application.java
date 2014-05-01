package br.com.brasilct.codechallenge;


import org.neo4j.kernel.EmbeddedGraphDatabase;
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
	
    @SuppressWarnings("deprecation")
	@Bean
    EmbeddedGraphDatabase graphDatabaseService() {
        return new EmbeddedGraphDatabase("graphDatabase.db");
    }
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}