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

    private val questionList: MutableList<Question> = QuestionProvider.QuestionList
    private var question: Question = questionList.random()
    private var cont = 0
    private val timer: CountDownTimer? = timer()
    private val feedBackTimer: CountDownTimer? = feedBackTimer()

    private lateinit var binding: ActivityQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        quiz()
    }

    private fun quiz() {
        buildQuestion(question, cont)

        binding.btnResponse1.setOnClickListener {
            if (selectionResponse1(question)) {
                cont += 1
                nextQuestion(questionList, cont)

            } else {
                timer?.cancel()
                feedBackTimer?.start()
            }

        }
        binding.btnResponse2.setOnClickListener {
            if (selectionResponse2(question)) {
                cont += 1
                nextQuestion(questionList, cont)
            } else {
                timer?.cancel()
                feedBackTimer?.start()
            }
        }
        binding.btnResponse3.setOnClickListener {
            if (selectionResponse3(question)) {
                cont += 1
                nextQuestion(questionList, cont)

            } else {
                timer?.cancel()
                feedBackTimer?.start()
            }
        }
    }

    private fun nextQuestion(questionList: MutableList<Question>, cont: Int) {
        goToResult(questionList, cont)
        questionList.remove(question)
        question = questionList.random()
        buildQuestion(question, cont)
    }

    private fun timer(): CountDownTimer? {
        val initTimer: Long = 31
        val timerMilisecond: Long = initTimer * 1000
        timer?.cancel()
        val timer = object : CountDownTimer(timerMilisecond, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val timerSeconds: Int = (millisUntilFinished / 1000).toInt()
                binding.tvTimer.text = timerSeconds.toString()
                styleTime(timerSeconds)
            }

            override fun onFinish() {
                Toast.makeText(this@QuizActivity, "El Telefono Vibra", Toast.LENGTH_LONG).show()
                feedBackTimer?.start()

            }
        }.start()
        return timer
    }

    private fun feedBackTimer(): CountDownTimer {
        timer?.cancel()

        val feedBackTimer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                disableBtn()
                when (question.answer) {
                    question.response1 -> {
                        binding.btnResponse1.setTextColor(Color.GREEN)
                    }
                    question.response2 -> {
                        binding.btnResponse2.setTextColor(Color.GREEN)
                    }
                    question.response3 -> {
                        binding.btnResponse3.setTextColor(Color.GREEN)
                    }
                }
            }

            override fun onFinish() {
                nextQuestion(questionList, cont)

            }
        }
        return feedBackTimer
    }

    fun buildQuestion(question: Question?, cont: Int) {
        val tvQuestion = binding.tvQuestion
        val tvCont = binding.tvContador
        val btnResponse1 = binding.btnResponse1
        val btnResponse2 = binding.btnResponse2
        val btnResponse3 = binding.btnResponse3
        enableBtn()
        if (question != null) {
            //Titulo
            tvQuestion.text = question.question
            //Response1
            btnResponse1.text = question.response1
            btnResponse1.setTextColor(Color.BLACK)
            //Response2
            btnResponse2.text = question.response2
            btnResponse2.setTextColor(Color.BLACK)
            //Response3
            btnResponse3.text = question.response3
            btnResponse3.setTextColor(Color.BLACK)
            //Imagen
            Glide.with(this).load(question.image).into(binding.ivQuestion)
            //Contador
            tvCont.text = cont.toString()
            //Inicio Timer
            timer?.cancel()
            timer?.start()
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
            in 16..31 -> {
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

    private fun disableBtn() {
        binding.btnResponse1.isEnabled = false
        binding.btnResponse2.isEnabled = false
        binding.btnResponse3.isEnabled = false
    }

    private fun enableBtn() {
        binding.btnResponse1.isEnabled = true
        binding.btnResponse2.isEnabled = true
        binding.btnResponse3.isEnabled = true
    }

}

