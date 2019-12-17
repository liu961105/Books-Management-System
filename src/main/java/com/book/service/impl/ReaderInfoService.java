package com.book.service.impl;

import com.book.dao.ReaderInfoDao;
import com.book.domain.ReaderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReaderInfoService {

    private ReaderInfoDao readerInfoDao;
    @Autowired
    public void setReaderInfoDao(ReaderInfoDao readerInfoDao) {
        this.readerInfoDao = readerInfoDao;
    }
    public ArrayList<ReaderInfo> readerInfos(){
        return readerInfoDao.getAllReaderInfo();
    }

    public boolean deleteReaderInfo(String readerId){
        return readerInfoDao.deleteReaderInfo(readerId)>0;
    }

    public ReaderInfo getReaderInfo(String readerId){
        return readerInfoDao.findReaderInfoByReaderId(readerId);
    }
    public boolean editReaderInfo(ReaderInfo readerInfo){
        return readerInfoDao.editReaderInfo(readerInfo)>0;
    }
    public boolean addReaderInfo(ReaderInfo readerInfo){
        return  readerInfoDao.addReaderInfo(readerInfo)>0;
    }
    public ReaderInfo findByName(String name){
    	return readerInfoDao.findByName(name);
    }
	public boolean matchReader(String searchWord) {
		
		return readerInfoDao.matchReader(searchWord)>0;
	}
	public List<ReaderInfo> queryReader(String searchWord) {
		
		return readerInfoDao.queryReader(searchWord);
	}
}

