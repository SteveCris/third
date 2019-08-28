package com.shangyong.thryt;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import com.shangyong.loan.autoconfigure.util.LocalDateUtil;
import com.shangyong.thryt.utils.IdUtil;
import com.shangyong.thryt.utils.JacksonUtil;
import com.shangyong.thryt.utils.OSSFileUtil;

public class TestInsert {

	private static String hStoreSql = "INSERT INTO `bodyStore`.`h_store`(`guid`, `name`, `descr`, `create_time`) VALUES ('%s', '%s', '%s', '%s');";

	private static String hGoodsSql = "INSERT INTO `bodyStore`.`h_goods`(`guid`, `store_guid`, `pic_url`, `price`, `name`, `english_name`, `descr`, `star_level`, `create_time`, `big_pic_url`, `service_type`, `fashion_type`, `ext1`, `ext2`) VALUES ('%s', '%s', '%s', %s, '%s', '%s','%s', %s, '%s', '%s', '%s', '%s', NULL, NULL);";

	private static String hGoodsCommonSql = "INSERT INTO `bodyStore`.`h_goods_common_details`(`guid`, `goods_guid`, `ext1`, `ext2`, `ext3`, `ext4`, `ext5`, `ext6`, `ext7`, `ext8`, `ext9`, `ext10`, `ext1_url`, `ext2_url`, `ext3_url`, `create_time`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');";
	
	private static String HGoodsClothesSql="INSERT INTO `bodyStore`.`h_goods_clothes_details`(`guid`, `goods_guid`, `color_type`, `colors_type`, `size`, `texture`, `style`, `brand`, `place_origin`, `apply_group`, `text_pic_url`, `ext1`, `ext2`, `create_time`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', NULL, NULL, '%s');";

	private static String hGoodsTagsSql = "INSERT INTO `bodyStore`.`h_goods_tags_mapping`(`guid`, `goods_guid`, `tags_guid`, `create_time`) VALUES ('%s', '%s', '%s', '%s');";

	private static String[] ext1s = new String[] { "白色", "黑色", "红色" };
	private static String[] ext3s = new String[] { "金属", "塑料", "玻璃" };
	private static String[] ext4s = new String[] { "时尚", "潮流", "最新" };
	private static String[] ext5s = new String[] { "**", "**" };
	private static String[] ext6s = new String[] { "中国大陆","海外" };
	private static String[] ext7s = new String[] { "青年", "中年", "全部" };

	private static String[] tags = new String[] { "HOME", "HOT" };

	public static void main(String[] args) throws UnsupportedEncodingException {
		// 创建商店
		HStore hstore = buildHStore();
		List<HGoods> list = buildHGoods(hstore);
		buildGoodsTags(list);
	}

	private static void buildGoodsTags(List<HGoods> list) {
		for (int i = 0; i < tags.length; i++) {
			// 打home标签
			for (HGoods hGoods : list) {
				if (RandomUtils.nextInt(0, 10) < 8) {
					HGoodsTags hGoodsTags = new HGoodsTags();
					hGoodsTags.setCreateTime(new Date());
					hGoodsTags.setGoodsGuid(hGoods.getGuid());
					hGoodsTags.setGuid(IdUtil.getNumberUuid("M"));
					hGoodsTags.setTagsGuid(hGoods.getStoreGuid() + "_" + tags[i]);
					System.out.println(String.format(hGoodsTagsSql, hGoodsTags.getGuid(), hGoodsTags.getGoodsGuid(),
							hGoodsTags.getTagsGuid(),
							LocalDateUtil.dateToString(hGoodsTags.getCreateTime(), "yyyy-MM-dd HH:mm:ss")));
				}
			}
		}

	}

	private static List<HGoods> buildHGoods(HStore hstore) throws UnsupportedEncodingException {

		File file = new File("C:\\Users\\Administrator\\Desktop\\电子产品");

		List<HGoods> list = new LinkedList<>();

		if (!file.exists()) {
			return null;
		}
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				// 上传文件
				uploadFile(files[i]);

				HGoods hgoods = new HGoods();
				hgoods.setCreateTime(new Date());
				hgoods.setFashionType("正常");

				HGoodsCommonDetails hGoodsCommonDetails = new HGoodsCommonDetails();
				hGoodsCommonDetails.setCreateTime(new Date());
				hGoodsCommonDetails.setExt1(ext1s[RandomUtils.nextInt(0, ext1s.length)]);
				hGoodsCommonDetails.setExt2(StringUtils.join(ext1s, "/"));
				hGoodsCommonDetails.setExt3(ext3s[RandomUtils.nextInt(0, ext3s.length)]);
				hGoodsCommonDetails.setExt4(ext4s[RandomUtils.nextInt(0, ext4s.length)]);
				hGoodsCommonDetails.setExt5(ext5s[RandomUtils.nextInt(0, ext5s.length)]);
				hGoodsCommonDetails.setExt6(ext6s[RandomUtils.nextInt(0, ext6s.length)]);
				hGoodsCommonDetails.setExt7(ext7s[RandomUtils.nextInt(0, ext7s.length)]);
				hGoodsCommonDetails.setExt8("");
				hGoodsCommonDetails.setExt9("");
				hGoodsCommonDetails.setExt10("");

				// 一条记录 一个商品
				String str = files[i].getName();
				int split1 = str.indexOf("-");
				int split2 = str.lastIndexOf("￥");
				String code = str.substring(0, split1);
				String name = str.substring(split1 + 1, split2);
				String price = str.substring(split2 + 1);

				hgoods.setGuid(hstore.getGuid() + "-" + code);
				hgoods.setName(name);
				hgoods.setServiceType("clothes");
				hgoods.setStarLevel(RandomUtils.nextInt(1, 5));
				hgoods.setStoreGuid(hstore.getGuid());
				hgoods.setPrice(new BigDecimal(price));
				hgoods.setEnglishName("");
				hgoods.setDescr("");

				if (files[i].isDirectory()) {
					File[] subFiles = files[i].listFiles();
					for (int j = 0; j < subFiles.length; j++) {
						String url = URLDecoder.decode(subFiles[j].getName().substring(6), "UTF-8");
						if (subFiles[j].getName().startsWith("1.")) {
							hgoods.setPicUrl(url);
						} else if (subFiles[j].getName().startsWith("2.")) {
							hgoods.setBigPicUrl(url);
							hGoodsCommonDetails.setExt1Url(url);
						}
					}
				}

				
				System.out.println(String.format(hGoodsSql, hgoods.getGuid(), hgoods.getStoreGuid(), hgoods.getPicUrl(),
						hgoods.getPrice(), hgoods.getName(), hgoods.getEnglishName(), hgoods.getDescr(),
						hgoods.getStarLevel(),
						LocalDateUtil.dateToString(hgoods.getCreateTime(), "yyyy-MM-dd HH:mm:ss"),
						hgoods.getBigPicUrl(), hgoods.getServiceType(), hgoods.getFashionType()));

				// `guid`, `goods_guid`,
				// `ext1`, `ext2`, `ext3`, `ext4`, `ext5`, `ext6`, `ext7`, `ext8`, `ext9`,
				// `ext10`,
				// `ext1_url`, `ext2_url`, `ext3_url`, `create_time`
//				System.out.println(String.format(hGoodsCommonSql, hgoods.getGuid() + "-D", hgoods.getGuid(),
//						hGoodsCommonDetails.getExt1(), hGoodsCommonDetails.getExt2(), hGoodsCommonDetails.getExt3(),
//						hGoodsCommonDetails.getExt4(), hGoodsCommonDetails.getExt5(), hGoodsCommonDetails.getExt6(),
//						hGoodsCommonDetails.getExt7(), hGoodsCommonDetails.getExt8(), hGoodsCommonDetails.getExt9(),
//						hGoodsCommonDetails.getExt10(), hGoodsCommonDetails.getExt1Url(),
//						hGoodsCommonDetails.getExt2Url(), hGoodsCommonDetails.getExt3Url(),
//						LocalDateUtil.dateToString(hGoodsCommonDetails.getCreateTime(), "yyyy-MM-dd HH:mm:ss")));

				
//				`guid`, `goods_guid`, `color_type`, `colors_type`, `size`, `texture`, `style`,
				// 	`brand`, `place_origin`, `apply_group`, `text_pic_url`, `ext1`, `ext2`, `create_time`) 
				//VALUES ('NO1-01-D', 'NO1-01', '黄色', '黄色/黑色/白色', 'L', '纯棉', '休闲类', '**', '浙江温州', '青少年', 'https://sy-test-oss.oss-cn-shanghai.aliyuncs.com/productPng/tbGoodsD/201965/f79a93be4a6a4effb716073a1ef454bc.jpg', NULL, NULL, '2019-05-15 17:43:37'
					
				
				System.out.println(String.format(HGoodsClothesSql, hgoods.getGuid() + "-D", hgoods.getGuid(),
						hGoodsCommonDetails.getExt1(), hGoodsCommonDetails.getExt2(),"", hGoodsCommonDetails.getExt3(),
						hGoodsCommonDetails.getExt4(), hGoodsCommonDetails.getExt5(), hGoodsCommonDetails.getExt6(),
						hGoodsCommonDetails.getExt7(), hGoodsCommonDetails.getExt1Url(),
						LocalDateUtil.dateToString(hGoodsCommonDetails.getCreateTime(), "yyyy-MM-dd HH:mm:ss")));
				
				list.add(hgoods);
			}
		}

		return list;
	}

	private static HStore buildHStore() {
		HStore hStroe = new HStore();
		hStroe.setGuid("DZCP_NO3");
		hStroe.setName("电子商店");
		hStroe.setCreateTime(new Date());
		hStroe.setDescr("电子产品描述");
		JacksonUtil.parseToMap(hStroe);
		System.out.println(String.format(hStoreSql, hStroe.getGuid(), hStroe.getName(), hStroe.getDescr(),
				LocalDateUtil.dateToString(hStroe.getCreateTime(), "yyyy-MM-dd HH:mm:ss")));
		return hStroe;
	}

	private static void uploadFile(File file) throws UnsupportedEncodingException {

		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int j = 0; j < files.length; j++) {

				if (files[j].getName().indexOf("http") != -1) {
					continue;
				}
				String url = OSSFileUtil.uploadByFile("STORE", "NO3/common", files[j].getPath());
				String filePath = files[j].getParent() + "/" + files[j].getName() + "-"
						+ URLEncoder.encode(url, "utf-8");
				files[j].renameTo(new File(filePath));
			}
		}

	}

}
