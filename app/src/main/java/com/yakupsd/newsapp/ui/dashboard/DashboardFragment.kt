package com.yakupsd.newsapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yakupsd.newsapp.database.NewsDatabase
import com.yakupsd.newsapp.databinding.FragmentDashboardBinding
import com.yakupsd.newsapp.model.News
import com.yakupsd.newsapp.model.Source
import com.yakupsd.newsapp.ui.dashboard.adapter.FavoritesAdapter

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private lateinit var viewModel:DashboardViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val factory = DashboardViewModelFactory(NewsDatabase.invoke(requireContext()))
        viewModel =
            ViewModelProvider(this,factory)[DashboardViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNews()

        viewModel.favoritesArticles.observe(viewLifecycleOwner){
            binding.rvArticle.adapter = FavoritesAdapter(newsList = it, itemclicklistener = { news ->
                findNavController().navigate(DashboardFragmentDirections.actionNavigationDashboardToNavigationNewsDetail(
                    News(
                        source = Source(newsName = news.sourceName),
                        newsUrl = news.newsUrl,
                        newsTitle = news.newsTitle,
                        newsDescription = news.newsDescription,
                        newsAuthor = news.author,
                        newsPublishedAt = news.publishedAt,
                        newsUrlToImage = news.newsImage
                    )
                ))
            }, deleteClickItemClickListener = {
                viewModel.deleteNews(it)
            })
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}