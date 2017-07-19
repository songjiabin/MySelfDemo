package gocar.jeno.com.tablayout;

/**
 * author : 宋佳
 * time   : 2017/07/11
 * desc   :
 * version: 1.0.0
 */

public class TabLayouuBean {


    public TabLayouuBean(String tagId, String tagName) {
        this.tagName = tagName;
        this.tagId = tagId;
    }

    private String tagName;
    private String tagId;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
