package com.rolandopalermo.facturacion.ec.common;

import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import lombok.experimental.UtilityClass;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Optional;

import static java.text.MessageFormat.format;

@UtilityClass
public class XmlUtils {

    private static final XPathFactory X_PATH_FACTORY = XPathFactory.newInstance();

    public static Document fromStringToDocument(String xmlStr) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(new StringReader(xmlStr)));
        } catch (ParserConfigurationException | IOException | SAXException ex) {
            throw new VeronicaException("No se pudo procesar el comprobante electrónico como documento XML");
        }
    }

    public static String fromDocumentToString(Document doc) {
        try {
            StringWriter stringWriter = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new DOMSource(doc), new StreamResult(stringWriter));
            return stringWriter.toString();
        } catch (Exception ex) {
            throw new VeronicaException("No se pudo procesar el objeto DOM como documento XML");
        }
    }

    public static Optional<String> evalXPath(Document document, String xpathExpression) {
        try {
            XPathExpression expr;
            Node node;
            String value;
            if (document == null) {
                return Optional.empty();
            }
            XPath xpf = X_PATH_FACTORY.newXPath();
            expr = xpf.compile(xpathExpression);
            try {
                node = (Node) expr.evaluate(document, XPathConstants.NODE);
                value = node != null ? node.getTextContent() : null;
            } catch (XPathExpressionException ex) {
                value = (String) expr.evaluate(document, XPathConstants.STRING);
            }
            return Optional.ofNullable(value);
        } catch (Exception ex) {
            throw new VeronicaException(format("No se pudo evaluar la expresión XPATH [{0}]", xpathExpression));
        }
    }

    public static String getRootElement(Document document) {
        Element root = document.getDocumentElement();
        return root.getNodeName();
    }

}
