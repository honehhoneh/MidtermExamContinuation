package app.android.google.com.midtermexamcontinuation.Api;


import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.android.google.com.midtermexamcontinuation.Entities.Book;
import app.android.google.com.midtermexamcontinuation.Utils.HttpUtils;

public class BookApi {

    public static final String GET_URL = "http://joseniandroid.herokuapp.com/api/books";

    private static final String B_ID = "_id";
    private static final String B_TITLE = "title";
    private static final String B_GENRE = "genre";
    private static final String B_AUTHOR = "author";
    private static final String B_ISREAD = "isRead";

    public static List<Book> getBooks() {
        String json = HttpUtils.GET(GET_URL);

        if (TextUtils.isEmpty(json)) {
            return null;
        }

        String id;
        String title;
        String genre;
        String author;
        Boolean isRead;

        JSONArray jsonArray;

        JSONObject jsonObject;

        List<Book> booksList = new ArrayList<>();

        try {
            jsonArray = new JSONArray(json);
            Log.d("JSON ARRAY SIZE", "" + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);

                id = jsonObject.getString(B_ID);
                title = jsonObject.getString(B_TITLE);
                genre = jsonObject.getString(B_GENRE);
                author = jsonObject.getString(B_AUTHOR);
                isRead = jsonObject.getBoolean(B_ISREAD);

                booksList.add(new Book(id, title, genre, author, isRead));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return booksList;
    }

}