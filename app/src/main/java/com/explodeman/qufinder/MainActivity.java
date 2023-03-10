package com.explodeman.qufinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.explodeman.qufinder.adapter.ItemAdapter;
import com.explodeman.qufinder.model.Item;
import com.explodeman.qufinder.model.content_parser.ContentParser;
import com.explodeman.qufinder.model.content_parser.CsvContentParser;
import com.explodeman.qufinder.model.file_reader.FileReader;
import com.explodeman.qufinder.model.file_reader.FileReaderBuffered;
import com.explodeman.qufinder.model.item_repository.ItemRepository;
import com.explodeman.qufinder.model.item_repository.item_file_repository.ItemFileRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MIN_SYMBOLS_FOUND = 3;
    private static final String CSV_FILE_NAME = "items.csv";

    private ItemRepository repository;

    private RecyclerView rvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = createRepository();
        initRv();


    }

    private void initRv() {
        rvItems = findViewById(R.id.rvItems);
        rvItems.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItems.setLayoutManager(layoutManager);
        List<Item> items = new ArrayList<>();
        ItemAdapter adapter = createItemsAdapter(items);
        setAdapter(adapter);
    }

    private ItemAdapter createItemsAdapter(List<Item> items) {
        return new ItemAdapter(items);
    }

    private void setAdapter(ItemAdapter adapter) {
        rvItems.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnCloseListener(this::onSearchClose);
//        searchView.setBackgroundColor(Color.parseColor("#0040FF"));

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.draw_search);
        searchView.setBackground(drawable);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                if (s.length() < MIN_SYMBOLS_FOUND) {
                    ItemAdapter adapter = createItemsAdapter(new ArrayList<>());
                    setAdapter(adapter);
                    return false;
                }

                List<Item> items = repository.findByFilter(s);

                ItemAdapter adapter = createItemsAdapter(items);
                setAdapter(adapter);

                return true;
            }
        });

        return true;
    }

    private boolean onSearchClose() {
        return false;
    }

    private ItemRepository createRepository() {
        //return new ItemMemoryRepository();

        final String fileName = CSV_FILE_NAME;
        InputStream stream;
        try {
            stream = getAssets().open(fileName);
        } catch (IOException e) {
            throw new IllegalArgumentException("error file open from assets: " + fileName);
        }
        FileReader fileReader = new FileReaderBuffered(stream);
        ContentParser contentParser = new CsvContentParser("#", 2);
        return new ItemFileRepository(fileReader, contentParser);
    }


}