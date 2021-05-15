package com.goddoro.junction.network.repositoryImpl

import android.net.Uri
import com.goddoro.junction.network.api.TestAPI
import com.goddoro.junction.network.model.Test
import com.goddoro.junction.network.repository.TestRepository


/**
 * created By DORO 5/15/21
 */

class TestRepositoryImpl ( val api : TestAPI) : TestRepository{


    override fun getMnistAnalysis(imageFile: Uri): Test {
        TODO("Not yet implemented")
    }
}