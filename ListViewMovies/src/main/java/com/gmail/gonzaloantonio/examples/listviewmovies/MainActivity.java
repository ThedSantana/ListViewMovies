package com.gmail.gonzaloantonio.examples.listviewmovies;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private ListView listView;
    private WebView webView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        requestWindowFeature (Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView (R.layout.activity_main);

        listView = (ListView) findViewById (R.id.listView);
        webView = (WebView) findViewById (R.id.webView);

        List<Item> items = new ArrayList<Item>();

        items.add (
                new Item (R.drawable.following, "Following", "http://www.imdb.com/title/tt0154506/")
        );

        items.add (
                new Item (R.drawable.memento, "Memento", "http://www.imdb.com/title/tt0209144/")
        );

        items.add (
                new Item (R.drawable.batman_begins, "Batman Begins", "http://www.imdb.com/title/tt0372784/")
        );

        items.add (
                new Item (R.drawable.prestige, "The Prestige", "http://www.imdb.com/title/tt0482571/")
        );

        items.add (
                new Item (R.drawable.darkk_night, "The Dark Night", "http://www.imdb.com/title/tt0468569/")
        );

        items.add (
                new Item (R.drawable.inception, "Inception", "http://www.imdb.com/title/tt1375666/")
        );

        items.add (
                new Item (R.drawable.dark_knight_rises, "The Dark Night Rises", "http://www.imdb.com/title/tt1345836/")
        );

        listView.setAdapter (new ItemAdapter (this, items));
        listView.setOnItemClickListener (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setProgressBarIndeterminateVisibility (true);
                progressDialog = ProgressDialog.show (MainActivity.this, "ProgressDialog", "Loading!");

                webView.getSettings().setJavaScriptEnabled (true);
                webView.setWebViewClient (new MyWebViewClient ());

                Item item = (Item) listView.getAdapter().getItem (i);
                webView.loadUrl (item.getUrl ());
            }
        });
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading (WebView view, String url) {
            view.loadUrl (url);
            return true;
        }

        @Override
        public void onPageFinished (WebView view, String url) {
            super.onPageFinished (view, url);

            setProgressBarIndeterminateVisibility (false);
            progressDialog.dismiss ();
        }
    }

}
