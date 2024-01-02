package com.example.todolistmain;

import java.io.Serializable;

public class
Items implements Serializable {

    String itemName;

    boolean isSelected;

    public Items(String itemName) {
        this.itemName = itemName;
        this.isSelected = false;
    }

    public Items(String itemName, boolean isSelected) {
        this.itemName = itemName;
        this.isSelected = isSelected;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return "Items{" +
                "itemName='" + itemName + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
