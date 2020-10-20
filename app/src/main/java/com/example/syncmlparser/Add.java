package com.example.syncmlparser;

import java.util.Vector;

public class Add {

    public String CmdID;
    Vector<Item> items = new Vector<>();




    public String getCmdID() {
        return CmdID;
    }

    public void setCmdID(String cmdID) {
        CmdID = cmdID;
    }

    public Vector<Item> getItems() {
        return items;
    }

    public void setItems(Vector<Item> items) {
        this.items = items;
    }
}
