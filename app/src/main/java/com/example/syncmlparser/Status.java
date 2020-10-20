package com.example.syncmlparser;

public class Status {

    public String CmdID;
    public String MsgRef;
    public String CmdRef;
    public String Cmd;
    public String TargetRef;
    public String SourceRef;
    public String Data;

    public String getCmd() {
        return Cmd;
    }

    public void setCmd(String cmd) {
        Cmd = cmd;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getSourceRef() {
        return SourceRef;
    }

    public void setSourceRef(String sourceRef) {
        SourceRef = sourceRef;
    }

    public String getTargetRef() {
        return TargetRef;
    }

    public void setTargetRef(String targetRef) {
        TargetRef = targetRef;
    }

    public String getCmdRef() {
        return CmdRef;
    }

    public void setCmdRef(String cmdRef) {
        CmdRef = cmdRef;
    }

    public String getMsgRef() {
        return MsgRef;
    }

    public void setMsgRef(String msgRef) {
        MsgRef = msgRef;
    }

    public String getCmdID() {
        return CmdID;
    }

    public void setCmdID(String cmdID) {
        CmdID = cmdID;
    }

}
