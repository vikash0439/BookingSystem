package com.booking.service;

import java.util.ArrayList;
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
		return bookingRepository.findAllByServiceid(serviceid);
	}
	
	public List<Long> getBookingID() {
		return bookingRepository.findServiceID();
	}
	
	public List<String> getServiceDate() {
		return bookingRepository.findServiceDate();
	}
	
	public void delete(Booking booking) {
		
//		bookingRepository.delete(booking);
		Long serviceid = booking.getServiceid();	
		System.out.println("Booking deleted from BookingService class" +serviceid);
		bookingRepository.deleteByServiceid(serviceid);
	
	}
	
	public String findDistinctSlot(String date) {			
		System.out.println("Slot from Booking service "+bookingRepository.findByServicedate(date));
		return bookingRepository.findByServicedate(date);
	}

}
