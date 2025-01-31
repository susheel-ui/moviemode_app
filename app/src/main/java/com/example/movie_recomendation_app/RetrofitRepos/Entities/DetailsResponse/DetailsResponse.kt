package com.example.movie_recomendation_app.RetrofitRepos.Entities.DetailsResponse
 data class DetailsResponse(
    val backdrop: Any,
    val critic_score: Any ,
    val end_year: Any ,
    val genre_names: List<String>,
    val genres: List<Int>,
    val id: Int,
    val imdb_id: String,
    val network_names: List<String>,
    val networks: List<Int>,
    val original_language: String,
    val original_title: String,
    val plot_overview: String,
    val popularity_percentile: Double,
    val poster: String,
    val posterLarge: String,
    val posterMedium: String,
    val release_date: String,
    val relevance_percentile: Double,
    val runtime_minutes: Any,
    val similar_titles: List<Int>,
    val title: String,
    val tmdb_id: Int,
    val tmdb_type: String,
    val trailer: Any,
    val trailer_thumbnail: Any,
    val type: String,
    val us_rating: Any,
    val user_rating: Double,
    val year: Int
){
    override fun equals(other: Any?): Boolean {
       if (this === other) return true
       if (javaClass != other?.javaClass) return false

       other as DetailsResponse

       if (backdrop != other.backdrop) return false
       if (critic_score != other.critic_score) return false
       if (end_year != other.end_year) return false
       if (genre_names != other.genre_names) return false
       if (genres != other.genres) return false
       if (id != other.id) return false
       if (imdb_id != other.imdb_id) return false
       if (network_names != other.network_names) return false
       if (networks != other.networks) return false
       if (original_language != other.original_language) return false
       if (original_title != other.original_title) return false
       if (plot_overview != other.plot_overview) return false
       if (popularity_percentile != other.popularity_percentile) return false
       if (poster != other.poster) return false
       if (posterLarge != other.posterLarge) return false
       if (posterMedium != other.posterMedium) return false
       if (release_date != other.release_date) return false
       if (relevance_percentile != other.relevance_percentile) return false
       if (runtime_minutes != other.runtime_minutes) return false
       if (similar_titles != other.similar_titles) return false
       if (title != other.title) return false
       if (tmdb_id != other.tmdb_id) return false
       if (tmdb_type != other.tmdb_type) return false
       if (trailer != other.trailer) return false
       if (trailer_thumbnail != other.trailer_thumbnail) return false
       if (type != other.type) return false
       if (us_rating != other.us_rating) return false
       if (user_rating != other.user_rating) return false
       if (year != other.year) return false

       return true
    }

    override fun hashCode(): Int {
       var result = backdrop.hashCode()
       result = 31 * result + critic_score.hashCode()
       result = 31 * result + end_year.hashCode()
       result = 31 * result + genre_names.hashCode()
       result = 31 * result + genres.hashCode()
       result = 31 * result + id
       result = 31 * result + imdb_id.hashCode()
       result = 31 * result + network_names.hashCode()
       result = 31 * result + networks.hashCode()
       result = 31 * result + original_language.hashCode()
       result = 31 * result + original_title.hashCode()
       result = 31 * result + plot_overview.hashCode()
       result = 31 * result + popularity_percentile.hashCode()
       result = 31 * result + poster.hashCode()
       result = 31 * result + posterLarge.hashCode()
       result = 31 * result + posterMedium.hashCode()
       result = 31 * result + release_date.hashCode()
       result = 31 * result + relevance_percentile.hashCode()
       result = 31 * result + runtime_minutes.hashCode()
       result = 31 * result + similar_titles.hashCode()
       result = 31 * result + title.hashCode()
       result = 31 * result + tmdb_id
       result = 31 * result + tmdb_type.hashCode()
       result = 31 * result + trailer.hashCode()
       result = 31 * result + trailer_thumbnail.hashCode()
       result = 31 * result + type.hashCode()
       result = 31 * result + us_rating.hashCode()
       result = 31 * result + user_rating.hashCode()
       result = 31 * result + year
       return result
    }
 }
