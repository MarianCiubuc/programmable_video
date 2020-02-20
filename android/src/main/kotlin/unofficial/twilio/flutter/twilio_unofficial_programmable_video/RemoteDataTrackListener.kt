package unofficial.twilio.flutter.twilio_unofficial_programmable_video

import com.twilio.video.RemoteDataTrack
import java.nio.ByteBuffer

class RemoteDataTrackListener : BaseListener(), RemoteDataTrack.Listener {

    override fun onMessage(remoteDataTrack: RemoteDataTrack, message: ByteBuffer) {
        TwilioUnofficialProgrammableVideoPlugin.debug("RemoteDataTrackListener.onMessage => sid: ${remoteDataTrack.sid}, message (ByteBuffer): $message ")

        sendEvent("bufferMessage", mapOf(
                "remoteDataTrack" to remoteDataTrackToMap(remoteDataTrack),
                "message" to message.array()
        ))
    }

    override fun onMessage(remoteDataTrack: RemoteDataTrack, message: String) {
        TwilioUnofficialProgrammableVideoPlugin.debug("RemoteDataTrackListener.onMessage => sid: ${remoteDataTrack.sid}, message (String): $message")

        sendEvent("stringMessage", mapOf(
                "remoteDataTrack" to remoteDataTrackToMap(remoteDataTrack),
                "message" to message
        ))
    }

    companion object {
        @JvmStatic
        fun remoteDataTrackToMap(remoteDataTrack: RemoteDataTrack): Map<String, Any> {
            return mapOf(
                    "sid" to remoteDataTrack.sid,
                    "name" to remoteDataTrack.name,
                    "enabled" to remoteDataTrack.isEnabled,
                    "ordered" to remoteDataTrack.isOrdered,
                    "reliable" to remoteDataTrack.isReliable,
                    "maxPacketLifeTime" to remoteDataTrack.maxPacketLifeTime,
                    "maxRetransmits" to remoteDataTrack.maxRetransmits
            )
        }
    }
}
