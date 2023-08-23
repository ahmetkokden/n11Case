package com.ng.n11case.utilities.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.ng.n11case.R
import com.ng.n11case.data.model.UserItem
import com.ng.n11case.databinding.UserCardViewBinding

class UserCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {
    private lateinit var userItem: UserItem

    private var _binding: UserCardViewBinding? = null
    val binding: UserCardViewBinding
        get() = _binding!!

    private var clickedFavouriteIcon: (String) -> Unit = {}
    private var clickedUserCard: (String) -> Unit = {}

    init {
        _binding =
            UserCardViewBinding.inflate(LayoutInflater.from(context), this, false)

        binding.apply {
            ivFavoriteUser.setOnClickListener {
                clickedFavouriteIcon(userItem.userName)
                userItem.isFavourited =
                    !userItem.isFavourited
                ivFavoriteUser.setImageResource(if (userItem.isFavourited) R.drawable.ic_favourited else R.drawable.ic_favourite)

            }

            flUserItem.setOnClickListener {
                clickedUserCard(userItem.userName)
            }


        }
    }

    fun setFieldsWithModel(userItem: UserItem) {
        this.userItem = userItem
        Glide.with(this).load(userItem.profileImage).into(binding.ivUserAvatar)
        binding.apply {
            tvUserName.text = userItem.userName
            tvUserId.text = userItem.userId.toString()
            tvUserScore.text = userItem.score.toString()
        }
    }

    fun setOnClickFavouriteIconListener(callback: (String) -> Unit) {
        clickedFavouriteIcon = callback
    }

    fun setOnClickUserCardListener(callback: (String) -> Unit) {
        clickedUserCard = callback
    }

    override fun onDetachedFromWindow() {
        _binding = null
        super.onDetachedFromWindow()
    }
}