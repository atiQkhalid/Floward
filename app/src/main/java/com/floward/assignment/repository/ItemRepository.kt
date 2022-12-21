package com.floward.assignment.repository

import com.floward.assignment.network.RetrofitClient
import com.floward.assignment.utils.Const.BASE_URL

class ItemRepository {

    companion object {
        private var instance: ItemRepository? = null
        fun getInstance(): ItemRepository {
            if (instance == null)
                instance =
                    ItemRepository()
            return instance!!
        }
    }
}