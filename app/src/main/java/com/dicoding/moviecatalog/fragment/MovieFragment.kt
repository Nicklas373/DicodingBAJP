package com.dicoding.moviecatalog.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.activity.SusActivity
import com.dicoding.moviecatalog.adapter.MovieAdapter
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieListEntity
import com.dicoding.moviecatalog.databinding.FragmentMovieBinding
import com.dicoding.moviecatalog.utils.SortUtils.NEWEST
import com.dicoding.moviecatalog.utils.SortUtils.OLDEST
import com.dicoding.moviecatalog.viewmodel.MovieViewModel
import com.dicoding.moviecatalog.viewmodel.ViewModelFactory
import com.dicoding.moviecatalog.vo.Resource
import com.dicoding.moviecatalog.vo.Status

class MovieFragment : Fragment() {

    private lateinit var _binding: FragmentMovieBinding
    private val binding get() = _binding
    private lateinit var movieAdapter: MovieAdapter

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
            hideStaticUI()
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(
                this, factory
            )[MovieViewModel::class.java]
            movieAdapter = MovieAdapter()
            viewModel.getMovie(NEWEST).observe(viewLifecycleOwner, movieObserver)
            viewModel.isLoading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
            viewModel.isToast.observe(viewLifecycleOwner) { isToast ->
                showToast(isToast, viewModel.toastReason.value.toString())
            }
            with(_binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
            binding.susListFab.setOnClickListener {
                if (binding.susFavFab.visibility == View.GONE) {
                    showStaticUI()
                } else {
                    hideStaticUI()
                }
            }
            binding.susFavFab.setOnClickListener {
                val intent = Intent(context, SusActivity::class.java)
                context?.startActivity(intent)
            }
            binding.susOrderAsc.setOnClickListener {
                viewModel.getMovie(NEWEST).observe(viewLifecycleOwner, movieObserver)
                hideStaticUI()
            }
            binding.susOrderDesc.setOnClickListener {
                viewModel.getMovie(OLDEST).observe(viewLifecycleOwner, movieObserver)
                hideStaticUI()
            }
        }
    }

    private val movieObserver = Observer<Resource<PagedList<MovieListEntity>>> { movie ->
        if (movie != null) {
            when (movie.status) {
                Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    movieAdapter.setMovies(movie.data)
                    movieAdapter.submitList(movie.data)
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        context,
                        resources.getString(R.string.error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(isToast: Boolean, toastReason: String) {
        if (!isToast) {
            Toast.makeText(context, toastReason, Toast.LENGTH_LONG).show()
        }
    }

    private fun hideStaticUI() {
        binding.susFavFab.visibility = View.GONE
        binding.susOrderAsc.visibility = View.GONE
        binding.susOrderDesc.visibility = View.GONE
    }

    private fun showStaticUI() {
        binding.susFavFab.visibility = View.VISIBLE
        binding.susOrderAsc.visibility = View.VISIBLE
        binding.susOrderDesc.visibility = View.VISIBLE
    }
}