package com.example.testproject.api;

public class NewsItem {

    private String mTitle;
    private String mDesc;
    private String mContent;
    private String mUrl;
    private String mImage;
    private String mPubl;

   /* private String mSourceName;
    private String mSourceUrl;*/

    /* public NewsItem(String title, String desc, String content, String url, String image,
                     String publ)
                     OLD CONSTRUCTOR
                     */
    public NewsItem(String title, String desc, String image, String content, String publ) {
        mTitle = title;
        mDesc = desc;
        mImage = image;
        mContent = content;
        mPubl = publ;

        /*   mUrl = url;*/
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDesc() {return mDesc;}

    public String getmContent() {return mContent;}

    public String getmUrl() {return mUrl;}

    public String getmImage() {return mImage;}

    public String getmPubl() {return mPubl;}

}
