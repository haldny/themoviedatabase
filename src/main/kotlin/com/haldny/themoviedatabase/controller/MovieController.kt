package com.haldny.themoviedatabase.controller

import com.haldny.themoviedatabase.entity.Movie
import com.haldny.themoviedatabase.entity.es.MovieES
import com.haldny.themoviedatabase.service.MovieService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/movie")
class MovieController {

    @Autowired
    private lateinit var movieService: MovieService

    @PostMapping(value = ["/save"],
                 consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE],
                 produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun saveMovie(@RequestBody movie: Movie): ResponseEntity<Movie> {
        return ResponseEntity.ok(movieService.saveMovie(movie.title))
    }

    @GetMapping(value = ["/movies/bytitle"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getMoviesByTitle(@RequestParam title: String) : ResponseEntity<List<MovieES>> {
        System.out.println("MovieController: getMoviesByTitle: $title")
        return ResponseEntity.ok(movieService.getMoviesByTitle(title))
    }

    @GetMapping(value = ["/movies/bysynopsis"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getMoviesBySynopsis(@RequestParam synopsis: String) : ResponseEntity<List<MovieES>> {
        System.out.println("MovieController: getMoviesBySynopsis: $synopsis")
        return ResponseEntity.ok(movieService.getMoviesBySynopsis(synopsis))
    }

    @GetMapping(value = ["/movies/byactor"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getMoviesByActor(@RequestParam actor: String) : ResponseEntity<List<MovieES>> {
        System.out.println("MovieController: getMoviesByActor: $actor")
        return ResponseEntity.ok(movieService.getMoviesByActor(actor))
    }

    @GetMapping(value = ["/movies/bydirector"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getMoviesByDirector(@RequestParam director: String) : ResponseEntity<List<MovieES>> {
        System.out.println("MovieController: getMoviesByDirector: $director")
        return ResponseEntity.ok(movieService.getMoviesByDirector(director))
    }

    @GetMapping(value = ["/movies/bytime"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getMoviesByTime(@RequestParam startDate: String, endDate: String) : ResponseEntity<List<MovieES>> {
        System.out.println("MovieController: getMoviesByTime: $startDate - $endDate")
        return ResponseEntity.ok(movieService.getMoviesByTime(startDate, endDate))
    }

    @GetMapping(value = ["/movies/bygenre"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getMoviesByGenre(@RequestParam genre: Int) : ResponseEntity<List<MovieES>> {
        System.out.println("MovieController: getMoviesByGenre: $genre")
        return ResponseEntity.ok(movieService.getMoviesByGenre(genre))
    }

}