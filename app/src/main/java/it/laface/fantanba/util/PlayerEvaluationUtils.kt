@file:Suppress("MagicNumber")

package it.laface.fantanba.util

import it.laface.fantanba.models.PlayerGameStats
import kotlin.math.roundToInt

/**
 * Created by Carmine Laface on 24/12/2018.
 */

fun PlayerGameStats.getPlayerValue(): Int {
    val playerValue = getPlayerRealValue()
    return when {
        playerValue > 50 -> 50
        playerValue < -10 -> -10
        else -> playerValue.roundToInt()
    }
}

fun PlayerGameStats.getPlayerRealValue(): Float {
    val positiveValue: Float = getAllPositiveValues()
    val negativeValue: Float = getAllNegativeValues()
    val plusMinusValue: Float = getPlusMinusValue()
    return when (didWin()) {
        true -> (positiveValue * 1.03f) + (negativeValue * .97f) + plusMinusValue
        false -> (negativeValue * 1.03f) + (positiveValue * .97f) + plusMinusValue
        null -> positiveValue + negativeValue
    } / 2f
}

private fun PlayerGameStats.getAllPositiveValues(): Float {
    return getPointsValue() +
        getAssistValue() +
        getReboundValue() +
        getStealsValue() +
        getBlocksValue()
}

private fun PlayerGameStats.getAllNegativeValues(): Float {
    return getFieldGoalsMissedValue() +
        getFoulsValue() +
        getTurnoversValue()
}

private fun PlayerGameStats.getPointsValue(): Float {
    return points
}

fun PlayerGameStats.getFieldGoalsMissedValue(): Float {
    return fieldGoalsMissed() * 0.8f
}

fun PlayerGameStats.fieldGoalsMissed(): Int {
    return fieldGoalsMade - fieldGoalsAttempted
}

private fun PlayerGameStats.getAssistValue(): Float {
    return assist * 1.7f
}

private fun PlayerGameStats.getReboundValue(): Float {
    return totalRebounds * 1.25f
}

private fun PlayerGameStats.getStealsValue(): Float {
    return steals * 3
}

private fun PlayerGameStats.getBlocksValue(): Float {
    return blocks * 2
}

private fun PlayerGameStats.getFoulsValue(): Float {
    return -personalFouls * .7f
}

private fun PlayerGameStats.getPlusMinusValue(): Float {
    return plusMinus * .2f
}

private fun PlayerGameStats.getTurnoversValue(): Float {
    return -turnovers * 1.8f
}

fun PlayerGameStats.didWin(): Boolean? {
    return if (result.isEmpty()) {
        null
    } else result[0].equals('W', true)
}
