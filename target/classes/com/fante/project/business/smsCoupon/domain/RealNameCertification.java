package com.fante.project.business.smsCoupon.domain;


public class RealNameCertification{
    
	private String	ID ;//id
	private String	Openid ;//微信ID
	private String	Name ;//姓名
	private String	Sex ;//性别
	private String	Race ;//民族
	private String	Birth ;//生日
	private String	Address ;//地址
	private String	Idcard ;//身份证号
	private String	Issue ;//签发机关
	private String	StartTime ;//有效期始
	private String	EndTime ;//有效期止
	private String	Pic ;//头像
	private String	Remark ;//备注
	private String	CreateUserId ;//创建用户微信ID
	private String	CreateDate ;//创建时间
	private String	FrontIMG ;//正面图片base64
	private String	ReverseSideIMG ;//反面图片base64
	private String	ModifyUserId ;//修改用户微信ID
	private String	ModifyDate ;//修改时间
	private String	longitude ;//经度
	private String	latitude ;//纬度
	private String	FrontDirection ;//token 
	private String	FrontImageStatus ; //是否已通知 1有0否
	private String	FrontRiskType ;  //实名认证请求字符串
	private String	ReverseSideDirection ;//实名认证响应字符串
	private String	ReverseSideImageStatus ;//备用字段
	private String	ReverseSideRiskType ;//备用字段
	private String	face_result ;//人脸核身结果 1:验证通过（是同一个人），0：验证失败
	private String	image_best ;//人脸识别面部图片base64
	private String	image_ext ;//备用字段
	private String	verify_confidence ;//检测结果置信度 浮点数取值［0，100］，数字越大表示越可能是同一个人
	private String	referees ;//推荐人ID
	private String	tourists ;//区分biz_id
	private String	ShareOne ;//备用字段
	private String	realnamebinding ;//实名认证绑定 1.绑定（默认） 2.解绑
	private String	mallbinding ;//白马商城券的码的url
	private String	EmployeeNumber ;//员工号ID
	private String	CreditCard ;//是否信用卡，有、无
	private String	TheStar ;//星级
	private String	IdentifyFields1 ;//标识字段1
	private String	IdentifyFields2 ;//标识字段2
	private String	IdentifyFields3 ;//标识字段3
	private String	IdentifyFields4 ;//标识字段4
	private String	IdentifyFields5 ;//标识字段5
	private String	IdentifyFields6 ;//标识字段6
	private String	IdentifyFields7 ;//标识字段7
	private String	IdentifyFields8 ;//标识字段8
	private String	IdentifyFields9 ;//标识字段9
	private String	IdentifyFields10 ;//标识字段10
	
	private String nickname; //昵称
	private String headimgurl; //头像
	private String CreateDateAbort;//截止创建时间
	private String sex;//性别
	private String country;//国家
	private String province;//省份
	private String city;//城市
	private String subscribe;//是否关注
	private String unionid;//联合id
	private String remark;//备注
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getOpenid() {
		return Openid;
	}
	public void setOpenid(String openid) {
		Openid = openid;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getRace() {
		return Race;
	}
	public void setRace(String race) {
		Race = race;
	}
	public String getBirth() {
		return Birth;
	}
	public void setBirth(String birth) {
		Birth = birth;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}

	public String getIdcard() {
		return Idcard;
	}

	public void setIdcard(String idcard) {
		Idcard = idcard;
	}

	public String getIssue() {
		return Issue;
	}
	public void setIssue(String issue) {
		Issue = issue;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	public String getPic() {
		return Pic;
	}
	public void setPic(String pic) {
		Pic = pic;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getCreateUserId() {
		return CreateUserId;
	}
	public void setCreateUserId(String createUserId) {
		CreateUserId = createUserId;
	}
	public String getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}
	public String getFrontIMG() {
		return FrontIMG;
	}
	public void setFrontIMG(String frontIMG) {
		FrontIMG = frontIMG;
	}
	public String getReverseSideIMG() {
		return ReverseSideIMG;
	}
	public void setReverseSideIMG(String reverseSideIMG) {
		ReverseSideIMG = reverseSideIMG;
	}
	public String getModifyUserId() {
		return ModifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		ModifyUserId = modifyUserId;
	}
	public String getModifyDate() {
		return ModifyDate;
	}
	public void setModifyDate(String modifyDate) {
		ModifyDate = modifyDate;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getFrontDirection() {
		return FrontDirection;
	}
	public void setFrontDirection(String frontDirection) {
		FrontDirection = frontDirection;
	}
	public String getFrontImageStatus() {
		return FrontImageStatus;
	}
	public void setFrontImageStatus(String frontImageStatus) {
		FrontImageStatus = frontImageStatus;
	}
	public String getFrontRiskType() {
		return FrontRiskType;
	}
	public void setFrontRiskType(String frontRiskType) {
		FrontRiskType = frontRiskType;
	}
	public String getReverseSideDirection() {
		return ReverseSideDirection;
	}
	public void setReverseSideDirection(String reverseSideDirection) {
		ReverseSideDirection = reverseSideDirection;
	}
	public String getReverseSideImageStatus() {
		return ReverseSideImageStatus;
	}
	public void setReverseSideImageStatus(String reverseSideImageStatus) {
		ReverseSideImageStatus = reverseSideImageStatus;
	}
	public String getReverseSideRiskType() {
		return ReverseSideRiskType;
	}
	public void setReverseSideRiskType(String reverseSideRiskType) {
		ReverseSideRiskType = reverseSideRiskType;
	}
	public String getFace_result() {
		return face_result;
	}
	public void setFace_result(String face_result) {
		this.face_result = face_result;
	}
	public String getImage_best() {
		return image_best;
	}
	public void setImage_best(String image_best) {
		this.image_best = image_best;
	}
	public String getImage_ext() {
		return image_ext;
	}
	public void setImage_ext(String image_ext) {
		this.image_ext = image_ext;
	}
	public String getVerify_confidence() {
		return verify_confidence;
	}
	public void setVerify_confidence(String verify_confidence) {
		this.verify_confidence = verify_confidence;
	}
	public String getReferees() {
		return referees;
	}
	public void setReferees(String referees) {
		this.referees = referees;
	}
	public String getTourists() {
		return tourists;
	}
	public void setTourists(String tourists) {
		this.tourists = tourists;
	}
	public String getShareOne() {
		return ShareOne;
	}
	public void setShareOne(String shareOne) {
		ShareOne = shareOne;
	}
	public String getRealnamebinding() {
		return realnamebinding;
	}
	public void setRealnamebinding(String realnamebinding) {
		this.realnamebinding = realnamebinding;
	}
	public String getMallbinding() {
		return mallbinding;
	}
	public void setMallbinding(String mallbinding) {
		this.mallbinding = mallbinding;
	}
	public String getEmployeeNumber() {
		return EmployeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		EmployeeNumber = employeeNumber;
	}
	public String getCreditCard() {
		return CreditCard;
	}
	public void setCreditCard(String creditCard) {
		CreditCard = creditCard;
	}
	public String getTheStar() {
		return TheStar;
	}
	public void setTheStar(String theStar) {
		TheStar = theStar;
	}
	public String getIdentifyFields1() {
		return IdentifyFields1;
	}
	public void setIdentifyFields1(String identifyFields1) {
		IdentifyFields1 = identifyFields1;
	}
	public String getIdentifyFields2() {
		return IdentifyFields2;
	}
	public void setIdentifyFields2(String identifyFields2) {
		IdentifyFields2 = identifyFields2;
	}
	public String getIdentifyFields3() {
		return IdentifyFields3;
	}
	public void setIdentifyFields3(String identifyFields3) {
		IdentifyFields3 = identifyFields3;
	}
	public String getIdentifyFields4() {
		return IdentifyFields4;
	}
	public void setIdentifyFields4(String identifyFields4) {
		IdentifyFields4 = identifyFields4;
	}
	public String getIdentifyFields5() {
		return IdentifyFields5;
	}
	public void setIdentifyFields5(String identifyFields5) {
		IdentifyFields5 = identifyFields5;
	}
	public String getIdentifyFields6() {
		return IdentifyFields6;
	}
	public void setIdentifyFields6(String identifyFields6) {
		IdentifyFields6 = identifyFields6;
	}
	public String getIdentifyFields7() {
		return IdentifyFields7;
	}
	public void setIdentifyFields7(String identifyFields7) {
		IdentifyFields7 = identifyFields7;
	}
	public String getIdentifyFields8() {
		return IdentifyFields8;
	}
	public void setIdentifyFields8(String identifyFields8) {
		IdentifyFields8 = identifyFields8;
	}
	public String getIdentifyFields9() {
		return IdentifyFields9;
	}
	public void setIdentifyFields9(String identifyFields9) {
		IdentifyFields9 = identifyFields9;
	}
	public String getIdentifyFields10() {
		return IdentifyFields10;
	}
	public void setIdentifyFields10(String identifyFields10) {
		IdentifyFields10 = identifyFields10;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getCreateDateAbort() {
		return CreateDateAbort;
	}
	public void setCreateDateAbort(String createDateAbort) {
		CreateDateAbort = createDateAbort;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
}