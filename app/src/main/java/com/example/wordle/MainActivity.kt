package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.wordle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // labels
        val guessOne = binding.tvGuessone
        val guessOneCheck = binding.tvGuessonecheck
        val guessTwo = binding.tvGuesstwo
        val guessTwoCheck = binding.tvGuesstwocheck
        val guessThree = binding.tvGuessthree
        val guessThreeCheck = binding.tvGuessthreecheck

        val firstGuess = binding.tvFirstguess
        val firstCheck = binding.tvFirstguesscheck
        val secondGuess = binding.tvSecondguess
        val secondCheck = binding.tvSecondguesscheck
        val thirdGuess = binding.tvThirdguess
        val thirdCheck = binding.tvThirdguesscheck

        val wordGuessed = binding.etGuess
        val button = binding.btnGuess
        val targetWord = binding.tvTarget


        var counter = 0

        targetWord.text = FourLetterWordList.getRandomFourLetterWord().lowercase()
        val wordToGuess = targetWord.text.toString().lowercase()

        button.setOnClickListener{
            counter++

            when(counter) {
                1 -> {
                    guessOne.visibility = View.VISIBLE
                    guessOneCheck.visibility = View.VISIBLE

                    firstGuess.text = wordGuessed.text
                    firstGuess.visibility = View.VISIBLE

                    firstCheck.text = checkGuess(firstGuess.text.toString(), wordToGuess)
                    secondCheck.visibility = View.VISIBLE
                }
                2 -> {
                    guessTwo.visibility = View.VISIBLE
                    guessTwoCheck.visibility = View.VISIBLE

                    secondGuess.text = wordGuessed.text
                    secondCheck.text = checkGuess(secondGuess.text.toString(), targetWord.text.toString())
                }
                3 -> {
                    guessThree.visibility = View.VISIBLE
                    guessThreeCheck.visibility = View.VISIBLE

                    thirdGuess.text = wordGuessed.text
                    thirdCheck.text = checkGuess(thirdGuess.text.toString(), targetWord.text.toString())

                    targetWord.visibility = View.VISIBLE
                    button.isEnabled = false
                }
            }
        }
    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String, wordToGuess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}