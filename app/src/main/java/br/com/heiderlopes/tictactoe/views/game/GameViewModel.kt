package br.com.heiderlopes.tictactoe.views.game

import androidx.databinding.ObservableArrayMap
import br.com.heiderlopes.tictactoe.models.Player
import androidx.lifecycle.LiveData
import br.com.heiderlopes.tictactoe.models.Cell
import br.com.heiderlopes.tictactoe.models.Game
import androidx.lifecycle.ViewModel
import br.com.heiderlopes.tictactoe.utils.StringUtility.stringFromNumbers


class GameViewModel : ViewModel() {

    lateinit var cells: ObservableArrayMap<String, String>
    private lateinit var game: Game

    val winner: LiveData<Player>
        get() = game.winner

    fun init(player1: String, player2: String) {
        game = Game(player1, player2)
        cells = ObservableArrayMap()
    }

    fun onClickedCellAt(row: Int, column: Int) {
        if (game.cells[row][column] == null) {
            game.cells[row][column] = Cell(game.currentPlayer)
            cells[stringFromNumbers(row, column)] = game.currentPlayer.value
            if (game.hasGameEnded())
                game.reset()
            else
                game.switchPlayer()
        }
    }
}

