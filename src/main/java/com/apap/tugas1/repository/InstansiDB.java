package com.apap.tugas1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apap.tugas1.model.InstansiModel;

public interface InstansiDB extends JpaRepository<InstansiModel, Long> {
	InstansiModel findById(long id);
}
