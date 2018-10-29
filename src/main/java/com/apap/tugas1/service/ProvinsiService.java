package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.ProvinsiModel;

public interface ProvinsiService {
	ProvinsiModel getProvinsiDetailById(long id);
	List<ProvinsiModel> getProvinsiList();
}
