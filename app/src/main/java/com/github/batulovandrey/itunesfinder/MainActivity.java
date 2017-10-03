package com.github.batulovandrey.itunesfinder;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.batulovandrey.itunesfinder.bean.Track;
import com.github.batulovandrey.itunesfinder.bean.TrackResponse;
import com.github.batulovandrey.itunesfinder.presenter.MainPresenter;
import com.github.batulovandrey.itunesfinder.presenter.MainPresenterImpl;
import com.github.batulovandrey.itunesfinder.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Main Activity contains toolbar and container for data
 *
 * @author Andrey Batulov on 01/08/2017
 */

public class MainActivity extends AppCompatActivity
        implements MainFragment.OnItemClickListener, MainView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mMainPresenter = new MainPresenterImpl(this);
        mMainPresenter.onCreate(getIntent());
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
        mMainPresenter.handleIntent(intent);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setToolbar() {
        setSupportActionBar(mToolbar);
    }

    @Override
    public void showNoConnectionError() {
        showToast(R.string.no_connection);
    }

    @Override
    public void showServiceUnavailableError() {
        showToast(R.string.error);
    }

    @Override
    public void showTracksFragment(TrackResponse response) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, MainFragment.newInstance(response))
                .commit();
    }

    @Override
    public void setProgressColor() {
        mProgressBar.getIndeterminateDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
    }

    private void showToast(int messageResId) {
        Toast.makeText(this, getString(messageResId), Toast.LENGTH_LONG).show();
    }
}