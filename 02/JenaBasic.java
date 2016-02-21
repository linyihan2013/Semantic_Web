import org.apache.jena.rdf.model.*;
import java.io.FileNotFoundException;

/**
 * Created by yihan on 2015/9/1 0001.
 */
public class JenaBasic {
    public static void main(String[] argv) throws FileNotFoundException {
        String personURI =  "http://example.org/resource/";
        String predicate = "http://example.org/property/said";
        String object = "Hello World!";
        String fullName = "YihanLin";

        Model model = ModelFactory.createDefaultModel();
        Resource resource = model.createResource(personURI + fullName);
        Property property = model.createProperty(predicate);
        RDFNode node = model.createLiteral(object, "EN");

        model.add(resource, property, node);
        StmtIterator iterator = model.listStatements();

        Statement statement = iterator.nextStatement();
        Resource subject = statement.getSubject();
        Property property1 = statement.getPredicate();
        RDFNode object2 = statement.getObject();
        System.out.println(subject.toString());
        System.out.println(" "+property1.toString()+" "+object2.toString()+".");
    }
}
