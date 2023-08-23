package com.ng.n11case.ui.userlist

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ng.n11case.data.model.UserItem
import com.ng.n11case.utilities.customviews.UserCardView

class UserListAdapter(
    private val clickListener: (String) -> Unit,
    private val favouriteUser: (String) -> Unit
) : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {
    private var items: List<UserItem> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding = UserCardView(context = parent.context)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) =
        holder.bind(items[position], clickListener,favouriteUser)

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(itemList: List<UserItem>?) {
        items = itemList ?: emptyList()
        notifyDataSetChanged()
    }

    inner class UserListViewHolder(private val view: UserCardView) :
        RecyclerView.ViewHolder(view.binding.root) {
        fun bind(
            listItem: UserItem,
            clickListener: (String) -> Unit,
            favouriteUser: (String) -> Unit
        ) {
            view.setFieldsWithModel(listItem)
            view.setOnClickFavouriteIconListener { userName ->
                favouriteUser(userName)
            }
            view.setOnClickUserCardListener { userName ->
                clickListener(userName)
            }
        }
    }
}