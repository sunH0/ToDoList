package com.mission.todolist.domain.Item.entity;

public enum ItemStatus {
	COMPLETE,
	ACTIVE;

/*	public static ItemStatus findItemStatus(String code){
		return Arrays
			.stream(values())
			.filter(grade -> grade
				.equals(code))
			.findFirst()
			.orElseThrow(() -> new NotFoundException(ErrorCode.ITEMSTATE_NOT_FOUND));
	}*/

}

