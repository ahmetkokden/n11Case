package com.ng.n11case.ui.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ng.n11case.data.model.UserItem
import com.ng.n11case.data.model.base.NetworkResult
import com.ng.n11case.domain.repository.GithubDatabaseRepository
import com.ng.n11case.domain.usecase.UserSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userSearchUseCase: UserSearchUseCase,
    private val githubDatabaseRepository: GithubDatabaseRepository
) : ViewModel() {

    private val _userList = MutableStateFlow<List<UserItem>>(emptyList())
    val userList = _userList.asStateFlow()

    var pagePerSize: Int = 10
    var totalCount: Double = 0.0
    var isAllUserLoaded = false
    var searchText = " "

    fun searchUser(searchText: String) {
        this.searchText = searchText
        viewModelScope.launch {
            userSearchUseCase.searchUsers(searchText, per_page = pagePerSize).collect { response ->
                when (response.status) {
                    NetworkResult.Status.SUCCESS -> {
                        _userList.emit(response.data?.users ?: emptyList())
                        response.data?.let {
                            withContext(Dispatchers.IO) {
                                githubDatabaseRepository.deleteUserList()
                                githubDatabaseRepository.addUserList(it.users)
                                githubDatabaseRepository.setUserListParameter(response.data.totalCount.toDouble(),searchText)
                            }
                        }
                        totalCount = response.data?.totalCount?.toDouble() ?: 0.0
                        isAllUserLoaded = userList.value.count() >= totalCount
                    }
                    NetworkResult.Status.LOADING -> {

                    }
                    NetworkResult.Status.ERROR -> {

                    }
                }
            }
        }
    }

    fun favouriteUser(isFavourited: Boolean, userName: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                githubDatabaseRepository.update(isFavourited, userName)
            }
        }

    }

    suspend fun checkCacheData() {
        val userList = githubDatabaseRepository.getUserList()

        if (userList.isNotEmpty()) {
            _userList.emit(userList)
            totalCount = githubDatabaseRepository.getTotalCount()
            searchText = githubDatabaseRepository.getSearchText()
        } else {
            searchUser("a")
        }

    }

    fun loadMoreItems(currentTotalCount:Int) {
        pagePerSize += currentTotalCount
        viewModelScope.launch {
            userSearchUseCase.searchUsers(searchText, per_page = pagePerSize).collect { response ->
                when (response.status) {
                    NetworkResult.Status.SUCCESS -> {
                        _userList.emit(response.data?.users ?: emptyList())
                        response.data?.let {
                            withContext(Dispatchers.IO) {
                                githubDatabaseRepository.deleteUserList()
                                githubDatabaseRepository.addUserList(it.users)
                                githubDatabaseRepository.setUserListParameter(response.data.totalCount.toDouble(),searchText)
                            }
                        }
                        totalCount = response.data?.totalCount?.toDouble() ?: 0.0
                        isAllUserLoaded = userList.value.count() >= totalCount
                    }
                    NetworkResult.Status.LOADING -> {

                    }
                    NetworkResult.Status.ERROR -> {

                    }
                }
            }
        }
    }
}