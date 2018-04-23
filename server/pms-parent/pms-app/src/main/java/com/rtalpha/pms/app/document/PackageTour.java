package com.rtalpha.pms.app.document;

import java.util.List;

import org.pojomatic.annotations.AutoProperty;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.rtalpha.base.mongo.document.BaseDocument;

/**
 * @author Ricky Shi
 * @since Aug 9, 2017
 */
@AutoProperty
@Document(collection = "PackageTour", language = "english")
public class PackageTour extends Product {

	@Transient
	private static final long serialVersionUID = 1L;

	private String departure;
	private String destination;
	private String transportation;
	private Rating rating;
	private List<Price> rooms;
	private List<Price> insurances;
	private List<Itinerary> itineraries;

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getTransportation() {
		return transportation;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	public List<Price> getRooms() {
		return rooms;
	}

	public void setRooms(List<Price> rooms) {
		this.rooms = rooms;
	}

	public List<Price> getInsurances() {
		return insurances;
	}

	public void setInsurances(List<Price> insurances) {
		this.insurances = insurances;
	}

	public List<Itinerary> getItineraries() {
		return itineraries;
	}

	public void setItineraries(List<Itinerary> itineraries) {
		this.itineraries = itineraries;
	}

	@AutoProperty
	public static class Itinerary extends BaseDocument {
		private static final long serialVersionUID = 1L;
		private Integer day;
		private String summary;
		private String breakfast;
		private String lunch;
		private String supper;
		private String sight;
		private String notice;
		private String hotel;

		public Integer getDay() {
			return day;
		}

		public void setDay(Integer day) {
			this.day = day;
		}

		public String getSummary() {
			return summary;
		}

		public void setSummary(String summary) {
			this.summary = summary;
		}

		public String getBreakfast() {
			return breakfast;
		}

		public void setBreakfast(String breakfast) {
			this.breakfast = breakfast;
		}

		public String getLunch() {
			return lunch;
		}

		public void setLunch(String lunch) {
			this.lunch = lunch;
		}

		public String getSupper() {
			return supper;
		}

		public void setSupper(String supper) {
			this.supper = supper;
		}

		public String getSight() {
			return sight;
		}

		public void setSight(String sight) {
			this.sight = sight;
		}

		public String getNotice() {
			return notice;
		}

		public void setNotice(String notice) {
			this.notice = notice;
		}

		public String getHotel() {
			return hotel;
		}

		public void setHotel(String hotel) {
			this.hotel = hotel;
		}
	}

	@AutoProperty
	public static class Rating extends Product.Rating {
		private static final long serialVersionUID = 1L;
		private Float food;
		private Float sight;
		private Float guide;
		private Float hotel;
		private Float itinerary;

		public Float getFood() {
			return food;
		}

		public void setFood(Float food) {
			this.food = food;
		}

		public Float getSight() {
			return sight;
		}

		public void setSight(Float sight) {
			this.sight = sight;
		}

		public Float getGuide() {
			return guide;
		}

		public void setGuide(Float guide) {
			this.guide = guide;
		}

		public Float getHotel() {
			return hotel;
		}

		public void setHotel(Float hotel) {
			this.hotel = hotel;
		}

		public Float getItinerary() {
			return itinerary;
		}

		public void setItinerary(Float itinerary) {
			this.itinerary = itinerary;
		}
	}
}
