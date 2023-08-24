package com.ng.n11case.ui.userlist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ng.n11case.data.model.UserItem
import com.ng.n11case.utilities.customviews.UserCardView

class UserListAdapter(
    private val clickListener: (String) -> Unit,
    private val favouriteUser: (Boolean,String) -> Unit
) : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    var items: MutableList<UserItem> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding = UserCardView(context = parent.context)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) =
        holder.bind(items[position], clickListener,favouriteUser)

    override fun getItemCount(): Int = items.size

    fun updateItems(itemList: List<UserItem>?) {
        items = itemList?.toMutableList() ?: mutableListOf()
        this.notifyDataSetChanged()
    }

    inner class UserListViewHolder(private val view: UserCardView) :
        RecyclerView.ViewHolder(view.binding.root) {
        fun bind(
            listItem: UserItem,
            clickListener: (String) -> Unit,
            favouriteUser: (Boolean,String) -> Unit
        ) {
            view.setFieldsWithModel(listItem)
            view.setOnClickFavouriteIconListener { isFavourited,userName ->
                favouriteUser(isFavourited,userName)
            }
            view.setOnClickUserCardListener { userName ->
                clickListener(userName)
            }
        }
    }
}