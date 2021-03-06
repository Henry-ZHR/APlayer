package remix.myplayer.util

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import remix.myplayer.App
import remix.myplayer.BuildConfig

object PermissionUtil {
  private fun has(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(
        App.context,
        permission
    ) == PackageManager.PERMISSION_GRANTED
  }

  fun hasReadAndWriteExternalStorage(): Boolean {
    return has(Manifest.permission.READ_EXTERNAL_STORAGE) &&
           has(Manifest.permission.WRITE_EXTERNAL_STORAGE)
  }

  @RequiresApi(Build.VERSION_CODES.R)
  fun hasManageExternalStorage(): Boolean {
    return Environment.isExternalStorageManager()
  }

  @RequiresApi(Build.VERSION_CODES.R)
  fun requestManageExternalStorage(context: Context) {
    context.startActivity(
        Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION).setData(
            Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
        )
    )
    // TODO: show toast when "a matching Activity not exists"
  }
}