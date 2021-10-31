package com.joshnark.presentation_layer

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.Gif
import com.joshnark.domain_layer.models.Tag
import com.joshnark.domain_layer.models.item_types.GifItemType
import com.joshnark.use_cases_layer.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _categoriesLiveData = MutableLiveData<GenericResult<List<Tag>>?>()
    val categoriesLiveData: LiveData<GenericResult<List<Tag>>?>
        get() {
            if (_categoriesLiveData.value == null) {
                getCategories()
            }
            return _categoriesLiveData
        }

    fun getCategories() {
        viewModelScope.launch {
            _categoriesLiveData.value = getCategoriesUseCase()
        }
    }

}