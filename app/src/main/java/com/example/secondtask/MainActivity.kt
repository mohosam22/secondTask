package com.example.moviesapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.R


class MainActivity : AppCompatActivity() {

    private val moviesAdapter by lazy { MoviesAdapter() }
    private lateinit var binding: ActivityMainBinding
    private var isShowingUpdatedList = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.movieRV.itemAnimator = DefaultItemAnimator()
        binding.movieRV.layoutAnimation =
            AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_slide)



        binding.movieRV.adapter = moviesAdapter
        binding.movieRV.layoutManager = LinearLayoutManager(this)

        moviesAdapter.updateMovieList(moviesList())


        intent.getStringExtra("username")?.let {
            binding.welcomeTv.text = "Welcome $it"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.more -> {
                val updatedList = if (isShowingUpdatedList) moviesList() else updatedMoviesList()
                moviesAdapter.updateMovieList(updatedList)
                isShowingUpdatedList = !isShowingUpdatedList
                binding.movieRV.scheduleLayoutAnimation()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moviesList() = listOf(
        Movie(1, "The Dark Knight", "Batman faces the Joker, a criminal mastermind bent on chaos in Gotham City", 9.0, "2008", R.drawable.img_2),
        Movie(2, "Inception", "A thief enters dreams to steal secrets but must complete one last mission to redeem himself", 8.8, "2010", R.drawable.img_3),
        Movie(3, "The Shawshank Redemption", "Two imprisoned men form a bond over years of confinement and shared hope", 9.3, "1994", R.drawable.img_4),
        Movie(4, "Forrest Gump", "The story of a man with a low IQ who leads a remarkable life despite the odds", 8.8, "1994", R.drawable.img_5),
        Movie(5, "The Lion King", "A young lion prince flees his kingdom, only to learn his destiny and responsibilities", 8.5, "1994", R.drawable.img_6),
        Movie(6, "The Matrix", "A computer hacker learns about the true nature of his reality and his role in the war against its controllers", 8.7, "1999", R.drawable.img_7),
        Movie(7, "Titanic", "A young couple from different social classes fall in love aboard the ill-fated RMS Titanic", 7.9, "1997", R.drawable.img_8),
        Movie(8, "Avatar", "A paraplegic Marine becomes part of a mission on the alien planet Pandora and finds himself torn between two worlds", 7.8, "2009", R.drawable.img_9),
        Movie(9, "The Avengers", "Earth's mightiest heroes must come together to stop a global threat", 8.0, "2012", R.drawable.img_10),
        Movie(10, "Saving Private Ryan", "A group of soldiers embark on a mission to save a paratrooper during World War II", 8.6, "1998", R.drawable.img_11)
    )


    private fun updatedMoviesList() = listOf(
        Movie(1, "Avengers: Endgame", "Earth's mightiest heroes assemble for one final battle against Thanos", 8.4, "2019", R.drawable.img_12),
        Movie(2, "The Godfather", "The powerful saga of a crime family and its rise and fall in America", 9.2, "1972", R.drawable.img_13),
        Movie(3, "Pulp Fiction", "Interwoven tales of crime, redemption, and dark humor in Los Angeles", 8.9, "1994", R.drawable.img_14),
        Movie(4, "Toy Story", "A group of toys come to life when humans aren't around, navigating friendship and adventure", 8.3, "1995", R.drawable.img_15),
        Movie(5, "Jurassic Park", "A theme park showcasing live dinosaurs faces chaos when the creatures break free", 8.2, "1993", R.drawable.img_16)
    )
}

