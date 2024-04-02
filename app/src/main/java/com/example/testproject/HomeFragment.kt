package com.example.testproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class HomeFragment : Fragment(), UserInteractionListener {

    lateinit var txtTitle: TextView
    lateinit var txtSubTitle: TextView
    lateinit var txtDescription: TextView
    lateinit var companyLogo: ImageView
    lateinit var imgBanner: ImageView
    lateinit var listFragment: ListFragment

    var player: ExoPlayer? = null
    var videoURL =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"


    val DISCONNECT_TIMEOUT: Long = 5000 // 5 min = 5 * 60 * 1000 ms


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        (activity as MainActivity?)!!.setUserInteractionListener(this)

        player = ExoPlayer.Builder(requireActivity()).build()
        val playerView = view.findViewById<StyledPlayerView>(R.id.playerView)
        playerView.visibility = View.INVISIBLE



        init(view)

        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//    }

    fun init(view: View) {
        val playerView: StyledPlayerView = view.findViewById(R.id.playerView)
        imgBanner = view.findViewById(R.id.img_banner)
        txtTitle = view.findViewById(R.id.title)
        txtSubTitle = view.findViewById(R.id.subtitle)
        txtDescription = view.findViewById(R.id.description)
        companyLogo = view.findViewById(R.id.companyLogo)
//        Picasso.get().load("http://172.16.0.180/iptv-api/images/channel/iqtvicon.png").fit()
//            .centerInside().into(companyLogo)


        listFragment = ListFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.list_fragment, listFragment)
        transaction.commit()


        val gson = Gson()
        val i: InputStream = requireContext().assets.open("movies.json")
        val br = BufferedReader(InputStreamReader(i))
        val dataList: DataModel = gson.fromJson(br, DataModel::class.java)

//        val playerView: StyledPlayerView = view.findViewById(R.id.playerView)
//        playerView.isVisible = false


        listFragment.bindData(dataList)

        listFragment.setOnContentSelectedListener {
            updateBanner(it)

            if (player != null) {
                playerView.player = player
                val mediaItem: MediaItem = MediaItem.fromUri(videoURL)
                player!!.setMediaItem(mediaItem)
                player!!.prepare()
            } else {
                Toast.makeText(activity, "player went offline", Toast.LENGTH_SHORT).show()
            }



        }


        listFragment.setOnItemClickListener{
            if (player != null && player!!.isPlaying) {
                player!!.stop()
                player!!.release()
            }
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("id", it.id)
            startActivity(intent)
        }

    }


    fun updateBanner(dataList: DataModel.Result.Detail) {
        txtTitle.text = dataList.title
        txtDescription.text = dataList.overview


        val url = "https://www.themoviedb.org/t/p/w780" + dataList.backdrop_path
        Glide.with(this).load(url).into(imgBanner)



    }


    private val disconnectHandler = Handler { // todo
        true
    }

    private val disconnectCallback = Runnable { // Perform any required operation on disconnect
        if (player != null) {
            val playerView = view?.findViewById<StyledPlayerView>(R.id.playerView)
            playerView?.visibility = View.VISIBLE
            player!!.playWhenReady = true
        } else {

        }
    }

    fun resetDisconnectTimer() {
        disconnectHandler.removeCallbacks(disconnectCallback)
        disconnectHandler.postDelayed(disconnectCallback, DISCONNECT_TIMEOUT)
        val playerView = view?.findViewById<StyledPlayerView>(R.id.playerView)
        playerView?.visibility = View.INVISIBLE

    }

    fun stopDisconnectTimer() {
        disconnectHandler.removeCallbacks(disconnectCallback)
    }

    override fun onUserInteraction() {
        if(isAdded) {
            if (player != null && player!!.isPlaying) {
                player!!.stop()
                player!!.release()
                player = null
                player = ExoPlayer.Builder(requireActivity()).build()
            }
            resetDisconnectTimer()
        }

    }

    override fun onResume() {
        super.onResume()
        resetDisconnectTimer()
        player!!.stop()
        player!!.release()
        player = ExoPlayer.Builder(requireActivity()).build()
//        player!!.playWhenReady = true

    }

//    fun stop() {
//        if (player != null && player!!.isPlaying) {
//            player!!.stop()
//            player!!.release()
//            player = null;
//        }
//    }


    override fun onStop() {
        super.onStop()
        stopDisconnectTimer()
        player!!.stop()
        player!!.release()
        releasePlayer()

    }

    override fun onPause() {
        super.onPause()
        player!!.stop()
        player!!.release()
        releasePlayer()
//        stopDisconnectTimer()
    }

    private fun releasePlayer() {
        player!!.release()
    }


}


