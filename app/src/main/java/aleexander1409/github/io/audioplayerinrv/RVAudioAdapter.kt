package aleexander1409.github.io.audioplayerinrv

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar

class RVAudioAdapter : RecyclerView.Adapter<RVAudioAdapter.RVAudioVH>() {

    private var listAudio: List<AudioPlay> = emptyList()

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

        init {
           item.findViewById<ImageView>(R.id.ivPlay).setOnClickListener {
                when(listAudio[adapterPosition].stateEnum){
                    AudioStateEnum.STOPED -> {
//                        skBar.progress = 0
                        listAudio[adapterPosition].progress = 0
                    }
                    AudioStateEnum.PAUSED -> {
//                        skBar.progress = audioPlay.progress
                    }
                    else -> {
//                        skBar.progress = audioPlay.progress
                    }
                }
            }
        }

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
                when(listAudio[adapterPosition].stateEnum){
                    AudioStateEnum.STOPED -> {
                        skBar.progress = 0
                        listAudio[adapterPosition].progress = 0
                    }
                    AudioStateEnum.PAUSED -> {
                        skBar.progress = audioPlay.progress
                    }
                    else -> {
                        skBar.progress = audioPlay.progress
                    }
                }
            }
        }
    }
}