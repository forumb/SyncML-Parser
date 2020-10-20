package com.example.syncmlparser;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class CreateXML {

    //       File newxmlfile = new File("D:\new.xml");
//    SyncML syncML;
//    public String VERDTD = syncML.getSyncHdr().getVerDTD();



    public void createxml(Context context, SyncML syncML) {
        String VERDTD = syncML.getSyncHdr().getVerDTD();
        String VERPROTO = syncML.getSyncHdr().getVerProto();
        String SESSIONID = syncML.getSyncHdr().getSessionID();
        String MSGID = syncML.getSyncHdr().getMsgID();
        String TARGETLOCURI = syncML.getSyncHdr().getTarget().getLocUri();
        String SOURCELOCURI = syncML.getSyncHdr().getSource().getLocUri();
        String METAFORMAT = syncML.getSyncHdr().getCred().getMeta().getMetaformatvalue();
        String METATYPE = syncML.getSyncHdr().getCred().getMeta().getMetatypevalue();
        String DATA = syncML.getSyncHdr().getCred().getData();
        String METAMAXSIZE = syncML.getSyncHdr().getMeta().getMetamaxmsgsizevalue();


        DocumentBuilder builder = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (
                ParserConfigurationException e) {
            //       throw e;
        }
        Document document = builder.newDocument();
        // create root element
        Element root = document.createElement("SyncML");
        root.setAttribute("xmlns", "SYNCML:SYNCML1.2");
        // attach it to the document
        document.appendChild(root);
        // create user node
        Element syncHdr = document.createElement("SyncHdr");
        // create its id attribute
        // add user node to root node
        root.appendChild(syncHdr);
        // create name node and set its value
        Element vertdtd = document.createElement("VerDTD");
        vertdtd.setTextContent(VERDTD);
        syncHdr.appendChild(vertdtd);

        Element verproto = document.createElement("VerProto");
        verproto.setTextContent(VERPROTO);
        syncHdr.appendChild(verproto);

        Element sessionId = document.createElement("SessionID");
        sessionId.setTextContent(SESSIONID);
        syncHdr.appendChild(sessionId);

        Element msgId = document.createElement("MsgID");
        msgId.setTextContent(MSGID);
        syncHdr.appendChild(msgId);

        Element target = document.createElement("Target");
        syncHdr.appendChild(target);

        Element locuri = document.createElement("LocURI");
        locuri.setTextContent(TARGETLOCURI);
        target.appendChild(locuri);

        Element source = document.createElement("Source");
        syncHdr.appendChild(source);

        Element locuri2 = document.createElement("LocURI");
        locuri2.setTextContent(SOURCELOCURI);
        source.appendChild(locuri2);

        Element cred = document.createElement("Cred");
        syncHdr.appendChild(cred);

        Element meta = document.createElement("Meta");
        cred.appendChild(meta);

        Element format = document.createElement("Format");
        format.setAttribute("xmlns", "syncml:metinf");
        format.setTextContent(METAFORMAT);
        meta.appendChild(format);

        Element type = document.createElement("Type");
        type.setAttribute("xmlns", "syncml:metinf");
        type.setTextContent(METATYPE);
        meta.appendChild(type);

        Element data = document.createElement("Data");
        data.setTextContent(DATA);
        cred.appendChild(data);

        Element meta2 = document.createElement("Meta");
        syncHdr.appendChild(meta2);

        Element maxMsgSize = document.createElement("MaxMsgSize");
        maxMsgSize.setAttribute("xmlns", "syncml:metinf");
        maxMsgSize.setTextContent(METAMAXSIZE);
        meta2.appendChild(maxMsgSize);

        // write xml
        Transformer transformer;
        try {
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            transformer = transformerFactory.newTransformer();

            String fileName = "SyncHdr.xml";
//                File xml;
//                xml = new File(getCacheDir(), fileName);
            Result output = new StreamResult(new File(context.getCacheDir(), fileName));
            Source input = new DOMSource(document);
            // if you want xml to be properly formatted
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(input, output);
        } catch (
                TransformerConfigurationException e) {
            //          throw e;

        } catch (
                TransformerException e) {
            //         throw e;
        }
    }
}
