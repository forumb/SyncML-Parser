package com.example.syncmlparser;

import java.util.Vector;

public class SyncBody {


    Add add = new Add();
    Status status = new Status();
    Vector<Status> statuses = new Vector<>();

    public Vector<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(Vector<Status> statuses) {
        this.statuses = statuses;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public Add getAdd() {
        return add;
    }

    public void setAdd(Add add) {
        this.add = add;
    }
}
