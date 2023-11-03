package com.cape.quicktask.core.domain.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

val main: CoroutineDispatcher = Dispatchers.Main
val default: CoroutineDispatcher = Dispatchers.Default
val io: CoroutineDispatcher = Dispatchers.IO
val unconfined: CoroutineDispatcher = Dispatchers.Unconfined