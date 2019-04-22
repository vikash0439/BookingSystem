package com.booking.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import com.booking.bean.Booking;
import com.booking.repository.BookingRepository;

@Service
public class BookingService {
	
private final BookingRepository bookingRepository;
	
	public BookingService(BookingRepository bookingRepository) {   // constructor injection with IOC
		this.bookingRepository = bookingRepository;
	}	
	
	public Booking save(Booking booking) {
		return bookingRepository.save(booking);		
	}
	
	public List<Booking> getBooking(){
		List<Booking> c = new ArrayList<Booking>();
		bookingRepository.findAllByOrder().forEach(c :: add);
		return c;
	}

	public Booking find(Long serviceid) {
		return bookingRepository.findAllByBookingid(serviceid);
	}
	
	public List<Long> getBookingID() {
		return bookingRepository.findBookingID();
	}
	
	public List<String> getServiceDate() {
		return bookingRepository.findBookingDates();
	}
	
	public void updateBookingById(Booking booking) {

//		Long bookingid = booking.getBookingid();	
//		System.out.println("Booking deleted from BookingService class: " +bookingid);	
//		bookingRepository.deleteByServiceid(serviceid);
		 Date d = new Date();
		 
		booking.setBooked("Cancelled");
		booking.setDateofcancel(d.toString());
		bookingRepository.save(booking);
	}
	
	public String findDistinctSlot(String date) {			
		System.out.println("Slot from Booking table db : "+bookingRepository.findByBookingdates(date));
		return bookingRepository.findByBookingdates(date);
	}

	public List<String> getAllService(String item) {
		// TODO Auto-generated method stub
		return bookingRepository.findAllServices(item);
	}

	public String getBasePrice(long contractid) {
		// TODO Auto-generated method stub
		return bookingRepository.getTotalBasePrice(contractid);
	}

}
