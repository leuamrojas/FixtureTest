package com.manuelrojas.fixture.presentation.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.manuelrojas.fixture.R;
import com.manuelrojas.fixture.data.utils.DateTimeUtil;
import com.manuelrojas.fixture.presentation.model.FixtureModel;
import com.manuelrojas.fixture.presentation.model.ScoreModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FixtureListAdapter extends RecyclerView.Adapter<BaseViewHolder> implements Filterable {

    private static final String TAG = "FixtureListAdapter";
    private List<FixtureModel> fixtureModelList;
    private int fixtureType = 0;
    private Context context;

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private static final String POSTPONED = "postponed";

    private List<FixtureModel> originalData;
    private ItemFilter filter = new ItemFilter();

    @Inject
    Picasso picasso;

    @Inject
    FixtureListAdapter(Context context) {
        this.context = context;
        fixtureModelList = Collections.emptyList();
        originalData = fixtureModelList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new FixtureViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.row_fixture, parent, false));
            case VIEW_TYPE_HEADER:
                return new HeaderHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.row_fixture_header, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (fixtureModelList.get(position).isHeader())
            return VIEW_TYPE_HEADER;
        else
            return VIEW_TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return fixtureModelList != null ? fixtureModelList.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void validateFixtureModelCollection(Collection<FixtureModel> fixtureModelCollection) {
        if (fixtureModelCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public void setFixturesCollection(Collection<FixtureModel> fixturesCollection, int fixtureType) {
        this.validateFixtureModelCollection(fixturesCollection);
        this.fixtureModelList = (List<FixtureModel>) fixturesCollection;
        setRowHeaders();
        originalData = fixtureModelList;
        this.fixtureType = fixtureType;
        this.notifyDataSetChanged();
    }

    public void clear() {
        this.fixtureModelList.clear();
        notifyDataSetChanged();
    }

    private void setRowHeaders(){

        String headerMonth = "";
        String headerYear = "";
        List<FixtureModel> listWithHeaders = new ArrayList<>();

        for (FixtureModel fixtureModel : fixtureModelList) {
            String month = DateTimeUtil.getMonth(fixtureModel.getDate());
            String year = DateTimeUtil.getYear(fixtureModel.getDate());
            if ( !(month).equals(headerMonth) || !(year.equals(headerYear)) ) {
                FixtureModel headerModel = new FixtureModel();
                headerModel.setHeader(true);
                headerModel.setMonthYear(month + " " + year);
                listWithHeaders.add(headerModel);
                headerMonth = month;
                headerYear = year;
            }
            listWithHeaders.add(fixtureModel);
        }
        fixtureModelList.clear();
        fixtureModelList.addAll(listWithHeaders);
    }

    class FixtureViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_fixture_competition_name)
        TextView tvCompetitionName;

        @BindView(R.id.tv_fixture_venue)
        TextView tvVenue;

        @BindView(R.id.tv_fixture_date)
        TextView tvDate;

        @BindView(R.id.tv_fixture_home)
        TextView tvHome;

        @BindView(R.id.tv_fixture_away)
        TextView tvAway;

        @BindView(R.id.tv_fixture_day_month)
        TextView tvDayOfMonth;

        @BindView(R.id.tv_fixture_day_week)
        TextView tvDayOfWeek;

        @BindView(R.id.rl_fixture_date)
        RelativeLayout rlFixtureDate;

        @BindView(R.id.rl_fixture_result)
        RelativeLayout rlFixtureResult;

        @BindView(R.id.tv_fixture_score_home)
        TextView tvScoreHome;

        @BindView(R.id.tv_fixture_score_away)
        TextView tvScoreAway;

        @BindView(R.id.tv_fixture_postponed)
        TextView tvFixturePostponed;

        FixtureViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            final FixtureModel fixtureModel = fixtureModelList.get(position);
            tvCompetitionName.setText(fixtureModel.getCompetitionStage().getCompetition().getName()
                    .toUpperCase());
            tvVenue.setText(fixtureModel.getVenue().getName());
            tvDate.setText(DateTimeUtil.getFullDateTime(fixtureModel.getDate()));
            tvHome.setText(fixtureModel.getHomeTeam().getName());
            tvAway.setText(fixtureModel.getAwayTeam().getName());
            tvDayOfMonth.setText(DateTimeUtil.getDayOfMonth(fixtureModel.getDate()));
            tvDayOfWeek.setText(DateTimeUtil.getDayOfWeekShort(fixtureModel.getDate()).toUpperCase());

            if (fixtureType == 0) {
                rlFixtureDate.setVisibility(View.VISIBLE);
                rlFixtureResult.setVisibility(View.GONE);
                String fixtureState = fixtureModel.getState();
                if (fixtureState!=null && fixtureState.equals(POSTPONED)) {
                    tvFixturePostponed.setVisibility(View.VISIBLE);
                    tvDate.setTextColor(ContextCompat.getColor(context, R.color.colorOrange));
                } else {
                    tvFixturePostponed.setVisibility(View.GONE);
                    tvDate.setTextColor(Color.BLACK);
                }
            } else {
                rlFixtureDate.setVisibility(View.GONE);
                rlFixtureResult.setVisibility(View.VISIBLE);

                ScoreModel scoreModel = fixtureModel.getScore();
                if (scoreModel != null) {
                    tvScoreHome.setText(String.valueOf(scoreModel.getHome()));
                    tvScoreAway.setText(String.valueOf(scoreModel.getAway()));
                    if (scoreModel.getHome() > scoreModel.getAway()) {
                        tvScoreHome.setTextColor(Color.BLUE);
                        tvScoreAway.setTextColor(Color.BLACK);
                    } else if (scoreModel.getHome() < scoreModel.getAway()) {
                        tvScoreAway.setTextColor(Color.BLUE);
                        tvScoreHome.setTextColor(Color.BLACK);
                    } else {
                        tvScoreAway.setTextColor(Color.BLACK);
                        tvScoreHome.setTextColor(Color.BLACK);
                    }
                }
            }

        }
    }

    class HeaderHolder extends BaseViewHolder {

        @BindView(R.id.tv_fixture_header)
        TextView tvFixtureHeader;

        HeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            final FixtureModel fixtureModel = fixtureModelList.get(position);
            if (fixtureModel != null)
                tvFixtureHeader.setText(fixtureModel.getMonthYear());
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase(Locale.ENGLISH);
            final List<FixtureModel> list = originalData;
            int count = list.size();
            final ArrayList<FixtureModel> nlist = new ArrayList<>(count);
            String filterableStringName;
            for (int i = 0; i < count; i++) {
                if (list.get(i).getCompetitionStage() != null) {
                    filterableStringName = list.get(i).getCompetitionStage().getCompetition().getName();
                    if (filterableStringName != null) {
                        if (filterableStringName.trim().toLowerCase(Locale.ENGLISH)
                                .contains(filterString.trim().toLowerCase(Locale.ENGLISH))) {
                            nlist.add(list.get(i));
                        }
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            fixtureModelList = (ArrayList<FixtureModel>) results.values;
            notifyDataSetChanged();
        }

    }

}
