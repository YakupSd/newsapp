package com.yakupsd.newsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yakupsd.newsapp.adapter.NewsAdapter
import com.yakupsd.newsapp.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.getBreakingNews()//arama ekranı değeri
        newsList.layoutManager = LinearLayoutManager(context)
        observerLiveData()

        binding.searchBtn.setOnClickListener {
            if (!binding.etSearch.text.isEmpty()) {
                viewModel.getDataFromApi(binding.etSearch.text.toString())
            } else {
                viewModel.getBreakingNews()
            }
        }
    }

    private fun observerLiveData() {

        viewModel.allNews.observe(viewLifecycleOwner, Observer {
            if (it.status == "ok") {
                newsAdapter = NewsAdapter(it.article) {
                    findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationNewsDetail(it))
                }
                newsList.adapter = newsAdapter
                newsError.visibility = View.GONE
                newsProgressbar.visibility = View.GONE
            } else {
                newsError.text = it.message
                newsError.visibility = View.VISIBLE
            }
        })

        viewModel.allNews.observe(viewLifecycleOwner, Observer {
            if (it.status == "ok") {
                newsAdapter = NewsAdapter(it.article) {
                    findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationNewsDetail(it))
                }
                newsList.adapter = newsAdapter
                newsError.visibility = View.GONE
                newsProgressbar.visibility = View.GONE
            } else {
                newsError.text = it.message
                newsError.visibility = View.VISIBLE
            }
        })
    }
}