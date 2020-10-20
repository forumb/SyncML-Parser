package com.example.syncmlparser;

import java.util.Vector;

public class Item {

    public String data;
    public Target target;
    public Source source;
    Meta meta = new Meta();
    Vector<Target> targets = new Vector<>();

    public void setTargets(Vector<Target> targets) {
        this.targets = targets;
    }

    public Vector<Target> getTargets() {
        return targets;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Target getTarget() {
        return target;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Meta getMeta() {
        return meta;
    }
}
