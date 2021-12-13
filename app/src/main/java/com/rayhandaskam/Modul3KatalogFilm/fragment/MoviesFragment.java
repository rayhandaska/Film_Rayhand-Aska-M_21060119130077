package com.rayhandaskam.Modul3KatalogFilm.fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.rayhandaskam.Modul3KatalogFilm.R;
import com.rayhandaskam.Modul3KatalogFilm.adapter.MoviesAdapter;
import com.rayhandaskam.Modul3KatalogFilm.model.Movies;
import com.rayhandaskam.Modul3KatalogFilm.viewModel.MoviesViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    private MoviesAdapter adapter;
    private ProgressBar progressBar;
    private MoviesViewModel moviesViewModel;

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        adapter = new MoviesAdapter();
        View view = inflater.inflate(R.layout.fragment_movies,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.getMovies().observe(this, getMovie);
        moviesViewModel.setMovies("EXTRA_MOVIE");

        showLoading(true);

        return view;
    }
    private Observer<ArrayList<Movies>> getMovie = new Observer<ArrayList<Movies>>() {
        @Override
        public void onChanged(ArrayList<Movies> movies) {
            if (movies != null) {
                adapter.setData(movies);
            }

            showLoading(false);

        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
