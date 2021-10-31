package com.joshnark.domain_layer.models.exceptions

import java.lang.UnsupportedOperationException

class UnsupportedItemTypeException: UnsupportedOperationException("The view type is unknown")