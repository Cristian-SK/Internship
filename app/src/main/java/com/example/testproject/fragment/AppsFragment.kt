package com.example.testproject.fragment

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.testproject.R
import com.squareup.picasso.Picasso
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.cardview.widget.CardView


class AppsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_apps, container, false)

        //variable for title,description and image
        val TitleApp = view.findViewById<TextView>(R.id.TitleApp)
        val DescriptionApp = view.findViewById<TextView>(R.id.DescriptionApp)
        val imageView = view.findViewById<ImageView>(R.id.AppImageView)


        //CardView
        val cardViewYT = view.findViewById<CardView>(R.id.cardviewOYT)
        val cardViewNF = view.findViewById<CardView>(R.id.cardviewONt)
        val cardViewDP = view.findViewById<CardView>(R.id.cardviewODP)
        val cardViewF = view.findViewById<CardView>(R.id.cardviewOF)
        val cardViewMLB = view.findViewById<CardView>(R.id.cardviewOMLB)
        val cardViewH = view.findViewById<CardView>(R.id.cardviewOHL)
        val cardViewNBA = view.findViewById<CardView>(R.id.cardviewONBA)
        val cardViewNFL = view.findViewById<CardView>(R.id.cardviewONFL)
        val cardViewATV = view.findViewById<CardView>(R.id.cardviewOATV)
        val cardViewPP = view.findViewById<CardView>(R.id.cardviewOPP)
        val cardViewPV = view.findViewById<CardView>(R.id.cardviewOPV)
        val cardViewUFC = view.findViewById<CardView>(R.id.cardviewOUFC)

        //variable for hover on focus
        val hoverYouTube = view.findViewById<ImageView>(R.id.OpenYoutube)
        val hoverNetflix = view.findViewById<ImageView>(R.id.OpenNetflix)
        val hoverDisney = view.findViewById<ImageView>(R.id.OpenDisneyPlus)
        val hoverFormula = view.findViewById<ImageView>(R.id.OpenFormula)
        val hoverMLB = view.findViewById<ImageView>(R.id.OpenMLB)
        val hoverHulu = view.findViewById<ImageView>(R.id.OpenHulu)
        val hoverNBA = view.findViewById<ImageView>(R.id.OpenNBA)
        val hoverNFL = view.findViewById<ImageView>(R.id.OpenNFL)
        val hoverAppleTV = view.findViewById<ImageView>(R.id.OpenAppleTV)
        val hoverParamountPlus = view.findViewById<ImageView>(R.id.OpenParamountPlus)
        val hoverPrimeVideo = view.findViewById<ImageView>(R.id.OpenPrimeVideo)
        val hoverUFC = view.findViewById<ImageView>(R.id.OpenUFC)
        //val hover = findViewById<ImageView>(R.id.Open)

        //calls the filter
        val button = view.findViewById<Button>(R.id.button)

        //variable shares checkbox current state
        val sharedPref = requireContext().getSharedPreferences("checkboxState", Context.MODE_PRIVATE)

        //Calls the check box layout
        button.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.checkbox)
            dialog.setTitle("Select Options")
            val window = dialog.window
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

            //Variable for each checkbox
            val checkBoxYouTube = dialog.findViewById<CheckBox>(R.id.checkBoxYouTube)
            val checkBoxNetflix = dialog.findViewById<CheckBox>(R.id.checkBoxNetflix)
            val checkBoxDisney = dialog.findViewById<CheckBox>(R.id.checkBoxDisney)
            val checkBoxFormula = dialog.findViewById<CheckBox>(R.id.checkBoxFormula)
            val checkBoxMLB = dialog.findViewById<CheckBox>(R.id.checkBoxMLB)
            val checkBoxHulu = dialog.findViewById<CheckBox>(R.id.checkBoxHulu)
            val checkBoxNBA = dialog.findViewById<CheckBox>(R.id.checkBoxNBA)
            val checkBoxNFL = dialog.findViewById<CheckBox>(R.id.checkBoxNFL)
            val checkBoxAppleTV = dialog.findViewById<CheckBox>(R.id.checkBoxAppleTV)
            val checkBoxParamountPlus = dialog.findViewById<CheckBox>(R.id.checkBoxParamountPlus)
            val checkBoxPrimeVideo = dialog.findViewById<CheckBox>(R.id.checkBoxPrimeVideo)
            val checkBoxUFC = dialog.findViewById<CheckBox>(R.id.checkBoxUFC)
            //val checkBox = dialog.findViewById<CheckBox>(R.id.checkBox)

            //Assign all checkbox true for them to all
            checkBoxYouTube.isChecked = sharedPref.getBoolean("checkBoxYouTube", true)
            checkBoxNetflix.isChecked = sharedPref.getBoolean("checkBoxNetflix", true)
            checkBoxDisney.isChecked = sharedPref.getBoolean("checkBoxDisney", true)
            checkBoxFormula.isChecked = sharedPref.getBoolean("checkBoxFormula", true)
            checkBoxMLB.isChecked = sharedPref.getBoolean("checkBoxMLB", true)
            checkBoxHulu.isChecked = sharedPref.getBoolean("checkBoxHulu", true)
            checkBoxNBA.isChecked = sharedPref.getBoolean("checkBoxNBA", true)
            checkBoxNFL.isChecked = sharedPref.getBoolean("checkBoxNFL", true)
            checkBoxAppleTV.isChecked = sharedPref.getBoolean("checkBoxAppleTV", true)
            checkBoxParamountPlus.isChecked = sharedPref.getBoolean("checkBoxParamountPlus", true)
            checkBoxPrimeVideo.isChecked = sharedPref.getBoolean("checkBoxPrimeVideo", true)
            checkBoxUFC.isChecked = sharedPref.getBoolean("checkBoxUFC", true)
            //checkBox.isChecked = sharedPref.getBoolean("checkBox", true)

            //changes the true statement when needed to a false
            checkBoxYouTube.setOnCheckedChangeListener { _, isChecked ->
                hoverYouTube.visibility = if (isChecked) View.VISIBLE else View.GONE
                with (sharedPref.edit()) {
                    putBoolean("checkBoxYouTube", isChecked)
                    apply()
                }
            }
            checkBoxNetflix.setOnCheckedChangeListener { _, isChecked ->
                hoverNetflix.visibility = if (isChecked) View.VISIBLE else View.GONE
                with (sharedPref.edit()) {
                    putBoolean("checkBoxNetflix", isChecked)
                    apply()
                }
            }
            checkBoxDisney.setOnCheckedChangeListener { _, isChecked ->
                hoverDisney.visibility = if (isChecked) View.VISIBLE else View.GONE
                with (sharedPref.edit()) {
                    putBoolean("checkBoxDisney", isChecked)
                    apply()
                }
            }
            checkBoxFormula.setOnCheckedChangeListener { _, isChecked ->
                hoverFormula.visibility = if (isChecked) View.VISIBLE else View.GONE
                with (sharedPref.edit()) {
                    putBoolean("checkBoxFormula", isChecked)
                    apply()
                }
            }
            checkBoxMLB.setOnCheckedChangeListener { _, isChecked ->
                hoverMLB.visibility = if (isChecked) View.VISIBLE else View.GONE
                with (sharedPref.edit()) {
                    putBoolean("checkBoxMLB", isChecked)
                    apply()
                }
            }
            checkBoxHulu.setOnCheckedChangeListener { _, isChecked ->
                hoverHulu.visibility = if (isChecked) View.VISIBLE else View.GONE
                with (sharedPref.edit()) {
                    putBoolean("checkBoxHulu", isChecked)
                    apply()
                }
            }
            checkBoxNBA.setOnCheckedChangeListener { _, isChecked ->
                hoverNBA.visibility = if (isChecked) View.VISIBLE else View.GONE
                with (sharedPref.edit()){
                    putBoolean("checkBoxNBA", isChecked)
                    apply()
                }

            }
            checkBoxNFL.setOnCheckedChangeListener { _, isChecked ->
                hoverNFL.visibility = if (isChecked) View.VISIBLE else View.GONE
                with (sharedPref.edit()){
                    putBoolean("checkBoxNFL", isChecked)
                    apply()
                }

            }
            checkBoxAppleTV.setOnCheckedChangeListener { _, isChecked ->
                hoverAppleTV.visibility = if (isChecked) View.VISIBLE else View.GONE
                with (sharedPref.edit()){
                    putBoolean("checkBoxAppleTV", isChecked)
                    apply()
                }

            }
            checkBoxParamountPlus.setOnCheckedChangeListener { _, isChecked ->
                hoverParamountPlus.visibility = if (isChecked) View.VISIBLE else View.GONE
                with (sharedPref.edit()){
                    putBoolean("checkBoxParamountPlus", isChecked)
                    apply()
                }

            }
            checkBoxPrimeVideo.setOnCheckedChangeListener { _, isChecked ->
                hoverPrimeVideo.visibility = if (isChecked) View.VISIBLE else View.GONE
                with (sharedPref.edit()){
                    putBoolean("checkBoxPrimeVideo", isChecked)
                    apply()
                }

            }
            checkBoxUFC.setOnCheckedChangeListener { _, isChecked ->
                hoverUFC.visibility = if (isChecked) View.VISIBLE else View.GONE
                with (sharedPref.edit()){
                    putBoolean("checkBoxUFC", isChecked)
                    apply()
                }

            }


            //variable to confirm changes
            val confirmButton = dialog.findViewById<Button>(R.id.confirmButton)
            //What is not checked wont show up on screen
            confirmButton.setOnClickListener {
                hoverYouTube.visibility = if (checkBoxYouTube.isChecked) View.VISIBLE else View.GONE
                hoverNetflix.visibility = if (checkBoxNetflix.isChecked) View.VISIBLE else View.GONE
                hoverDisney.visibility = if (checkBoxDisney.isChecked) View.VISIBLE else View.GONE
                hoverFormula.visibility = if (checkBoxFormula.isChecked) View.VISIBLE else View.GONE
                hoverMLB.visibility = if (checkBoxMLB.isChecked) View.VISIBLE else View.GONE
                hoverHulu.visibility = if (checkBoxHulu.isChecked) View.VISIBLE else View.GONE
                hoverNBA.visibility = if (checkBoxNBA.isChecked) View.VISIBLE else View.GONE
                hoverNFL.visibility = if (checkBoxNFL.isChecked) View.VISIBLE else View.GONE
                hoverAppleTV.visibility = if (checkBoxAppleTV.isChecked) View.VISIBLE else View.GONE
                hoverParamountPlus.visibility = if (checkBoxParamountPlus.isChecked) View.VISIBLE else View.GONE
                hoverPrimeVideo.visibility = if (checkBoxPrimeVideo.isChecked) View.VISIBLE else View.GONE
                hoverUFC.visibility = if (checkBoxUFC.isChecked) View.VISIBLE else View.GONE
                //hover--.visibility = if (checkBox--.isChecked) View.VISIBLE else View.GONE

                with (sharedPref.edit()) {
                    putBoolean("checkBoxYouTube", checkBoxYouTube.isChecked)
                    putBoolean("checkBoxNetflix", checkBoxNetflix.isChecked)
                    putBoolean("checkBoxDisney", checkBoxDisney.isChecked)
                    putBoolean("checkBoxFormula", checkBoxFormula.isChecked)
                    putBoolean("checkBoxMLB", checkBoxMLB.isChecked)
                    putBoolean("checkBoxHulu", checkBoxHulu.isChecked)
                    putBoolean("checkBoxNBA", checkBoxNBA.isChecked)
                    putBoolean("checkBoxNFL", checkBoxNFL.isChecked)
                    putBoolean("checkBoxAppleTV", checkBoxAppleTV.isChecked)
                    putBoolean("checkBoxParamountPlus", checkBoxParamountPlus.isChecked)
                    putBoolean("checkboxPrimeVideo", checkBoxPrimeVideo.isChecked)
                    putBoolean("checkboxUFC", checkBoxUFC.isChecked)
                    //putBoolean("checkBox--", checkBox--.isChecked)
                    apply()
                }

                dialog.dismiss()
            }

            // Set the background semi-transparent
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            dialog.show()
        }

//        hoverYouTube.requestFocus()



        hoverYouTube.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_in_tv)
                cardViewYT.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.text = "YouTube"
                DescriptionApp.text = "Servicio de video donde usuarios " +
                        "pueden ver, compartir, comentar y" +
                        " subir videos propios."
                // Picasso.get().load().fit().centerInside().into(youtubeimage)
                Picasso.get().load(R.drawable.youtubepng).fit().centerInside().into(imageView)

                // imageView.setImageResource(R.drawable.youtubeimage)
                TitleApp.visibility = View.VISIBLE
            } else{
                val anim = AnimationUtils.loadAnimation(requireContext(),R.anim.scale_out_tv)
                cardViewYT.startAnimation(anim)
                anim.fillAfter = true
                TitleApp.visibility = View.INVISIBLE
            }
        }
        hoverNetflix.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_in_tv)
                cardViewNF.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.text = "Netflix"
                DescriptionApp.text = "Servicio de streaming por subscripcion para" +
                        " los miembros para poder ver Series TV y peliculas en sus" +
                        " servicios conectados a la internet."
                Picasso.get().load(R.drawable.netflixpng).fit().centerInside().into(imageView)
                TitleApp.visibility = View.VISIBLE
            } else{
                val anim = AnimationUtils.loadAnimation(requireContext(),R.anim.scale_out_tv)
                cardViewNF.startAnimation(anim)
                anim.fillAfter = true
                TitleApp.visibility = View.INVISIBLE
            }
        }
        hoverDisney.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_in_tv)
                cardViewDP.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.text = "Disney"
                DescriptionApp.text = "Enjoy unlimited entertainment on Disney+ Channel!\n" +
                        "\n" +
                        "Stream and download exclusive Disney+ movies and TV shows on your mobile device at anytime and anywhere."
                Picasso.get().load(R.drawable.disney).fit().centerInside().into(imageView)
                TitleApp.visibility = View.VISIBLE
            } else{
                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_out_tv)
                cardViewDP.startAnimation(anim)
                anim.fillAfter = true
                TitleApp.visibility = View.INVISIBLE
            }
        }
        hoverFormula.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_in_tv)
                cardViewF.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.text = "Formula"
                DescriptionApp.text = "Permite saber todo sobre la competicion de " +
                        "automovilismo.Puedes ver horarios de los grandes premios," +
                        "todas las noticias despues de las carreras, " +
                        "analisis de expertos, mapa interactivos e incluso" +
                        "los datos de telemetria en vivo."
                Picasso.get().load(R.drawable.formulaonepngi).fit().centerInside().into(imageView)
                TitleApp.visibility = View.VISIBLE
            } else{
                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_out_tv)
                cardViewF.startAnimation(anim)
                anim.fillAfter = true
                TitleApp.visibility = View.INVISIBLE
            }
        }
        hoverMLB.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){

                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_in_tv)
                cardViewMLB.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.text = "MLB"
                DescriptionApp.text = "Servicio numero uno de Baseball en vivo. Te brinda acceso a " +
                        "transmisiones de juegos en vivo," +
                        "resutados y horarios, noticias de ultima hora y mas."
                Picasso.get().load(R.drawable.mlbpng).fit().centerInside().into(imageView)
                TitleApp.visibility = View.VISIBLE
            } else{

                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_out_tv)
                cardViewMLB.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.visibility = View.INVISIBLE
            }
        }
        hoverHulu.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){

                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_in_tv)
                cardViewH.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.text = "Hulu"
                DescriptionApp.text = "Transmite programas de televisión, películas y más con Hulu." +
                        " Desde programas y películas galardonados hasta NFL y ESPN," +
                        " solo toma unos segundos descargar, ver programas y mantenerse al día con" +
                        " los últimos deportes en vivo."
                Picasso.get().load(R.drawable.hulupng).fit().centerInside().into(imageView)
                TitleApp.visibility = View.VISIBLE
            } else{

                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_out_tv)
                cardViewH.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.visibility = View.INVISIBLE
            }
        }
        hoverNBA.setOnFocusChangeListener {v, hasFocus ->
            if(hasFocus){

                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_in_tv)
                cardViewNBA.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.text = "NBA"
                DescriptionApp.text = "No te pierdas ni un momento con las últimas noticias " +
                        "de la liga, historias entre bastidores y lo más destacado de los " +
                        "partidos para acercarte a la Asociación Nacional de Baloncesto."
                Picasso.get().load(R.drawable.nbapng).fit().centerInside().into(imageView)
                TitleApp.visibility = View.VISIBLE
            } else{

                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_out_tv)
                cardViewNBA.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.visibility= View.INVISIBLE
            }
        }
        hoverNFL.setOnFocusChangeListener {v, hasFocus ->
            if(hasFocus){

                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_in_tv)
                cardViewNFL.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.text = "NFL"
                DescriptionApp.text = "NFL+ es el servicio de transmisión exclusivo " +
                        "de la Liga Nacional de Fútbol Americano (NFL, por sus siglas " +
                        "en inglés) y te ofrece juegos locales y en horario estelar EN" +
                        " VIVO en dispositivos móviles, NFL RedZone, NFL Network, " +
                        "repeticiones de juegos y más, todo en un solo lugar. NFL+ existe " +
                        "dentro de la aplicación de la NFL y el ecosistema NFL.com."
                Picasso.get().load(R.drawable.nflpng).fit().centerInside().into(imageView)
                TitleApp.visibility = View.VISIBLE
            } else{

                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_out_tv)
                cardViewNFL.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.visibility= View.INVISIBLE
            }
        }
        hoverAppleTV.setOnFocusChangeListener {v, hasFocus ->
            if(hasFocus){

                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_in_tv)
                cardViewATV.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.text = "AppleTV"
                DescriptionApp.text = "Obtén todos tus televisores favoritos, " +
                        "todo en una sola aplicación. Mira Apple Originals " +
                        "exclusivos de Apple TV+. Mira películas nuevas y populares" +
                        " y canales premium. Todo curado y personalizado para ti."
                Picasso.get().load(R.drawable.appletvpng).fit().centerInside().into(imageView)
                TitleApp.visibility = View.VISIBLE
            } else{

                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_out_tv)
                cardViewATV.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.visibility= View.INVISIBLE
            }
        }
        hoverParamountPlus.setOnFocusChangeListener {v, hasFocus ->
            if(hasFocus){

                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_in_tv)
                cardViewPP.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.text = "Paramount+"
                DescriptionApp.text = "Las estrellas se han alineado: Paramount+ es " +
                        "el nuevo hogar de transmisión de SHOWTIME. ¡Mira episodios " +
                        "completos bajo demanda de series exitosas como Survivor, " +
                        "NCIS, Bob Esponja, Ink Master y más!"
                Picasso.get().load(R.drawable.paramountpluspng).fit().centerInside().into(imageView)
                TitleApp.visibility = View.VISIBLE
            } else{

                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_out_tv)
                cardViewPP.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.visibility= View.INVISIBLE
            }
        }
        hoverPrimeVideo.setOnFocusChangeListener {v, hasFocus ->
            if(hasFocus){

                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_in_tv)
                cardViewPV.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.text = "PrimeVideo"
                DescriptionApp.text = "Watch movies, TV, and sports, as well as recommendations" +
                        " just for you. Prime Video delivers exclusive Amazon Originals " +
                        "like The Lord of the Rings: The Rings of Power, The Boys, " +
                        "The Marvelous Mrs. Maisel, and Tom Clancy’s Jack Ryan, along with " +
                        "International Originals like Last One Laughing and Mirzapur."
                Picasso.get().load(R.drawable.primevideopng).fit().centerInside().into(imageView)
                TitleApp.visibility = View.VISIBLE
            } else{

                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_out_tv)
                cardViewPV.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.visibility= View.INVISIBLE
            }
        }
        hoverUFC.setOnFocusChangeListener {v, hasFocus ->
            if(hasFocus){

                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_in_tv)
                cardViewUFC.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.text = "UFC"
                DescriptionApp.text = "La aplicación oficial de Ultimate Fighting Championship" +
                        " es el hogar de UFC FIGHT PASS, el servicio de transmisión exclusivo " +
                        "de UFC y el principal destino para los fanáticos de los deportes de " +
                        "combate."
                Picasso.get().load(R.drawable.ufcpng).fit().centerInside().into(imageView)
                TitleApp.visibility = View.VISIBLE
            } else{

                val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_out_tv)
                cardViewUFC.startAnimation(anim)
                anim.fillAfter = true

                TitleApp.visibility= View.INVISIBLE
            }
        }


        val openYouTubeButton = view.findViewById<ImageButton>(R.id.OpenYoutube)
        val openNetflixButton = view.findViewById<ImageButton>(R.id.OpenNetflix)
        val openDisneyButton = view.findViewById<ImageButton>(R.id.OpenDisneyPlus)
        val openFormulaButton = view.findViewById<ImageButton>(R.id.OpenFormula)
        val openMLBButton = view.findViewById<ImageButton>(R.id.OpenMLB)
        val openHuluButton = view.findViewById<ImageButton>(R.id.OpenHulu)
        val openNBAButton = view.findViewById<ImageButton>(R.id.OpenNBA)
        val openNFLButton = view.findViewById<ImageButton>(R.id.OpenNFL)
        val openAppleTVButton = view.findViewById<ImageButton>(R.id.OpenAppleTV)
        val openParamountPlusButton = view.findViewById<ImageButton>(R.id.OpenParamountPlus)
        val openPrimeVideoButton = view.findViewById<ImageButton>(R.id.OpenPrimeVideo)
        val openUFCButton = view.findViewById<ImageButton>(R.id.OpenUFC)


        Picasso.get().load(R.drawable.youtubepng).fit().centerInside().into(openYouTubeButton)
        Picasso.get().load(R.drawable.netflixpng).fit().centerInside().into(openNetflixButton)
        Picasso.get().load(R.drawable.disney).fit().centerInside().into(openDisneyButton)
        Picasso.get().load(R.drawable.formulaonepngi).fit().centerInside().into(openFormulaButton)
        Picasso.get().load(R.drawable.mlbpng).fit().centerInside().into(openMLBButton)
        Picasso.get().load(R.drawable.hulupng).fit().centerInside().into(openHuluButton)
        Picasso.get().load(R.drawable.nbapng).fit().centerInside().into(openNBAButton)
        Picasso.get().load(R.drawable.nflpng).fit().centerInside().into(openNFLButton)
        Picasso.get().load(R.drawable.appletvpng).fit().centerInside().into(openAppleTVButton)
        Picasso.get().load(R.drawable.paramountpluspng).fit().centerInside().into(openParamountPlusButton)
        Picasso.get().load(R.drawable.primevideopng).fit().centerInside().into(openPrimeVideoButton)
        Picasso.get().load(R.drawable.ufcpng).fit().centerInside().into(openUFCButton)


        //val open--Button = findViewById<View>(R.id.Open-)
        //pkg=com.google.android.youtube.tv cmp=com.google.android.youtube.tv/.MainActivity
        openYouTubeButton.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                val launchIntent =
                    activity!!.packageManager.getLaunchIntentForPackage("com.google.android.youtube.tv")
                if (launchIntent != null) {
                    startActivity(launchIntent)
                } else {
                    Toast.makeText(
                        activity,
                        "Not available at this moment",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        })
        //Contrato
        openNetflixButton.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                val launchIntent =
                    activity!!.packageManager.getLaunchIntentForPackage("com.netflix.mediaclient")
                if (launchIntent != null) {
                    startActivity(launchIntent)
                } else {
                    Toast.makeText(
                        activity,
                        "Not available at this moment",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        })
        openDisneyButton.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                val launchIntent =
                    activity!!.packageManager.getLaunchIntentForPackage("com.disney.disneyplus")
                if (launchIntent != null) {
                    startActivity(launchIntent)
                } else {
                    Toast.makeText(
                        activity,
                        "Not available at this moment",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        })
        openFormulaButton.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                val launchIntent =
                    activity!!.packageManager.getLaunchIntentForPackage("com.formulaone.production")
                if (launchIntent != null) {
                    startActivity(launchIntent)
                } else {
                    Toast.makeText(
                        activity,
                        "Not available at this moment",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        })
        openNBAButton.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                val launchIntent =
                    activity!!.packageManager.getLaunchIntentForPackage("com.nbaimd.gametime.nba2011")
                if (launchIntent != null) {
                    startActivity(launchIntent)
                } else {
                    Toast.makeText(
                        activity,
                        "Not available at this moment",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        })
        openHuluButton.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                val launchIntent =
                    activity!!.packageManager.getLaunchIntentForPackage("com.hulu.plus")
                if (launchIntent != null) {
                    startActivity(launchIntent)
                } else {
                    Toast.makeText(
                        activity,
                        "Not available at this moment",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        })
        openMLBButton.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                val launchIntent =
                    activity!!.packageManager.getLaunchIntentForPackage("com.bamnetworks.mobile.android.gameday.atbat")
                if (launchIntent != null) {
                    startActivity(launchIntent)
                } else {
                    Toast.makeText(
                        activity,
                        "Not available at this moment",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        })
        openNFLButton.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                val launchIntent =
                    activity!!.packageManager.getLaunchIntentForPackage("com.gotv.nflgamecenter.us.lite")
                if (launchIntent != null) {
                    startActivity(launchIntent)
                } else {
                    Toast.makeText(
                        activity,
                        "Not available at this moment",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        })
        openAppleTVButton.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                val launchIntent =
                    activity!!.packageManager.getLaunchIntentForPackage("com.apple.atve.androidtv.appletv")
                if (launchIntent != null) {
                    startActivity(launchIntent)
                } else {
                    Toast.makeText(
                        activity,
                        "Not available at this moment",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        })
        openParamountPlusButton.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                val launchIntent =
                    activity!!.packageManager.getLaunchIntentForPackage("com.cbs.ott")
                if (launchIntent != null) {
                    startActivity(launchIntent)
                } else {
                    Toast.makeText(
                        activity,
                        "Not available at this moment",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        })
        //pkg=com.amazon.amazonvideo.livingroom cmp=com.amazon.amazonvideo.livingroom/com.amazon.ignition.IgnitionActivity
        openPrimeVideoButton.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                val launchIntent =
                    activity!!.packageManager.getLaunchIntentForPackage("com.amazon.avod.thirdpartyclient")
                if (launchIntent != null) {
                    startActivity(launchIntent)
                } else {
                    Toast.makeText(
                        activity,
                        "Not available at this moment",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        })
        //pkg=com.neulion.smartphone.ufc.android cmp=com.neulion.smartphone.ufc.android/com.dicetv.MainActivity
        openUFCButton.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                val launchIntent =
                    activity!!.packageManager.getLaunchIntentForPackage("com.neulion.smartphone.ufc.android")
                if (launchIntent != null) {
                    startActivity(launchIntent)
                } else {
                    Toast.makeText(
                        activity,
                        "Not available at this moment",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        })

        return view
    }




}