package com.example.syncmlparser;

class Cred {

Meta meta = new Meta();
public String data;

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
