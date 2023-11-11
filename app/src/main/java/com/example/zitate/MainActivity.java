package com.example.zitate;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.zitate.model.Quote;
import com.example.zitate.model.QuoteArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> mSampleQuotes;
    private List<Quote> mQuoteList;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // WindowCompat.setDecorFitsSystemWindows(getWindow(), false);


        Toolbar toolbar = findViewById(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSupportActionBar(toolbar);
        }

        mListView = findViewById(R.id.zitate_list);

        setupSampleStrings();
        bindAdapter();
    }

    private void setupSampleStrings() {
        String[] sampleQuotes = getResources().getStringArray(R.array.sample_quotes);
        mSampleQuotes = new ArrayList<>(Arrays.asList(sampleQuotes));
        mQuoteList = new ArrayList<>();

        Quote sampleQuote = new Quote(sampleQuotes[0], "Johann Wolfgang v. Goethe", "goethe");
        mQuoteList.add(sampleQuote);

        mQuoteList.add(new Quote(sampleQuotes[1], "Friedrich Schiller", "schiller"));
        mQuoteList.add(new Quote(sampleQuotes[2], "Johann Wolfgang v. Goethe", "goethe"));
        mQuoteList.add(new Quote(sampleQuotes[3], "Friedrich Schiller", "schiller"));
        mQuoteList.add(new Quote(sampleQuotes[4], "Johann Wolfgang v. Goethe", "goethe"));
        mQuoteList.add(new Quote(sampleQuotes[5], "Friedrich Schiller", "schiller"));
        mQuoteList.add(new Quote(sampleQuotes[6], "Johann Wolfgang v. Goethe", "goethe"));
        mQuoteList.add(new Quote(sampleQuotes[7], "Friedrich Schiller", "schiller"));
        mQuoteList.add(new Quote(sampleQuotes[8], "Johann Wolfgang v. Goethe", "goethe"));
        mQuoteList.add(new Quote(sampleQuotes[9], "Unbekannt", "unknown"));
    }

    private void bindAdapter() {
        /*ArrayAdapter<Quote> quoteArrayAdapter =
                new ArrayAdapter<>(
                        this, // Die aktuelle Umgebung (diese Activity)
                        R.layout.list_row, // Die ID des Zeilenlayouts (der XML-Layout Datei)
                        R.id.quote_text,   // Die ID eines TextView-Elements im Zeilenlayout
                        mQuoteList); // Beispieldaten in einer ArrayList*/
        QuoteArrayAdapter arrayAdapter = new QuoteArrayAdapter(this, mQuoteList);
        mListView.setAdapter(arrayAdapter);
    }
}