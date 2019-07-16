package org.bridgedb.tools.VoID;

import org.bridgedb.DataSource;
import org.bridgedb.IDMapperException;
import org.bridgedb.rdb.SimpleGdb;
import org.bridgedb.rdb.SimpleGdbFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class VoIDTool {
    private final File Db;
    private PrintStream out;
    private SimpleGdb Gdb;
    public VoIDTool(File f1) throws IDMapperException {
        this(f1, System.out);
    }
    public VoIDTool(File f1, OutputStream out){
        Db = f1;
        this.out = new PrintStream(out);
    }
    Map<DataSource, Integer> Set = new HashMap<DataSource, Integer>();

    public void initDatabases() throws IDMapperException{
        String url = "jdbc:derby:jar:(" + Db + ")database";
        Gdb = SimpleGdbFactory.createInstance("Database", url);
    }
    public void createVoid(String db1, String db2)throws IOException{
        FileWriter file = new FileWriter(".\\rdf.void", true);
        file.write("@prefix void:  <http://rdfs.org/ns/void#> .\n");
        file.write("@prefix pav:   <http://purl.org/pav/> .\n");
        file.write("@prefix freq:  <http://purl.org/cld/freq/> .\n");
        file.write("@prefix biopax: <http://www.biopax.org/release/biopax-level3.owl#> .\n");
        file.write("@prefix skos:  <http://www.w3.org/2004/02/skos/core#> .\n");
        file.write("@prefix rdfs:  <http://www.w3.org/2000/01/rdf-schema#> .\n");
        file.write("@prefix ncbigene: <http://identifiers.org/ncbigene/> .\n");
        file.write("@prefix pubmed: <http://www.ncbi.nlm.nih.gov/pubmed/> .\n");
        file.write("@prefix hmdb:  <http://identifiers.org/hmdb/> .\n");
        file.write("@prefix rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n");
        file.write("@prefix gpml:  <http://vocabularies.wikipathways.org/gpml#> .\n");
        file.write("@prefix wp:    <http://vocabularies.wikipathways.org/wp#> .\n");
        file.write("@prefix dcterms: <http://purl.org/dc/terms/> .\n");
        file.write("@prefix dcat:  <http://www.w3.org/ns/dcat#> .\n");
        file.write("@prefix prov:  <http://www.w3.org/ns/prov#> .\n");
        file.write("@prefix foaf:  <http://xmlns.com/foaf/0.1/> .\n");
        file.write("\n");
        switch(db1) {
            case "GMPL":
                file.write("<http://rdf.wikipathways.org/20190713/rdf/gpml> \n");
                file.write("        a                 dcat:Distribution ;\n");
                file.write("void:dataDump     <https://jenkins.bigcat.unimaas.nl/job/GPML%20to%20GPML%20+%20WP%20RDF/ws/WP2RDF/output/gpml/*zip*/gpml.zip> ;\n");
                file.write("dcat:downloadURL  <https://jenkins.bigcat.unimaas.nl/job/GPML%20to%20GPML%20+%20WP%20RDF/ws/WP2RDF/output/gpml/*zip*/gpml.zip> ;");

                break;
            case "WikiPathways":
                file.write("http://rdf.wikipathways.org/20190713/datasetDescription/");
                file.write("        a                           void:Dataset ;\n");
                file.write(" void:DatasetDescription ;\n");
                file.write(" dcterms:description  \"This is the VoID description for this WikiPathways RDF dataset created on 20190713.\"@en \n");
                file.write(" dcterms:issued \"2019-07-13T12:48:33.25Z\"^^xsd:dateTime ;\n");
                file.write(" dcterms:modified \"2019-07-13T12:48:33.25Z\"^^xsd:dateTime ;\n");
                file.write(" dcterms:title \"WikiPathways RDF VoID Description\"@en ;\n");
                file.write(" foaf:primaryTopic    <http://rdf.wikipathways.org/20190713/rdf/> .\n");
                file.write(" <http://rdf.wikipathways.org/20190713/linkset/wikidata>. \n");
                file.write("        a                    void:Linkset ;");
                file.write(" dcterms:title        \"WPRDF to Wikidata Linkset\"; \n");
                file.write(" void:linkPredicate   wp:bdbWikidata ;\n");
                file.write(" void:objectsTarget   <https://www.wikidata.org/entity/Q2013> ; \n");
                file.write(" void:subjectsTarget  <http://rdf.wikipathways.org/20190713/rdf/>. \n");
                break;
            case "HMDB":
                file.write();
                break;
            case "Chebi":
                file.write();
                break;
        }
    }
}
