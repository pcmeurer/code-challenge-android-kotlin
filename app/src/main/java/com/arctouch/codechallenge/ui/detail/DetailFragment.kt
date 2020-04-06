package com.arctouch.codechallenge.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.arctouch.codechallenge.databinding.FragmentDetailBinding
import com.arctouch.codechallenge.util.autoCleared
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModel()
    private var binding by autoCleared<FragmentDetailBinding>()

    private fun getMovieId(): Int =
            arguments?.getInt("movieId") ?: 0

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.setMovieId(getMovieId())
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.movie.observe(
                viewLifecycleOwner,
                Observer { movie ->
                    binding.movie = movie
                }
        )
    }
}