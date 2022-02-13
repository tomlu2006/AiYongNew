package com.yunbao.common.upload;

import java.io.File;

/**
 * Created by cxf on 2019/4/16.
 */

public class UploadBean {

    public static final int IMG = 0;
    public static final int VIDEO = 1;
    public static final int VOICE = 2;
    private File mOriginFile;//要被上传的源文件
    private File mCompressFile;//压缩后的图片文件
    private String mRemoteFileName;//上传成功后在云存储上的文件名字
    private boolean mSuccess;//是否上传成功了
    private int mType;
    private Object mTag;

    public UploadBean() {
    }

    public UploadBean(File originFile, int type) {
        mOriginFile = originFile;
        mType = type;
    }

    public File getOriginFile() {
        return mOriginFile;
    }

    public void setOriginFile(File originFile) {
        mOriginFile = originFile;
    }

    public String getRemoteFileName() {
        return mRemoteFileName;
    }

    public void setRemoteFileName(String remoteFileName) {
        mRemoteFileName = remoteFileName;
    }

    public File getCompressFile() {
        return mCompressFile;
    }

    public void setCompressFile(File compressFile) {
        mCompressFile = compressFile;
    }

    public boolean isSuccess() {
        return mSuccess;
    }

    public void setSuccess(boolean success) {
        mSuccess = success;
    }


    public void setEmpty() {
        mOriginFile = null;
        mRemoteFileName = null;
    }

    public boolean isEmpty() {
        return mOriginFile == null && mRemoteFileName == null;
    }

    public int getType() {
        return mType;
    }

    public Object getTag() {
        return mTag;
    }

    public void setTag(Object tag) {
        mTag = tag;
    }
}
