package ariefhhahha.com.thebestdictionary.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import ariefhhahha.com.thebestdictionary.R;
import ariefhhahha.com.thebestdictionary.adapters.IndoInggrisAdapter;
import ariefhhahha.com.thebestdictionary.database.KamusIndoInggrisHelper;
import ariefhhahha.com.thebestdictionary.models.IndoInggrisModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class IndoInggrisActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview) RecyclerView recyclerView;

    IndoInggrisAdapter indoInggrisAdapter;
    KamusIndoInggrisHelper kamusIndoInggrisHelper;
    String searchQuery;
    static  final String SEARCH = "search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indo_inggris);

        ButterKnife.bind(this);

        indoInggrisAdapter = new IndoInggrisAdapter(this);
        kamusIndoInggrisHelper = new KamusIndoInggrisHelper(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(indoInggrisAdapter);

        kamusIndoInggrisHelper.open();

        ArrayList<IndoInggrisModel> indoInggrisModels = kamusIndoInggrisHelper.getAllIndoInggrisData();

        kamusIndoInggrisHelper.close();

        indoInggrisAdapter.addItem(indoInggrisModels);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Indo to Eng");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        kamusIndoInggrisHelper.open();

        ArrayList<IndoInggrisModel> indoInggrisModels = kamusIndoInggrisHelper.getDataIndoInggrisByName(savedInstanceState.getString(SEARCH));
        searchQuery = savedInstanceState.getString(SEARCH);
        kamusIndoInggrisHelper.close();

        if ((indoInggrisModels).isEmpty()) {
            Toast.makeText(getApplicationContext(), "Not Found :(", Toast.LENGTH_SHORT).show();
        }

        indoInggrisAdapter.addItem(indoInggrisModels);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchManager != null) {
            SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchQuery = query;

                    kamusIndoInggrisHelper.open();

                    ArrayList<IndoInggrisModel> indoInggrisModels = kamusIndoInggrisHelper.getDataIndoInggrisByName(query);

                    kamusIndoInggrisHelper.close();

                    if ((indoInggrisModels).isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Not Found :(", Toast.LENGTH_SHORT).show();
                    }

                    indoInggrisAdapter.addItem(indoInggrisModels);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_indo_ke_inggris :
                break;
            case R.id.menu_inggris_ke_indo :
                Intent intent = new Intent(IndoInggrisActivity.this, InggrisIndoActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SEARCH, searchQuery);
    }
}
