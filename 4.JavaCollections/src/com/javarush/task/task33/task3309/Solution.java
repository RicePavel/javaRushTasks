package com.javarush.task.task33.task3309;

import org.w3c.dom.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws Exception {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter writer = new StringWriter();
        mar.marshal(obj, writer);
        String str = writer.toString();
        InputStream is = new ByteArrayInputStream(str.getBytes());
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = db.parse(is);
        Element element = document.getDocumentElement();
        addCdata(element, document);
        if (element.getTagName().equals(tagName)) {
            Comment c = document.createComment(comment);
            element.getParentNode().insertBefore(c, element);
        }
        addComment(element, document, tagName, comment);

        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.setOutputProperty(OutputKeys.STANDALONE, "no");
        Writer w = new StringWriter();
        tf.transform(new DOMSource(document), new StreamResult(w));
        return w.toString();
    }

    private static void addCdata(Node node, Document doc) {
        String text = node.getTextContent();
        if (node.getNodeType() == Node.ELEMENT_NODE && text.matches(".*[<>&'\"].*")) {
            CDATASection c = doc.createCDATASection(text);
            node.setTextContent("");
            node.appendChild(c);
        }
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node n = list.item(i);
            addCdata(n, doc);
        }
    }

    private static void addComment(Element element, Document doc, String tagName, String comment) {
        List<Element> elements = new ArrayList<>();
        NodeList list = element.getChildNodes();
        /*
        for (int i = 0; i < list.getLength(); i++) {
            Node n = list.item(i);
            addCdata(n, doc);
        }
         */
        for (int i = 0; i < list.getLength(); i++) {
            Node n = list.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) n;
                if (el.getTagName().equals(tagName)) {
                    elements.add(el);
                }
            }
        }
        for (Element el: elements) {
            Comment c = doc.createComment(comment);
            el.getParentNode().insertBefore(c, el);
        }
        for (int i = 0; i < list.getLength(); i++) {
            Node n = list.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) n;
                addComment(el, doc, tagName, comment);
            }
        }
    }


    public static void main(String[] args) {

    }
}
