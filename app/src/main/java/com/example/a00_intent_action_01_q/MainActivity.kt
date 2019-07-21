
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

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
