package bak.mateusz.sourcemeter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;

import bak.mateusz.sourcemeter.model.Project;
import bak.mateusz.sourcemeter.network.NetworkCalls;
import bak.mateusz.sourcemeter.widgets.Fab;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity representing a list of Projects. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ProjectDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ProjectListActivity extends AppCompatActivity {
    public static final String CHECKED_PROJECT_NAME = "checkedProjectName";
    private boolean mTwoPane;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.project_list) View recyclerView;
    Fab chartsButton;
    View sheetView, overlay;
    Snackbar snackbar;
    List<Project> projectsList;
    String checkedProjectName;
    MaterialSheetFab materialSheetFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        if(savedInstanceState != null){
            String checkedTitle = savedInstanceState.getString(CHECKED_PROJECT_NAME);
            this.checkedProjectName = checkedTitle;
        }

        setSnackbar(); //TODO: don't show snackbar for a while at the very beginning, add waiting process
        if (findViewById(R.id.project_detail_container) != null) {
            chartsButton = (Fab) findViewById(R.id.fab);
            sheetView = findViewById(R.id.fab_sheet);
            overlay = findViewById(R.id.overlay);

            materialSheetFab = new MaterialSheetFab(chartsButton,sheetView,overlay,R.color.background_card,R.color.colorAccent);


            mTwoPane = true;
            if(savedInstanceState != null){
                toolbar.setSubtitle(checkedProjectName);
            }
        }
    }

    private void setSnackbar() {
        snackbar = Snackbar.make(recyclerView, "Cannot download projects list", Snackbar.LENGTH_LONG);
        snackbar.setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getApplication().onCreate(); //TODO: unsafe
                snackbar.dismiss();
            }
        }).setDuration(Snackbar.LENGTH_INDEFINITE);
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);

        /*Keep snackbar showed on screen rotation if no project list is obtained*/
        if(projectsList == null) snackbar.show();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(projectsList));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Project> mValues;

        public SimpleItemRecyclerViewAdapter(List<Project> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).getProjectName());
            holder.mContentView.setText(mValues.get(position).getProjectLanguage());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putInt(ProjectDetailFragment.ARG_ITEM_ID, holder.mItem.getUid());
                        ProjectDetailFragment fragment = new ProjectDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.project_detail_container, fragment)
                                .commit();
                        toolbar.setSubtitle(holder.mItem.getProjectName());
                        v.setSelected(true);
                        checkedProjectName = holder.mItem.getProjectName(); //To save project name on screen rotation
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ProjectDetailActivity.class);
                        intent.putExtra(ProjectDetailFragment.ARG_ITEM_ID, holder.mItem.getUid());
                        context.startActivity(intent);
                    }
                    try {
                        NetworkCalls.getDevelopersList(holder.mItem.getUid());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            @BindView(R.id.text1) TextView mIdView;
            @BindView(R.id.text2) TextView mContentView;
            Project mItem;
            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
                mView = view;
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onProjectsListEvent(List<Project> event){
        if(this.projectsList == null) {
            this.projectsList = event;
            snackbar.dismiss();
            setupRecyclerView((RecyclerView) recyclerView);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetworkError(Throwable t){
        setSnackbar();
        snackbar.show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(checkedProjectName != null)
            outState.putString(CHECKED_PROJECT_NAME, checkedProjectName);
        super.onSaveInstanceState(outState);
    }
}