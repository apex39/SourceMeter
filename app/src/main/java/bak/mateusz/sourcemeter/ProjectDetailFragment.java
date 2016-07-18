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

/**
 * A fragment representing a single Project detail screen.
 * This fragment is either contained in a {@link ProjectListActivity}
 * in two-pane mode (on tablets) or a {@link ProjectDetailActivity}
 * on handsets.
 */
public class ProjectDetailFragment extends Fragment  {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    private Result mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProjectDetailFragment() {
    }
    CollapsingToolbarLayout appBarLayout;
    Activity activity;
    View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
           activity = this.getActivity();
           appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.project_detail, container, false);
        if (appBarLayout != null) {
            appBarLayout.setTitle(mItem.getProjectName());
        }

        // Show the  content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.projectLanguage)).append(mItem.getProjectLanguage());
            ((TextView) rootView.findViewById(R.id.qualityModel)).append(mItem.getQualityModel());
            ((TextView) rootView.findViewById(R.id.currentVersion)).append(String.valueOf(mItem.getCurrentVersion()));
            ((TextView) rootView.findViewById(R.id.quality)).append(String.valueOf(mItem.getQuality()));
            ((TextView) rootView.findViewById(R.id.qualityChange)).append(String.valueOf(mItem.getQualityChange()));
            ((TextView) rootView.findViewById(R.id.threshold)).append(String.valueOf(mItem.getThreshold()));
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
}
