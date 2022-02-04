package com.dicoding.moviecatalog.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.adapter.MovieFavAdapter
import com.dicoding.moviecatalog.databinding.FragmentMovieFavBinding
import com.dicoding.moviecatalog.viewmodel.MovieFavViewModel
import com.dicoding.moviecatalog.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MovieFavFragment : Fragment() {

    private var _binding: FragmentMovieFavBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MovieFavViewModel
    private lateinit var susAdapter: MovieFavAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.rvMovie)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(
                this, factory
            )[MovieFavViewModel::class.java]
            susAdapter = MovieFavAdapter()

            viewModel.getFavMovies().observe(viewLifecycleOwner) { susMovies ->
                if (susMovies != null) {
                    binding.progressBar.visibility = View.GONE
                    susAdapter.setMovies(susMovies)
                    susAdapter.submitList(susMovies)
                }
            }
            viewModel.isLoading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
            viewModel.isToast.observe(viewLifecycleOwner) { isToast ->
                showToast(isToast, viewModel.toastReason.value.toString())
            }
            _binding?.let {
                with(it.rvMovie) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = susAdapter
                }
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val susMovie = susAdapter.getSwipedData(swipedPosition)
                susMovie?.let { viewModel.updateFavMovies(it) }
                val snackBar = Snackbar.make(view as View, R.string.removed, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.undo) { _ ->
                    susMovie?.let { viewModel.updateFavMovies(it) }
                }
                snackBar.show()
            }
        }
    })

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
}