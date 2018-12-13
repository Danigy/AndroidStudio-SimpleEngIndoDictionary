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
import ariefhhahha.com.thebestdictionary.adapters.InggrisIndoAdapter;
import ariefhhahha.com.thebestdictionary.database.KamusInggrisIndoHelper;
import ariefhhahha.com.thebestdictionary.models.InggrisIndoModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class InggrisIndoActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview) RecyclerView recyclerView;

    InggrisIndoAdapter inggrisIndoAdapter;
    //KamusIndoInggrisHelper kamusIndoInggrisHelper;
    KamusInggrisIndoHelper kamusInggrisIndoHelper;
    String searchQuery;
    static  final String SEARCH = "search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inggris_indo);

        ButterKnife.bind(this);

        inggrisIndoAdapter = new InggrisIndoAdapter(this);
        kamusInggrisIndoHelper = new KamusInggrisIndoHelper(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(inggrisIndoAdapter);

        kamusInggrisIndoHelper.open();
        ArrayList<InggrisIndoModel> inggrisIndoModels = kamusInggrisIndoHelper.getAllInggrisIndoData();

        kamusInggrisIndoHelper.close();

        inggrisIndoAdapter.addItem(inggrisIndoModels);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Eng to Indo");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        kamusInggrisIndoHelper.open();

        ArrayList<InggrisIndoModel> inggrisIndoModels = kamusInggrisIndoHelper.getDataInggrisIndoByName(savedInstanceState.getString(SEARCH));
        searchQuery = savedInstanceState.getString(SEARCH);
        kamusInggrisIndoHelper.close();

        if ((inggrisIndoModels).isEmpty()) {
            Toast.makeText(getApplicationContext(), "Not Found :(", Toast.LENGTH_SHORT).show();
        }

        inggrisIndoAdapter.addItem(inggrisIndoModels);
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

                    kamusInggrisIndoHelper.open();

                    ArrayList<InggrisIndoModel> inggrisIndoModels = kamusInggrisIndoHelper.getDataInggrisIndoByName(query);

                    kamusInggrisIndoHelper.close();

                    if ((inggrisIndoModels).isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Not Found :(", Toast.LENGTH_SHORT).show();
                    }

                    inggrisIndoAdapter.addItem(inggrisIndoModels);
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
                Intent intent = new Intent(InggrisIndoActivity.this, IndoInggrisActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_inggris_ke_indo :
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
