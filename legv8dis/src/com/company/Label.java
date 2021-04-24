package com.company;

public class Label {
    private String name;
    private int indexAt;

    public Label(String name, int indexAt) {
        this.name = name;
        this.indexAt = indexAt;
    }

    public String getName() {
        return name;
    }

    public int getIndex(){ return indexAt; }

    public void setIndex(int index){
        indexAt = index;
    }

}
