package com.karikari.goodfilter.model;

public class Item {

    private String text;
    private int icon = -1;
    private Object tag;

    public Item() {
    }

    public Item(String text) {
        this.text = text;
    }

    public Item(String text, int icon) {
        this.text = text;
        this.icon = icon;
    }

    public Item(String text, int icon, Object tag) {
        this.text = text;
        this.icon = icon;
        this.tag = tag;
    }

    public Item(String text, Object tag) {
        this.text = text;
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }
}
