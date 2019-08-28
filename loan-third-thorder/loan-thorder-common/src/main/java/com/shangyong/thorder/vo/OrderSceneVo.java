package com.shangyong.thorder.vo;

import java.io.Serializable;
import java.util.List;

import com.shangyong.center.vo.CenterSceneInsuranceVo;
import com.shangyong.center.vo.CenterSceneVo;

public class OrderSceneVo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5678041114439523653L;

	private CenterSceneVo centerSceneVo;
	
	private List<CenterSceneInsuranceVo> centerSceneInsuranceVoList;

	public CenterSceneVo getCenterSceneVo() {
		return centerSceneVo;
	}

	public void setCenterSceneVo(CenterSceneVo centerSceneVo) {
		this.centerSceneVo = centerSceneVo;
	}

	public List<CenterSceneInsuranceVo> getCenterSceneInsuranceVoList() {
		return centerSceneInsuranceVoList;
	}

	public void setCenterSceneInsuranceVoList(List<CenterSceneInsuranceVo> centerSceneInsuranceVoList) {
		this.centerSceneInsuranceVoList = centerSceneInsuranceVoList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderSceneVo [centerSceneVo=");
		builder.append(centerSceneVo);
		builder.append(", centerSceneInsuranceVoList=");
		builder.append(centerSceneInsuranceVoList);
		builder.append("]");
		return builder.toString();
	}
	
	
}
