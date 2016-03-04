package app.android.google.com.midtermexamcontinuation.Fragment;


import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.android.google.com.midtermexamcontinuation.Adapter.BookAdapter;
import app.android.google.com.midtermexamcontinuation.Api.BookApi;
import app.android.google.com.midtermexamcontinuation.Entities.Book;
import app.android.google.com.midtermexamcontinuation.R;

public class ListViewFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private TextView mTvGettingBooks;
    private ProgressBar mProgBar;

    private List<Book> Libro = new ArrayList<>();
    private BookAdapter adapter;


    public static ListViewFragment newInstance() {
        return new ListViewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FetchBook fb= new FetchBook();
        fb.execute();

    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_listview, container, false);
    }

    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState){
        // find all the views
        mListView = (ListView) view.findViewById(R.id.mainListView);
        mTvGettingBooks = (TextView) view.findViewById(R.id.tvProgressGettingBooks);
        mProgBar = (ProgressBar) view.findViewById(R.id.progressBarLoadingBooks);

        // create a new instance of adapter
        adapter = new BookAdapter(getActivity(), R.layout.list_item, Libro);

        // set the adapter
        mListView.setAdapter(adapter);


        // set item click listener
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    public class FetchBook extends AsyncTask<String, Void, List<Book>> {
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected List<Book> doInBackground(String... params) {

            return BookApi.getBooks();
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            super.onPostExecute(books);
            mTvGettingBooks.setVisibility(View.GONE);
            mProgBar.setVisibility(View.GONE);
            adapter.addAll(books);
        }
    }
}
