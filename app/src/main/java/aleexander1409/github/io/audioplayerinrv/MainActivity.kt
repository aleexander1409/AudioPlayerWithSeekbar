package aleexander1409.github.io.audioplayerinrv

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var mAdapter : RVAudioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = RVAudioAdapter()
        rvAudio.adapter = mAdapter

        val arrayListAudio = mutableListOf<AudioPlay>()
        for (i in 0 .. 10){
            arrayListAudio.add(AudioPlay(i.toString(),0,0,AudioStateEnum.STOPED))
        }
        mAdapter.addAllAudio(arrayListAudio)
    }
}
