package com.ng.n11case.ui.userlist

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ng.n11case.utilities.extensions.dp

class UserListRecyclerViewDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        outRect.bottom = 7.dp
        outRect.top = 7.dp
        outRect.right = 18.dp
        outRect.left = 19.dp

    }
}