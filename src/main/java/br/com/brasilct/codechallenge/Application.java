package br.com.brasilct.codechallenge;


import java.io.File;
import java.io.IOException;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableNeo4jRepositories (basePackages = "br.com.brasilct.codechallenge.repository")
@EnableTransactionManagement(mode=AdviceMode.ASPECTJ)
public class Application extends Neo4jConfiguration{
	
	Application() {
        setBasePackage("br.com.brasilct.codechallenge.model");
    }
	
	@Bean
	GraphDatabaseService graphDatabaseService() {
        return  new GraphDatabaseFactory().newEmbeddedDatabase("data/graphDatabase.db");// new EmbeddedGraphDatabase("graphDatabase.db");
    }

	
	public static void main(String[] args) throws IOException {
		FileUtils.deleteRecursively(new File("data/graphDatabase.db"));
		SpringApplication.run(Application.class, args);
	}
	
}