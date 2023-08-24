package com.ng.n11case.ui.userdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ng.n11case.data.model.UserDetailItem
import com.ng.n11case.data.model.base.NetworkResult
import com.ng.n11case.domain.repository.GithubDatabaseRepository
import com.ng.n11case.domain.usecase.UserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val userDetailUseCase: UserDetailUseCase,
    private val githubDatabaseRepository: GithubDatabaseRepository
) : ViewModel() {

    private val _userDetail = MutableStateFlow<UserDetailItem?>(null)
    val userDetail = _userDetail.asStateFlow()

    fun getUserDetail(userName: String) {
        viewModelScope.launch {
            userDetailUseCase.getUserDetail(userName).collect { response ->
                when (response.status) {
                    NetworkResult.Status.SUCCESS -> {
                        withContext(Dispatchers.IO) {
                            response.data?.let { checkWithCachedData(response.data.userName, it) }
                        }
                    }
                    NetworkResult.Status.LOADING -> {

                    }
                    NetworkResult.Status.ERROR -> {

                    }
                }
            }
        }
    }

    suspend fun checkWithCachedData(userName: String, userDetailItem: UserDetailItem) {
        githubDatabaseRepository.setUserDetailEntity(userDetailItem)
        val user = githubDatabaseRepository.getUserByName(userName)

        userDetailItem.apply {
            this.isFavourited = user?.isFavourited ?: false
        }

        _userDetail.emit(userDetailItem)
    }

    suspend fun checkCacheData(userName: String) {
        val userDetail = githubDatabaseRepository.getUserDetailByName(userName)

        if (userDetail != null) {
            checkWithCachedData(userName,userDetail)
        } else {
            getUserDetail(userName)
        }

    }

    fun favouriteUser(isFavourited: Boolean, userName: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                githubDatabaseRepository.update(isFavourited, userName)
            }
            _userDetail.update {
                it?.copy(isFavourited = isFavourited)
            }
        }
    }

}