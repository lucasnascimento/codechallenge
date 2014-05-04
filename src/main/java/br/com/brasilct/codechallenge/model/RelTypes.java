package br.com.brasilct.codechallenge.model;

import org.neo4j.graphdb.RelationshipType;

public enum RelTypes implements RelationshipType {
	KNOWS, LEADS_TO
}
