package shen.houky.chaxun

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.progressDialog
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import shen.houky.litepal.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        numberProgressBar.progress = 0
        var b = 0
        var handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                if (msg.what == 0x123) {
                    numberProgressBar.progress = b    //更新进度
                } else {
                    toast("耗时操作已完成")
                    startActivity<MainActivity>()
                    finish()
                }
            }
        }
        Thread {
            run {
                while (true) {
                    b += 1
                    Thread.sleep(20)
                    if (b < 100)
                        handler.sendEmptyMessage(0x123)
                    else {
                        handler.sendEmptyMessage(0x111)
                        break
                    }
                }
            }
        }.start()//这个start一定要有

    }
}
