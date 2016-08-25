package bak.mateusz.sourcemeter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.gordonwong.materialsheetfab.MaterialSheetFab;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bak.mateusz.sourcemeter.model.DeveloperItem;
import bak.mateusz.sourcemeter.model.Project;
import bak.mateusz.sourcemeter.model.ProjectQualityTimeline;
import bak.mateusz.sourcemeter.network.NetworkCalls;
import bak.mateusz.sourcemeter.widgets.Fab;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * A fragment representing a single Project detail screen.
 * This fragment is either contained in a {@link ProjectListActivity}
 * in two-pane mode (on tablets) or a {@link ProjectDetailActivity}
 * on handsets.
 */
public class ProjectDetailFragment extends Fragment implements View.OnClickListener {

    Activity activity;
    View rootView;
    CollapsingToolbarLayout appBarLayout;
    @BindView(R.id.project_details) RecyclerView projectDetails;
    MaterialSheetFab materialSheetFab;
    private SectionedRecyclerViewAdapter sectionAdapter;
    private Unbinder unbinder;
    private Project project;
    Fab chartsButton;
    ProjectQualityTimeline projectQualityTimeline;
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProjectDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        activity = this.getActivity();
        
    }
public void setupFab(){
    chartsButton = (Fab) getActivity().findViewById(R.id.fab);
    chartsButton.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_timeline));
    View sheetView = getActivity().findViewById(R.id.fab_sheet);
    View overlay = getActivity().findViewById(R.id.overlay);
    materialSheetFab = new MaterialSheetFab(chartsButton,sheetView,overlay,R.color.background_dim_overlay,R.color.colorAccent);

    getActivity().findViewById(R.id.fab_sheet_item_daytime).setOnClickListener(this);
    getActivity().findViewById(R.id.fab_sheet_item_week).setOnClickListener(this);
    getActivity().findViewById(R.id.fab_sheet_item_year).setOnClickListener(this);
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.project_detail, container, false);



        setupFab();
        unbinder = ButterKnife.bind(this,rootView);
        appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        sectionAdapter = new SectionedRecyclerViewAdapter();
        sectionAdapter.addSection(new ProjectDetailsSection(Project.getProjectStringDetails(project, getContext()),"Project details"));

        if (appBarLayout != null) {
            appBarLayout.setTitle(project.getProjectName());
        }

        if (project != null) {
            projectDetails.setAdapter(sectionAdapter);
            projectDetails.setLayoutManager(new LinearLayoutManager(getContext()));
        }


        return rootView;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.POSTING)
    public void onProjectsListEvent(Map<Integer, Project> event){
        if (getArguments().containsKey(ARG_ITEM_ID))
            this.project = event.get(getArguments().getInt(ARG_ITEM_ID));
    }

    public void getQuality()  { //TODO:butterknife catching exceptions, butterknife onClick in fragments
        try {
            NetworkCalls.getQualityTimeline(project.getUid());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        getQuality();
        switch(view.getId()){
            case R.id.fab_sheet_item_daytime:
                showDaysPickers(R.id.fab_sheet_item_daytime);
                break;
            case R.id.fab_sheet_item_week:
                showDaysPickers(R.id.fab_sheet_item_week);
                break;
            case R.id.fab_sheet_item_year:
                showYearPicker();
                break;
        }


    }

    LocalDate startDate, endDate;
    private void showDaysPickers(final int viewId){
        if(startDate == null && endDate == null) {
            startDate = LocalDate.now();
            endDate = LocalDate.now();
        }
        final DatePickerDialog startDatePicker = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        startDate = new LocalDate(year, monthOfYear + 1, dayOfMonth);

                        DatePickerDialog finalDatePicker = new DatePickerDialog(getContext(),
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {

                                        endDate = new LocalDate(year, monthOfYear + 1 , dayOfMonth);

                                        switch (viewId){
                                            case R.id.fab_sheet_item_daytime:
                                                List<Double> dayStatistics = projectQualityTimeline.getDayStatistics(startDate, endDate);
                                                materialSheetFab.hideSheet();
                                                createChart(dayStatistics, ChartFragment.DAY);

                                                break;
                                            case R.id.fab_sheet_item_week:
                                                List<Double> weekStatistics = projectQualityTimeline.getWeekStatistics(startDate, endDate);
                                                materialSheetFab.hideSheet();
                                                createChart(weekStatistics, ChartFragment.WEEK);
                                                break;
                                        }
                                    }
                                }, endDate.getYear(), endDate.getMonthOfYear()-1, endDate.getDayOfMonth());

                        finalDatePicker.getDatePicker().setMinDate(startDate.toDateTimeAtCurrentTime().getMillis());
                        finalDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
                        finalDatePicker.show();
                    }
                }, startDate.getYear(), startDate.getMonthOfYear() - 1, startDate.getDayOfMonth());
        startDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
        startDatePicker.show();
    }

    LocalDate year;
    private void showYearPicker() {
        if(year == null) {
            year = LocalDate.now();
        }
        final Dialog d = new Dialog(getContext());
        d.setTitle("Choose year");
        d.setContentView(R.layout.year_dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(new LocalDate().getYear());
        np.setMinValue(2005);
        np.setValue(new LocalDate().getYear());
        np.setWrapSelectorWheel(false);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                year = new LocalDate(np.getValue(),1,1);
                createChart(projectQualityTimeline.getYearStatistics(year), ChartFragment.YEAR);
                materialSheetFab.hideSheet();
                d.dismiss();
            }
        });
        d.show();

    }
    private void createChart(List<Double> statistics, String statisticType){
        ChartFragment chartFragment = new ChartFragment();
        Bundle args = new Bundle();
        args.putSerializable(ChartFragment.STATISTICS, new ArrayList<>(statistics));
        args.putString(ChartFragment.STAT_TYPE, statisticType);
        chartFragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.project_detail_container, chartFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onQualityTimelineEvent(ProjectQualityTimeline event){
        projectQualityTimeline = event;

    }

    class ProjectDetailsSection extends StatelessSection {
        List<Project.StringPair> items;
        String title;

        public ProjectDetailsSection(List<Project.StringPair> items, String title) {
            super(R.layout.list_header, R.layout.item_list);
            this.title = title;
            this.items = items;
        }

        @Override
        public int getContentItemsTotal() {
            return items.size();
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            itemHolder.text1.setText(items.get(position).getFristString());
            itemHolder.text2.setText(items.get(position).getSecondString());
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.header.setText(title);
        }
    }
    class DevelopersSection extends StatelessSection {
        List<DeveloperItem> items;
        String title;

        public DevelopersSection(List<DeveloperItem> items, String title) {
            super(R.layout.list_header, R.layout.item_developer_list);
            this.title = title;
            this.items = items;
        }

        @Override
        public int getContentItemsTotal() {
            return items.size();
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new DeveloperItemHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, final int position) {
            DeveloperItemHolder itemHolder = (DeveloperItemHolder) holder;
            itemHolder.developerName.setText(items.get(position).getName());

            Double qualityChange = items.get(position).getQualityChange();
            BigDecimal qualityChangeRounded = getRoundedDouble(qualityChange);
            if(qualityChangeRounded.signum() == -1) {
                itemHolder.qualityChange.setTextColor(ContextCompat.getColor(getContext(),R.color.negativeChange));
                itemHolder.qualityChange.setText("");
            } else if (qualityChangeRounded.signum() == 1) {
                itemHolder.qualityChange.setTextColor(ContextCompat.getColor(getContext(),R.color.positiveChange));
                itemHolder.qualityChange.setText("+");
            } else if (qualityChangeRounded.signum() == 0) {
                itemHolder.qualityChange.setTextColor(ContextCompat.getColor(getContext(),R.color.noChange));
                itemHolder.qualityChange.setText("");
            }
            itemHolder.qualityChange.append(qualityChangeRounded + "%");
            itemHolder.commitsNumber.setText(items.get(position).getCommits().toString());


        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.header.setText(title);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text1) TextView text1;
        @BindView(R.id.text2) TextView text2;
        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    class DeveloperItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.developer_name) TextView developerName;
        @BindView(R.id.quality_change) TextView qualityChange;
        @BindView(R.id.commits_number) TextView commitsNumber;
        View rootView;
        public DeveloperItemHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            rootView = view;
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.headerTitle) TextView header;
        public HeaderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onDevelopersListEvent(List<DeveloperItem> event){
        List<DeveloperItem> developersList = event;

        sectionAdapter.addSection(new DevelopersSection(developersList, "Developers"));
        sectionAdapter.notifyDataSetChanged();
        EventBus.getDefault().removeStickyEvent(event);
    }

    private BigDecimal getRoundedDouble(Double value){
        return BigDecimal.valueOf(value).setScale(4, BigDecimal.ROUND_HALF_UP);
    }
}
