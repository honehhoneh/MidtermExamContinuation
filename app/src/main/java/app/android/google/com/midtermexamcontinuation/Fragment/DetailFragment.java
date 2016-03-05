package app.android.google.com.midtermexamcontinuation.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import app.android.google.com.midtermexamcontinuation.R;

public class DetailFragment extends Fragment {


    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    EditText mTitle, mGenre, mAuthor;
    CheckBox mIsRead;
    MenuItem menuItem;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_details, container, false);
    }




}
