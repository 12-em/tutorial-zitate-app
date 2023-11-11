package com.example.zitate;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.zitate.model.Quote;
import com.example.zitate.model.QuoteArrayAdapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Quote> mQuoteList;
    private ListView mListView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private QuoteArrayAdapter mQuoteArrayAdapter;

    private LinearLayout mLayoutRoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // WindowCompat.setDecorFitsSystemWindows(getWindow(), false);


        Toolbar toolbar = findViewById(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSupportActionBar(toolbar);
        }

        mLayoutRoot = findViewById(R.id.layout_root);
        mListView = findViewById(R.id.zitate_list);
        mSwipeRefreshLayout = findViewById(R.id.swiperefresh);

        setupSampleStrings();
        bindAdapter();

        registerListViewClickListener();

        mSwipeRefreshLayout.setOnRefreshListener(this::refreshListView);
    }

    private void registerListViewClickListener() {
        mListView.setOnItemClickListener((parent, view, position, id) -> {
            int duration = Snackbar.LENGTH_LONG;
            String author = mQuoteList.get(position).getQuoteAuthor();
            String message = "click on q from " + author;

            Snackbar.make(mLayoutRoot, message, duration).show();
        });

        mListView.setOnItemLongClickListener((parent, view, position, id) -> {
            new MaterialAlertDialogBuilder(this)
                    .setTitle(mQuoteList.get(position).getQuoteAuthor())
                    .setMessage(mQuoteList.get(position).getQuoteText())
                    .setPositiveButton(R.string.close, null)
                    .show();

            // Der "Lange Klick" wurde von uns behandelt, daher geben wir als Wert true zurück
            return true;
        });
    }

    private void refreshListView() {
        mSwipeRefreshLayout.setRefreshing(true);

        // do something
        Collections.shuffle(mQuoteList);
        mQuoteArrayAdapter.notifyDataSetChanged();

        // done
        mSwipeRefreshLayout.setRefreshing(false);
        Snackbar.make(mLayoutRoot, R.string.snackbar_finished_loading, Snackbar.LENGTH_SHORT).show();
    }

    private void setupSampleStrings() {
        String[] sampleQuotes = getResources().getStringArray(R.array.sample_quotes);
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
        mQuoteArrayAdapter = new QuoteArrayAdapter(this, mQuoteList);
        mListView.setAdapter(mQuoteArrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_get_data) {
            refreshListView();
            // refresh yourself now
            return true;
        }
        else if (id == R.id.action_settings) {
            // TODO implement settings
            Snackbar.make(mLayoutRoot, R.string.settings_placeholder, Snackbar.LENGTH_LONG).show();
            // settings
            return true;
        }
        else {
            // default
            return super.onOptionsItemSelected(item);
        }
    }
}