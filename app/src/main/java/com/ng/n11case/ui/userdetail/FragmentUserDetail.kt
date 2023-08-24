package com.ng.n11case.ui.userdetail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.ng.n11case.R
import com.ng.n11case.base.BaseFragment
import com.ng.n11case.base.viewBinding
import com.ng.n11case.data.model.UserDetailItem
import com.ng.n11case.databinding.FragmentUserDetailBinding
import com.ng.n11case.ui.userlist.FragmentUserList.Companion.USERNAME
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class FragmentUserDetail : BaseFragment(R.layout.fragment_user_detail) {
    private val userDetailViewModel: UserDetailViewModel by viewModels()
    private val binding: FragmentUserDetailBinding by viewBinding(FragmentUserDetailBinding::bind)

    private lateinit var userName: String

    override fun observeVariables() {
        viewLifecycleOwner.lifecycleScope.launch {
            userDetailViewModel.userDetail.collect {
                it?.let {
                    setFieldsWithModel(it)
                }
            }
        }
    }

    override fun initUI(savedInstanceState: Bundle?) {
        userName = requireArguments().getString(USERNAME).toString()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            withContext(Dispatchers.IO) {
                userDetailViewModel.checkCacheData(userName)
            }
        }
        setUpListeners()
    }

    private fun setFieldsWithModel(userDetailItem: UserDetailItem) {
        binding.apply {
            Glide.with(requireView()).load(userDetailItem.profileImage).into(ivUserAvatar)
            ivUserFavourite.setImageResource(if (userDetailItem.isFavourited) R.drawable.ic_favourited else R.drawable.ic_favourite)
            tvUserScore.text = userDetailItem.score.toString()
            tvFollowing.text = userDetailItem.following.toString()
            tvUserName.text = userDetailItem.userName
            tvUserFollowers.text = userDetailItem.followers.toString()
            tvUserPublicRepo.text = userDetailItem.publicRepoCount.toString()
        }
    }

    private fun setUpListeners() {
        binding.apply {
            ivUserFavourite.setOnClickListener {
                userDetailViewModel.favouriteUser(
                    !(userDetailViewModel.userDetail.value?.isFavourited ?: true), userName
                )
            }
        }
    }
}