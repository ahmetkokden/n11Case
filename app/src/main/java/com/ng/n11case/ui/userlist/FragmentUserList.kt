package com.ng.n11case.ui.userlist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ng.n11case.R
import com.ng.n11case.base.BaseFragment
import com.ng.n11case.base.viewBinding
import com.ng.n11case.data.model.UserItem
import com.ng.n11case.databinding.FragmentUserlistBinding
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
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            launch {
                userListViewModel.userList.collect {
                    binding.tvUserListInfo.visibility =
                        if (userListViewModel.userList.value.isEmpty()) View.VISIBLE else View.GONE
                    adapter.updateItems(it)
                }
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
            favouriteUser = { userName ->
                favouriteUser(
                    userName
                )
            }
        )
        adapter.updateItems(userList)
        binding.rvUserList.adapter = adapter
    }

    private fun userCardClicked(userName: String) {
        Log.d("CLİCK", "Clicked -> $userName")
    }

    private fun favouriteUser(userName: String) {
        Log.d("CLİCK", "Clicked -> $userName")
    }
}