package com.example.testproject

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.testproject.databinding.ActivityDetailBinding
import com.example.testproject.model.DetailResponse
import com.example.testproject.viewmodel.DetailViewmodel
import com.example.testproject.viewmodel.DetailViewmodelFactory


class DetailActivity : FragmentActivity() {

    lateinit var binding: ActivityDetailBinding
    lateinit var viewmodel: DetailViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val movieId = intent.getIntExtra("id", 0)
        val repository = (application as MyApplication).tmdbRepo

        viewmodel = ViewModelProvider(this, DetailViewmodelFactory(repository, movieId)).get(DetailViewmodel::class.java)

        viewmodel.movieDetails.observe(this) {

            when (it) {
                is com.example.testproject.api.Response.Loading -> {


                }

                is com.example.testproject.api.Response.Success -> {

                    setData(it.data)

                }

                is com.example.testproject.api.Response.Error -> {
                    Toast.makeText(this, "Error Loading, Server Problems", Toast.LENGTH_LONG).show()

                }
            }
        }

    }

    private fun setData(it: DetailResponse?) {


        binding.title.text = it?.title ?: ""
        binding.subtitle.text = getSubtitle(it)
        binding.description.text = it?.overview ?: ""


        val path= "https://www.themoviedb.org/t/p/w780/" + (it?.backdrop_path ?: "")
        Glide.with(this)
            .load(path)
            .into(binding.imgBanner)
    }

    fun getSubtitle(response: DetailResponse?): String {
        val rating = if (response!!.adult) {
            "18+"
        } else {
            "13+"
        }

        val genres = response.genres.joinToString(
            prefix = " ",
            postfix = " • ",
            separator = " • "
        ) { it.name }

        val hours: Int = response.runtime / 60
        val min: Int = response.runtime % 60

        return rating + genres + hours + "h " + min + "m"
    }
}






