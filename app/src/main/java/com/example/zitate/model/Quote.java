package com.example.zitate.model;

import androidx.annotation.NonNull;

public class Quote {
    private final String quoteText;
    private final String quoteAuthor;
    private final String imageId;

    public Quote(String quoteText, String quoteAuthor, String imageId) {
        this.quoteText = quoteText;
        this.quoteAuthor = quoteAuthor;
        this.imageId = imageId;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public String getQuoteAuthor() {
        return quoteAuthor;
    }

    public String getImageId() {
        return imageId;
    }

    @NonNull
    @Override
    public String toString() {

        return quoteText + " - " + quoteAuthor + " - ID: " + imageId;
    }
}
