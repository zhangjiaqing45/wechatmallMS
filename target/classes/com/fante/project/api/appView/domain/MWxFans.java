package com.fante.project.api.appView.domain;

public class MWxFans {

    private int currentPage;

    private int totalPage;

    private int total;

    private int pageSize;

    private int currentNum;

    private String id;

    private String subscribe;

    private String openid;

    private String nickname;

    private String phonenumber;

    private String sex;

    private String city;

    private String country;

    private String province;

    private String language;

    private String headimgurl;

    private String subscribetime;

    private String unionid;

    private String remark;

    private String groupid;

    private String tagidlist;

    private String subscribescene;

    private String qrscene;

    private String qrscenestr;
    
    private String black;
    
    private String latitude;
    
    private String longitude;

    private String subscribenum;

    private String unsubscribenum;

    private String source;

    private String subscribetimeStr;

    private String createtime;
    
    private String createtimeend;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe == null ? null : subscribe.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public String getSubscribetime() {
        return subscribetime;
    }

    public void setSubscribetime(String subscribetime) {
        this.subscribetime = subscribetime == null ? null : subscribetime.trim();
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid == null ? null : groupid.trim();
    }

    public String getTagidlist() {
        return tagidlist;
    }

    public void setTagidlist(String tagidlist) {
        this.tagidlist = tagidlist == null ? null : tagidlist.trim();
    }

    public String getSubscribescene() {
        return subscribescene;
    }

    public void setSubscribescene(String subscribescene) {
        this.subscribescene = subscribescene == null ? null : subscribescene.trim();
    }

    public String getQrscene() {
        return qrscene;
    }

    public void setQrscene(String qrscene) {
        this.qrscene = qrscene == null ? null : qrscene.trim();
    }

    public String getQrscenestr() {
        return qrscenestr;
    }

    public void setQrscenestr(String qrscenestr) {
        this.qrscenestr = qrscenestr == null ? null : qrscenestr.trim();
    }

	public String getBlack() {
		return black;
	}

	public void setBlack(String black) {
		this.black = black;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

    public String getSubscribenum() {
        return subscribenum;
    }

    public void setSubscribenum(String subscribenum) {
        this.subscribenum = subscribenum;
    }

    public String getUnsubscribenum() {
        return unsubscribenum;
    }

    public void setUnsubscribenum(String unsubscribenum) {
        this.unsubscribenum = unsubscribenum;
    }

    public String getPhonenumber()
    {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber)
    {
        this.phonenumber = phonenumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSubscribetimeStr() {
        return subscribetimeStr;
    }

    public void setSubscribetimeStr(String subscribetimeStr) {
        this.subscribetimeStr = subscribetimeStr;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

	public String getCreatetimeend() {
		return createtimeend;
	}

	public void setCreatetimeend(String createtimeend) {
		this.createtimeend = createtimeend;
	}

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }
}