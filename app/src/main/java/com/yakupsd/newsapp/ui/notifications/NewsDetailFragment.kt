package com.yakupsd.newsapp.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.yakupsd.newsapp.WebViewActivty
import com.yakupsd.newsapp.database.NewsDatabase
import com.yakupsd.newsapp.databinding.FragmentDetailNewsBinding

class NewsDetailFragment : Fragment() {

    lateinit var binding:FragmentDetailNewsBinding
    private lateinit var viewModel: NewsDetailViewModel

    private val navArgs : NewsDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailNewsBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = NewsDetailViewModelFactory(NewsDatabase.invoke(requireContext()))
        viewModel = ViewModelProvider(this,factory)[NewsDetailViewModel::class.java]

        navArgs.news.apply {
            binding.newsNameTitle.text = newsTitle
            binding.author.text = newsAuthor
            binding.newsDescription.text = newsDescription
            binding.newsTime.text = newsPublishedAt
            binding.sourceBtn.text = source.newsName
            Glide.with(requireContext()).load(newsUrlToImage).into(binding.newsImageView)

            binding.sourceBtn.setOnClickListener {
                startActivity(WebViewActivty.callingIntent(requireContext(), newsUrl.orEmpty()))
            }

            binding.saveBtn.setOnClickListener {
                viewModel.saveNews(this)

            }
        }
    }

}