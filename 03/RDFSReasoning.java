import org.apache.jena.base.Sys;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

/**
 * Created by yihan on 2015/9/6 0006.
 */
public class RDFSReasoning {
    public static void main(String[] argv) throws FileNotFoundException {
        Model model = ModelFactory.createDefaultModel();
        String fileName = "tourism.ttl";
        InputStream in = FileManager.get().open(fileName);
        if (in == null) {
            throw new IllegalArgumentException("File" + fileName + "not found.");
        }
        model.read(in, null, "TTL");

        StmtIterator stmtIterator = model.listStatements();
        List<Statement> list = new ArrayList<Statement>();
        while (stmtIterator.hasNext()) {
            Statement statement = stmtIterator.nextStatement();
            String name1 = statement.getSubject().getLocalName();
            String name2 = statement.getObject().toString();
            name2 = name2.substring(name2.lastIndexOf("/") + 1);

            if (!name1.equals("Museion") && !name2.equals("ChickenHut")) {
                list.add(statement);
            }
        }
        model.remove(list);
        model.write(System.out);
        File file = new File("new-triples.ttl");
        model.write(new FileOutputStream(file), "TTL");
    }
}
