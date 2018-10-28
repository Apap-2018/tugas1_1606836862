package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.JabatanModel;

public interface JabatanService {
	List<JabatanModel> findAllJabatan();
	void addJabatan(JabatanModel jabatan);
	void deleteJabatan(JabatanModel jabatan);
	JabatanModel getJabatanDetailById(long id);
}
