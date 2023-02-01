import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class XMLGenerator {
    public XMLGenerator() {
    }

    public void formatXML(String path, KeyValueList kvl, ArrayList<Cell> cells) {
        System.out.println("saving data to: " + path);
        for (int i = 0; i < kvl.getSize(); i++) {
            System.out.println(Arrays.toString(kvl.getKV(i)));
        }

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("config");
        doc.appendChild(rootElement);

        for (int i = 0; i < kvl.getSize(); i++) {
            Element e = doc.createElement(kvl.getKV(i)[0].replace("_", "-"));
            if (kvl.getKV(i)[1].isEmpty()) {
                e.setTextContent("");
            } else {
                e.setTextContent(kvl.getKV(i)[1]);
            }
            rootElement.appendChild(e);
        }
        // Output cells
        Element cellsElement = doc.createElement("cells");
        rootElement.appendChild(cellsElement);

        for (Cell c: cells) {
            Element singleCell = doc.createElement("cell");
            singleCell.setAttribute("row", c.getRow() + "");
            singleCell.setAttribute("col", c.getCol() + "");
            singleCell.setAttribute("state", c.isLive() + "");
            cellsElement.appendChild(singleCell);
        }

        // write dom document to a file
        try (FileOutputStream output = new FileOutputStream(path)) {
            writeXML(doc, output);
        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }

    }

    private static void writeXML(Document doc, OutputStream output) throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // pretty print XML
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }
}
