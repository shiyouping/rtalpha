package com.rtalpha.pms.app.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.pojomatic.annotations.AutoProperty;

import com.rtalpha.base.web.model.FullModel;

/**
 * @author Ricky Shi
 * @since Aug 11, 2017
 */
@AutoProperty
public class PackageTourFullModel extends ProductFullModel {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Size(min = 1, max = 30)
	private String departure;
	@NotEmpty
	@Size(min = 1, max = 30)
	private String destination;
	@NotEmpty
	@Size(min = 1, max = 50)
	private String transportation;
	@Null
	private RatingFullModel rating;
	@Valid
	private List<PriceFullModel> rooms;
	@Valid
	private List<PriceFullModel> insurances;
	@Valid
	@NotEmpty
	@Size(min = 1, max = 20)
	private List<ItineraryFullModel> itineraries;

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

	public List<PriceFullModel> getRooms() {
		return rooms;
	}

	public void setRooms(List<PriceFullModel> rooms) {
		this.rooms = rooms;
	}

	public List<PriceFullModel> getInsurances() {
		return insurances;
	}

	public void setInsurances(List<PriceFullModel> insurances) {
		this.insurances = insurances;
	}

	public List<ItineraryFullModel> getItineraries() {
		return itineraries;
	}

	public void setItineraries(List<ItineraryFullModel> itineraries) {
		this.itineraries = itineraries;
	}

	public RatingFullModel getRating() {
		return rating;
	}

	public void setRating(RatingFullModel rating) {
		this.rating = rating;
	}

	@AutoProperty
	public static class ItineraryFullModel extends FullModel {
		private static final long serialVersionUID = 1L;
		@NotNull
		@Min(1)
		private Integer day;
		@NotEmpty
		@Size(min = 1, max = 500)
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
	public static class RatingFullModel extends ProductFullModel.RatingFullModel {
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
