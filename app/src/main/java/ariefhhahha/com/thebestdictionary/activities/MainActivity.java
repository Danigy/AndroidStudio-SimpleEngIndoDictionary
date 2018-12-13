package ariefhhahha.com.thebestdictionary.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ariefhhahha.com.thebestdictionary.AppPreference;
import ariefhhahha.com.thebestdictionary.R;
import ariefhhahha.com.thebestdictionary.database.KamusIndoInggrisHelper;
import ariefhhahha.com.thebestdictionary.database.KamusInggrisIndoHelper;
import ariefhhahha.com.thebestdictionary.models.IndoInggrisModel;
import ariefhhahha.com.thebestdictionary.models.InggrisIndoModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.progress_bar) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        new LoadData().execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadData extends AsyncTask<Void, Integer, Void> {

        KamusIndoInggrisHelper kamusIndoInggrisHelper;
        KamusInggrisIndoHelper kamusInggrisIndoHelper;

        AppPreference appPreference;

        double progress;
        double maxProgress = 100;

        @Override
        protected void onPreExecute() {
            kamusIndoInggrisHelper = new KamusIndoInggrisHelper(MainActivity.this);
            kamusInggrisIndoHelper = new KamusInggrisIndoHelper(MainActivity.this);

            appPreference = new AppPreference(MainActivity.this);
        }

        @Override
        protected Void doInBackground(Void... params) {

            Boolean firstRun = appPreference.getFirstRun();

            if (firstRun) {
                ArrayList<IndoInggrisModel> indoInggrisModels = preLoadRawIndoInggris();
                ArrayList<InggrisIndoModel> inggrisIndoModels = preLoadRawInggrisIndo();

                progress = 30;
                publishProgress((int) progress);
                Double progressMaxInsert = 80.0;
                Double progressDiff = (progressMaxInsert - progress) / (indoInggrisModels.size() + inggrisIndoModels.size());

                /////////////////////

                kamusIndoInggrisHelper.open();

                kamusIndoInggrisHelper.beginTransaction();

                try {
                    for (IndoInggrisModel model : indoInggrisModels) {
                        kamusIndoInggrisHelper.insertTransactionIndoInggris(model);
                        progress += progressDiff;
                        publishProgress((int) progress);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                kamusIndoInggrisHelper.setTransactionSuccess();
                kamusIndoInggrisHelper.endTransaction();

                kamusIndoInggrisHelper.close();

                /////////////////////

                kamusInggrisIndoHelper.open();

                kamusInggrisIndoHelper.beginTransaction();

                try {
                    for (InggrisIndoModel model : inggrisIndoModels) {
                        kamusInggrisIndoHelper.insertTransactionInggrisIndo(model);
                        progress += progressDiff;
                        publishProgress((int) progress);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                kamusInggrisIndoHelper.setTransactionSuccess();
                kamusInggrisIndoHelper.endTransaction();

                kamusInggrisIndoHelper.close();

                ////////////////////

                appPreference.setFirstRun(false);

                publishProgress((int) maxProgress);

            } else {

                try {
                    synchronized (this) {
                        this.wait(1000);

                        publishProgress(50);

                        this.wait(1000);
                        publishProgress((int) maxProgress);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent i = new Intent(MainActivity.this, IndoInggrisActivity.class);
            startActivity(i);
            finish();
        }
    }

    public ArrayList<IndoInggrisModel> preLoadRawIndoInggris() {
        ArrayList<IndoInggrisModel> indoInggrisModels = new ArrayList<>();
        String line;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.indonesia_english);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;

            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                IndoInggrisModel indoInggrisModel;

                indoInggrisModel = new IndoInggrisModel(splitstr[0], splitstr[1]);
                indoInggrisModels.add(indoInggrisModel);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return indoInggrisModels;
    }

    public ArrayList<InggrisIndoModel> preLoadRawInggrisIndo() {
        ArrayList<InggrisIndoModel> inggrisIndoModels = new ArrayList<>();
        String line;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.english_indonesia);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;

            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                InggrisIndoModel inggrisIndoModel;

                inggrisIndoModel = new InggrisIndoModel(splitstr[0], splitstr[1]);
                inggrisIndoModels.add(inggrisIndoModel);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inggrisIndoModels;
    }
}
