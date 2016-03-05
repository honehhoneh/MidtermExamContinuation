package app.android.google.com.midtermexamcontinuation;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

import app.android.google.com.midtermexamcontinuation.Api.BookApi;
import app.android.google.com.midtermexamcontinuation.Entities.Book;

public class DetailActivity extends ActionBarActivity {


    int position;
    EditText mTitle;
    EditText mAuthor;
    EditText mGenre;
    CheckBox mIsRead;
    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            throw new RuntimeException("MovieDetailsActivity is expecting an int extra passed by Intent");
        }
        position = bundle.getInt("position", -1);

        if (position == -1) {
            throw new IllegalArgumentException("position passed is invalid.");
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        mTitle = (EditText) findViewById(R.id.edtTitle);
        mGenre = (EditText) findViewById(R.id.edtGenre);
        mAuthor = (EditText) findViewById(R.id.edtAuthor);
        mIsRead = (CheckBox) findViewById(R.id.checkBox);
        menuItem = (MenuItem) findViewById(R.id.action_edit);


        mTitle.setEnabled(false);
        mGenre.setEnabled(false);
        mAuthor.setEnabled(false);
        mIsRead.setEnabled(false);

        FetchDetails fetchDetails = new FetchDetails();
        fetchDetails.execute();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        getMenuInflater().inflate(R.menu.menu_delete, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {

        } else if (id == R.id.action_edit) {

        }else if (id == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public class FetchDetails extends AsyncTask<String, Void, ArrayList<Book>> {

        @Override
        protected ArrayList<Book> doInBackground(String... params) {

            return BookApi.getBooks("http://joseniandroid.herokuapp.com/api/books");
        }

        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            super.onPostExecute(books);
            Book book = books.get(position);
            mTitle.setText(book.getmTitle());
            mAuthor.setText(book.getmAuthor());
            mGenre.setText(book.getmGenre());
            if(book.isRead()==true){
                mIsRead.setChecked(true);
            }



        }
    }




}
