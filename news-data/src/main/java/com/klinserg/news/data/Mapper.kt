package com.klinserg.news.data

import com.klinserg.news.api.models.ArticleDTO
import com.klinserg.news.api.models.SourceDTO
import com.klinserg.news.data.models.Article
import com.klinserg.news.data.models.Source
import com.klinserg.news.db.models.ArticleDBO
import com.klinserg.news.db.models.SourceDBO

internal fun ArticleDTO.toArticle(): Article {
    return Article(
        id = 0,
        source = this.source.toSource(),
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content,
    )
}

internal fun SourceDTO.toSource(): Source {
    return Source(
        id = this.id,
        name = this.name,
    )
}

internal fun ArticleDTO.toArticleDBO(): ArticleDBO {
    return ArticleDBO(
        source = this.source.toSourceDBO(),
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content,
    )
}

internal fun SourceDTO.toSourceDBO(): SourceDBO {
    return SourceDBO(
        id = this.id,
        name = this.name,
    )
}

internal fun ArticleDBO.toArticle(): Article {
    return Article(
        id = 0,
        source = this.source.toSource(),
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content,
    )
}

internal fun SourceDBO.toSource(): Source {
    return Source(
        id = this.id,
        name = this.name,
    )
}

