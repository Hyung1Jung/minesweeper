import java.util.Random

class MineSweeper {

    companion object {
        const val ROW = 10
        const val COL = 10
        const val MINE_COUNT = 8
        const val GAME_BOARD_DEFAULT_VALUE = "0"
        const val MINE = "*"
    }

    val x = IntArray(MINE_COUNT)
    val y = IntArray(MINE_COUNT)

    val dx = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
    val dy = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)

    private val mineBoard = Array(ROW) { arrayOfNulls<String>(COL) }

    fun play() {
        initMap()
        makeMine()
        showGameBoard()
    }

    private fun initMap() {
        for (i in 0 until ROW) {
            for (j in 0 until COL) {
                mineBoard[i][j] = GAME_BOARD_DEFAULT_VALUE
            }
        }
    }

    private fun makeRandomPosition(index: Int): IntArray {
        val random = Random()
        var randomX: Int
        var randomY: Int

        val result = IntArray(2)

        randomX = random.nextInt(ROW)
        randomY = random.nextInt(COL)

        var i = 0
        while (i < index) {
            if (randomX == x[i] && randomY == y[i]) {
                i = -1
                randomX = random.nextInt(ROW)
                randomY = random.nextInt(COL)
            }
            i++
        }

        result[0] = randomX
        result[1] = randomY

        return result
    }

    private fun makeMine() {

        (0 until MINE_COUNT).forEach { i ->
            val result = makeRandomPosition(i)
            x[i] = result[0]
            y[i] = result[1]
        }

        for (i in 0 until MINE_COUNT) {
            findMine(x[i], y[i])
        }

        for (i in 0 until MINE_COUNT) {
            changeToMine(x[i], y[i])
        }
    }

    private fun changeToMine(x: Int, y: Int) {
        mineBoard[x][y] = MINE
    }

    private fun findMine(x: Int, y: Int) {
        for (k in 0..7) {
            val nx = x + dx[k]
            val ny = y + dy[k]

            if (nx >= ROW || nx < 0 || ny >= COL || ny < 0) continue
            mineBoard[nx][ny] = (mineBoard[nx][ny]!!.toInt() + 1).toString()
        }
    }

    private fun showGameBoard() {
        for (i in 0 until ROW) {
            for (j in 0 until COL) {
                print(mineBoard[i][j].toString() + " ")
            }
            println()
        }
    }

}

fun main() {
    val minesweeper = MineSweeper()
    minesweeper.play()
}

