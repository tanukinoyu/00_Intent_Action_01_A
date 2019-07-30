
// 00_Intent_Action_01_Q 画面遷移

//（１）５病後にアラームがなる。
//（２）「今が起きる時」「目覚めよ」、「おはよう」
//     　３種類の文字列からランダムで１文字列を選択し、その文字列を表示
//（３）同時に神経を逆なでするBGM （bgm_beatalarm.ogg）が鳴る。
//（４）入力画面に選択された文字列と同じ文字列を入力する
//（５）STOPボタンを押す
//　　　　正誤判定：正しい場合
//              → 結果画面に遷移する。
//                画面遷移後のResultActivityで受け取ったデータテキストと、
//                「今日も１日がんばろう」メッセージを表示。
//                効果音（se_alarm_stop.ogg）がなり、BGMが止まる。
//
//　　　　　　　　：誤り又は何も書かれてない場合
// 　　　　　　　 → エラーメッセージを表示
//                効果音（se_false.ogg）をならす。


package com.example.a00_intent_action_01_q

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var player: MediaPlayer
    private lateinit var soundPool: SoundPool
    private var soundTrue = 0
    private var soundFalse = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = Handler()
        handler.postDelayed(Runnable{
            timerText.text = setText()
            player = MediaPlayer.create(this,R.raw.bgm_beatalarm)
            player.isLooping = true
            player.start()
        }, 5000)

        button.setOnClickListener {
            if(editText.text.toString() == timerText.text.toString()){
                player.pause()
                soundPool.play(soundTrue,0.5f,0.5f, 0,0, 1.0f)
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("ALARM",timerText.text.toString())
                startActivity(intent)
            } else {
                soundPool.play(soundFalse,0.5f,0.5f, 0,0, 1.0f)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        soundPool = SoundPool(2, AudioManager.STREAM_ALARM, 0)
        soundTrue = soundPool.load(this, R.raw.se_alarm_stop, 1)
        soundFalse = soundPool.load(this, R.raw.se_false, 1)
    }
}

fun setText(): String{
    val text = when((Math.random() * 3).toInt()){
        0 -> "今が起きる時"
        1 -> "目覚めよ"
        else -> "おはよう"
    }
    return text
}