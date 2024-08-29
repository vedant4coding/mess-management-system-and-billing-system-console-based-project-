package org.mess.service;

import org.mess.model.*;
import org.mess.repository.*;

public class DatesService {
	DatesRepository repository = new DatesRepository();

	public boolean insertDatesService(DatesModel model) {

		return repository.insertDatesRepo(model);

	}
	public boolean UpdateUsedAmnt(DatesModel obj) {
		return repository.UpdateUsedAmnt(obj);
	}
	public boolean CheckMessMember(DatesModel obj) {
		return repository.CheckMessMember(obj);
	}
}
