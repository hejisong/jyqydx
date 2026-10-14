package com.ywjc.glsyjs.sys.service.impl;



import java.util.List;

import javax.annotation.Resource;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.sys.entity.Encryptedinfo;
import com.ywjc.glsyjs.sys.repositories.EncryptedinfoRepository;
import com.ywjc.glsyjs.sys.service.EncryptedinfoService;


@Service("encryptedinfoServiceImpl")
public class EncryptedinfoServiceImpl extends BaseServiceImpl<Encryptedinfo, String>
implements EncryptedinfoService {

		@Resource(name = "encryptedinfoRepository")
		private EncryptedinfoRepository repository;
		
		@Resource(name = "encryptedinfoRepository")
		public void setReposity(
			PagingAndSortingRepository<Encryptedinfo, String> repository) {
		super.setReposity(repository);
		}


		public List<Object []> findTable() {
			
			
			
			return repository.findTable();
		}


		public List<String> findbytablename(String tablename) {
			return repository.findbytablename(tablename);
		}


		@Override
		public String findbytablename2(String tablename) {
			return repository.findbytablename2( tablename);
		}


		@Override
		public List<String> findbybeanName1(String beanName) {
			return repository.findbybeanName1( beanName);
		}


		@Override
		public String findbybeanName2(String beanName) {
			return repository.findbybeanName2( beanName);
		}


		@Override
		public String findbybeanName3(String beanName) {
			return repository.findbybeanName3( beanName);
		}


		@Override
		public List<String> findbytablename3(String tablename) {
			return repository.findbytablename3( tablename);
		}


		@Override
		public List<String> findTableName() {
			return repository.findTableName();
		}


		@Override
		public List<String> findbytablename4(String tablename) {
			return repository.findbytablename4( tablename);
		}	

}
