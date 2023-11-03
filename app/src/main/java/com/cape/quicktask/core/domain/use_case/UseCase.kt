package com.cape.quicktask.core.domain.use_case

import com.cape.quicktask.core.domain.Result
import kotlinx.coroutines.withContext
import com.cape.quicktask.core.domain.dispatcher.io
import com.cape.quicktask.core.domain.failureOf
import com.cape.quicktask.core.domain.successOf
import timber.log.Timber

abstract class UseCase<Type, in Params> where Type : Any {

    protected abstract suspend fun run(params: Params): Type

    suspend operator fun invoke(params: Params): Result<Type> {
        return withContext(io) {
            try {
                successOf(run(params))
            } catch (e: Exception) {
                Timber.e("Use case error => ${e.message} \n ${e.stackTraceToString()}")
                failureOf(RuntimeException(e.message))
            }
        }
    }

    object None
}
