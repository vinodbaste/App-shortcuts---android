package com.kl.appshortcut

import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttons()
    }

    private fun buttons() {

        findViewById<Button>(R.id.button2).setOnClickListener {
            openURL()
        }

        findViewById<Button>(R.id.button4).setOnClickListener {
            removeShortcuts()
        }
    }

    @TargetApi(25)
    private fun openURL() {
        val shortcutManager = getSystemService(ShortcutManager::class.java)

        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.action = Intent.ACTION_VIEW

        val shortcut1 = ShortcutInfo.Builder(applicationContext, "mask_up")
            .setShortLabel("MaskUp Dynamic")
            .setRank(0)
            .setLongLabel("Open Maskup Dynamic")
            .setIcon(Icon.createWithResource(applicationContext, R.drawable.ic_masks))
            .setIntent(intent)
            .build()

        val shortcut2 = ShortcutInfo.Builder(applicationContext, "web_link")
            .setShortLabel("google Dynamic")
            .setRank(1)
            .setLongLabel("Open google Dynamic")
            .setIcon(Icon.createWithResource(applicationContext, R.drawable.ic_web))
            .setIntent(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/")
                )
            )
            .build()

        shortcutManager?.dynamicShortcuts = listOf(shortcut1, shortcut2)
    }

    @TargetApi(25)
    private fun removeShortcuts() {
        val manager = getSystemService(ShortcutManager::class.java)
        manager.removeAllDynamicShortcuts()
    }
}
