package com.joshnark.domain_layer.models.db_entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.joshnark.domain_layer.models.Gif
import com.joshnark.domain_layer.models.db_entities.GifEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class GifEntity(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("embed_url")
    val embedUrl: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("username")
    val username: String?,

    val cachedUrl: String?,
    val isLiked: Boolean
) {

    fun toGif(): Gif {
        return Gif(
            id = this.id,
            embedUrl = this.embedUrl,
            slug = this.slug,
            title = this.title,
            type = this.type,
            username = this.username,
            isLiked = this.isLiked,
            cachedUrl = this.cachedUrl
        )
    }

    companion object {
        fun fromGif(gif: Gif): GifEntity {
            return GifEntity(
                id = gif.id,
                embedUrl = gif.embedUrl,
                slug = gif.slug,
                title = gif.title,
                type = gif.type,
                username = gif.username,
                isLiked = gif.isLiked,
                cachedUrl = gif.images?.original?.url
            )
        }

        internal const val TABLE_NAME= "saved_gifs"
    }
}
