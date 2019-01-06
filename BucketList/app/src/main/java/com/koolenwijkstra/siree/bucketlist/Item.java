package com.koolenwijkstra.siree.bucketlist;

import android.view.View;
import android.widget.CheckBox;

public class Item {
    private String title;
    private String description;
    private Boolean checked = false;

    public Item(String title, String description, Boolean checked) {
        this.title = title;
        this.description = description;
        this.checked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }


}
