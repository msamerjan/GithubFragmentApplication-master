package edu.lclark.githubfragmentapplication;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import edu.lclark.githubfragmentapplication.models.GithubFollower;

/**
 * Created by maiaphoebedylansamerjan on 3/3/16.
 */
public class LoginAsyncTask extends AsyncTask<String, Integer, Boolean> {


    public static final String TAG = LoginAsyncTask.class.getSimpleName();
    private final LoginListener mLoginListener;

    public interface LoginListener {
        void ;
    }

    public LoginAsyncTask(LoginListener listener) {
        mLoginListener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "Started AsyncTask");
    }

    @Override
    protected Boolean doInBackground(String... params) {

        StringBuilder responseBuilder = new StringBuilder();
        if (params.length == 0) {
            return null;
        }

        String userId = params[0];

        try {
            URL url = new URL("https://api.github.com/users/" + userId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();


            InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(inputStream);
            String line;

            if (isCancelled()) {
                return null;
            }
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);

                if (isCancelled()) {
                    return null;
                }
            }



            if (isCancelled()) {
                return null;
            }
        } catch (IOException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }

        return true;
    }


    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        login(result);
    }
}
