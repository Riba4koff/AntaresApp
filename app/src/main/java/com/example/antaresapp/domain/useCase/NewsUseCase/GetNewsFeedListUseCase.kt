package com.example.antaresapp.domain.useCase.NewsUseCase

import com.example.antaresapp.data.RepositoryImpl.NewsRepositoryImpl
import com.example.antaresapp.domain.models.NewsItem
import com.example.antaresapp.domain.repository.NewsRepository

class GetNewsFeedListUseCase(private val newsRepository: NewsRepositoryImpl) {
    fun execute() : List<NewsItem> {
        return newsRepository.getNews()
    }
}