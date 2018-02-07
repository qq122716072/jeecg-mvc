package com.emotte.eserver.core.db.local.model;

public class Account {
	private Long id;
	private String name;
	private String bank;
	private String bankNum;
	private Integer valid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankNum() {
		return bankNum;
	}
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", bank=" + bank
				+ ", bankNum=" + bankNum + ", valid=" + valid + "]";
	}
}
