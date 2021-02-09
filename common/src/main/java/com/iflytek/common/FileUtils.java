package com.iflytek.common;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import androidx.annotation.NonNull;

/**
 * assets 工具类
 *
 * @author aljiang
 */
public final class FileUtils {

    /**
     * 获取 assets 目录下的文件
     *
     * @param path 文件在 assets 文件夹中的路径
     * @return 文件内容
     */
    public static String getStringFromAssets(@NonNull Context context, @NonNull String path) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bf = null;
        try {
            AssetManager assetManager = context.getAssets();
            bf = new BufferedReader(new InputStreamReader(assetManager.open(path)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            safeClose(bf);
        }
        return stringBuilder.toString();
    }

    /**
     * 获取 assets 目录下的图片
     *
     * @param path 图片在 assets 文件夹中的路径
     * @return
     */
    public static Bitmap getBitmapFromAssets(@NonNull Context context, @NonNull String path) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();
        InputStream is = null;
        try {
            is = am.open(path);
            image = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            safeClose(is);
        }
        return image;
    }

    public static boolean copyFileFromAssets(Context context, String fileName, String path) {
        boolean copyIsFinish = false;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = context.getAssets().open(fileName);
            File file = new File(path);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            copyIsFinish = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            safeClose(fos);
            safeClose(is);
        }
        return copyIsFinish;
    }

    public static void copyRaw2File(Context ctx, String destFilePath, int rawID)
            throws IOException {
        String[] parts = splitPathIntoFileDir(destFilePath);
        File dir = new File(parts[0]);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream os = null;
        os = new FileOutputStream(destFilePath);
        InputStream is = ctx.getResources().openRawResource(rawID);
        byte[] buffer = new byte[10240];
        int count = 0;
        try {

            while ((count = is.read(buffer)) > 0) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            safeClose(is);
            safeClose(os);
        }
    }

    public static String[] splitPathIntoFileDir(String path) {

        String[] ret = new String[2];

        if (path == null) {
            return null;
        }

        if (path.endsWith("/")) {
            ret[0] = path;
            ret[1] = "";
        } else {
            ret[1] = path.substring(path.lastIndexOf("/") + 1);
            ret[0] = path.substring(0, path.lastIndexOf("/"));
        }
        return ret;
    }

    public static void safeClose(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}