package com.dicoding.moviecatalog.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.moviecatalog.adapter.TvShowAdapter
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
            val tvShow = viewModel.getTvShow()
            val adapter = TvShowAdapter()
            adapter.setTvShow(tvShow)
            _binding?.let {
                with(it.rvTvshow) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    this.adapter = adapter
                }
            }
        }
    }
}