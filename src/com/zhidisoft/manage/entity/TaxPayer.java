package com.zhidisoft.manage.entity;

public class TaxPayer {
	
	private Integer id;
	private String payerCode;
	private String payerName;		//付款人姓名
	private String bizAddress;		//营业地址
	private Integer taxOrganId;		//税务机关编号
	private Integer industryId;		//行业编号
	private String bizScope;		//业务范围
	private String invoiceType;		//发票类型
	private String legalPerson;		//法人
	private String legalIdCard;		//合法身份证
	private String legalMobile;		//合法电话
	private String legalIdCardImageURL;	//合法身份证图片网址
	private String finaceName;		//财务名称
	private String finaceIdCard;	//金融身份证
	private String finaceMobile;
	private String finaceIdCardImageURL;
	private String taxerName;
	private String taxerIdCard;		//税务员身份证
	private String taxerMobile;
	private String taxerIdCardImageURL;
	private String bizAddressPhone;		//商务地址 电话
	private String recordDate;		//记录日期
	private Integer userId;
	private Integer removeState = 0;	//移除状态
	public TaxPayer() {
		super();
	}
	
	public TaxPayer(Integer id, String payerCode, String payerName,
			String bizAddress, Integer taxOrganId, Integer industryId,
			String bizScope, String invoiceType, String legalPerson,
			String legalIdCard, String legalMobile, String legalIdCardImageURL,
			String finaceName, String finaceIdCard, String finaceMobile,
			String finaceIdCardImageURL, String taxerName, String taxerIdCard,
			String taxerMobile, String taxerIdCardImageURL,
			String bizAddressPhone, String recordDate, Integer userId,
			Integer removeState) {
		super();
		this.id = id;
		this.payerCode = payerCode;		//支付代码
		this.payerName = payerName;		//付款人姓名
		this.bizAddress = bizAddress;	//营业地址
		this.taxOrganId = taxOrganId;	//税务机关编号
		this.industryId = industryId;	//行业编号
		this.bizScope = bizScope;		//业务范围
		this.invoiceType = invoiceType; //发票类型
		this.legalPerson = legalPerson; //法人
		this.legalIdCard = legalIdCard; //合法身份证
		this.legalMobile = legalMobile; //合法电话
		this.legalIdCardImageURL = legalIdCardImageURL;
		this.finaceName = finaceName;
		this.finaceIdCard = finaceIdCard;
		this.finaceMobile = finaceMobile;
		this.finaceIdCardImageURL = finaceIdCardImageURL;
		this.taxerName = taxerName;
		this.taxerIdCard = taxerIdCard;
		this.taxerMobile = taxerMobile;
		this.taxerIdCardImageURL = taxerIdCardImageURL;
		this.bizAddressPhone = bizAddressPhone;
		this.recordDate = recordDate;
		this.userId = userId;
		this.removeState = removeState;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPayerCode() {
		return payerCode;
	}
	public void setPayerCode(String payerCode) {
		this.payerCode = payerCode;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getBizAddress() {
		return bizAddress;
	}
	public void setBizAddress(String bizAddress) {
		this.bizAddress = bizAddress;
	}
	public Integer getTaxOrganId() {
		return taxOrganId;
	}
	public void setTaxOrganId(Integer taxOrganId) {
		this.taxOrganId = taxOrganId;
	}
	public Integer getIndustryId() {
		return industryId;
	}
	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}
	public String getBizScope() {
		return bizScope;
	}
	public void setBizScope(String bizScope) {
		this.bizScope = bizScope;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getLegalIdCard() {
		return legalIdCard;
	}
	public void setLegalIdCard(String legalIdCard) {
		this.legalIdCard = legalIdCard;
	}
	public String getLegalMobile() {
		return legalMobile;
	}
	public void setLegalMobile(String legalMobile) {
		this.legalMobile = legalMobile;
	}
	public String getLegalIdCardImageURL() {
		return legalIdCardImageURL;
	}
	public void setLegalIdCardImageURL(String legalIdCardImageURL) {
		this.legalIdCardImageURL = legalIdCardImageURL;
	}
	public String getFinaceName() {
		return finaceName;
	}
	public void setFinaceName(String finaceName) {
		this.finaceName = finaceName;
	}
	public String getFinaceIdCard() {
		return finaceIdCard;
	}
	public void setFinaceIdCard(String finaceIdCard) {
		this.finaceIdCard = finaceIdCard;
	}
	public String getFinaceMobile() {
		return finaceMobile;
	}
	public void setFinaceMobile(String finaceMobile) {
		this.finaceMobile = finaceMobile;
	}
	public String getFinaceIdCardImageURL() {
		return finaceIdCardImageURL;
	}
	public void setFinaceIdCardImageURL(String finaceIdCardImageURL) {
		this.finaceIdCardImageURL = finaceIdCardImageURL;
	}
	public String getTaxerName() {
		return taxerName;
	}
	public void setTaxerName(String taxerName) {
		this.taxerName = taxerName;
	}
	public String getTaxerIdCard() {
		return taxerIdCard;
	}
	public void setTaxerIdCard(String taxerIdCard) {
		this.taxerIdCard = taxerIdCard;
	}
	public String getTaxerMobile() {
		return taxerMobile;
	}
	public void setTaxerMobile(String taxerMobile) {
		this.taxerMobile = taxerMobile;
	}
	public String getTaxerIdCardImageURL() {
		return taxerIdCardImageURL;
	}
	public void setTaxerIdCardImageURL(String taxerIdCardImageURL) {
		this.taxerIdCardImageURL = taxerIdCardImageURL;
	}
	public String getBizAddressPhone() {
		return bizAddressPhone;
	}
	public void setBizAddressPhone(String bizAddressPhone) {
		this.bizAddressPhone = bizAddressPhone;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getRemoveState() {
		return removeState;
	}

	public void setRemoveState(Integer removeState) {
		this.removeState = removeState;
	}

	@Override
	public String toString() {
		return "TaxPayer [id=" + id + ", payerCode=" + payerCode
				+ ", payerName=" + payerName + ", bizAddress=" + bizAddress
				+ ", taxOrganId=" + taxOrganId + ", industryId=" + industryId
				+ ", bizaScope=" + bizScope + ", invoiceType=" + invoiceType
				+ ", legalPerson=" + legalPerson + ", legalIdCard="
				+ legalIdCard + ", legalMobile=" + legalMobile
				+ ", legalIdCardImageURL=" + legalIdCardImageURL
				+ ", finaceName=" + finaceName + ", finaceIdCard="
				+ finaceIdCard + ", finaceMobile=" + finaceMobile
				+ ", finaceIdCardImageURL=" + finaceIdCardImageURL
				+ ", taxerName=" + taxerName + ", taxerIdCard=" + taxerIdCard
				+ ", taxerMobile=" + taxerMobile + ", taxerIdCardImageURL="
				+ taxerIdCardImageURL + ", bizAddressPhone=" + bizAddressPhone
				+ ", recordDate=" + recordDate + ", userId=" + userId + "]";
	}
	
}
