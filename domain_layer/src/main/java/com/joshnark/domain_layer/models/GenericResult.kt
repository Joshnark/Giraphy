package com.joshnark.domain_layer.models

import java.lang.Exception

sealed class GenericResult<T>  {
    class Success<T>(val data: T): GenericResult<T>()
    class Loading<T>(val data: T? = null): GenericResult<T>()
    class Error<T>(val exception: Exception, val data: T? = null): GenericResult<T>()
}
