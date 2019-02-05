package com.rugvedinamdar.embibedemo.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.rugvedinamdar.embibedemo.R;
import com.rugvedinamdar.embibedemo.data.model.Movie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MovieDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MovieDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailFragment extends Fragment implements View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String POSITION = "position";
    private static final String MOVIE_LIST = "movieList";
    private static final String LOG_TAG = MovieDetailFragment.class.getSimpleName();
    WebView mWebView;
    Button mPrevious, mNext;
    ProgressDialog pd;
    private int position;
    private ArrayList<Movie> movieList;
    private OnFragmentInteractionListener mListener;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param position Imdb movie Id.
     * @return A new instance of fragment MovieDetailFragment.
     */
    public static MovieDetailFragment newInstance(int position, ArrayList<Movie> movies) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        args.putParcelableArrayList(MOVIE_LIST, movies);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            position = getArguments().getInt(POSITION);
            movieList = getArguments().getParcelableArrayList(MOVIE_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        pd = ProgressDialog.show(getContext(), "", "Loading...", true);
        mPrevious = (Button) view.findViewById(R.id.btn_prev);
        mPrevious.setOnClickListener(this);
        mNext = (Button) view.findViewById(R.id.btn_next);
        mNext.setOnClickListener(this);
        mWebView = (WebView) view.findViewById(R.id.webview);
        mWebView.loadUrl("https://m.imdb.com/title/" + movieList.get(position).getImdbId());

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (pd != null && pd.isShowing()) {
                    pd.dismiss();
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setTitle(movieList.get(position).getTitle());
        super.onActivityCreated(savedInstanceState);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_prev:
                position = position - 1;
                Log.d(LOG_TAG, "btn_prev:pos:" + position);
                if (position >= 0) {
                    pd.show();
                    ((MainActivity)getActivity()).setTitle(movieList.get(position).getTitle());
                    mWebView.loadUrl("https://m.imdb.com/title/" + movieList.get(position).getImdbId());
                } else {
                    Toast.makeText(getContext(), "End of List", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_next:
                position = position + 1;
                Log.d(LOG_TAG, "btn_next:pos:" + position);
                if (position <= movieList.size() - 1) {
                    pd.show();
                    ((MainActivity)getActivity()).setTitle(movieList.get(position).getTitle());
                    mWebView.loadUrl("https://m.imdb.com/title/" + movieList.get(position).getImdbId());
                } else {
                    Toast.makeText(getContext(), "End of List", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item=menu.findItem(R.id.action_sort);
        item.setVisible(false);
    }
}
