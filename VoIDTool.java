package org.bridgedb.tools.VoID;

import org.bridgedb.DataSource;
import org.bridgedb.IDMapperException;
import org.bridgedb.rdb.SimpleGdb;
import org.bridgedb.rdb.SimpleGdbFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class VoIDTool {
    private PrintStream out;
    private final File oldDb;
    private final File newDb;
    private SimpleGdb oldGdb;
    private SimpleGdb newGdb;
    FileWriter file = new FileWriter(".\\rdf.void", true);

    public VoIDTool(File f1, File f2) throws IDMapperException, IOException {
        this(f1,f2, System.out);
    }
    public VoIDTool(File f1, File f2, OutputStream out) throws IOException {
        oldDb= f1;
        newDb= f2;
        this.out = new PrintStream(out);
    }
    Map<DataSource, Integer> Set = new HashMap<DataSource, Integer>();

    public void initDatabases() throws IDMapperException{
        String url = "jdbc:derby:jar:(" + oldDb+ ")database";
        oldGdb = SimpleGdbFactory.createInstance("db1", url);
        String url2 = "jdbc:derby:jar:(" + newDb + ")database";
        newGdb = SimpleGdbFactory.createInstance("db2", url2);
    }
    public void run()throws IDMapperException{
        initDatabases();
        createVoid();

    }
    public void createVoid(String db1, String db2)throws IOException{
        db1 = db1.toLowerCase();
        db2 = db2.toLowerCase();
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
            case "gmpl":
                file.write("<http://rdf.wikipathways.org/20190713/rdf/gpml> \n");
                file.write("        a                 dcat:Distribution ;\n");
                file.write("void:dataDump     <https://jenkins.bigcat.unimaas.nl/job/GPML%20to%20GPML%20+%20WP%20RDF/ws/WP2RDF/output/gpml/*zip*/gpml.zip> ;\n");
                file.write("dcat:downloadURL  <https://jenkins.bigcat.unimaas.nl/job/GPML%20to%20GPML%20+%20WP%20RDF/ws/WP2RDF/output/gpml/*zip*/gpml.zip> ;");

                break;
            case "wikipathways":
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
            case "hmdb":
                file.write();
                break;
            case "chebi":
                file.write("void:DatasetDescription ; \n");
                file.write("dcterms:title \"Chemical Entities of Biological Interest (ChEBI)\"@en ; \n");
                file.write("dcterms:description \"Chemical Entities of Biological Interest (ChEBI) is a freely available dictionary of molecular entities focused on &#39;small&#39; chemical compounds.\"@en ; \n");
                file.write("foaf:homepage <http://www.ebi.ac.uk/chebi/> ; \n");
                file.write("void:dataDump <ftp://ftp.ebi.ac.uk/pub/databases/chebi/ontology/chebi.owl> ; \n");
                file.write("dcterms:license <http://creativecommons.org/licenses/by-sa/3.0/> ; \n");
                break;
            case "chembl":
                file.write("void:DatasetDescription ; \n");
                file.write("dcterms:title \"The ChEMBL Database\" ;\n");
                file.write("dcterms:description \\\"ChEMBL is a database of bioactive drug-like small molecules, it contains 2-D structures, calculated properties (e.g. logP, Molecular Weight, Lipinski Parameters, etc.) and abstracted bioactivities (e.g. binding constants, pharmacology and ADMET data). The data is abstracted and curated from the primary scientific literature, and cover a significant fraction of the SAR and discovery of modern drugs.\\\" ;\");\n");
                file.write("foaf:page <ftp://ftp.ebi.ac.uk/pub/databases/chembl/> ;");
                file.write("dcterms:license <http://creativecommons.org/licenses/by-sa/3.0/> ;");
                file.write("void:dataDump <ftp://ftp.ebi.ac.uk/pub/databases/chembl/ChEMBL-RDF/20.0/chembl_20.0_targetrel.ttl.gz> .");
            case "uniprot":
                file.write();
        }
    }
}
