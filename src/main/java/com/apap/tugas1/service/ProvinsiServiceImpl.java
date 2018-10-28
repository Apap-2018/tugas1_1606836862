package com.apap.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.ProvinsiDB;

@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService {
	@Autowired
	private ProvinsiDB provinsiDB;

	@Override
	public ProvinsiModel getProvinsiDetailById(long id) {
		return provinsiDB.getOne(id);
	}

	@Override
	public List<ProvinsiModel> getProvinsiList() {
		return provinsiDB.findAll();
	}


	//@Override
	//public long countEntity() {
		//return pegawaiDB.count();
	//}

	//@Override
	//public PegawaiModel getPegawaiDetailById(Long id) {
		//return pegawaiDB.getOne(id);
	//}
	
}