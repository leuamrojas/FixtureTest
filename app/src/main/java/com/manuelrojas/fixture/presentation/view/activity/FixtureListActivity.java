package com.manuelrojas.fixture.presentation.view.activity;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import com.google.android.material.tabs.TabLayout;
import com.manuelrojas.fixture.R;
import com.manuelrojas.fixture.presentation.di.HasComponent;
import com.manuelrojas.fixture.presentation.di.component.DaggerFixtureComponent;
import com.manuelrojas.fixture.presentation.di.component.FixtureComponent;
import com.manuelrojas.fixture.presentation.view.MainPagerAdapter;
import com.manuelrojas.fixture.presentation.view.fragment.FixtureListFragment;

public class FixtureListActivity extends BaseActivity implements HasComponent<FixtureComponent> {

    private SearchView searchView;
    private static final String SEARCH_QUERY = "search_query";
    private static final String ANDROID_ID_SEARCH_BUTTON = "android:id/search_button";
    private static final String ANDROID_ID_SEARCH_SRC_TEXT = "android:id/search_src_text";
    private static final String ANDROID_ID_SEARCH_CLOSE_BTN = "android:id/search_close_btn";
    private static final String VIEW_PAGER_PAGE_0 = "android:switcher:" + R.id.view_pager_fixture + ":" + 0;
    private static final String VIEW_PAGER_PAGE_1 = "android:switcher:" + R.id.view_pager_fixture + ":" + 1;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, FixtureListActivity.class);
    }

    private FixtureComponent fixtureComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixture_list);

        this.initializeInjector();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        setViewPager();

    }

    private void initializeInjector() {
        this.fixtureComponent = DaggerFixtureComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        setupSearchView(searchView);

        return true;
    }

    private void setupSearchView(SearchView searchView) {

//        Fragment page1 = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager_fixture + ":" + 0);
//        Fragment page2 = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager_fixture + ":" + 1);
        Fragment page1 = getSupportFragmentManager().findFragmentByTag(VIEW_PAGER_PAGE_0);
        Fragment page2 = getSupportFragmentManager().findFragmentByTag(VIEW_PAGER_PAGE_1);

        FixtureListFragment fixturesFragment = (FixtureListFragment) page1;
        FixtureListFragment resultsFragment = (FixtureListFragment) page2;

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                if (fixturesFragment != null) {
                    fixturesFragment.filterAdapterView(query);
                }
                if (resultsFragment != null) {
                    resultsFragment.filterAdapterView(query);
                }
                return false;
            }
        });

        setSearchTextColor(searchView);
    }

    private void setSearchTextColor(SearchView searchView) {
        int searchId = getId(searchView, ANDROID_ID_SEARCH_SRC_TEXT);
        EditText searchPlate = searchView.findViewById(searchId);
        searchPlate.setTextColor(getResources().getColor(R.color.colorWhite));
//        searchPlate.setHintTextColor(getResources().getColor(R.color.colorWhite));
//        searchPlate.setBackgroundResource(R.drawable.edit_text_holo_light);
        searchPlate.setImeOptions(EditorInfo.IME_ACTION_SEARCH);

        searchId = getId(searchView, ANDROID_ID_SEARCH_BUTTON);
        ImageView mSearchHintIcon = searchView.findViewById(searchId);
        mSearchHintIcon.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        searchId = getId(searchView, ANDROID_ID_SEARCH_CLOSE_BTN);
        ImageView closeButton = searchView.findViewById(searchId);
        closeButton.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
    }

    private int getId(SearchView searchView, String strId) {
        return searchView.getContext().getResources().getIdentifier(strId, null, null);
    }

    @Override
    public FixtureComponent getComponent() {
        return fixtureComponent;
    }

    private void setViewPager() {

        MainPagerAdapter tabsPagerAdapter = new MainPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager_fixture);
        viewPager.setAdapter(tabsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs_fixture);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SEARCH_QUERY, searchView.getQuery().toString());
    }
}
