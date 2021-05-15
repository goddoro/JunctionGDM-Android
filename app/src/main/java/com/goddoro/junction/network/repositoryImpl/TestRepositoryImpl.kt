package com.goddoro.junction.network.repositoryImpl

import android.net.Uri
import com.goddoro.junction.network.api.TestAPI
import com.goddoro.junction.network.model.Test
import com.goddoro.junction.network.repository.TestRepository
import com.goddoro.module.util.MultiPartUtil


/**
 * created By DORO 5/15/21
 */

class TestRepositoryImpl ( val api : TestAPI, val multiPartUtil: MultiPartUtil) : TestRepository{




    override suspend fun getMnistAnalysis(imageFile: Uri): Test {
        return api.getMnistAnalysis(multiPartUtil.uriToPart("image", imageFile))
    }
}