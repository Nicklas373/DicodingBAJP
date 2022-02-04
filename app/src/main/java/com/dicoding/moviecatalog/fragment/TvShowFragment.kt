package com.dicoding.moviecatalog.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.activity.SusActivity
import com.dicoding.moviecatalog.adapter.TvShowAdapter
import com.dicoding.moviecatalog.databinding.FragmentTvShowBinding
import com.dicoding.moviecatalog.viewmodel.TvShowViewModel
import com.dicoding.moviecatalog.viewmodel.ViewModelFactory
import com.dicoding.moviecatalog.vo.Status

class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val tvShowAdapter = TvShowAdapter()
            if (activity != null) {
                val factory = ViewModelFactory.getInstance(requireActivity())
                val viewModel = ViewModelProvider(
                    this, factory
                )[TvShowViewModel::class.java]
                viewModel.getTvShow().observe(viewLifecycleOwner) { tvShow ->
                    if (tvShow != null) {
                        when (tvShow.status) {
                            Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding.progressBar.visibility = View.GONE
                                tvShowAdapter.setTvShow(tvShow.data)
                                tvShowAdapter.submitList(tvShow.data)
                            }
                            Status.ERROR -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(
                                    context,
                                    resources.getString(R.string.error),
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                    }
                }
                viewModel.isLoading.observe(viewLifecycleOwner) {
                    showLoading(it)
                }
                viewModel.isToast.observe(viewLifecycleOwner) { isToast ->
                    showToast(isToast, viewModel.toastReason.value.toString())
                }
                _binding?.let {
                    with(it.rvTvshow) {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = tvShowAdapter
                    }
                }
            }
        }
        binding.susListFab.setOnClickListener {
            val intent = Intent(context, SusActivity::class.java)
            context?.startActivity(intent)
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
}