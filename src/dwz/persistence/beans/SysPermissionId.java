﻿package dwz.persistence.beans;

/**
 * SysPermissionId generated by MyEclipse Persistence Tools
 */

public class SysPermissionId implements java.io.Serializable {

	// Fields

	private SysRole sysRole;
	private SysResource sysResource;

	// Constructors

	/** default constructor */
	public SysPermissionId() {
	}

	/** full constructor */
	public SysPermissionId(SysRole sysRole, SysResource sysResource) {
		this.sysRole = sysRole;
		this.sysResource = sysResource;
	}

	// Property accessors

	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public SysResource getSysResource() {
		return this.sysResource;
	}

	public void setSysResource(SysResource sysResource) {
		this.sysResource = sysResource;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysPermissionId))
			return false;
		SysPermissionId castOther = (SysPermissionId) other;

		return ((this.getSysRole() == castOther.getSysRole()) || (this
				.getSysRole() != null
				&& castOther.getSysRole() != null && this.getSysRole().equals(
				castOther.getSysRole())))
				&& ((this.getSysResource() == castOther.getSysResource()) || (this
						.getSysResource() != null
						&& castOther.getSysResource() != null && this
						.getSysResource().equals(castOther.getSysResource())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSysRole() == null ? 0 : this.getSysRole().hashCode());
		result = 37
				* result
				+ (getSysResource() == null ? 0 : this.getSysResource()
						.hashCode());
		return result;
	}

}