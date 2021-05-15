package com.goddoro.junction.network.repository

import android.net.Uri
import com.goddoro.junction.network.model.Test


/**
 * created By DORO 5/15/21
 */

interface TestRepository {


    suspend fun getMnistAnalysis(
        imageFile : Uri
    ) : Test
}