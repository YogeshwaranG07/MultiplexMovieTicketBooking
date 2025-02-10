package com.cts.multiplexmoviebooking.dtos;

import com.cts.multiplexmoviebooking.models.Booking;

public class BookingDTO extends Booking {
	private int showId;
	private int userId;
	public int getShowId() {
		return showId;
	}
	public void setShowId(int showId) {
		this.showId = showId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

}
