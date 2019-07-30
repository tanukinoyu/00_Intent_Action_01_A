
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
import android.media.AudioAttributes
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

    //MediaPlayerとSoundPoolのインスタンスを遅延初期化するためにlateinitを用いて変数を定義する。

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

        //5秒後に処理を行うためにHandlerの遅延処理を使用する。

        button.setOnClickListener {
            if(editText.text.toString() == timerText.text.toString()){

                //editTextで入力された文字列はCharSequense型なのでString型にする。

                player.pause()
                soundPool.play(soundTrue,0.5f,0.5f, 0,0, 1.0f)
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("ALARM",timerText.text.toString())
                startActivity(intent)
            } else {
                soundPool.play(soundFalse,0.5f,0.5f, 0,0, 1.0f)
            }
        }

        //STOPボタンのonClickメソッドで入力テキストの判定を行い、正誤に基づいて音を鳴らす。
        //正解の場合はインテントで入力テキストを格納し、遷移先のアクティビティで受け取る準備をする。
    }

    override fun onResume() {
        super.onResume()
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ALARM)
            .build()
        soundPool = SoundPool.Builder()
            .setMaxStreams(2)
            .setAudioAttributes(audioAttributes)
            .build()
        soundTrue = soundPool.load(this, R.raw.se_alarm_stop, 1)
        soundFalse = soundPool.load(this, R.raw.se_false, 1)
    }

    //AudioAttributesとSoundPoolの設定を行い、予めサウンドリソースを読み込んで変数にIDを割り当てる。
}

fun setText(): String{
    val text = when((Math.random() * 3).toInt()){
        0 -> "今が起きる時"
        1 -> "目覚めよ"
        else -> "おはよう"
    }
    return text
}

//ランダムに文字列を表示させるための関数。