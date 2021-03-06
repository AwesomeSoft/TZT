package com.awesomesoft.tzt.service.ns.xml;

import com.awesomesoft.tzt.service.ns.error.NsApiException;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XmlPresent extends Xml {

    private final String name;

    private final String content;

    private final Map<String, String> nameAttributes = new HashMap<String, String>();

    private final Map<String, List<Xml>> nameChildren = new HashMap<String, List<Xml>>();

    private static Element rootElement(InputStream stream, String rootName) {
        if (stream == null) {
            throw new NullPointerException("Inputstream cannot be null");
        }
        if (rootName == null) {
            throw new NullPointerException("Name of the root element cannot be null");
        }
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(stream);
            Element rootElement = document.getDocumentElement();
            if (!rootElement.getNodeName().equals(rootName)) {
                throw new NsApiException("Could not find root node: " + rootName);
            }
            return rootElement;
        }
        catch (Exception exception) {
            throw new NsApiException("Unknown error", exception);
        }
    }

    protected XmlPresent(InputStream stream, String rootName) {
        this(XmlPresent.rootElement(stream, rootName));
    }

    private XmlPresent(Element element) {
        name = element.getNodeName();
        content = element.getTextContent();
        NamedNodeMap namedNodeMap = element.getAttributes();
        int n = namedNodeMap.getLength();
        for (int i = 0; i < n; i++) {
            Node node = namedNodeMap.item(i);
            String name = node.getNodeName();
            addAttribute(name, node.getNodeValue());
        }
        NodeList nodes = element.getChildNodes();
        n = nodes.getLength();
        for (int i = 0; i < n; i++) {
            Node node = nodes.item(i);
            int type = node.getNodeType();
            if (type == Node.ELEMENT_NODE) {
                addChild(node.getNodeName(), new XmlPresent((Element) node));
            }
        }
    }

    private void addAttribute(String name, String value) {
        nameAttributes.put(name, value);
    }

    private void addChild(String name, XmlPresent child) {
        List<Xml> children = nameChildren.get(name);
        if (children == null) {
            children = new ArrayList<Xml>();
            nameChildren.put(name, children);
        }
        children.add(child);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String content() {
        return content;
    }

    @Override
    public Xml child(String name) {
        List<Xml> children = children(name);
        if (children.size() != 1) {
            return new XmlAbsent(name);
        }
        return children.get(0);
    }

    @Override
    public List<Xml> children(String name) {
        List<Xml> children = nameChildren.get(name);
        return children == null ? new ArrayList<Xml>() : children;
    }

    @Override
    public String attr(String name) {
        return nameAttributes.get(name);
    }

    @Override
    public boolean isPresent(String name) {
        return nameChildren.containsKey(name);
    }

}