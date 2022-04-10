package com.example.newsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_data.Instance
import com.example.news_data.model.News
import com.example.newsapp.databinding.FragmentNewsBinding
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {
    private var binding: FragmentNewsBinding?=null
    private lateinit var newsFragmentViewModel: NewsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater)
        newsFragmentViewModel = ViewModelProvider(this)[NewsFragmentViewModel::class.java]
        val manager = GridLayoutManager(this.context, requireContext().resources.getInteger(R.integer.element_count))

        lifecycleScope.launch {


            if (newsFragmentViewModel.getData() == null) {
                val instance = Instance.getNewsRepository()
                newsFragmentViewModel.putData(instance.getNewsHeadlines())
            }

            val adapter = newsFragmentViewModel.getData()?.let { recyclerViewAdapter(it.articles) }
            binding?.recyclerView?.adapter = adapter
            binding?.recyclerView?.layoutManager = manager

            adapter?.setOnItemClickListener(object : recyclerViewAdapter.onItemClickListener{
                override fun onItemClick(position: Int) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsFragmentViewModel.getData()?.articles?.get(position)?.url))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.setPackage("com.android.chrome")
                    startActivity(intent)
                }
            })
        }

        return binding!!.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewsFragment()
    }
}

class NewsFragmentViewModel : ViewModel() {
    private var newsData: News? = null

    fun getData(): News? {
        return newsData
    }

    fun putData(data: News) {
        newsData = data
    }

}