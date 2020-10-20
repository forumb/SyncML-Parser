package com.example.syncmlparser;

public class SyncHdr {

    public String verDTD;
    public String verProto;
    public String sessionID;
    public String msgID;
    public String LocUri;
    public String respURI;
    public Boolean noResp;

    public Target target;
    public Source source;
    public Cred cred;
    public Meta meta;

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Meta getMeta() {
        return meta;
    }

    public Cred getCred() {
        return cred;
    }

    public void setCred(Cred cred) {
        this.cred = cred;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getVerDTD() {
        return verDTD;
    }

    public void setVerDTD(String verDTD) {
        this.verDTD = verDTD;
    }

    public String getVerProto() {
        return verProto;
    }

    public void setVerProto(String verProto) {
        this.verProto = verProto;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getMsgID() {
        return msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
    }


    public String getRespURI() {
        return respURI;
    }

    public void setRespURI(String respURI) {
        this.respURI = respURI;
    }

    public Boolean getNoResp() {
        return noResp;
    }

    public void setNoResp(Boolean noResp) {
        this.noResp = noResp;
    }

    public String getLocUri() {
        return LocUri;
    }

    public void setLocUri(String locUri) {
        LocUri = locUri;
    }


    //    public class target {
//
//        public String    LocUri;
//
//        target(String LocUri) {
//            this.LocUri = LocUri;
//        }
//        public void setLocUri(String locUri) {
//            LocUri = locUri;
//        }
//
//        public String getLocUri() {
//            return LocUri;
//        }
//    }
}

