package bak.mateusz.sourcemeter;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import bak.mateusz.sourcemeter.model.Result;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    @BindView(R.id.projectLanguage) TextView projectLanguage;
    @BindView(R.id.qualityModel) TextView qualityModel;
    @BindView(R.id.currentVersion) TextView currentVersion;
    @BindView(R.id.quality) TextView quality;
    @BindView(R.id.qualityChange) TextView qualityChange;
    @BindView(R.id.threshold) TextView threshold;
    private Unbinder unbinder;
    private Result mItem;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.project_detail, container, false);
        unbinder = ButterKnife.bind(this,rootView);
        appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);

        if (appBarLayout != null) {
            appBarLayout.setTitle(mItem.getProjectName());
        }

        // Show the  content as text in a TextView.
        if (mItem != null) {
            projectLanguage.append(mItem.getProjectLanguage());
            qualityModel.append(mItem.getQualityModel());
            currentVersion.append(String.valueOf(mItem.getCurrentVersion()));
            quality.append(String.valueOf(mItem.getQuality()));
            qualityChange.append(String.valueOf(mItem.getQualityChange()));
            threshold.append(String.valueOf(mItem.getThreshold()));
        }
        return rootView;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onProjectsListEvent(Map<Integer, Result> event){
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
}
