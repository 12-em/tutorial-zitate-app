package com.example.zitate.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.zitate.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuoteArrayAdapter extends ArrayAdapter<Quote> {

    private Context mContext;
    private List<Quote> mQuoteList;
    private LayoutInflater mLayoutInflater;

    private Resources mResources;
    private String mPackageName;
    private Map<String, Drawable> mQuoteAuthorDrawables = new HashMap<>();

    public QuoteArrayAdapter(Context context, List<Quote> quoteList) {
        super(context, R
                .layout.list_row, quoteList);

        mContext = context;
        mQuoteList = quoteList;
        mLayoutInflater = LayoutInflater.from(context);

        mResources = context.getResources();
        mPackageName = context.getPackageName();
        createQuoteAuthorDrawables();
    }

    @SuppressLint("DiscouragedApi")
    private void createQuoteAuthorDrawables() {
        int imageId;
        String[] quoteAuthors = mResources.getStringArray(R.array.quote_authors);

        for (String author : quoteAuthors)
        {
            imageId = mResources.getIdentifier(author, "drawable", mPackageName);
            if (imageId > 0) {
                mQuoteAuthorDrawables.put(author, ContextCompat.getDrawable(mContext, imageId));
            }
        }
    }

    @NonNull
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        // Erzeugen der View-Hierarchie auf Grundlage des Zeilenlayouts
        View rowView;
        if (convertView == null) {
            rowView = mLayoutInflater.inflate(R.layout.list_row, parent, false);
        }
        else {
            rowView = convertView;
        }

        // Anfordern des zur Listenposition gehörenden Datenobjekts
        Quote currentQuote = mQuoteList.get(position);

        // Finden der einzelnen View-Objekte in der View-Hierarchie (Zeilenlayout)
        TextView tvQuoteText   = (TextView) rowView.findViewById(R.id.quote_text);
        TextView tvQuoteAuthor = (TextView) rowView.findViewById(R.id.quote_author);
        ImageView ivAuthorImage  = (ImageView) rowView.findViewById(R.id.author_image);

        // Füllen der View-Objekte mit den passenden Inhalten des Datenobjekts
        tvQuoteText.setText("\"" + currentQuote.getQuoteText() + "\"");
        tvQuoteAuthor.setText(currentQuote.getQuoteAuthor());

        Drawable quoteAuthorDrawable = mQuoteAuthorDrawables.get(currentQuote.getImageId());
        if (quoteAuthorDrawable != null) {
            ivAuthorImage.setImageDrawable(quoteAuthorDrawable);
        }

        // Rückgabe der befüllten View-Hierarchie an den aufrufenden AdapterView
        return rowView;
    }
}