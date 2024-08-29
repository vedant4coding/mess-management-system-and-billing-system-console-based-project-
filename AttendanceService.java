package org.mess.service;
import org.mess.model.*;
import org.mess.repository.*;
public class AttendanceService {
	AttendanceRepository modelAttRepo = new AttendanceRepository();
	
	public boolean InsertionAttendanceSer(AttendanceModel model) {
		return modelAttRepo.InsertionAttendanceRepo(model);
	}
}
