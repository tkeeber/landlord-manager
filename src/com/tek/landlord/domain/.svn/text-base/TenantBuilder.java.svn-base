package com.tek.landlord.domain;


public class TenantBuilder {

	private int id;
	private int unitId;
	private String name;
	private String emails;
	private String telephone;
	
	public TenantBuilder aTenant() {
		return new TenantBuilder();
	}
	
	private TenantBuilder() {}
	
	public TenantBuilder withId(int id) {
		this.id = id;
		return this;
	}
	
	public TenantBuilder withName(final String name) {
		this.name = name;
		return this;
	}
	
	public TenantBuilder withEmails(final String emails) {
		this.emails = emails;
		return this;
	}
	
	public TenantBuilder withTelephone(final String telephone) {
		this.telephone = telephone;
		return this;
	}
	
	public TenantBuilder withUnitId(final int unitId) {
		this.unitId = unitId;
		return this;
	}
	
   public Tenant build() {
	   return new Tenant(this.id, this.unitId, this.name, this.emails, this.telephone);
   }

}
