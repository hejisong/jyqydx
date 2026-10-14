package com.ywjc.glsyjs.sys.service.impl;

import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.repository.UserbfRepository;
import com.ywjc.glsyjs.core.service.QueryService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.core.util.MegerObjUtil;
import com.ywjc.glsyjs.core.util.RSAUtils;
import com.ywjc.glsyjs.sys.repositories.EncryptionRepository;
import com.ywjc.glsyjs.sys.service.DecryptionService;
import com.ywjc.glsyjs.sys.service.EncryptedinfoService;
import com.ywjc.glsyjs.sys.service.EncryptionService;
import com.ywjc.glsyjs.sys.entity.Decryption;
import com.ywjc.glsyjs.sys.entity.Encryption;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service("encryptionServiceImpl")
public class EncryptionServiceImpl extends BaseServiceImpl<Encryption, String> implements EncryptionService {
	private static Logger log = Logger.getLogger(EncryptionServiceImpl.class);
	@Resource(name = "encryptionRepository")
	private EncryptionRepository repository;

	@Resource(name = "decryptionServiceImpl")
	private DecryptionService decryptionService;

	@Resource(name = "encryptedinfoServiceImpl")
	private EncryptedinfoService encryptedinfoService;

	@Resource(name = "queryServiceImpl")
	protected QueryService queryService;

	@Resource(name = "userbfRepository")
	private UserbfRepository userbfrepository;

	@Resource(name = "encryptionRepository")
	public void setReposity(PagingAndSortingRepository<Encryption, String> repository) {
		super.setReposity(repository);
	}

	/**
	 * 随机生成密钥对 ,并且存储在数据库
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	@Override
	public String genKeyPairforcolumn(String tablename, String colmname, String recordid, String val, Date now,
			HttpSession session) {
		Encryption encryption = repository.findbyrecordid(tablename, colmname, recordid);
		Decryption decryptio = decryptionService.findbyrecordid(tablename, colmname, recordid);
		String publicKey = "";
		String privateKey = "";
		if (null != encryption && null != decryptio) {
			publicKey = encryption.getEncryptcode();
			privateKey = decryptio.getDecryptcode();
		} else {
			Map<String, String> keyMap = RSAUtils.createKeys(1024);
			publicKey = keyMap.get("publicKey");
			privateKey = keyMap.get("privateKey");
			saveKey(tablename, colmname, recordid, publicKey, privateKey, now, session);
		}
		String encodedData = "";
		try {
			encodedData = RSAUtils.publicEncrypt(val, RSAUtils.getPublicKey(publicKey));
		} catch (NoSuchAlgorithmException e) {
			System.err.println("NoSuchAlgorithmException Occured");
		} catch (InvalidKeySpecException e) {
			System.err.println("InvalidKeySpecException Occured");
		} // 传入明文和公钥加密,得到密文

		return encodedData;
	}

	private void saveKey(String tablename, String colmname, String recordid, String publicKeyString,
			String privateKeyString, Date now, HttpSession session) {
		// 人id
		String personid = "";
		// 人姓名
		String person = "";
		if (null != session) {
			if (null != session.getAttribute("userinfo")) {
				Principal principal = (Principal) session.getAttribute("userinfo");
				if (null != principal) {
					User user = principal.getUser();
					if (null != user) {
						personid = StringUtils.isBlank(user.getId()) ? "" : user.getId();
						person = StringUtils.isBlank(user.getUsername()) ? "" : user.getUsername();
					}
				}
			}

		}

		// 查询数据，该字段，该表，该id是否加密过，
		Encryption encryption = repository.findbyrecordid(tablename, colmname, recordid);
		Decryption decryptio = decryptionService.findbyrecordid(tablename, colmname, recordid);
		if (null != encryption && null != decryptio) {
			encryption.setUdate(now);
			encryption.setUpdateperson(person);
			encryption.setUpdatepersonid(personid);
			encryption.setEncryptcode(publicKeyString);
			super.save(encryption);
			decryptio.setUdate(now);
			decryptio.setUpdateperson(person);
			decryptio.setUpdatepersonid(personid);
			decryptio.setDecryptcode(privateKeyString);
			decryptionService.save(decryptio);
		} else {
			encryption = new Encryption();
			decryptio = new Decryption();
			encryption.setEncryptid(KeyUtils.getKey());
			encryption.setCdate(now);
			encryption.setCreatepersonid(personid);
			encryption.setCreateperson(person);
			// 公钥
			encryption.setEncryptcode(publicKeyString);
			encryption.setEntablename(tablename);
			encryption.setRecordid(recordid);
			encryption.setColmname(colmname);
			super.save(encryption);
			decryptio.setEncryptid(KeyUtils.getKey());
			decryptio.setCdate(new Date());
			decryptio.setCreatepersonid(personid);
			decryptio.setCreateperson(person);
			// 私钥
			decryptio.setDecryptcode(privateKeyString);
			decryptio.setColmname(colmname);
			decryptio.setEntablename(tablename);
			decryptio.setRecordid(recordid);
			decryptionService.save(decryptio);
		}
	}

	// 加密所有重要信息
	@Override
	@Transactional
	public void encrypted() {
		// 得到所有表名字
		List<String> sqllist = new ArrayList<>();
//			List<Object []> list=encryptedinfoService.findTable();
		List<Object[]> list = new ArrayList<Object[]>();
//			list.add(new Object[]{"grxx","person_id"});
		list.add(new Object[] { "grxxweb", "person_id" });
//			list.add(new Object[]{"qydjxx","corp_id"});
//			list.add(new Object[]{"qydjxxweb","corp_id"});
//			list.add(new Object[]{"cwzhweb","finaccount_id"});
//			list.add(new Object[]{"cwzh","finaccount_id"});
//			list.add(new Object[]{"credit_questions","id"});
//			list.add(new Object[]{"userinfo","id"});
//			list.add(new Object[]{"corpregister","id"});
//			list.add(new Object[]{"xmbdryxx","person_id"});
//			list.add(new Object[]{"xmbdryxxweb","person_id"});
//			list.add(new Object[]{"reportinfo","report_id"});
//			list.add(new Object[]{"registersmsinfo","smsinfo_id"});
		List<String> colmnamelist = null;
		String[] cloumArray1 = new String[10];// 加密后需要存放的数据
		String[] cloumArray2 = new String[10];// 字段是否加密1加密，0没有加密
		StringBuffer sql = new StringBuffer("select ");
		StringBuffer updatesql1 = new StringBuffer("update from ");
		StringBuffer updatesql2 = new StringBuffer("");
		StringBuffer updatesql4 = new StringBuffer("");
		int length;
		int num = 1;
		String zhujian;
		String ziduan;// 需要加密的字段
		String ziduan_encryption;// 需要加密的字段的加密情况，1加密，其他情况未加密
		Calendar c = Calendar.getInstance();
		Date now = Calendar.getInstance().getTime();
		boolean falg = false;
		String tablename = "";
		if (null != list && list.size() > 0) {
			for (Object[] tableAndp_key : list) {
				num = 1;
				if (StringUtils.isNotBlank(tableAndp_key[0].toString())
						&& StringUtils.isNotBlank(tableAndp_key[1].toString())) {
					tablename = StringUtils.isBlank(tableAndp_key[0].toString()) ? "" : tableAndp_key[0].toString();
					// 表中的主键
					zhujian = StringUtils.isBlank(tableAndp_key[1].toString()) ? "" : tableAndp_key[1].toString();
					sql.setLength(0);
					updatesql1.setLength(0);
					updatesql2.setLength(0);
					updatesql4.setLength(0);
					sql.append("select ");
					updatesql1.append("update ");
					// 表中需要加密的字段,isencrypted为1的时候，表示这个字段已经加密，将不再进行加密，定时任务时间太长，可以分批执行
					colmnamelist = encryptedinfoService.findbytablename3(tablename);
					// 加密字段
					ziduan = "";
					// 字段加密情况
					ziduan_encryption = "";
//						sql=sql.
					if (null != colmnamelist && colmnamelist.size() > 0) {
						for (int i = 0; i < colmnamelist.size(); i++) {
							ziduan = ziduan + colmnamelist.get(i) + ",";
							ziduan_encryption = ziduan_encryption + colmnamelist.get(i) + "_en,";
						}
						sql.append(ziduan);
						sql.append(ziduan_encryption);
//							if("cwzh".equals(tablename)) {
						sql.append(zhujian);
//							}
						if (StringUtils.isNotBlank(zhujian)) {
							sql.append(" from " + tablename);
							updatesql1.append(tablename + "  set ");
							// 查询出表中上有需要加密的数据，且字段为需要加密的字段
//								if("registersmsinfo".equals(tablename)) {
//									num=num+1;
//								}
							List<Object[]> listobj = queryService.queryObjectsBySql(sql.toString());
							if (null != listobj && listobj.size() > 0) {
								length = listobj.get(0).length;
								for (Object[] obj : listobj) {
									updatesql2.append(updatesql1);
									for (int j = 0; j < colmnamelist.size(); j++) {
										// 加密处理
										if (null != obj[j]) {
											if (null != obj[j + colmnamelist.size()]) {
												// 如果加密状态为1则不加密
												if (StringUtils.isNotBlank(obj[j + colmnamelist.size()].toString())) {
													if (!"1".equals(obj[j + colmnamelist.size()].toString())) {
														cloumArray1[j] = genKeyPairforcolumn(tablename,
																colmnamelist.get(j), obj[length - 1].toString(),
																obj[j].toString(), now, null);
														cloumArray2[j] = "1";
														falg = true;
													} else {
														cloumArray1[j] = obj[j].toString();
														cloumArray2[j] = "1";
														falg = false;
													}
												}
											} else {
												cloumArray1[j] = genKeyPairforcolumn(tablename, colmnamelist.get(j),
														obj[length - 1].toString(), obj[j].toString(), now, null);
												cloumArray2[j] = "1";
												falg = true;
											}

										} else {
											// 如果数据为空则不加密
											cloumArray1[j] = "";
											cloumArray2[j] = "0";
											falg = true;
										}
									}
									if (falg) {
										for (int k = 0; k < colmnamelist.size(); k++) {
											cloumArray2[k] = StringUtils.isBlank(cloumArray2[k]) ? "" : cloumArray2[k];
											if (colmnamelist.size() == 1) {
												updatesql2.append(colmnamelist.get(k) + " = '" + cloumArray1[k] + "',"
														+ colmnamelist.get(k) + "_en = '" + cloumArray2[k] + "'");
											} else {
												if (k < colmnamelist.size() - 1) {
													updatesql2.append(colmnamelist.get(k) + " = '" + cloumArray1[k]
															+ "'," + colmnamelist.get(k) + "_en = '" + cloumArray2[k]
															+ "',");
												} else {
													updatesql2.append(colmnamelist.get(k) + " = '" + cloumArray1[k]
															+ "'," + colmnamelist.get(k) + "_en = '" + cloumArray2[k]
															+ "'");
												}
											}

										}
										updatesql2.append(
												" where  " + zhujian + "= '" + obj[length - 1].toString() + "'");
//											System.out.println(tablename+"--"+num+"---"+obj[length-1].toString() );
//											queryService.executeSql(updatesql2.toString());
										sqllist.add(updatesql2.toString());
//											executeBatchSql

									}
									updatesql2.setLength(0);
									if (num >= 5000) {// 每5000执行一次
										queryService.executeBatchSql(sqllist);
										sqllist.clear();
										num = 0;
									} else {
										num++;
									}

								}
								queryService.executeBatchSql(sqllist);
								queryService.executeSql("update encryptedinfo set isencrypted='1' where  entablename='"
										+ tablename + "'");
							}
						}

					}
				}
			}

		}
	}

	@Override
	@Transactional
	public void encryptforbean(String tablename, String p_key_value) {
//			backup(tablename,p_key_value);//用来备份未加密数据
//			((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String p_key = encryptedinfoService.findbytablename2(tablename);
		// 表中需要加密的字段
		List<String> colmnamelist = encryptedinfoService.findbytablename(tablename);
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		StringBuffer sql = new StringBuffer("select ");
		StringBuffer updatesql = new StringBuffer("update ");
		// 加密字段
		String ziduan = "";
		// 字段加密情况
		String ziduan_encryption = "";
		String[] cloumArray1 = new String[10];// 加密后需要存放的数据
		String[] cloumArray2 = new String[10];// 字段是否加密1加密，0没有加密

		int length = 0;
		boolean falg = false;
		Calendar c = Calendar.getInstance();
		Date now = Calendar.getInstance().getTime();
		Matcher m;
		if (null != colmnamelist && colmnamelist.size() > 0) {
			for (int i = 0; i < colmnamelist.size(); i++) {
				ziduan = ziduan + colmnamelist.get(i) + ",";
				ziduan_encryption = ziduan_encryption + colmnamelist.get(i) + "_en,";
			}
			sql.append(ziduan + ziduan_encryption + p_key + " from " + tablename);
			sql.append(" where " + p_key + " = '" + p_key_value + "'");
			List<Object[]> listobj = queryService.queryObjectsBySql(sql.toString());
			if (null != listobj) {
				if (listobj.size() == 1) {
					Object[] obj = listobj.get(0);
					length = obj.length;
					for (int j = 0; j < colmnamelist.size(); j++) {
						// 加密处理
						if (null != obj[j]) {
							if (StringUtils.isNotBlank(obj[j].toString())) {
								if (obj[j].toString().length() == 171) {// 长度不等于171则未加密过
									m = p.matcher(obj[j].toString());
									if (m.find()) {// 没有汉字责加密过
										cloumArray1[j] = genKeyPairforcolumn(tablename, colmnamelist.get(j),
												obj[length - 1].toString(), obj[j].toString(), now, null);
										cloumArray2[j] = "1";
										falg = true;
									} else {
										cloumArray1[j] = obj[j].toString();
										cloumArray2[j] = "1";
										falg = true;
									}
								} else {
									cloumArray1[j] = genKeyPairforcolumn(tablename, colmnamelist.get(j),
											obj[length - 1].toString(), obj[j].toString(), now, null);
									cloumArray2[j] = "1";
									falg = true;
								}

							} else {
								// 如果数据为空则不加密
								cloumArray1[j] = "";
								cloumArray2[j] = "0";
								falg = true;
							}

						} else {
							// 如果数据为空则不加密
							cloumArray1[j] = "";
							cloumArray2[j] = "0";
							falg = true;
						}
					}
				}
			}
			if (falg) {
				updatesql.append(tablename + " set  ");
				for (int k = 0; k < colmnamelist.size(); k++) {
					cloumArray2[k] = StringUtils.isBlank(cloumArray2[k]) ? "" : cloumArray2[k];
					if (colmnamelist.size() == 1) {
						updatesql.append(colmnamelist.get(k) + " = '" + cloumArray1[k] + "'," + colmnamelist.get(k)
								+ "_en = '" + cloumArray2[k] + "'");
					} else {
						if (k < colmnamelist.size() - 1) {
							updatesql.append(colmnamelist.get(k) + " = '" + cloumArray1[k] + "'," + colmnamelist.get(k)
									+ "_en = '" + cloumArray2[k] + "',");
						} else {
							updatesql.append(colmnamelist.get(k) + " = '" + cloumArray1[k] + "'," + colmnamelist.get(k)
									+ "_en = '" + cloumArray2[k] + "'");
						}
					}

				}
				updatesql.append(" where  " + p_key + "= '" + p_key_value + "'");// 更新信息
				queryService.executeSql(updatesql.toString());
			}
		}

	}

	// 备份数据
	private void backup(String tablename, String p_key_value) {
		String p_key = encryptedinfoService.findbytablename2(tablename);
		StringBuffer sql1 = new StringBuffer("select column_name from user_tab_columns  where table_name='");
		StringBuffer sql2 = new StringBuffer("select column_name from user_tab_columns  where table_name='");
		StringBuffer sql3 = new StringBuffer(
				"select *  from bu_" + tablename + "   where " + p_key + "='" + p_key_value + "'");
		StringBuffer sql4 = new StringBuffer("select ");
		StringBuffer sql5 = new StringBuffer("");
		sql1.append("BU_" + tablename.toUpperCase() + "'");
		sql2.append(tablename.toUpperCase() + "'");
		List<Object> list1 = queryService.queryObjectBySql(sql1.toString());// 获取该表的所有字段，备份
		List<Object> list2 = queryService.queryObjectBySql(sql2.toString());// 获取该表的所有字段，加密
		List<Object> listobj3 = queryService.queryObjectBySql(sql3.toString());// 查询备份表中是是否有该字段，有就更新，没有则插入
		List<String> listobj1 = new ArrayList<String>();
		List<String> listobj2 = new ArrayList<String>();
		for (Object obj : list1) {
			listobj1.add(obj.toString());
		}
		for (Object obj : list2) {
			listobj2.add(obj.toString());
		}

		List<String> list = new ArrayList<String>();
		String cloum = "";
		if (null != listobj1 && null != listobj2) {
			if (listobj1.size() > 0 && listobj2.size() > 0) {
				int a = 0;
				for (int i = 0; i < listobj2.size(); i++) {
					if (listobj2.contains(listobj1.get(i))) {
						list.add(listobj1.get(i).toString());
					}
				}
				if (list.size() > 0) {
					for (int j = 0; j < list.size(); j++) {
						if (list.size() == 1) {
							cloum = list.get(j);
						} else {
							if (j < list.size() - 1) {
								cloum = cloum + list.get(j) + ",";
							} else {
								cloum = cloum + list.get(j);
							}
						}
					}
					sql4.append(cloum + " from " + tablename + "   where " + p_key + "='" + p_key_value + "'");
					List<Object[]> listobj4 = queryService.queryObjectsBySql(sql4.toString());// 他们共同的字段的值
					if (null != listobj4) {
						if (null != listobj3 && listobj3.size() > 0) {// listobj3不为空，更新
							sql5.append("update bu_" + tablename + "set ");
							for (int k = 0; k < list.size(); k++) {
								if (null != listobj4.get(k)) {
									if (list.size() == 1) {
										sql5.append(list.get(k) + " = '" + listobj4.get(k) + "'");
									} else {
										if (k < list.size() - 1) {
											sql5.append(list.get(k) + " = '" + listobj4.get(k) + "',");
										} else {
											sql5.append(list.get(k) + " = '" + listobj4.get(k) + "'");
										}
									}
								}
							}
							sql5.append(" where " + p_key + "='" + p_key_value + "'");
							queryService.executeSql(sql5.toString());
						} else {// 插入
							sql5.append("insert into bu_" + tablename + " ( " + cloum + " ) select " + cloum + " from "
									+ tablename + " where " + p_key + "='" + p_key_value + "'");
							queryService.executeSql(sql5.toString());
						}
					}

				}
			}
		}

	}

	@Override
	public Object encryptforbean2(Object obj) {
		if (null != obj) {
			// 备份到备份的表中
			saveTotablebf(obj);
			Calendar c = Calendar.getInstance();
			Date now = Calendar.getInstance().getTime();
			Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
			Class clazz = obj.getClass();// 获取对象的类型
			String[] bits = clazz.getName().split("\\.");
			String beanName = bits[bits.length - 1].toLowerCase();// 获取bean名称
			List<String> colmnamelist = encryptedinfoService.findbybeanName1(beanName);
			String tableName = encryptedinfoService.findbybeanName2(beanName);
			String p_key = encryptedinfoService.findbybeanName3(beanName);
			// 获取主键的值
			String p_key_value = getFieldValueByFieldName(p_key, obj);
			// 获取字段的值
			String colname_value = "";
			// 获取字段的加密情况1为加密，其他为未加密
			String colname_en = "";
			// 私钥
			String privateKey = "";
			// 加密之后的值
			String encrypt_value = "";
			Matcher m;
			if (null != colmnamelist && StringUtils.isNotBlank(tableName) && StringUtils.isNotBlank(p_key_value)) {
				if (colmnamelist.size() > 0) {
					for (String colname : colmnamelist) {
						colname_value = getFieldValueByFieldName(colname, obj);
						if (StringUtils.isNotBlank(colname_value)) {
							if (null != colname_value && colname_value.length() != 171) {// 如果长度为171则可能是加密过，如果不是171则未加密；
								// 加密处理
								encrypt_value = genKeyPairforcolumn(tableName, colname, p_key_value, colname_value, now,
										null);
								setFieldValueByFieldName(colname, obj, encrypt_value);
								setFieldValueByFieldName(colname + "_en", obj, "1");
							}
						} else {
							setFieldValueByFieldName(colname + "_en", obj, "0");
						}
					}
				}
			}
		}

		return obj;
	}

	public static void setProperty(Object obj, String propertyName, Object value) {
		Class clazz = obj.getClass();// 获取对象的类型
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(propertyName, clazz);
			Method setMethod = pd.getWriteMethod();// 从属性描述器中获取 set 方法
			try {
				setMethod.invoke(obj, value);
			} catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException Occured");
			} catch (IllegalArgumentException e) {
				System.err.println("IllegalArgumentException Occured");
			} catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException Occured");
			} // invoke是执行set
		} catch (IntrospectionException e) {
			System.err.println("IntrospectionException Occured");
		}
	}

	public static Object getProperty(Object obj, String propertyName) {
		Class clazz = obj.getClass();// 获取对象的类型
		PropertyDescriptor pd;
		Object val = "";
		try {
			pd = new PropertyDescriptor(propertyName, clazz);
			Method getMethod = pd.getReadMethod();// 从属性描述器中获取 get 方法
			try {
				val = getMethod.invoke(obj);
			} catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException Occured");
			} catch (IllegalArgumentException e) {
				System.err.println("IllegalArgumentException Occured");
			} catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException Occured");
			}
		} catch (IntrospectionException e) {
			System.err.println("IntrospectionException Occured");
		} // 获取 clazz 类型中的 propertyName 的属性描述器

		return val;// 返回值
	}

	/**
	 * 根据属性名获取属性值
	 *
	 * @param fieldName
	 * @param object
	 * @return
	 */
	private String getFieldValueByFieldName(String fieldName, Object object) {
		Field field = null;
		try {
			field = object.getClass().getDeclaredField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			log.error(e.getMessage());
		}
		// 设置对象的访问权限，保证对private的属性的访问
		if(null!=field) {
			field.setAccessible(true);
		}
		try {
			if(null!=field&&null!=object) {
				return (String) field.get(object);
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 根据属性名设置属性值
	 *
	 * @param fieldName
	 * @param object
	 * @return
	 */
	private void setFieldValueByFieldName(String fieldName, Object object, String value) {
//		try {
		// 获取obj类的字节文件对象
		Class c = object.getClass();
		// 获取该类的成员变量
		Field f = null;
		try {
			f = c.getDeclaredField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			log.error(e.getMessage());
		}
		// 取消语言访问检查
		if(null!=f) {
			f.setAccessible(true);
		}
		
		// 给变量赋值
		try {
			if(null!=f) {
				f.set(object, value);
			}
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.error(e.getMessage());
		}
	}

//		@Override
//		public void encryptedAll() {
//			List<Qydjxx> list;
//			List<String> tablenamelist=encryptedinfoService.findTableName();
//			for(String tablename:tablenamelist) {
//				if("qydjxx".equals(tablename)) {
//					list= qydjxxService.findAll();
//					if(null!=list) {
//						for(Qydjxx qydjxx:list) {
//							
//						}
//					}
//				}
//			}
//			
//		}

//		@Override
//		public void encryptforQydjxx(Qydjxx qydjxx) {
//			Calendar c = Calendar.getInstance();
//			Date now=Calendar.getInstance().getTime();
//			if(null!=qydjxx) {
//				if(StringUtils.isNotBlank(qydjxx.getLinkman_mobile())) {
//					qydjxx.setLinkman_mobile(genKeyPairforcolumn( "qydjxx","linkman_mobile" ,qydjxx.getCorp_id() , 
//							qydjxx.getLinkman_mobile(), now,null));
//				}
//				if(StringUtils.isNotBlank(qydjxx.getLinkman_mobile())) {
//					qydjxx.setLinkman_mobile(genKeyPairforcolumn( "qydjxx","linkman_mobile" ,qydjxx.getCorp_id() , 
//							qydjxx.getLinkman_mobile(), now,null));
//				}
//				if(StringUtils.isNotBlank(qydjxx.getLinkman_mobile())) {
//					qydjxx.setLinkman_mobile(genKeyPairforcolumn( "qydjxx","linkman_mobile" ,qydjxx.getCorp_id() , 
//							qydjxx.getLinkman_mobile(), now,null));
//				}
//				qydjxxrepository.save(qydjxx);
//			}
//		}

	@Override
	@Transactional
	public void encryptforcolmname(String tablename, String p_key_value, String colname, String decrypt_value) {
		Date now = Calendar.getInstance().getTime();
		String p_key = encryptedinfoService.findbytablename2(tablename);
		String encrypt_value = genKeyPairforcolumn(tablename, colname, p_key_value, p_key_value, now, null);
		StringBuffer updatesql = new StringBuffer("update " + tablename + " set " + colname + " = '" + encrypt_value
				+ "'  where " + p_key + " = '" + p_key_value + "'");
		queryService.executeSql(updatesql.toString());

	}

	@Override
	public String findbyColnameAndtablename(String tableName, String colname, String p_key_value) {
		return repository.findbyColnameAndtablename(tableName, colname, p_key_value);
	}

	private void saveTotablebf(Object obj) {
		Calendar c = Calendar.getInstance();
		Class clazz = obj.getClass();// 获取对象的类型
		String[] bits = clazz.getName().split("\\.");
		String beanName = bits[bits.length - 1].toLowerCase();// 获取bean名称
		if (StringUtils.isNotBlank(beanName)) {
			String tableName = encryptedinfoService.findbybeanName2(beanName);
			String p_key = encryptedinfoService.findbybeanName3(beanName);
			String p_key_value = (String) getProperty(obj, p_key);
			if (StringUtils.isNotBlank(tableName) && StringUtils.isNotBlank(p_key_value)) {

			}
		}

	}
}
