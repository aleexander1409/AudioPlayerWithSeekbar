package aleexander1409.github.io.audioplayerinrv

import android.media.MediaPlayer
import android.os.Handler
import android.util.Log
import java.util.concurrent.TimeUnit

/**
 * Created by alexanderserafin on 4/18/18
 */
class AudioPlayerUtil {

    private var player: MediaPlayer = MediaPlayer()
    //    private Disposable playingDisposable;
    private var progressListener: ProgressListener? = null
    private var isPaused = false
    private var urlAudio = ""
    private var audioListener: AudioListener? = null
    private val seekHandler = Handler()
    private var isFirstTime = true;

    val run = Runnable { seekUpdation() }

    init {
        player.setOnPreparedListener { mp ->
            seekUpdation()
            if (mp!!.isPlaying) {
                mp.pause()
            } else {
                mp.start()
            }
        }
        player.setOnCompletionListener {
            Log.d("onCompleted()", " setOnCompletionListener firstTime= $isFirstTime")
            if (!isFirstTime) {
                removeRunCallback()
                audioListener?.onCompleted()
            } else {
                isFirstTime = false
            }

        }
    }

    fun play() {
        isPaused = false
        if (player.isPlaying) {
            player.pause()
        } else {
            player.start()
        }

    }

    fun setAudioListener(listener: AudioListener) {
        this.audioListener = listener
    }

    fun setSource(url: String) {
        player.reset()
        player.setDataSource(url)
        player.prepareAsync()
    }

    fun play(url: String) {
//        if (urlAudio != url) {
            urlAudio = url
            setSource(url)
            audioListener?.onAudioDuration(player.duration / 1000)
//        }
        play()
    }

    fun durationMillis(): Int {
        return player.duration
    }

    fun duration(): String {
        return toSeconds(player.duration)
    }

    fun isPlaying(): Boolean {
        return player.isPlaying
    }

    fun position(): String {
        return toSeconds(player.currentPosition)
    }

    fun toSeconds(millis: Int): String {
        return String.format("%d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(millis.toLong()) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis.toLong()))
        )
    }

    fun stop() {
        player.stop()
        isPaused = false
        urlAudio = ""
    }

    fun resetPlayer() {
        player.reset()
    }

    fun releasePlayer() {
        player.release()
    }

    fun pause() {
        isPaused = true
        if (player.isPlaying) {
            player.pause()
        }
    }

    fun isPaused(): Boolean {
        return isPaused
    }

    interface ProgressListener {
        fun update(position: Int)
    }

    fun setProgressListener(listener: ProgressListener) {
        this.progressListener = listener
    }

    fun seekUpdation() {
        audioListener?.onProgressListener(player.currentPosition)
        seekHandler.postDelayed(run, 1000)
    }

    fun removeRunCallback() {
        seekHandler.removeCallbacks(run)
    }


}