package com.example.library.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
	
	ANONYMOUS_READ("anonymous:read"),
	ANONYMOUS_CREATE("anonymous:create"),
	ANONYMOUS_UPDATE("anonymous:update"),
	ANONYMOUS_DELETE("anonymous:delete"),
	
	USER_READ("user:read"),
	USER_CREATE("user:create"),
	USER_UPDATE("user:update"),
	USER_DELETE("user:delete"),
	
	ADMIN_READ("admin:read"),
	ADMIN_CREATE("admin:create"),
	ADMIN_UPDATE("admin:update"),
	ADMIN_DELETE("admin:delete"),
	
	;
	
	@Getter
	private final String permission;

}
