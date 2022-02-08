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
import com.dicoding.moviecatalog.adapter.TvShowAdapter
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowListEntity
import com.dicoding.moviecatalog.databinding.FragmentTvShowBinding
import com.dicoding.moviecatalog.utils.SortUtils.NEWEST
import com.dicoding.moviecatalog.utils.SortUtils.OLDEST
import com.dicoding.moviecatalog.viewmodel.TvShowViewModel
import com.dicoding.moviecatalog.viewmodel.ViewModelFactory
import com.dicoding.moviecatalog.vo.Resource
import com.dicoding.moviecatalog.vo.Status

class TvShowFragment : Fragment() {

    private lateinit var _binding: FragmentTvShowBinding
    private val binding get() = _binding
    private lateinit var tvShowAdapter: TvShowAdapter

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
            hideStaticUI()
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(
                this, factory
            )[TvShowViewModel::class.java]
            tvShowAdapter = TvShowAdapter()
            viewModel.getTvShow(NEWEST).observe(viewLifecycleOwner, tvShowObserver)
            viewModel.isLoading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
            viewModel.isToast.observe(viewLifecycleOwner) { isToast ->
                showToast(isToast, viewModel.toastReason.value.toString())
            }
            with(_binding.rvTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
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
                viewModel.getTvShow(NEWEST).observe(viewLifecycleOwner, tvShowObserver)
                hideStaticUI()
            }
            binding.susOrderDesc.setOnClickListener {
                viewModel.getTvShow(OLDEST).observe(viewLifecycleOwner, tvShowObserver)
                hideStaticUI()
            }
        }
    }

    private val tvShowObserver = Observer<Resource<PagedList<TvShowListEntity>>> { tvShow ->
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