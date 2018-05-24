package com.book.addict.dto;

import com.book.addict.constants.TextFilterType;

public class BookDashboardTextFilter {

    private String text;
    private TextFilterType type;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextFilterType getType() {
        return type;
    }

    public void setType(TextFilterType type) {
        this.type = type;
    }
}
