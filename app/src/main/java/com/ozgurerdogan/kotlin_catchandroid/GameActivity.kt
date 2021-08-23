package com.ozgurerdogan.kotlin_kennyiyakala
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {


    var arraysOf= ArrayList<ImageView>()
    var scorNumber:Int=0

    var handler=Handler(Looper.myLooper()!!)
    var runnable= Runnable {  }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arraysOf.add(r1)
        arraysOf.add(r2)
        arraysOf.add(r3)
        arraysOf.add(r4)
        arraysOf.add(r5)
        arraysOf.add(r6)
        arraysOf.add(r7)
        arraysOf.add(r8)
        arraysOf.add(r9)


        for (i in arraysOf){
            i.visibility=View.INVISIBLE
        }



        scor.text="Scor:$scorNumber"

        play()


        object : CountDownTimer(15000, 1000){

            override fun onTick(millisUntilFinished: Long) {
                time.text = "Remaining Time:\n ${millisUntilFinished / 1000}sn"

            }

            override fun onFinish() {
                handler.removeCallbacks(runnable)

                val alert=AlertDialog.Builder(this@GameActivity)
                alert.setTitle("Restart")
                alert.setMessage("Click Yes to start again")
                alert.setCancelable(false)
                alert.setPositiveButton("Yes"){dialog,which->

                    val intent=intent
                    finish()
                    startActivity(intent)

                }
                alert.setNegativeButton("No"){dialog,which->
                    finish()

                }
                alert.show()

            }

        }.start()
    }

    fun click(view: View){
        scorNumber++
        scor.text="Scor:$scorNumber"
    }

    fun play(){

        runnable=object :Runnable{

            override fun run() {


                for (i in arraysOf){
                    i.visibility=View.INVISIBLE
                }


                val number=List(1){Random.nextInt(0,9)}
                arraysOf[number[0]].visibility=View.VISIBLE

                handler.postDelayed(runnable,1000)
            }

        }
        handler.post(runnable)


       }

}
