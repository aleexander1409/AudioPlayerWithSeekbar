package aleexander1409.github.io.audioplayerinrv

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var mAdapter: RVAudioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = RVAudioAdapter()
        rvAudio.adapter = mAdapter

        val arrayListAudio = mutableListOf<AudioPlay>()
        for (i in 0..10) {
            arrayListAudio.add(AudioPlay(i.toString(), "https://awstaxibucket01.s3.amazonaws.com/xmppDemoLib/9361413d-5e49-4c80-ae73-0bff6debe155.m4a", 0, 0, AudioStateEnum.STOPED))
        }
        mAdapter.addAllAudio(arrayListAudio)
    }
}
