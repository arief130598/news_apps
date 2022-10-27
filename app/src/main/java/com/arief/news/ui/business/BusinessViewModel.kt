package com.arief.news.ui.business

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arief.news.BuildConfig
import com.arief.news.model.News
import com.arief.news.repository.ApiNewsRepo
import com.arief.news.utils.NetworkHelper
import com.arief.news.utils.Resource
import kotlinx.coroutines.launch

class BusinessViewModel(private val apiNewsRepo: ApiNewsRepo,
                        private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _news = MutableLiveData<Resource<List<News>>>()
    val news: LiveData<Resource<List<News>>>
        get() = _news

    init {
        getNews()
    }

    private fun getNews(){
        viewModelScope.launch {
            _news.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                apiNewsRepo.getNews("us", "business", BuildConfig.API_KEY).let {
                    if (it.isSuccessful) {
                        if(it.body() != null) {
                            _news.postValue(Resource.success(it.body()!!.articles))
                        }else{
                            _news.postValue(Resource.success(listOf()))
                        }
                    } else _news.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _news.postValue(Resource.error("No internet connection", null))
        }
    }
}