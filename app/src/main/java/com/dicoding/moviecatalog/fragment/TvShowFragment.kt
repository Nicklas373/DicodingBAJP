package com.dicoding.moviecatalog.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.moviecatalog.adapter.TvShowAdapter
import com.dicoding.moviecatalog.adapter.TvShowApiAdapter
import com.dicoding.moviecatalog.data.tvshow.response.TvShowListResponse
import com.dicoding.moviecatalog.databinding.FragmentTvShowBinding
import com.dicoding.moviecatalog.viewmodel.TvShowViewModel
import com.dicoding.moviecatalog.viewmodel.ViewModelFactory

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
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(
                this, factory
            )[TvShowViewModel::class.java]
            val adapter = TvShowAdapter()
            _binding?.progressBar?.visibility = View.VISIBLE
            viewModel.getTvShow().observe(viewLifecycleOwner, { tvShowId ->
                _binding?.progressBar?.visibility = View.GONE
                adapter.setTvShow(tvShowId)
                adapter.notifyDataSetChanged()
            })

            viewModel.tvShowList.observe(viewLifecycleOwner, { tvShowId ->
                _binding?.progressBar?.visibility = View.GONE
                setTvShowListApi(tvShowId)
                TvShowApiAdapter(tvShowId).notifyDataSetChanged()
            })

            viewModel.isLoading.observe(viewLifecycleOwner, {
                showLoading(it)
            })

            viewModel.isToast.observe(viewLifecycleOwner, { isToast ->
                showToast(isToast, viewModel.toastReason.value.toString())
            })

            viewModel.getTvShowList()

            _binding?.let {
                with(it.rvTvshow) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    this.adapter = adapter
                }
            }
        }
    }

    private fun setTvShowListApi(tvShowListApi: ArrayList<TvShowListResponse>) {
        val listReview = ArrayList<TvShowListResponse>()
        for (tvShowList in tvShowListApi) {
            val tvShowApi = TvShowListResponse(
                tvShowList.id,
                tvShowList.overview,
                tvShowList.originalName,
                tvShowList.releasedDate,
                tvShowList.voteAverage,
                tvShowList.name,
                tvShowList.posterPath
            )
            listReview.add(tvShowApi)
        }
        val adapter = TvShowApiAdapter(listReview)
        binding.rvTvshow.adapter = adapter
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