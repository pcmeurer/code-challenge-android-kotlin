package com.arctouch.codechallenge.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.arctouch.codechallenge.AppExecutors
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.binding.FragmentDataBindingComponent
import com.arctouch.codechallenge.util.autoCleared
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val appExecutors: AppExecutors by inject()
    private val dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    private var adapter by autoCleared<HomeAdapter>()
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        initMoviesList()
    }

    private fun setupRecycler() {
        val adapter = HomeAdapter(dataBindingComponent, appExecutors) { movie ->

        }

        this.adapter = adapter
        recyclerView.adapter = adapter
    }

    private fun initMoviesList() {
        viewModel.movies.observe(
                viewLifecycleOwner,
                Observer { moviesList ->
                    adapter.submitList(moviesList.map { it.copy() })

                    //TODO: refatorar
                    progressBar.visibility = View.GONE
                }
        )
    }
}