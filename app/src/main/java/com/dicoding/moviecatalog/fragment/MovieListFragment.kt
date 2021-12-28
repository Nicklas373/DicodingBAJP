package com.dicoding.moviecatalog.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.moviecatalog.adapter.MovieDetailAdapterClickListener
import com.dicoding.moviecatalog.adapter.MovieListAdapter
import com.dicoding.moviecatalog.callback.MovieReaderCallback
import com.dicoding.moviecatalog.data.MovieModuleEntity
import com.dicoding.moviecatalog.databinding.FragmentMovieListBinding
import com.dicoding.moviecatalog.utils.MovieDatabase

class MovieListFragment : Fragment(), MovieDetailAdapterClickListener {

    companion object {
        val TAG: String = MovieListFragment::class.java.simpleName

        fun newInstance(): MovieListFragment = MovieListFragment()
    }

    private lateinit var fragmentModuleListBinding: FragmentMovieListBinding
    private lateinit var adapter: MovieListAdapter
    private lateinit var movieReaderCallback: MovieReaderCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentModuleListBinding = FragmentMovieListBinding.inflate(inflater, container, false)
        return fragmentModuleListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MovieListAdapter(this)
        populateRecyclerView(MovieDatabase.generateMovieDetails("1"))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        movieReaderCallback = context as MovieReaderCallback
    }

    override fun onItemClicked(position: Int, movieId: String) {
        movieReaderCallback.moveTo(position, movieId)
    }

    private fun populateRecyclerView(movieModule: List<MovieModuleEntity>) {
        with(fragmentModuleListBinding) {
            progressBar.visibility = View.GONE
            adapter.setMovieModule(movieModule)
            rvModule.layoutManager = LinearLayoutManager(context)
            rvModule.setHasFixedSize(true)
            rvModule.adapter = adapter
            val dividerItemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            rvModule.addItemDecoration(dividerItemDecoration)
        }
    }
}