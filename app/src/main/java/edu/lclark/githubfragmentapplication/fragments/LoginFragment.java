package edu.lclark.githubfragmentapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import edu.lclark.githubfragmentapplication.LoginAsyncTask;
import edu.lclark.githubfragmentapplication.NetworkAsyncTask;
import edu.lclark.githubfragmentapplication.R;


/**
 * Created by maiaphoebedylansamerjan on 3/3/16.
 */
public class LoginFragment extends Fragment implements LoginAsyncTask.LoginListener{

    LoginAsyncTask mLoginAsyncTask;
    public LoginFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        return rootView;
    }

    public void login(Boolean result) {
        EditText user=(EditText)getActivity().findViewById(R.id.username_entry);
        String username=user.getText().toString();
    }

        @Override
        public void onStart() {
            super.onStart();

            if (mLoginAsyncTask == null ) {
                mLoginAsyncTask = new NetworkAsyncTask(this);
                mLoginAsyncTask.execute(username);
            }
        }

        @Override
        public void onStop() {
            super.onStop();
            if (mLoginAsyncTask != null && !mLoginAsyncTask.isCancelled()) {
                mLoginAsyncTask.cancel(true);
                mLoginAsyncTask = null;
            }
        }





}
