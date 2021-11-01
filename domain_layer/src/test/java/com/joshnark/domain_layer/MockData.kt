package com.joshnark.domain_layer

import com.joshnark.domain_layer.models.*

object MockData {

    val mockGifList = (0..5).map {
        Gif(
            id = it.toString(),
            title = "test title",
            embedUrl = "https://giphy.com/embed/ErZ8hv5eO92JW",
            username = "test username",
            source = "test source name",
            images = Images(
                original = Original(
                    url = "https://media3.giphy.com/media/ErZ8hv5eO92JW/giphy.gif?cid=ee710cb7ftqtf7onsr7au4o46nupwg97xr2fawu5xbo47rbo&rid=giphy.gif&ct=g",
                    webp = "https://media3.giphy.com/media/ErZ8hv5eO92JW/giphy.webp?cid=ee710cb7ftqtf7onsr7au4o46nupwg97xr2fawu5xbo47rbo&rid=giphy.webp&ct=g"
                ),
                previewGif = PreviewGif(
                    url = "https://media3.giphy.com/media/ErZ8hv5eO92JW/giphy-preview.gif?cid=ee710cb7ftqtf7onsr7au4o46nupwg97xr2fawu5xbo47rbo&rid=giphy-preview.gif&ct=g"
                )
            ),
            user = User(
                displayName = "test user",
                avatarUrl = "https://media3.giphy.com/media/ErZ8hv5eO92JW/giphy_s.gif?cid=ee710cb7ftqtf7onsr7au4o46nupwg97xr2fawu5xbo47rbo&rid=giphy_s.gif&ct=g",
            ),
            isLiked = false,
            cachedUrl = null,
        )
    }

}