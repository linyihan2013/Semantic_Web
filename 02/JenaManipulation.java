import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

import java.io.*;

/**
 * Created by yihan on 2015/9/1 0001.
 */
public class JenaManipulation {
    public static void main(String[] argv) throws FileNotFoundException {
        String personURI =  "http://example.org/resource/";
        String predicate = "http://example.org/property/said";
        String object = "Hello World!";
        String fullName = "YihanLin";
        String friend = "Yingjie";

        Model model = ModelFactory.createDefaultModel();
        String fileName = "test.ttl";
        InputStream in = FileManager.get().open(fileName);
        if (in == null) {
            throw new IllegalArgumentException("File" + fileName + "not found.");
        }
        model.read(in, null, "TTL");
        Resource resource = model.createResource(personURI+friend);
        resource.addProperty(VCARD.NICKNAME, "Wingy").addProperty(VCARD.Given, "Yingjie").addProperty(VCARD.Family, "Wang");
        model.write(System.out);
        File file = new File("output.xml");
        model.write(new FileOutputStream(file), "RDF/XML");
    }
}
