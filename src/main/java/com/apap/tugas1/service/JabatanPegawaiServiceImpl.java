package com.apap.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.repository.JabatanPegawaiDB;

@Service
@Transactional
public class JabatanPegawaiServiceImpl implements JabatanPegawaiService {
	@Autowired
	private JabatanPegawaiDB jabatanPegawaiDB;

	@Override
	public List<JabatanPegawaiModel> getJabatanByPegawaiId(long id) {
		return null;
	}

	@Override
	public long sizeJabatanPegawai() {
		return jabatanPegawaiDB.count();
	}

	@Override
	public JabatanPegawaiModel checkWho(long id) {
		return jabatanPegawaiDB.getOne(id);
	}

	@Override
	public boolean isExistByJabatanId(long id) {
		boolean isExist = false;
		for (JabatanPegawaiModel jabatanPegawai:jabatanPegawaiDB.findAll()) {
			//if (jabatanPegawai.g)
		}
		return isExist;
	}

}
