package huce.fit.mvvmpattern.views.fragments.home.section;

import java.util.List;

import huce.fit.mvvmpattern.views.fragments.home.itemHistory.Item;

public class Section {
    private String sectionName;
    private List<Item> items;

    public Section(String sectionName, List<Item> items) {
        this.sectionName = sectionName;
        this.items = items;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
