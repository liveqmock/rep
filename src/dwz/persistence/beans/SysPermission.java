﻿package dwz.persistence.beans;

/**
 * SysPermission generated by MyEclipse Persistence Tools
 */

public class SysPermission implements java.io.Serializable {

	// Fields

	private SysPermissionId id;

	// Constructors

	/** default constructor */
	public SysPermission() {
	}

	/** full constructor */
	public SysPermission(SysPermissionId id) {
		this.id = id;
	}

	// Property accessors

	public SysPermissionId getId() {
		return this.id;
	}

	public void setId(SysPermissionId id) {
		this.id = id;
	}

}