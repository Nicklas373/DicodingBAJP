package com.dicoding.moviecatalog.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.moviecatalog.adapter.MovieAdapter
import com.dicoding.moviecatalog.adapter.MovieApiAdapter
import com.dicoding.moviecatalog.data.movie.response.MovieListResponse
import com.dicoding.moviecatalog.data.movie.source.Repository
import com.dicoding.moviecatalog.data.movie.source.remote.RemoteDataSource
import com.dicoding.moviecatalog.databinding.FragmentMovieBinding
import com.dicoding.moviecatalog.viewmodel.MovieViewModel
import com.dicoding.moviecatalog.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(
                this, factory
            )[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter()
            _binding?.progressBar?.visibility = View.VISIBLE
            viewModel.getMovieApi().observe(viewLifecycleOwner, { movieId ->
                _binding?.progressBar?.visibility = View.GONE
                setMovieListApi(movieId)
                MovieApiAdapter(movieId).notifyDataSetChanged()
            })

            //viewModel.movieList.observe(viewLifecycleOwner, { movieId ->
            //    _binding?.progressBar?.visibility = View.GONE
            //    setMovieListApi(movieId)
            //    MovieApiAdapter(movieId).notifyDataSetChanged()
            //})

            viewModel.isLoading.observe(viewLifecycleOwner, {
                showLoading(it)
            })

            viewModel.isToast.observe(viewLifecycleOwner, { isToast ->
                showToast(isToast, viewModel.toastReason.value.toString())
            })

            //viewModel.getMovieList()

            _binding?.let {
                with(it.rvMovie) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = movieAdapter
                }
            }
        }
    }

    private fun setMovieListApi(movieListApi: ArrayList<MovieListResponse>) {
        val listReview = ArrayList<MovieListResponse>()
        for (movieList in movieListApi) {
            val movieApi = MovieListResponse(
                movieList.movieId,
                movieList.overview,
                movieList.originalTitle,
                movieList.releaseDate,
                movieList.voteAverage,
                movieList.title,
                movieList.posterPath,
                movieList.revenue,
                movieList.originalLanguage
            )
            listReview.add(movieApi)
            Log.e(TAG, "response size: ${listReview.size}")
        }
        val adapter = MovieApiAdapter(listReview)
        binding.rvMovie.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(isToast: Boolean, toastReason: String) {
        if (!isToast) {
            Toast.makeText(context, toastReason, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "MovieFragment"
    }
}