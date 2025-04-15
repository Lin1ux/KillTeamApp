package Objects

object KillOpPoints
{
    val KillOpPointMatrix: Map<Int, Map<Int, Int>> = mapOf(
        0 to mapOf(5 to 0, 6 to 0, 7 to 0, 8 to 0, 9 to 0, 10 to 0, 11 to 0, 12 to 0, 13 to 0, 14 to 0),
        1 to mapOf(5 to 1, 6 to 1, 7 to 1, 8 to 0, 9 to 0, 10 to 0, 11 to 0, 12 to 0, 13 to 0, 14 to 0),
        2 to mapOf(5 to 2, 6 to 2, 7 to 1, 8 to 1, 9 to 1, 10 to 1, 11 to 1, 12 to 1, 13 to 0, 14 to 0),
        3 to mapOf(5 to 3, 6 to 2, 7 to 2, 8 to 2, 9 to 1, 10 to 1, 11 to 1, 12 to 1, 13 to 1, 14 to 1),
        4 to mapOf(5 to 4, 6 to 3, 7 to 3, 8 to 2, 9 to 2, 10 to 2, 11 to 2, 12 to 1, 13 to 1, 14 to 1),
        5 to mapOf(5 to 5, 6 to 4, 7 to 3, 8 to 3, 9 to 3, 10 to 2, 11 to 2, 12 to 2, 13 to 2, 14 to 1),
        6 to mapOf(5 to 5, 6 to 5, 7 to 4, 8 to 4, 9 to 3, 10 to 3, 11 to 2, 12 to 2, 13 to 2, 14 to 2),
        7 to mapOf(5 to 5, 6 to 5, 7 to 5, 8 to 4, 9 to 4, 10 to 3, 11 to 3, 12 to 3, 13 to 2, 14 to 2),
        8 to mapOf(5 to 5, 6 to 5, 7 to 5, 8 to 5, 9 to 4, 10 to 4, 11 to 3, 12 to 3, 13 to 3, 14 to 3),
        9 to mapOf(5 to 5, 6 to 5, 7 to 5, 8 to 5, 9 to 5, 10 to 4, 11 to 4, 12 to 3, 13 to 3, 14 to 3),
        10 to mapOf(5 to 5, 6 to 5, 7 to 5, 8 to 5, 9 to 5, 10 to 5, 11 to 4, 12 to 4, 13 to 4, 14 to 3),
        11 to mapOf(5 to 5, 6 to 5, 7 to 5, 8 to 5, 9 to 5, 10 to 5, 11 to 5, 12 to 4, 13 to 4, 14 to 4),
        12 to mapOf(5 to 5, 6 to 5, 7 to 5, 8 to 5, 9 to 5, 10 to 5, 11 to 5, 12 to 5, 13 to 4, 14 to 4),
        13 to mapOf(5 to 5, 6 to 5, 7 to 5, 8 to 5, 9 to 5, 10 to 5, 11 to 5, 12 to 5, 13 to 5, 14 to 4),
        14 to mapOf(5 to 5, 6 to 5, 7 to 5, 8 to 5, 9 to 5, 10 to 5, 11 to 5, 12 to 5, 13 to 5, 14 to 5)
    )

    //KillOpPointMatrix[killed]?.get(maxOperators) // returns points

}