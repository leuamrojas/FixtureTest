package com.manuelrojas.fixture.presentation.view.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.manuelrojas.fixture.R;
import com.manuelrojas.fixture.presentation.di.component.FixtureComponent;
import com.manuelrojas.fixture.presentation.model.FixtureModel;
import com.manuelrojas.fixture.presentation.view.FixtureListView;
import com.manuelrojas.fixture.presentation.view.adapter.FixtureListAdapter;
import com.manuelrojas.fixture.data.utils.NetworkUtil;
import com.manuelrojas.fixture.presentation.presenter.FixtureListPresenter;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FixtureListFragment extends BaseFragment implements FixtureListView,
        SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "FixtureListFragment";

    @Inject
    FixtureListPresenter fixtureListPresenter;

    @Inject
    FixtureListAdapter fixtureListAdapter;

    @BindView(R.id.swipeRefreshFixtures)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.rv_fixtures)
    RecyclerView rvFixtures;

    @BindView(R.id.rl_progress)
    RelativeLayout rlProgress;

    @BindView(R.id.rl_retry)
    RelativeLayout rlRetry;

    @BindView(R.id.bt_retry)
    Button btRetry;

    @Inject
    NetworkUtil networkUtil;

    private Unbinder unbinder;

    private LinearLayoutManager layoutManager;

    private boolean refreshing = false;

    public FixtureListFragment() {
        setRetainInstance(true);
    }

    private static final String PARAM_FIXTURE_TYPE = "param_fixture_type";

    public static FixtureListFragment forType(int fixtureType) {
        final FixtureListFragment fixtureListFragment = new FixtureListFragment();
        final Bundle arguments = new Bundle();
        arguments.putInt(PARAM_FIXTURE_TYPE, fixtureType);
        fixtureListFragment.setArguments(arguments);
        return fixtureListFragment;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(FixtureComponent.class).inject(this);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_fixture_list, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.fixtureListPresenter.attachView(this);
        if (savedInstanceState == null) {
            loadFixtureList(getFixtureType());
        }
    }
	
	@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            filterAdapterView("");
        }
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        rvFixtures.setAdapter(null);
        if (unbinder != null)
            unbinder.unbind();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        this.fixtureListPresenter.detachView();
    }

    @Override public void onDetach() {
        super.onDetach();
    }

    @Override public void showLoading() {
        if ( !refreshing ) {
            this.rlProgress.setVisibility(View.VISIBLE);
            this.getActivity().setProgressBarIndeterminateVisibility(true);
        }
    }

    @Override public void hideLoading() {
        if ( !refreshing ) {
            this.rlProgress.setVisibility(View.GONE);
            this.getActivity().setProgressBarIndeterminateVisibility(false);
        } else {
            swipeRefresh.setRefreshing(false);
            refreshing = false;
        }
    }

    @Override
    public void renderFixtureList(Collection<FixtureModel> fixtureModelCollection) {
        if (fixtureModelCollection != null) {
            fixtureListAdapter.setFixturesCollection(fixtureModelCollection, getFixtureType());
        }
    }

    @Override public void showRetry() {
        this.rlRetry.setVisibility(View.VISIBLE);
    }

    @Override public void hideRetry() {
        this.rlRetry.setVisibility(View.GONE);
    }

    @Override public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override public Context context() {
        return this.getActivity().getApplicationContext();
    }

    private void setupRecyclerView() {
        setSwipeRefresh();
        this.rvFixtures.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context());
        this.rvFixtures.setLayoutManager(layoutManager);
        this.rvFixtures.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(rvFixtures.getContext(), layoutManager.getOrientation());
        rvFixtures.addItemDecoration(dividerItemDecoration);
        this.rvFixtures.setAdapter(fixtureListAdapter);
    }

    private void setSwipeRefresh() {
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeResources(R.color.colorAccent);
    }

    private void loadFixtureList(int fixtureType) {
//        if (fixtureType==0)
            this.fixtureListPresenter.loadFixtures(fixtureType);
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        loadFixtureList(getFixtureType());
    }

    @Override
    public void onRefresh() {
        fixtureListAdapter.clear();
        refreshing = true;
        loadFixtureList(getFixtureType());
    }

    /**
     * Get fixture type from fragments arguments.
     */
    private int getFixtureType() {
        final Bundle arguments = getArguments();
        return arguments.getInt(PARAM_FIXTURE_TYPE);
    }

    public void filterAdapterView(String query) {
        fixtureListAdapter.getFilter().filter(query);
    }
}
