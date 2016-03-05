package app.android.google.com.midtermexamcontinuation.Api;


import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.android.google.com.midtermexamcontinuation.Entities.Book;
import app.android.google.com.midtermexamcontinuation.Utils.HttpUtils;

public class BookApi {

    private static final String B_ID = "_id";
    private static final String B_TITLE = "title";
    private static final String B_GENRE = "genre";
    private static final String B_AUTHOR = "author";
    private static final String B_ISREAD = "isRead";

    public static ArrayList<Book> bookList = new ArrayList<>();

    public static ArrayList<Book> getBooks(String url) {
        String json = HttpUtils.GET(url);

        if (TextUtils.isEmpty(json)) {
            return null;
        }

        String id;
        String title;
        String genre;
        String author;
        boolean isRead;

        try{
            JSONArray jsonArray = new JSONArray(json);
            for(int i=0; i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                id = jsonObject.getString(B_ID);
                title = jsonObject.getString(B_TITLE);
                genre = jsonObject.getString(B_GENRE);
                author = jsonObject.getString(B_AUTHOR);
                isRead = jsonObject.getBoolean(B_ISREAD);

                bookList.add(new Book(id,title,genre,author,isRead));

            }
            return bookList;
        }catch(JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}