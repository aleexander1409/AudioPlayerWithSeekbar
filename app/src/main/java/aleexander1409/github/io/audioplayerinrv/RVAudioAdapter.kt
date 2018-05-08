package aleexander1409.github.io.audioplayerinrv

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar

class RVAudioAdapter : RecyclerView.Adapter<RVAudioAdapter.RVAudioVH>(), AudioListener {

    private var listAudio: List<AudioPlay> = emptyList()
    private val audioPlayer = AudioPlayerUtil()

    init {
        audioPlayer.setAudioListener(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAudioVH {
        return RVAudioVH(LayoutInflater.from(parent.context).inflate(R.layout.item_audio, parent, false))
    }

    override fun getItemCount(): Int = listAudio.size

    override fun onBindViewHolder(holder: RVAudioVH, position: Int) {
        holder.bindView(listAudio[position])
    }

    fun addAllAudio(list: List<AudioPlay>) {
        listAudio = list
        notifyDataSetChanged()
    }

    inner class RVAudioVH(item: View) : RecyclerView.ViewHolder(item) {

//        init {
//            item.findViewById<ImageView>(R.id.ivPlay).setOnClickListener {
//                val audioPlay = listAudio[adapterPosition]
//                when (audioPlay.stateEnum) {
//                    AudioStateEnum.STOPED -> {
////                        skBar.progress = 0
//                        audioPlay.progress = 0
//                        audioPlayer.play(audioPlay.urlAudio)
//                        audioPlay.stateEnum = AudioStateEnum.PLAYING
//                    }
//                    AudioStateEnum.PAUSED -> {
//                        audioPlayer.play(audioPlay.urlAudio)
//                    }
//                    else -> {
//                        audioPlayer.pause()
//                        audioPlay.progress = audioPlayer.durationMillis()
//                        audioPlay.stateEnum = AudioStateEnum.PAUSED
//                    }
//                }
//            }
//        }

        fun bindView(audioPlay: AudioPlay) {
            val ivPlay = itemView.findViewById<ImageView>(R.id.ivPlay)
            val skBar = itemView.findViewById<SeekBar>(R.id.skBar)

            if (audioPlay.duration != 0) {
                skBar.max = audioPlay.duration
            }
            when (audioPlay.stateEnum) {
                AudioStateEnum.STOPED -> {
                    skBar.progress = 0
                }
                AudioStateEnum.PAUSED -> {
                    skBar.progress = 0
                }
                else -> {
                    skBar.progress = audioPlay.progress
                }
            }

            ivPlay.setOnClickListener {
                when (audioPlay.stateEnum) {
                    AudioStateEnum.STOPED -> {
//                        skBar.progress = 0
                        audioPlay.progress = 0
                        audioPlayer.play(audioPlay.urlAudio)
                        audioPlay.stateEnum = AudioStateEnum.PLAYING
                    }
                    AudioStateEnum.PAUSED -> {
                        audioPlayer.play(audioPlay.urlAudio)
                    }
                    else -> {
                        audioPlayer.pause()
                        audioPlay.progress = audioPlayer.durationMillis()
                        audioPlay.stateEnum = AudioStateEnum.PAUSED
                    }
                }
            }
        }
    }

    override fun onAudioDuration(duration: Int) {
        Log.d("RVAudioAdapter","onAudioDuration : $duration")
    }

    override fun onProgressListener(progress: Int) {
        Log.d("RVAudioAdapter","onProgressListener : $progress")
    }

    override fun onCompleted() {
        Log.d("RVAudioAdapter","onCompleted")
    }
}