package com.example.syncmlparser;

public class SyncML {

    private SyncHdr  header;
    private SyncBody body;


    public SyncML() {}

    public SyncML(final SyncHdr  header,
                  final SyncBody body) {
        setSyncHdr(header);
        setSyncBody(body);

    }

    public SyncHdr getSyncHdr() {
        return header;
    }


    public void setSyncHdr(SyncHdr header) {
        if (header == null) {
            throw new IllegalArgumentException("header cannot be null");
        }
        this.header = header;
    }


    public SyncBody getSyncBody() {
        return body;
    }


    public void setSyncBody(SyncBody body) {
        if (body == null) {
            throw new IllegalArgumentException("body cannot be null");
        }
        this.body = body;
    }

}
