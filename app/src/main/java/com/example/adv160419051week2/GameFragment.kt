package com.example.adv160419051week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlin.random.Random


class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var playerName = ""
        if (arguments != null) {
            playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName

            if (playerName == "") {
                playerName = "Anonymous"
            }
        }

        txtTurn.text = "$playerName's Turn"

        var num1 = (0..11).random()
        var num2 = (0..11).random()
        var score = 0

        txtNumber1.text = num1.toString()
        txtNumber2.text = num2.toString()

        btnSubmit.setOnClickListener {
            var keyAnswer = (num1 + num2).toString()
            var userAnswer = txtAnswer.text.toString()

            if (userAnswer == keyAnswer) {
                score += 1

                num1 = (0..11).random()
                num2 = (0..11).random()

                txtNumber1.text = num1.toString()
                txtNumber2.text = num2.toString()

                txtAnswer.setText("")

            } else {
                val action = GameFragmentDirections.actionResultFragment(score)
                Navigation.findNavController(it).navigate(action)
            }

        }
    }
}