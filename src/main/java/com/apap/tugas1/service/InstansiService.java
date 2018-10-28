package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;

public interface InstansiService {
	//List<String> getInstansiList();
	InstansiModel getInstansiById(long id);
	List<InstansiModel> findAllInstansi();
}
