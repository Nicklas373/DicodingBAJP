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

class TvShowFragment : Fragment() {

    lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[TvShowViewModel::class.java]
            val TvShow = viewModel.getTvShow()
            val adapter = TvShowAdapter()
            adapter.setTvShow(TvShow)
            with(fragmentTvShowBinding.rvTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }
}