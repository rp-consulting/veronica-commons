package com.rolandopalermo.facturacion.ec.common;

import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

import static java.lang.String.format;

/**
 * Utilitarios para el manejo de documentos XML
 *
 * @author Rolando Rodríguez
 */
public class XMLUtils {

    private static final XPathFactory X_PATH_FACTORY = XPathFactory.newInstance();

    private XMLUtils() {
    }

    /**
     * Convierte una cadena de texto XML a un Documento XML
     *
     * @param xmlStr Cadena de texto XML
     * @return Documento XML
     * @throws VeronicaException Retorna una excepción en caso el documento XML no sea válido
     */
    public static Document convertStringToDocument(String xmlStr) throws VeronicaException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(new StringReader(xmlStr)));
        } catch (Exception e) {
            throw new VeronicaException(format("No se pudo leer la estrucutura DOM del documento XML [%s]", xmlStr));
        }
    }

    /**
     * @param document        Documento XML
     * @param xpathExpression Expresión XPath a evaluar
     * @return Nodo consultado
     */
    public static String xPathToString(Document document, String xpathExpression) {
        if (document == null) {
            throw new VeronicaException("El documento es nulo");
        }
        XPath xPath = X_PATH_FACTORY.newXPath();
        Node node;
        try {
            node = (Node) xPath.compile(xpathExpression).evaluate(document, XPathConstants.NODE);
            return node != null ? node.getTextContent() : "";
        } catch (Exception ex) {
            try {
                return (String) xPath.compile(xpathExpression).evaluate(document, XPathConstants.STRING);
            } catch (Exception ex2) {
                throw new VeronicaException(format("No se pudo evaluar la expresión %s", xpathExpression));
            }
        }
    }

    /**
     * @param document Documento XML
     * @return Nodo raíz de un documento XML
     */
    public static String getXmlRootElement(Document document) {
        Element root = document.getDocumentElement();
        return root.getNodeName();
    }

}