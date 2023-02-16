package com.noname.androidtask_osamaomar.presentation.postdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.noname.androidtask_osamaomar.R
import com.noname.androidtask_osamaomar.data.local.room.LocalPost
import com.noname.androidtask_osamaomar.databinding.FragmentPostDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostDetailsFragment : Fragment() {

    private val args: PostDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentPostDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_post_details,
            container,
            false
        )
        val post = args.localPost
        bindDats(post)
        return binding.root
    }

    private fun bindDats(post: LocalPost) {
        binding.tvTitle.text = post.title
        Glide.with(requireContext()).load(post.postImage).into(binding.ivPost)
    }
}