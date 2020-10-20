package com.example.syncmlparser;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import static org.xmlpull.v1.XmlPullParser.END_TAG;
import static org.xmlpull.v1.XmlPullParser.START_TAG;


public class MainActivity extends Activity{


    SyncHdr syncHdr;
    SyncBody syncBody = new SyncBody();
    Target target = new Target();
    Source source = new Source();
    Add add = new Add();

    SyncML syncML = new SyncML();
    Cred cred = new Cred();
    Vector<Item> items = new Vector<>();
    Vector<Target> targets = new Vector<>();
    Vector<Status> statuses = new Vector<>();
//    ArrayList<SyncHdr> syncHdrs = null;
//    ArrayList<SyncBody> syncBodyArrayList = null;

    CreateXML createXML = new CreateXML();

    private static final String TAG_LOG = "MainActivity";
    private static final String HEADER = "SyncHdr";
    private static final String BODY = "SyncBody";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        XmlPullParserFactory pullParserFactory;

        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            InputStream in = getApplicationContext().getAssets().open("response.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, "UTF-8");


            int eventType = parser.getEventType();
            if (eventType == XmlPullParser.START_DOCUMENT) {

                while (parser.next() != XmlPullParser.END_DOCUMENT) {

                    //Log.d("event type", "while event type:" + parser.getEventType());

                    if (parser.getEventType() != START_TAG) {
                        //Log.d("event type", "while event type inside if:" + parser.getEventType());


                        continue;
                    }

                    String name = parser.getName();
                    //Log.d("event type", "name of tag:" + name);

                    if (name.equals("SyncHdr")) {
 //                       Log.d("name", name);
                        parser.nextTag();
                        syncHdr = parseSyncHdr(parser);
                    } else if (name.equals("SyncBody")) {
//                        Log.d("Inside SyncBody", "inside body");
                        parser.nextTag();
                        syncBody = parseSyncBody(parser);
//                        Log.d("Check3", "Vector status " + syncBody.getStatuses().get(1).getData());


                    }

                }

                if (syncHdr != null) {
                    syncML.setSyncHdr(syncHdr);

//                   Log.d("Data", "syncml  " + syncML.getSyncHdr().getTarget().getLocUri());
                }
                if (syncBody != null) {
                    syncML.setSyncBody(syncBody);

//                    Log.d("Data", "syncmlbody  " + syncBody.getAdd().getItems().get(1).getData());

                }
                createXML.createxml(MainActivity.this,syncML);

 //               logclass();
            }


        } catch (XmlPullParserException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logclass() {

        Log.d(HEADER, "Parsing Header Information..");
        Log.d(HEADER, "VerDTD: " + syncML.getSyncHdr().getVerDTD());
        Log.d(HEADER, "VerProto: " + syncML.getSyncHdr().getVerProto());
        Log.d(HEADER, "SessionID: " + syncML.getSyncHdr().getSessionID());
        Log.d(HEADER, "MsgID: " + syncML.getSyncHdr().getMsgID());
        Log.d(HEADER, "Target LocUri: " + syncML.getSyncHdr().getTarget().getLocUri());
        Log.d(HEADER, "Source LocUri: " + syncML.getSyncHdr().getSource().getLocUri());
        Log.d(HEADER, "RespURI: " + syncML.getSyncHdr().getRespURI());
        Log.d(HEADER, "Cred Meta Format Attribute: " + syncML.getSyncHdr().getCred().getMeta().getMetaformatattribute());
        Log.d(HEADER, "Cred Meta Format Value: " + syncML.getSyncHdr().getCred().getMeta().getMetaformatvalue());
        Log.d(HEADER, "Cred Meta Type Attribute: " + syncML.getSyncHdr().getCred().getMeta().getMetatypeattribute());
        Log.d(HEADER, "Cred Meta Type Value: " + syncML.getSyncHdr().getCred().getMeta().getMetatypevalue());
        Log.d(HEADER, "Cred Data: " + syncML.getSyncHdr().getCred().getData());
        Log.d(HEADER, "Meta maxsize attribute: " + syncML.getSyncHdr().getMeta().getMetamaxmsgsizeattribute());
        Log.d(HEADER, "Meta maxsize value: " + syncML.getSyncHdr().getMeta().getMetamaxmsgsizevalue());



        Log.d(BODY, "Parsing Body Information..");
 //       Log.d(BODY, "Command name : Add ");
        Log.d(BODY, "Command name : Status");
        Log.d(BODY, "Status 1: CmdId: " + syncML.getSyncBody().getStatuses().get(0).getCmdID());
        Log.d(BODY, "Status 1: Cmd: " + syncML.getSyncBody().getStatuses().get(0).getCmd());
        Log.d(BODY, "Status 1: Sourceref: " + syncML.getSyncBody().getStatuses().get(0).getSourceRef());
        Log.d(BODY, "Status 1: Targetref: " + syncML.getSyncBody().getStatuses().get(0).getTargetRef());
        Log.d(BODY, "Status 1: Data: " + syncML.getSyncBody().getStatuses().get(0).getData());

        Log.d(BODY, "Status 2: CmdId: " + syncML.getSyncBody().getStatuses().get(1).getCmdID());
        Log.d(BODY, "Status 2: Cmd: " + syncML.getSyncBody().getStatuses().get(1).getCmd());
        Log.d(BODY, "Status 2: Sourceref: " + syncML.getSyncBody().getStatuses().get(1).getSourceRef());
        Log.d(BODY, "Status 2: Targetref: " + syncML.getSyncBody().getStatuses().get(1).getTargetRef());
        Log.d(BODY, "Status 2: Data: " + syncML.getSyncBody().getStatuses().get(1).getData());

        Log.d(BODY, "Status 3: CmdId: " + syncML.getSyncBody().getStatuses().get(2).getCmdID());
        Log.d(BODY, "Status 3: Cmd: " + syncML.getSyncBody().getStatuses().get(2).getCmd());
        Log.d(BODY, "Status 3: Sourceref: " + syncML.getSyncBody().getStatuses().get(2).getSourceRef());
        Log.d(BODY, "Status 3: Targetref: " + syncML.getSyncBody().getStatuses().get(2).getTargetRef());
        Log.d(BODY, "Status 3: Data: " + syncML.getSyncBody().getStatuses().get(2).getData());

//
//        Log.d(BODY, "CmdID: " + syncML.getSyncBody().getAdd().getCmdID());
//        Log.d(BODY, "Number of Items in Add command: " + syncML.getSyncBody().getAdd().getItems().size());
//
////        Log.d(BODY, "Item 1 : Target LocUri: " + syncML.getSyncBody().getAdd().getItems().get(0).getTargets().get(0).getLocUri());
//        Log.d(BODY, "Item 1 : Meta Format: " + syncML.getSyncBody().getAdd().getItems().get(0).getMeta().getMetaformatvalue());
//        Log.d(BODY, "Item 1 : Meta Attribute: " + syncML.getSyncBody().getAdd().getItems().get(0).getMeta().getMetaformatattribute());
//        Log.d(BODY, "Item 2 : Data: " + syncML.getSyncBody().getAdd().getItems().get(1).getData());
//        Log.d(BODY, "Item 4 : Data: " + syncML.getSyncBody().getAdd().getItems().get(3).getData());
//        Log.d(BODY, "Item 1 : Target LocUri: " + syncML.getSyncBody().getAdd().getItems().get(0).getTargets().get(1).getLocUri());

//        for(int i = 0; i <= items.size(); i++){
//            Log.d(BODY, "Item Target LocUri: " + syncML.getSyncBody().getAdd().getItems().get(i).getTarget().getLocUri());
//        }




    }


    private SyncHdr parseSyncHdr(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.getEventType();
        syncHdr = new SyncHdr();
        Meta meta;

        while (eventType != XmlPullParser.END_TAG) {
            String name;
            name = parser.getName();

            if (eventType == START_TAG) {
                switch (name) {
                    case "VerDTD":
                        syncHdr.verDTD = parser.nextText();
                        String verDTD = syncHdr.getVerDTD();
                        syncHdr.setVerDTD(verDTD);
//                        Log.d(TAG_LOG, "Sync header VerDTD value :  " + verDTD);

                        break;
                    case "VerProto":
                        syncHdr.verProto = parser.nextText();
                        String verProto = syncHdr.getVerProto();
                        syncHdr.setVerProto(verProto);
//                        Log.d(TAG_LOG, "Sync header VerProto value :  " + verProto);

                        break;
                    case "SessionID":
                        syncHdr.sessionID = parser.nextText();
                        String sessionId = syncHdr.getSessionID();
                        syncHdr.setSessionID(sessionId);
//                        Log.d(TAG_LOG, "Sync header sessionId value :  " + sessionId);

                        break;
                    case "MsgID":
                        syncHdr.msgID = parser.nextText();
                        String msgID = syncHdr.getMsgID();
                        syncHdr.setMsgID(msgID);
//                        Log.d(TAG_LOG, "Sync header msgID value :  " + msgID);

                        break;
                    case "RespURI":
                        syncHdr.respURI = parser.nextText();
                        String respuri = syncHdr.getRespURI();
                        Log.d("Respuri", "hdr " + respuri);
                        syncHdr.setRespURI(respuri);

                        break;

                    case "Meta":
                        eventType = parser.nextTag();
                        meta = parseMeta(parser);
                        syncHdr.setMeta(meta);
                        eventType = parser.next();

                        break;

                    case "Target":
                        eventType = parser.nextTag();
                        Log.d("event type", "inside target:" + eventType);
                        String locuri = parseTarget(parser);
                        syncHdr.setTarget(target);
                        eventType = parser.next();
                        if(eventType == 4){
                            eventType = parser.next();
                        }

                        break;


                    case "Source":
                        eventType = parser.nextTag();
//                        Log.d("event type", "inside source:" + eventType);
                         source = parseSource(parser);
                        syncHdr.setSource(source);
                        eventType = parser.next();
                        if(eventType == 4){
                            eventType = parser.next();
                        }

                        break;


                    case "Cred":
                        eventType = parser.nextTag();
                        cred = parseCred(parser);
                        syncHdr.setCred(cred);
                        break;



                }
            }

            eventType = parser.next();
        }

        return syncHdr;
    }

    private Cred parseCred(XmlPullParser parser) throws XmlPullParserException, IOException {

        Meta meta;

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_TAG) {
            String name = parser.getName();
            if (eventType == START_TAG) {
                switch (name) {
                    case "Meta":
                        parser.nextTag();
                        meta = parseMeta(parser);
                        cred.setMeta(meta);
                    break;

                    case "Data":
                        String data = parser.nextText();
                        cred.setData(data);
                        Log.d("Cred", "Data "+ data);
                        break;
                }
            }

           eventType = parser.next();
        }
        return cred;
    }


    public String parseTarget(XmlPullParser parser) throws XmlPullParserException {
        String name = parser.getName();

        String locuri = null;
        if (name.equals("LocURI")) {
            try {
                locuri = parser.nextText();
                target.setLocUri(locuri);
                targets.add(target);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }

        return locuri;
    }

    public Source parseSource(XmlPullParser parser) {
        String name = parser.getName();
        String locuri = null;
        if (name.equals("LocURI")) {
            try {
                locuri = parser.nextText();
                source.setLocUri(locuri);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }

        return source;
    }

    private SyncBody parseSyncBody(XmlPullParser parser) throws XmlPullParserException, IOException {

        int eventType = parser.getEventType();
        Status status = new Status();
//        syncBody = new SyncBody();
        while (eventType != XmlPullParser.END_TAG) {
            String name = parser.getName();

            if (eventType == START_TAG) {
                switch (name) {
                    case "Add":

//                        Add add = new Add();
                        parser.nextTag();
                        add = parseAdd(parser);
                        syncBody.setAdd(add);
//                        Log.d("Syncbody", "inside add  " + syncBody.getAdd().getCmdID());
                        break;

                    case "Status":
                        parser.nextTag();
                        status = parseStatus(parser);
//                        syncBody.setStatus(status);
                        statuses.add(status);
                        syncBody.setStatuses(statuses);

 //                       Log.d("Check", "Status " + syncBody.getStatus().getData());
 //                       Log.d("Check", "Status Vector values " + statuses.get(0).getCmd());
                        break;

                }

            }
   //         statuses.add(status);
//            Log.d("Check1", "Status Vector values " + syncBody.getStatuses().get(0).getCmd());
 //           Log.d("Check2", "Status Vector values " + statuses.get(0).getCmd());

            eventType = parser.next();
        }

    //    syncBody.setStatuses(statuses);
        return syncBody;

    }

    private Status parseStatus(XmlPullParser parser) throws XmlPullParserException, IOException {

        int eventType = parser.getEventType();
        Status status = new Status();

        while (eventType != END_TAG) {

            String name = parser.getName();
            if (eventType == START_TAG) {
                switch (name) {
                    case "CmdID":
                        String cmdid = parser.nextText();
                        status.setCmdID(cmdid);
                        Log.d("Status" , "CmdId "+ cmdid);
                        break;

                    case "MsgRef":
                        String msgref = parser.nextText();
                        status.setMsgRef(msgref);
                        Log.d("Status" , "Msgref"+ msgref);
                        break;

                    case "CmdRef":
                        String cmdref = parser.nextText();
                        status.setCmdRef(cmdref);
                        Log.d("Status" , "Cmdref "+ cmdref);
                        break;

                    case "Cmd":
                        String cmd = parser.nextText();
                        status.setCmd(cmd);
                        Log.d("Status" , "Cmd "+ cmd);
                        break;

                    case "TargetRef":
                        String targetref = parser.nextText();
                        status.setTargetRef(targetref);
                        Log.d("Status" , "Targetref "+ targetref);
                        break;

                    case "SourceRef":
                        String sourceref = parser.nextText();
                        status.setSourceRef(sourceref);
                        Log.d("Status" , "Sourceref "+ sourceref);
                        break;

                    case "Data":
                        String data = parser.nextText();
                        status.setData(data);
                        Log.d("Status" , "Data "+ data);
                        break;


                }
            }
            eventType = parser.next();
        }

        return status;
    }

    private Add parseAdd(XmlPullParser parser) throws XmlPullParserException, IOException {

        int eventType = parser.getEventType();
        Item item = new Item();

  //      Vector<Item> items = new Vector<>();
        while (eventType != END_TAG) {

            String name = parser.getName();
            if (eventType == START_TAG) {
                switch (name) {
                    case "CmdID":
                        String cmdid = parser.nextText();
                        add.setCmdID(cmdid);
 //                       Log.d(cmdid, "in add tag");
                        break;

                    case "Item":
 //                         while (name.equals("Item")) {
                            eventType = parser.nextTag();
 //                       Vector<Item> items = parseItems(parser);
                        item = parseItems(parser);
                        items.add(item);
                        add.setItems(items);

                }

            }
            eventType = parser.next();
        }
        return add;
    }


     private Item parseItems(XmlPullParser parser) throws XmlPullParserException, IOException {


            Meta meta;
        int eventType = parser.getEventType();
        Item item = new Item();

        Vector<Item> items = new Vector<>();
        while (eventType != END_TAG) {


            String name = parser.getName();
            if (eventType == START_TAG) {

                switch (name) {
                    case "Target":
                        eventType = parser.nextTag();
//                        Log.d("Items", "inside target:" + parser.getEventType());
                        String locuri = parseTarget(parser);
                        item.setTarget(target);

                        eventType = parser.nextTag();
                        break;

                    case "Meta":
                        parser.nextTag();
                        meta = parseMeta(parser);
                        item.setMeta(meta);
 //                       eventType = parser.nextTag();
                        break;

                    case "Data":
                        item.data = parser.nextText();
                        String data = item.getData();
                        item.setData(data);
                        break;

                }


            }
//            items.add(item);
            eventType = parser.next();

        }
  //      itemvector.add(item);

        return item;

    }

    private Meta parseMeta(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.getEventType();
        Meta meta = new Meta();
        while (eventType != END_TAG) {

            String name = parser.getName();
            if (eventType == START_TAG) {
                switch (name){
                    case "Format":
                        String formatattritube = parser.getAttributeValue(null, "xmlns");
                        Log.d("Cred: ", "Format attribute " + formatattritube);
                        meta.setMetaformatattribute(formatattritube);

                        String formatvalue = parser.nextText();
                        Log.d("Cred: ", "Format value " + formatvalue);
                        meta.setMetaformatvalue(formatvalue);
                        break;


                    case "Type":
                        String typeattribute = parser.getAttributeValue(null, "xmlns");
                        Log.d("Cred: ", "Type attribute " + typeattribute);
                        meta.setMetatypeattribute(typeattribute);

                        String typevalue = parser.nextText();
                        Log.d("Cred: ", "Type value " + typevalue);
                        meta.setMetatypevalue(typevalue);

                        break;

                    case "MaxMsgSize":

                        String attribute = parser.getAttributeValue(null, "xmlns");
                        meta.setMetamaxmsgsizeattribute(attribute);

                        String value = parser.nextText();
                        meta.setMetamaxmsgsizevalue(value);
                        break;




                }
            }
            eventType = parser.next();

            }
        return meta;
        }

    }
