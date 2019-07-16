package com.karikari.goodfilter.model;

public class SelectableItem extends Item {

    private boolean isSelected;

    public SelectableItem(Item item, boolean isSelected) {
        super(item.getText(), item.getIcon(), item.getTag());
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
