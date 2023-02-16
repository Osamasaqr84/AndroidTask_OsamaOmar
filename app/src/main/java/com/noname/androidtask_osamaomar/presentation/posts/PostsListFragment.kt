package com.noname.androidtask_osamaomar.presentation.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.noname.androidtask_osamaomar.R
import com.noname.androidtask_osamaomar.data.local.room.LocalPost
import com.noname.androidtask_osamaomar.databinding.FragmentPostsBinding
import com.noname.androidtask_osamaomar.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostsListFragment : Fragment(), OnItemClickListener {


    private val mainViewModel: MainViewModel by viewModels()
    private var postsAdapter: ArticlesAdapter? = null
    private lateinit var binding: FragmentPostsBinding

    private fun observeOnData() {

        lifecycleScope.launch(Dispatchers.Main) {
            mainViewModel.articlesFlow.collect {
                it.let { it1 -> postsAdapter?.submitData(it1) }
            }
        }

        lifecycleScope.launch {
            postsAdapter?.loadStateFlow?.collectLatest { loadStates ->
                binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
                binding.errorMsg.isVisible = loadStates.refresh is LoadState.Error
            }
        }
    }

    private fun loadArticlesData() {
        mainViewModel.loadArticlesDate()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        if (this::binding.isInitialized)
            return binding.root
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_posts, container, false)
        initUi()
        loadArticlesData()
        observeOnData()

        return binding.root
    }


    private fun initUi() {
        postsAdapter = ArticlesAdapter(requireContext(), this)
        binding.rvArticels.adapter = postsAdapter
    }

    override fun onItemClick(localPost: LocalPost?) {
        localPost ?: return
        val action = PostsListFragmentDirections.navigateToDetails(localPost)
        findNavController().navigate(action)
    }


}