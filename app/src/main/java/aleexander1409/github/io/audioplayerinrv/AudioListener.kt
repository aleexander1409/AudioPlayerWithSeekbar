package aleexander1409.github.io.audioplayerinrv

interface AudioListener {

    fun onAudioDuration(duration : Int)
    fun onProgressListener(progress : Int)
    fun onCompleted()
}