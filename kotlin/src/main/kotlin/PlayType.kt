import kotlin.math.floor
import kotlin.math.max

enum class PlayType(
    val flatCost: Int,
    val costPerAttendee: Int,
    val attendeeThreshold: Int,
    val flatCostOverThreshold: Int,
    val costPerAttendeeOverThreshold: Int
) {
    Tragedy(40000, 0, 30, 0, 1000),
    Comedy(30000, 300, 20, 10000, 500);

    fun cost(numAttendees: Int): Int {
        val underThresholdCost = flatCost + costPerAttendee*numAttendees
        val attendeesOverThreshold = numAttendees - attendeeThreshold
        return if (numAttendees > attendeeThreshold) {
            underThresholdCost + flatCostOverThreshold + costPerAttendeeOverThreshold*attendeesOverThreshold
        } else {
            underThresholdCost
        }
    }

    fun credits(numAttendees: Int): Int = when (this) {
        Tragedy -> max(numAttendees - 30, 0)
        Comedy -> max(numAttendees - 30, 0) + floor((numAttendees / 5).toDouble()).toInt()
    }
}
