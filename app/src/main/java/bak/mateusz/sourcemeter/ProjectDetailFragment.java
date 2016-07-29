package bak.mateusz.sourcemeter;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import bak.mateusz.sourcemeter.model.DeveloperItem;
import bak.mateusz.sourcemeter.model.Project;
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
public class ProjectDetailFragment extends Fragment  {
    Activity activity;
    View rootView;
    CollapsingToolbarLayout appBarLayout;
    @BindView(R.id.project_details) RecyclerView projectDetails;
    private SectionedRecyclerViewAdapter sectionAdapter;
    private Unbinder unbinder;
    private Project mItem;
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    private List<DeveloperItem> developersList;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.project_detail, container, false);
        unbinder = ButterKnife.bind(this,rootView);
        appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        sectionAdapter = new SectionedRecyclerViewAdapter();
        sectionAdapter.addSection(new ProjectDetailsSection(Project.getProjectStringDetails(mItem, getContext()),"Project details"));

        if (appBarLayout != null) {
            appBarLayout.setTitle(mItem.getProjectName());
        }

        if (mItem != null) {
            projectDetails.setAdapter(sectionAdapter);
            projectDetails.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        return rootView;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.POSTING)
    public void onProjectsListEvent(Map<Integer, Project> event){
        if (getArguments().containsKey(ARG_ITEM_ID))
            this.mItem = event.get(getArguments().getInt(ARG_ITEM_ID));
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
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            DeveloperItemHolder itemHolder = (DeveloperItemHolder) holder;
            itemHolder.developerName.setText(items.get(position).getName());

            Double qualityChange = items.get(position).getQualityChange();
            String qualityChangeString = getRoundedString(qualityChange);
            if(qualityChange < 0){
                itemHolder.qualityChange.setTextColor(ContextCompat.getColor(getContext(),R.color.negativeChange));
                itemHolder.qualityChange.setText("");
            }
            else {
                itemHolder.qualityChange.setTextColor(ContextCompat.getColor(getContext(),R.color.positiveChange));
                itemHolder.qualityChange.setText("+");
            }
            itemHolder.qualityChange.append(qualityChangeString + "%");

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
        public DeveloperItemHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
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
        this.developersList = event;

        sectionAdapter.addSection(new DevelopersSection(developersList,"Developers"));
        sectionAdapter.notifyDataSetChanged();
        EventBus.getDefault().removeStickyEvent(event);

    }

    static final private DecimalFormat df = new DecimalFormat("#.####");
    private String getRoundedString(double value){
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(value);
    }
}
