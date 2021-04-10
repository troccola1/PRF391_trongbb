package com.example.prm391x_02_vn_project2_trongbbfx02929funixeduvn;

public class Item {
    private int mImgId;
    private String mTitle;
    private String mSubtitle;

    public Item(int mImgId, String mTitle, String mSubtitle) {
        super();
        this.setmImgId(mImgId);
        this.setmTitle(mTitle);
        this.setmSubtitle(mSubtitle);
    }

    public int getmImgId() {
        return mImgId;
    }

    public void setmImgId(int mImgId) {
        this.mImgId = mImgId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmSubtitle() {
        return mSubtitle;
    }

    public void setmSubtitle(String mSubtitle) {
        this.mSubtitle = mSubtitle;
    }
}
