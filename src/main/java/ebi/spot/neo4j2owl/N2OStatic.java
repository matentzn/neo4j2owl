package ebi.spot.neo4j2owl;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

public class N2OStatic {
    private static OWLDataFactory df = OWLManager.getOWLDataFactory();
    public static final String OBONS = "http://purl.obolibrary.org/obo/";
    private static final String NODETYPE_NAMEDINDIVIDUAL = "Individual";
    private static final String NODETYPE_OWLCLASS = "Class";
    private static final String NODETYPE_OWLOBJECTPROPERTY = "ObjectProperty";
    private static final String NODETYPE_OWLANNOTATIONPROPERTY = "AnnotationProperty";
    private static final String NODETYPE_OWLDATAPROPERTY = "DataProperty";
    public static final String NODETYPE_PROPERTY = "Property";
    private static final String NODETYPE_OWLENTITY = "Entity";
    public static final String RELTYPE_SUBCLASSOF = "SUBCLASSOF";
    public static final String RELTYPE_INSTANCEOF = "INSTANCEOF";
    private static final String NEO4J_LABEL = "http://n2o.neo/property/nodeLabel";
    public static final String NEO4J_UNMAPPED_PROPERTY_PREFIX_URI = "http://n2o.neo/custom/";
    public static final String NEO4J_BUILTIN_PROPERTY_PREFIX_URI = "http://n2o.neo/property/";

    public static final String ATT_LABEL = "label";
    public static final String ATT_SAFE_LABEL = "sl";
    public static final String ATT_QUALIFIED_SAFE_LABEL = "qsl";
    public static final String ATT_CURIE = "curie";
    public static final String ATT_IRI = "iri";
    public static final String ATT_SHORT_FORM = "short_form";
    public static final String ATT_NODE_TYPE = "type";

    public static final String ANNOTATION_DELIMITER  = "~|~|~";
    public static final String ANNOTATION_DELIMITER_ESCAPED  = "\\~\\|\\~\\|\\~";

    public static OWLAnnotationProperty ap_neo4jLabel = OWLManager.getOWLDataFactory().getOWLAnnotationProperty(IRI.create(N2OStatic.NEO4J_LABEL));

    public static final String CYPHER_FAILED_TO_EXECUTE = "Cypher query did NOT complete successfully (ERROR): ";
    public static final String CYPHER_EXECUTED_SUCCESSFULLY = "Cypher finished successfully: ";
    public static final String CSV_EXTENSION = ".csv";

    public static String getNeoType(OWLEntity e) {
        if(e instanceof OWLClass) {
            return N2OStatic.NODETYPE_OWLCLASS;
        } else if(e instanceof OWLIndividual) {
            return N2OStatic.NODETYPE_NAMEDINDIVIDUAL;
        } else if(e instanceof OWLObjectProperty) {
            return N2OStatic.NODETYPE_OWLOBJECTPROPERTY;
        } else if(e instanceof OWLAnnotationProperty) {
            return N2OStatic.NODETYPE_OWLANNOTATIONPROPERTY;
        } else if(e instanceof OWLDataProperty) {
            return N2OStatic.NODETYPE_OWLDATAPROPERTY;
        }
        return "UnknownType";
    }

    public static boolean isPropertyType(OWLEntity e) {
        if(e instanceof OWLObjectProperty) {
            return true;
        } else if(e instanceof OWLAnnotationProperty) {
            return true;
        } else if(e instanceof OWLDataProperty) {
            return true;
        }
        return false;
    }

    public static boolean isN2OBuiltInProperty(String property) {
        return property.equals(ATT_LABEL) || property.equals(ATT_SAFE_LABEL) || property.equals(ATT_QUALIFIED_SAFE_LABEL) || property.equals(ATT_CURIE) || property.equals(ATT_SHORT_FORM) || property.equals(ATT_IRI) || property.equals(ATT_NODE_TYPE);
    }

    public static boolean isN2OBuiltInProperty(OWLEntity property) {
        return property.equals(abp(ATT_LABEL)) || property.equals(abp(ATT_SAFE_LABEL)) || property.equals(abp(ATT_QUALIFIED_SAFE_LABEL)) || property.equals(abp(ATT_CURIE)) || property.equals(abp(ATT_SHORT_FORM)) || property.equals(abp(ATT_IRI)) || property.equals(abp(ATT_NODE_TYPE));
    }

    private static OWLEntity abp(String s) {
        return df.getOWLAnnotationProperty(IRI.create(NEO4J_BUILTIN_PROPERTY_PREFIX_URI+s));
    }

    public static boolean isOWLPropertyTypeLabel(String label) {
        return label.equals(NODETYPE_NAMEDINDIVIDUAL) || label.equals(NODETYPE_OWLCLASS) || label.equals(NODETYPE_OWLOBJECTPROPERTY) || label.equals(NODETYPE_OWLANNOTATIONPROPERTY) || label.equals(NODETYPE_OWLDATAPROPERTY) || label.equals(NODETYPE_OWLENTITY);
    }
}
