package com.spring.bookmanage.book.JGHservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.book.JGHmodel.JGHBookMapper;

@Service
public class JGHBookService {

	@Autowired private JGHBookMapper mapper;
}