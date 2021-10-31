package com.joshnark.framework_layer.utils

import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.exceptions.BadGatewayException
import com.joshnark.domain_layer.models.exceptions.InternalServerErrorException
import com.joshnark.domain_layer.models.exceptions.NotFoundException
import com.joshnark.domain_layer.models.exceptions.UnknownErrorException
import retrofit2.HttpException

internal inline fun <T: Any> processResult(f: () -> T): GenericResult<T> {
    return try {
        GenericResult.Success(f())
    }catch (exception: Exception) {
        exception.printStackTrace()
        GenericResult.Error(processException(exception))
    }
}

//i did not map all possible cases bc this is just a test project lol
internal fun processException(exception: Exception): Exception {
    return when(exception) {
        is HttpException -> {
            when(exception.code()) {
                404 -> NotFoundException()
                500 -> InternalServerErrorException()
                502 -> BadGatewayException()
                else -> UnknownErrorException()
            }
        }
        else -> UnknownErrorException()
    }
}