package com.haldny.themoviedatabase.service.impl

import com.haldny.themoviedatabase.entity.CreditResponse
import com.haldny.themoviedatabase.entity.Movie
import com.haldny.themoviedatabase.entity.MovieResponse
import com.haldny.themoviedatabase.entity.es.ConvertedMovieES
import com.haldny.themoviedatabase.entity.es.MovieES
import com.haldny.themoviedatabase.repository.FetchMovieRepository
import com.haldny.themoviedatabase.repository.MovieRepository
import com.haldny.themoviedatabase.repository.es.MovieESRepository
import com.haldny.themoviedatabase.service.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.apache.lucene.search.join.ScoreMode
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.index.query.QueryBuilders.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.stereotype.Service
import org.springframework.data.elasticsearch.core.query.SearchQuery
import java.text.SimpleDateFormat
import java.util.*


@Service
class MovieServiceImpl: MovieService {

    @Autowired
    private lateinit var movieRepository: MovieRepository

    @Autowired
    private lateinit var fetchMovieRepository: FetchMovieRepository

    @Autowired
    private lateinit var movieESRepository: MovieESRepository

    override fun saveMovie(title: String): Movie {
        var movie = Movie(title = title)
        fetchMovieFromAPIByTitle(title)
        movie = movieRepository.save(movie)
        System.out.println("Movie database value: " + movie)
        return movie
    }

    override fun getMoviesByTitle(title: String): List<MovieES> {
        System.out.println("MovieServiceImpl: getMoviesByTitle: $title")
        val movieList =
                movieESRepository.search(QueryBuilders.matchQuery("movie.title", title))
        //val movieByTitle = movieESRepository.findByMovieTitle(title)
        //System.out.println("MovieServiceImpl: getMoviesByTitle: $movieByTitle")
        System.out.println("MovieServiceImpl: getMoviesByTitle: $movieList")
        return movieList.toList()
    }

    override fun getMoviesBySynopsis(synopsis: String): List<MovieES> {
        System.out.println("MovieServiceImpl: getMoviesBySynopsis: $synopsis")
        val movieList =
                movieESRepository.search(QueryBuilders.termQuery("movie.overview", synopsis))
        System.out.println("MovieServiceImpl: getMoviesBySynopsis: $movieList")
        return movieList.toList()
    }

    override fun getMoviesByActor(actor: String): List<MovieES> {
        System.out.println("MovieServiceImpl: getMoviesByActor: $actor")
        val movieList =
                movieESRepository.search(QueryBuilders.matchQuery("credit.casts.name", actor))

        System.out.println("MovieServiceImpl: getMoviesByActor: $movieList")
        return movieList.toList()
    }

    override fun getMoviesByDirector(director: String): List<MovieES> {
        System.out.println("MovieServiceImpl: getMoviesByDirector: $director")

        val directorQueryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("credit.crews.name", director))
                .must(QueryBuilders.matchQuery("credit.crews.job", "Director"))

        val movieList = movieESRepository.search(directorQueryBuilder)
        System.out.println("MovieServiceImpl: getMoviesByDirector: $movieList")
        return movieList.toList()
    }

    override fun getMoviesByTime(startDate: String, endDate: String): List<MovieES> {
        System.out.println("MovieServiceImpl: getMoviesByTime: $startDate - $endDate")

        try {
            val start = SimpleDateFormat("yyyy-MM-dd").parse(startDate)
            val end = SimpleDateFormat("yyyy-MM-dd").parse(endDate)

            val queryBuilder = QueryBuilders.boolQuery()
                    .must(QueryBuilders.rangeQuery("movie.releaseDate")
                            .gte(start)
                            .lte(end))

            val movieList = movieESRepository.search(queryBuilder)
            System.out.println("MovieServiceImpl: getMoviesByTime: $movieList")
            return movieList.toList()
        } catch (e: Exception) {
            System.out.println("Error converting data")
            return arrayListOf()
        }
    }

    override fun getMoviesByGenre(genre: Int): List<MovieES> {
        System.out.println("MovieServiceImpl: getMoviesByGenre: $genre")

        val genreBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("movie.genreIds", genre))

        val movieList = movieESRepository.search(genreBuilder)
        System.out.println("MovieServiceImpl: getMoviesByGenre: $movieList")
        return movieList.toList()
    }

    private fun fetchMovieFromAPIByTitle(title: String) {
        val job = GlobalScope.async { getMovies(title) }
        GlobalScope.launch { onResult(job.await()) }
    }

    private suspend fun getMovies(title: String) : MovieResponse? {
        return fetchMovieRepository.fetchMovies(title)
    }

    private suspend fun getCredit(movieId: Int) : CreditResponse? {
        return fetchMovieRepository.fetchCredit(movieId)
    }

    private suspend fun onResult(movieResponse: MovieResponse?) {
        movieResponse?.let {
            it.movies.forEach { movie ->
                System.out.println("Movie: ${movie}")
                val credit = getCredit(movie.id)
                movieESRepository.save(MovieES(movie = ConvertedMovieES(movie), credit = credit))
            }
        } ?: run {
            System.out.println("Movie response is null.")
        }
    }

}