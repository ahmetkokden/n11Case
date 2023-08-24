package com.ng.n11case.ui.userlist

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ng.n11case.R
import com.ng.n11case.base.BaseFragment
import com.ng.n11case.base.viewBinding
import com.ng.n11case.data.model.UserItem
import com.ng.n11case.databinding.FragmentUserlistBinding
import com.ng.n11case.utilities.listener.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class FragmentUserList : BaseFragment(R.layout.fragment_userlist) {
    private val userListViewModel: UserListViewModel by viewModels()
    private val binding: FragmentUserlistBinding by viewBinding(FragmentUserlistBinding::bind)
    private lateinit var adapter: UserListAdapter

    override fun observeVariables() {
        viewLifecycleOwner.lifecycleScope.launch {
            userListViewModel.userList.collect {
                binding.tvUserListInfo.visibility =
                    if (userListViewModel.userList.value.isEmpty()) View.VISIBLE else View.GONE
                setUpAdapter(it)
            }
        }
    }

    override fun initUI(savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            withContext(Dispatchers.IO) {
                userListViewModel.checkCacheData()
            }
        }

        binding.apply {
            rvUserList.addItemDecoration(UserListRecyclerViewDecoration())
        }

        setUpListeners()
        addScrollListener()
        setUpAdapter(emptyList())
    }

    private fun setUpListeners() {
        binding.apply {
            ivUserSearch.setOnClickListener {
                userListViewModel.searchUser(etUserSearch.text.toString())
            }
        }
    }

    private fun setUpAdapter(userList: List<UserItem>) {
        adapter = UserListAdapter(
            clickListener = { userName ->
                userCardClicked(
                    userName
                )
            },
            favouriteUser = { isFavourited, userName ->
                favouriteUser(
                    isFavourited, userName
                )
            }
        )
        adapter.updateItems(userList)
        binding.rvUserList.adapter = adapter
    }

    private fun addScrollListener() {
        binding.apply {
            rvUserList.addOnScrollListener(object :
                PaginationScrollListener(rvUserList.layoutManager as LinearLayoutManager) {
                override fun loadMoreItems(currentTotalCount:Int) {
                    if(currentTotalCount >= userListViewModel.pagePerSize) {
                        userListViewModel.loadMoreItems(currentTotalCount)
                    }
                }

                override fun isLastPage() = userListViewModel.isAllUserLoaded
            })
        }
    }

    private fun userCardClicked(userName: String) {
        binding.apply {
            val bundle =
                bundleOf(USERNAME to userName)
            findNavController().navigate(
                R.id.action_userListFragment_to_userDetailFragment, bundle
            )
        }
    }

    private fun favouriteUser(isFavourited: Boolean, userName: String) {
        userListViewModel.favouriteUser(isFavourited, userName)
    }

    companion object {
        const val USERNAME = "user_name"
    }
}