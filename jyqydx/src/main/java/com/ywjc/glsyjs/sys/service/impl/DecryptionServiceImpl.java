package com.ywjc.glsyjs.sys.service.impl;

import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.repository.UserbfRepository;
import com.ywjc.glsyjs.core.service.QueryService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.core.util.RSAUtils;
import com.ywjc.glsyjs.sys.repositories.DecryptionRepository;
import com.ywjc.glsyjs.sys.service.DecryptionService;
import com.ywjc.glsyjs.sys.service.EncryptedinfoService;
import com.ywjc.glsyjs.sys.service.EncryptionService;
import com.ywjc.glsyjs.sys.entity.Decryption;
import com.ywjc.glsyjs.sys.entity.Encryption;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;


@Service("decryptionServiceImpl")
public class DecryptionServiceImpl extends BaseServiceImpl<Decryption, String>
        implements DecryptionService {
    private static Logger log = Logger.getLogger(DecryptionServiceImpl.class);
    @Resource(name = "decryptionRepository")
    private DecryptionRepository repository;

    @Resource(name = "encryptedinfoServiceImpl")
    private EncryptedinfoService encryptedinfoService;
    @Resource(name = "encryptionServiceImpl")
    private EncryptionService encryptionService;

    @Resource(name = "decryptionServiceImpl")
    private DecryptionService decryptionService;

    @PersistenceContext
    private EntityManager entityManger;

    @Resource(name = "queryServiceImpl")
    protected QueryService queryService;



    @Resource(name = "userbfRepository")
    private UserbfRepository userbfrepository;




    @Resource(name = "decryptionRepository")
    public void setReposity(
            PagingAndSortingRepository<Decryption, String> repository) {
        super.setReposity(repository);
    }

    @Override
    public Decryption findbyrecordid(String tablename, String colmname, String recordid) {
        return repository.findbyrecordid(tablename, colmname, recordid);
    }

    @Override
    public String decrypt(String str, String privateKey) {
        String decodedData = "";
        try {
            decodedData = RSAUtils.privateDecrypt(str, RSAUtils.getPrivateKey(privateKey));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("NoSuchAlgorithmException Occured");
        } catch (InvalidKeySpecException e) {
            System.err.println("InvalidKeySpecException Occured");
        } //传入密文和私钥,得到明文
        return decodedData;
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

    @Override
    public List<Object> decryptforPage(Page page) {
        List<Object> newlist = new ArrayList<Object>();
        if (null != page) {
            if (null != page.getContent()) {
                List<Object> list = (List<Object>) page.getContent();
                if (null != list && list.size() > 0) {
                    Class clazz = list.get(0).getClass();
                    String[] bits = clazz.getName().split("\\.");
                    String beanName = bits[bits.length - 1].toLowerCase();// 获取bean名称

                }

            }
        }
        return newlist;
    }

    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param object
     * @return
     */
    private String getFieldValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            return (String) field.get(object);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    private String addDecryptionAndEncryption(String tableName, String colname, String p_key_value) {
        Date now = Calendar.getInstance().getTime();
        String tableName2 = "";
        if ("qydjxxweb".equals(tableName)) {
            tableName2 = "qydjxx";
        } else if ("cwzhweb".equals(tableName)) {
            tableName2 = "cwzh";
        } else if ("grxxweb".equals(tableName)) {
            tableName2 = "grxx";
        } else if ("xmbdryxxweb".equals(tableName)) {
            tableName2 = "xmbdryxx";
        }
        String privateKey = "";
        String publicKey = "";
        if (StringUtils.isNotBlank(tableName2)) {
            privateKey = repository.findbyColnameAndtablename(tableName2, colname, p_key_value);// 获取私钥
            publicKey = encryptionService.findbyColnameAndtablename(tableName2, colname, p_key_value);// 获取私钥
            Encryption encryption = new Encryption();
            Decryption decryptio = new Decryption();
            encryption.setEncryptid(KeyUtils.getKey());
            encryption.setCdate(now);
            //公钥
            encryption.setEncryptcode(publicKey);
            encryption.setEntablename(tableName);
            encryption.setRecordid(p_key_value);
            encryption.setColmname(colname);
            encryptionService.save(encryption);
            decryptio.setEncryptid(KeyUtils.getKey());
            decryptio.setCdate(new Date());
            //私钥
            decryptio.setDecryptcode(privateKey);
            decryptio.setColmname(colname);
            decryptio.setEntablename(tableName);
            decryptio.setRecordid(p_key_value);
            decryptionService.save(decryptio);
        }
        return privateKey;

    }


    @Override
    public User decryptforUser(User obj) {
        if (null != obj) {
            // 获得hibernate的session管理对象
            HibernateEntityManager hEntityManager = (HibernateEntityManager) entityManager;
            Session session = hEntityManager.getSession();
            // 将该对象从持久态变为游离态
            session.evict(obj);
            String beanName = "user";
            List<String> colmnamelist = encryptedinfoService.findbybeanName1(beanName);
            String tableName = encryptedinfoService.findbybeanName2(beanName);
            // 获取主键的值
            String p_key_value = obj.getId();
            // 获取字段的值
            String colname_value = "";
            // 获取字段的加密情况1为加密，其他为未加密
            String colname_en = "";
            // 私钥
            String privateKey = "";
            // 解密之后的值
            String decrypt_value = "";
            if (null != colmnamelist && StringUtils.isNotBlank(tableName) && StringUtils.isNotBlank(p_key_value)) {
                if (colmnamelist.size() > 0) {
                    for (String colname : colmnamelist) {
                        colname_value = getFieldValueByFieldName(colname, obj);
                        if (StringUtils.isNotBlank(colname_value)) {
                            colname_en = getFieldValueByFieldName(colname + "_en", obj);
                            if (StringUtils.isNotBlank(colname_en)) {
                                if ("1".equals(colname_en) && colname_value.length() == 171) {// 状态未1，表示加密，加密才能解密
                                    privateKey = repository.findbyColnameAndtablename(tableName, colname, p_key_value);// 获取私钥
                                    if (StringUtils.isNotBlank(privateKey)) {
                                        try {
                                            decrypt_value = decrypt(colname_value, privateKey);
                                        } catch (Exception e) {
                                            decrypt_value = colname_value;
                                        }
                                        decrypt_value = StringUtils.isBlank(decrypt_value) ? "" : decrypt_value;
                                        // 赋值，bean,字段，解密之后的值
                                        if ("linknum".equals(colname)) {
                                            obj.setLinknum(decrypt_value);
                                            continue;
                                        } else if ("linktel".equals(colname)) {
                                            obj.setLinktel(decrypt_value);
                                            continue;
                                        } else if ("linkemail".equals(colname)) {
                                            obj.setLinkemail(decrypt_value);
                                            continue;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            hEntityManager.close();
        }
        return obj;
    }






    @Override
    public Page<Object> decryptforPage2(Page page, Pageable pageable) {
        List<Object> newlist = new ArrayList<Object>();
        List<Object> list = (List<Object>) page.getContent();
        if (null != page) {
            if (null != page.getContent()) {

                if (null != list && list.size() > 0) {
                    Class clazz = list.get(0).getClass();
                    String[] bits = clazz.getName().split("\\.");
                    String beanName = bits[bits.length - 1].toLowerCase();// 获取bean名称
                    if ("user".equals(beanName)) {
                        for (Object obj : list) {
                            obj = decryptionService.decryptforUser((User) obj);
                            newlist.add(obj);
                        }
                    }
                }

            }
        }
        return new PageImpl<Object>(list, pageable, page.getTotalElements());
    }

    @Override
    public void Decrypted() {
        //得到所有表名字
        List<String> sqllist = new ArrayList<>();
        List<Object[]> list = encryptedinfoService.findTable();
        List<String> colmnamelist = null;
        List<String> cloumvalue = new ArrayList<>();//解密后需要存放的数据
        List<String> cloumlist = new ArrayList<>();//字段是否加密1加密，0没有加密
        StringBuffer sql = new StringBuffer("select ");
        StringBuffer updatesql1 = new StringBuffer("update from ");
        StringBuffer updatesql2 = new StringBuffer("");
        StringBuffer updatesql4 = new StringBuffer("");
        int length;
        int num = 1;
        String zhujian;
        String ziduan;//需要加密的字段
        String ziduan_encryption;//需要加密的字段的加密情况，1加密，其他情况未加密
        Calendar c = Calendar.getInstance();
        Date now = Calendar.getInstance().getTime();
        boolean falg = false;
        String tablename = "";
        if (null != list && list.size() > 0) {
            for (Object[] tableAndp_key : list) {
                num = 1;
                if (StringUtils.isNotBlank(tableAndp_key[0].toString()) && StringUtils.isNotBlank(tableAndp_key[1].toString())) {
                    tablename = StringUtils.isBlank(tableAndp_key[0].toString()) ? "" : tableAndp_key[0].toString();
                    //表中的主键
                    zhujian = StringUtils.isBlank(tableAndp_key[1].toString()) ? "" : tableAndp_key[1].toString();
                    sql.setLength(0);
                    updatesql1.setLength(0);
                    updatesql2.setLength(0);
                    updatesql4.setLength(0);
                    sql.append("select ");
                    updatesql1.append("update ");
                    //表中需要加密的字段,isencrypted为1的时候，表示这个字段已经加密，可以解密
                    colmnamelist = encryptedinfoService.findbytablename4(tablename);
                    //加密字段
                    ziduan = "";
                    //字段加密情况
                    ziduan_encryption = "";
                    String privateKey = "";//私钥
                    String decrypt_value = "";//解密后的值

                    if (null != colmnamelist && colmnamelist.size() > 0) {
                        for (int i = 0; i < colmnamelist.size(); i++) {
                            ziduan = ziduan + colmnamelist.get(i) + ",";
                            ziduan_encryption = ziduan_encryption + colmnamelist.get(i) + "_en,";
                        }
                        sql.append(ziduan);
                        sql.append(ziduan_encryption);
                        sql.append(zhujian);
                        if (StringUtils.isNotBlank(zhujian)) {
                            sql.append(" from " + tablename);
                            updatesql1.append(tablename + "  set ");
                            //查询出表中上有需要加密的数据，且字段为需要加密的字段
                            List<Object[]> listobj = queryService.queryObjectsBySql(sql.toString());
                            if (null != listobj && listobj.size() > 0) {
                                length = listobj.get(0).length;
                                for (Object[] obj : listobj) {
                                    for (int j = 0; j < colmnamelist.size(); j++) {
                                        updatesql2.append(updatesql1);
                                        //加密处理
                                        if (null != obj[j]) {
                                            if (null != obj[j + colmnamelist.size()]) {
                                                //如果加密状态为1则解密
                                                if (StringUtils.isNotBlank(obj[j + colmnamelist.size()].toString())) {
                                                    if ("1".equals(obj[j + colmnamelist.size()].toString()) && obj[j].toString().length() == 171) {
                                                        privateKey = repository.findbyColnameAndtablename(tablename, colmnamelist.get(j), obj[length - 1].toString());// 获取私钥
                                                        if (StringUtils.isNotBlank(privateKey) && obj[j] != null && !"".equals(obj[j].toString())) {
                                                            try {
                                                                decrypt_value = decrypt(obj[j].toString(), privateKey);
                                                            } catch (Exception e) {
                                                                decrypt_value = obj[j].toString();
                                                            }
                                                            cloumvalue.add(decrypt_value);//加密值，私钥
                                                            cloumlist.add(colmnamelist.get(j));
                                                            falg = true;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (falg) {
                                            for (int k = 0; k < cloumvalue.size(); k++) {
                                                if (cloumvalue.size() == 1) {
                                                    updatesql2.append(cloumlist.get(k) + " = '" + cloumvalue.get(k) + "'," + cloumlist.get(k) + "_en = '0' ");
                                                } else {
                                                    if (k < cloumvalue.size() - 1) {
                                                        updatesql2.append(cloumlist.get(k) + " = '" + cloumvalue.get(k) + "'," + cloumlist.get(k) + "_en = '0', ");
                                                    } else {
                                                        updatesql2.append(cloumlist.get(k) + " = '" + cloumvalue.get(k) + "'," + cloumlist.get(k) + "_en = '0' ");
                                                    }
                                                }

                                            }
                                            updatesql2.append(" where  " + zhujian + "= '" + obj[length - 1].toString() + "'");
//										System.out.println(tablename+"--"+num+"---"+obj[length-1].toString() );
                                            sqllist.add(updatesql2.toString());
                                        }
                                        cloumlist.clear();
                                        cloumvalue.clear();
                                        updatesql2.setLength(0);
                                        num++;
                                    }
                                    queryService.executeBatchSql(sqllist);
                                    queryService.executeSql("update encryptedinfo set isencrypted='0' where  entablename='" + tablename + "'");


                                }
                            }

                        }
                    }
                }

            }

        }


    }

    private void saveTotablebf(Object obj) {
        Calendar c = Calendar.getInstance();
        Class clazz = obj.getClass();//获取对象的类型
        String[] bits = clazz.getName().split("\\.");
        String beanName = bits[bits.length - 1].toLowerCase();//获取bean名称
        if (StringUtils.isNotBlank(beanName)) {
            String tableName = encryptedinfoService.findbybeanName2(beanName);
            String p_key = encryptedinfoService.findbybeanName3(beanName);
            String p_key_value = (String) getProperty(obj, p_key);
            if (StringUtils.isNotBlank(tableName) && StringUtils.isNotBlank(p_key_value)) {

            }
        }
    }

}
	 

