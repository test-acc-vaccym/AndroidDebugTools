package com.gracker.meidascannertest;

import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

/**
 * Created by gaojianwu on 16-11-22.
 * <p>
 * Broadcast Action:  The media scanner has started scanning a directory.
 * The path to the directory being scanned is contained in the Intent.mData field.
 * <p>
 * public static final String ACTION_MEDIA_SCANNER_STARTED = "android.intent.action.MEDIA_SCANNER_STARTED";
 * <p>
 * /**
 * Broadcast Action:  The media scanner has finished scanning a directory.
 * The path to the scanned directory is contained in the Intent.mData field.
 * public static final String ACTION_MEDIA_SCANNER_FINISHED = "android.intent.action.MEDIA_SCANNER_FINISHED";
 * <p>
 * /**
 * Broadcast Action:  Request the media scanner to scan a file and add it to the media database.
 * The path to the file is contained in the Intent.mData field.
 * public static final String ACTION_MEDIA_SCANNER_SCAN_FILE = "android.intent.action.MEDIA_SCANNER_SCAN_FILE";
 */

/**
 * Broadcast Action:  The media scanner has started scanning a directory.
 * The path to the directory being scanned is contained in the Intent.mData field.
 * <p>
 * public static final String ACTION_MEDIA_SCANNER_STARTED = "android.intent.action.MEDIA_SCANNER_STARTED";
 * <p>
 * /**
 * Broadcast Action:  The media scanner has finished scanning a directory.
 * The path to the scanned directory is contained in the Intent.mData field.
 * public static final String ACTION_MEDIA_SCANNER_FINISHED = "android.intent.action.MEDIA_SCANNER_FINISHED";
 * <p>
 * /**
 * Broadcast Action:  Request the media scanner to scan a file and add it to the media database.
 * The path to the file is contained in the Intent.mData field.
 * public static final String ACTION_MEDIA_SCANNER_SCAN_FILE = "android.intent.action.MEDIA_SCANNER_SCAN_FILE";
 */

/**
 * MeidaScanner接收下面的通知来扫描
 *
 * 1. Intent.ACTION_BOOT_COMPLETED --开机广播,  扫描的文件为 MediaProvider.INTERNAL_VOLUME ("internal")
 * 2. UsbManager.ACTION_USB_STATE  --USB状态链接 扫描的文件为  MediaProvider.EXTERNAL_VOLUME ("external")
 * 3. Intent.ACTION_LOCALE_CHANGED --地区修改 扫描的文件为  MediaProvider.EXTERNAL_VOLUME ("internal")
 * 4. Intent.ACTION_MEDIA_MOUNTED  --Meida修改, 扫描的文件为传入的文件路径
 * 5. Intent.ACTION_MEDIA_SCANNER_SCAN_FILE  -- 文件修改,扫描的是传入的文件路径
 * 6. Intent.ACTION_MEDIA_UNMOUNTED
 *
 */

public class MeidaScannerUtils {

    public static final String ACTION_MEDIA_SCANNER_SCAN_DIR = "android.intent.action.MEDIA_SCANNER_SCAN_DIR";


    public static void scanFile(Context ctx, String filePath) {
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(Uri.fromFile(new File(filePath)));
        ctx.sendBroadcast(scanIntent);
    }

    public static void scanDirAsync(Context ctx, String dir) {
        Intent scanIntent = new Intent(ACTION_MEDIA_SCANNER_SCAN_DIR);
        scanIntent.setData(Uri.fromFile(new File(dir)));
        ctx.sendBroadcast(scanIntent);
    }

    public static void sdScan(Context ctx) {
        ctx.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"
                + Environment.getExternalStorageDirectory())));
    }

    public static void mediaMountTest(Context ctx, String dileDir) {
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_MOUNTED);
        scanIntent.setData(Uri.fromFile(new File(dileDir)));
        ctx.sendBroadcast(scanIntent);
    }

    public static void mediaConnectionTest(Context ctx) {
        String[] path = {"/Data/data/", "etc"};
        String[] minitype = {"/Data/data/", "etc"};
        MediaScannerConnection.scanFile(ctx, path, minitype, new MediaScannerConnection.OnScanCompletedListener() {
            @Override
            public void onScanCompleted(String s, Uri uri) {

            }
        });
    }
}
