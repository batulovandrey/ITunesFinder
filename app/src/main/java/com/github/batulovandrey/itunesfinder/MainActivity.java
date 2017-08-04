package com.github.batulovandrey.itunesfinder;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.github.batulovandrey.itunesfinder.bean.Track;
import com.github.batulovandrey.itunesfinder.bean.TrackResponse;
import com.github.batulovandrey.itunesfinder.net.ApiClient;
import com.github.batulovandrey.itunesfinder.net.TrackService;
import com.github.batulovandrey.itunesfinder.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainFragment.OnItemClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setToolbar();
        handleIntent(getIntent());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mToolbar.clearFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public void onItemClick(Track track) {
        startActivity(TrackDetailActivity.createExplicitIntent(this, track));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    // region private methods

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            if (Utils.hasConnection(this)) {
                getDataFromServer(query);
            } else {
                showNoConnectionMessage();
            }
        }
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
    }

    private void getDataFromServer(String query) {
        TrackService apiService = ApiClient.getRetrofit().create(TrackService.class);
        Call<TrackResponse> responseCall = apiService.getTracks(query);

        responseCall.enqueue(new Callback<TrackResponse>() {
            @Override
            public void onResponse(@NonNull Call<TrackResponse> call, @NonNull Response<TrackResponse> response) {
                TrackResponse trackResponse = response.body();
                if (trackResponse != null) {
                    startFragment(trackResponse);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TrackResponse> call, @NonNull Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void startFragment(TrackResponse response) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, MainFragment.newInstance(response))
                .commit();
    }

    private void showNoConnectionMessage() {
        Toast.makeText(this, getString(R.string.no_connection), Toast.LENGTH_SHORT).show();
    }

    // endregion
}