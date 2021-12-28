package com.dicoding.moviecatalog.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.data.MovieContentEntity
import com.dicoding.moviecatalog.data.MovieEntity
import com.dicoding.moviecatalog.databinding.FragmentMovieContentBinding

class MovieContentFragment : Fragment() {

    companion object {
        val TAG: String = MovieContentFragment::class.java.simpleName
        fun newInstance(): MovieContentFragment = MovieContentFragment()
    }

    private lateinit var fragmentMovieContentBinding: FragmentMovieContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentMovieContentBinding =
            FragmentMovieContentBinding.inflate(inflater, container, false)
        return fragmentMovieContentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val content =
                MovieContentEntity("<h3 class=\\\"fr-text-bordered\\\">Contoh Content</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
            populateWebView(content)
        }
    }

    private fun populateWebView(content: MovieContentEntity) {
        fragmentMovieContentBinding.webView.loadData(content.content ?: "", "text/html", "UTF-8")
    }
}