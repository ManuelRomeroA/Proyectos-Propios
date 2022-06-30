package com.example.myapplication.Activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication.QuestionProvider.Question
import com.example.myapplication.QuestionProvider.QuestionProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityQuizBinding


class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        quiz()
    }

    private fun quiz() {
        val initTimer: Long = 30
        val timerMilisecond: Long = initTimer * 1000
        val questionList: MutableList<Question> = QuestionProvider.QuestionList
        var question: Question = questionList.random()
        var cont = 0

        buildQuestion(question)
        binding.tvContador.text = cont.toString()

        val timer = object : CountDownTimer(timerMilisecond, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val timerSeconds: Int = (millisUntilFinished / 1000).toInt()
                binding.tvTimer.text = timerSeconds.toString()
                binding.tvContador.text = cont.toString()
                styleTime(timerSeconds)
            }

            override fun onFinish() {
                Toast.makeText(this@QuizActivity, "El Telefono Vibra", Toast.LENGTH_LONG).show()
                questionList.remove(question)
                question = questionList.random()
                buildQuestion(question)
                quiz()
            }
        }.start()

        binding.btnResponse1.setOnClickListener {
            if (selectionResponse1(question)) {
                cont += 1
            }
            goToResult(questionList, cont)
            questionList.remove(question)
            question = questionList.random()
            buildQuestion(question)
            timer.cancel()
            timer.start()
        }
        binding.btnResponse2.setOnClickListener {
            if (selectionResponse2(question)) {
                cont += 1
            }
            goToResult(questionList, cont)
            questionList.remove(question)
            question = questionList.random()
            buildQuestion(question)
            timer.cancel()
            timer.start()
        }
        binding.btnResponse3.setOnClickListener {
            if (selectionResponse3(question)) {
                cont += 1
            }
            goToResult(questionList, cont)
            questionList.remove(question)
            question = questionList.random()
            buildQuestion(question)
            timer.cancel()
            timer.start()
        }
    }

    fun buildQuestion(question: Question?) {
        if (question != null) {
            binding.tvQuestion.text = question.question
            binding.btnResponse1.text = question.response1
            binding.btnResponse2.text = question.response2
            binding.btnResponse3.text = question.response3
            Glide.with(this).load(question.image).into(binding.ivQuestion)

        }
    }

    fun selectionResponse1(question: Question): Boolean {
        if (question.answer == question.response1) {
            return true
        }
        return false
    }

    fun selectionResponse2(question: Question): Boolean {
        if (question.answer == question.response2) {
            return true
        }
        return false
    }

    fun selectionResponse3(question: Question): Boolean {
        if (question.answer == question.response3) {
            return true
        }
        return false
    }

    private fun goToResult(list: MutableList<Question>, cont: Int) {
        if (list.size == 1) {
            val intent = Intent(this, ResultActivity::class.java)
            val userName = getResult()
            intent.putExtra("NAME", userName)
            intent.putExtra("RESULTADO", cont)
            startActivity(intent)
        }
    }

    private fun getResult(): String {
        val bundle = intent.extras
        val userName = bundle?.get("NAME")
        return userName.toString()

    }

    private fun styleTime(timerSeconds: Int) {
        val styleTime = binding.tvTimer
        when (timerSeconds) {
            in 16..30 -> {
                styleTime.setTextColor(Color.GREEN)
                styleTime.setBackgroundResource(R.drawable.shape_timer_green)
            }
            in 6..15 -> {
                styleTime.setTextColor(Color.YELLOW)
                styleTime.setBackgroundResource(R.drawable.shape_timer_yellow)
            }
            in 0..5 -> {
                styleTime.setTextColor(Color.RED)
                styleTime.setBackgroundResource(R.drawable.shape_timer_red)
            }
        }
    }


}

