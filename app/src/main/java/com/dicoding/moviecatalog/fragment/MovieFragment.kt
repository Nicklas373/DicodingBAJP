package com.dicoding.moviecatalog.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.moviecatalog.adapter.MovieAdapter
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
            viewModel.getMovie().observe(viewLifecycleOwner, { movieId ->
                _binding?.progressBar?.visibility = View.GONE
                movieAdapter.setMovies(movieId)
                movieAdapter.notifyDataSetChanged()
            })
            _binding?.let {
                with(it.rvMovie) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = movieAdapter
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}