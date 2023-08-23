package com.ng.n11case.utilities

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

class Util {
    companion object {
        suspend fun ResponseBody.stringSuspending() =
            withContext(Dispatchers.IO) { string() }
    }
}