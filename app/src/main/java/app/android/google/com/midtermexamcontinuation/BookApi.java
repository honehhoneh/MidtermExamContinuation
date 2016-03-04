package app.android.google.com.midtermexamcontinuation;


import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class BookApi {

    public static final String GET_URL = "http://joseniandroid.herokuapp.com/api/books";

    private static final String BOOK_ID = "_id";
    private static final String BOOK_TITLE = "title";
    private static final String BOOK_GENRE = "genre";
    private static final String BOOK_AUTHOR = "author";
    private static final String BOOK_ISREAD = "isRead";

    public static List<Book> getBooks() {
        String json = HttpUtils.GET(GET_URL);

        if (TextUtils.isEmpty(json)) {
            return null;
        }

        //Parse the json response and convert it into a Book object
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

                id = jsonObject.getString(BOOK_ID);
                title = jsonObject.getString(BOOK_TITLE);
                genre = jsonObject.getString(BOOK_GENRE);
                author = jsonObject.getString(BOOK_AUTHOR);
                isRead = jsonObject.getBoolean(BOOK_ISREAD);

                booksList.add(new Book(id, title, genre, author, isRead));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return booksList;
    }

}
